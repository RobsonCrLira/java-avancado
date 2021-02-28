package processsamentoAssincronoParalelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureExemplo {
    private static final ExecutorService pessoasParaExecutarAtividade = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        Casa casa = new Casa(new Quarto());
        casa.obterAfazeresDaCasa().forEach( atividade -> pessoasParaExecutarAtividade.execute(atividade::realizar));
        pessoasParaExecutarAtividade.shutdown();
    }
}

class Casa{
    private List<Comodo> comodos;

    Casa(Comodo... comodos)
    {
        this.comodos = Arrays.asList((comodos));
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
    void realizar();
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

    private void arrumarGuardaRoupas() {

        System.out.println("Arrumar o Guarda Roupa");
    }

    private void varrerOQuarto() {
        System.out.println("Varrer o Quarto");
    }

    private void arrumarACama() {
        System.out.println("Arrumar A Cama");
    }
}
