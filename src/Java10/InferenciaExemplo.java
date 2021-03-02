package Java10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class InferenciaExemplo {
    public static void main(String[] args) throws IOException {
//        connectAndPrintURLJavaOracle();
        printarNomeCompleto("Robson", "Lira");
        print(10,20,30);
    }

    private static void connectAndPrintURLJavaOracle() throws IOException {
        var url = new URL("https://www.oracle.com/br/java/technologies/javase-jdk15-downloads.html");
        var urlConnection = url.openConnection();
        var bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        System.out.println(bufferedReader.lines().collect(Collectors.joining()).replaceAll(">",">\n"));
    }

    public static void printarNomeCompleto(String nome, String sobrenome){
        var nome_completo = String.format("%s %s", nome, sobrenome);
        System.out.println(nome_completo);
    }

    public static void print(int... numeros){

        var soma = 0 ;
        if(numeros.length> 0){
            for (int numero: numeros){
                soma+=numero;
            }
        }

        System.out.println("Soma é ::"+soma);

    }

}
