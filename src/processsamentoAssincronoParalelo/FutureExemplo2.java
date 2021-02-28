package processsamentoAssincronoParalelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureExemplo2 {
    private static final ExecutorService pessoasParaExecutarAtividade = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        Casa casa = new Casa(new Quarto());
        casa.obterAfazeresDaCasa().forEach( atividade -> pessoasParaExecutarAtividade.execute(() ->  {
            try {
                atividade.realizar();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }));



        pessoasParaExecutarAtividade.shutdown();
    }
}

class Casa{
    private final List<Comodo> comodos;

    Casa(Comodo... comodos)
    {
        this.comodos = Arrays.asList(comodos);
    }

    List<Atividade> obterAfazeresDaCasa(){
        return this.comodos.stream().map(Comodo::obterAfazeresDaCasa)
                .reduce(new ArrayList<Atividade>(),(pivo, atividades)->{
                    pivo.addAll(atividades);
                    return pivo;
                });
    }
}

interface Atividade{
    void realizar() throws InterruptedException;
}

abstract class Comodo{
    abstract List<Atividade> obterAfazeresDaCasa();
}

class Quarto extends Comodo{
    @Override
    List<Atividade> obterAfazeresDaCasa(){
        return Arrays.asList(
                this::arrumarGuardaRoupas,
                this::varrerOQuarto,
                this::arrumarACama
        );
    }

    private void arrumarGuardaRoupas () throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Arrumar o Guarda Roupa");
    }

    private void varrerOQuarto() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Varrer o Quarto");
    }

    private void arrumarACama() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Arrumar A Cama");
    }
}
