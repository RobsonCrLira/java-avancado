package Java11;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ClientHttpExemplo {

    static ExecutorService executor = Executors.newFixedThreadPool(6,new ThreadFactory(){
        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            System.out.println("New Thread Create ::"+(thread.isDaemon() ? "deamon": "")+"Thread Group ::"+thread.getThreadGroup());
            return thread;
        }

    });

    public static void main(String[] args) throws Exception {
//        connectAndPrintURLJavaOracle();
        connectAkamaiHttpClient();
    }

    public static void connectAkamaiHttpClient() throws Exception{
        System.out.println("Running Http/1.1 exemple ...");
        try {
            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .proxy(ProxySelector.getDefault())
                    .build();

            long start = System.currentTimeMillis();
            HttpRequest mainRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://http2.akamai.com/demo/h2_demo_frame.html"))
                    .build();

            HttpResponse<String> response =  httpClient.send(mainRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status Code::"+response.statusCode());
            System.out.println("Headers response ::"+response.headers());
            String responseBody = response.body();
            System.out.println(responseBody);

            List<Future<?>> future = new ArrayList<>();

            responseBody
                    .lines()
                    .filter(line -> line.trim().startsWith("<img height"))
                    .map(line -> line.substring(line.indexOf("src=")+5, line.indexOf("'/>")))
                    .forEach(image -> {
                        Future<?> imgFuture = executor.submit(()->{
                            HttpRequest imgRequest = HttpRequest.newBuilder()
                                    .uri(URI.create("http://http2.akamai.com"+image))
                                    .build();
                            try {
                                HttpResponse<String> imageResponse = httpClient.send(imgRequest, HttpResponse.BodyHandlers.ofString());
                                System.out.println("Imagem Carregada ::"+image+", Status Code ::"+imageResponse.statusCode());
                            } catch (IOException | InterruptedException e) {
                                System.out.println("Erro Durante a requisição para recuperar a imagem ::"+ image +"\nErro::"+e);

                            }
                        });
                        future.add(imgFuture);
                        System.out.println("Submetido um futuro para imagem ::"+ image);
                    });

            future.forEach(f -> {
                try{
                    f.get();
                }catch (ExecutionException | InterruptedException e){
                    System.out.println("Erro ao esperar a carregar a imagem do furuto\nErro:: "+e);
                    e.printStackTrace();
                }
            });

            long end = System.currentTimeMillis();
            System.out.println("Tempo de Carregamento Total ::"+(end-start)+" ms");

        }finally{
            executor.shutdown();
        }

    }

    private static void connectAndPrintURLJavaOracle() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET().uri(URI.create("https://www.oracle.com/br/java/technologies/javase-jdk15-downloads.html"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code::"+response.statusCode());
        System.out.println("Headers response ::"+response.headers());
        System.out.println(response.body());

    }
}
