package interfacesFuncionais;

import java.util.function.Supplier;

public class Suplidores {
    public static void main(String[] args) {
        Supplier<Pessoa> instanciaPessoa = () -> new Pessoa();
//        Supplier<Pessoa> instanciaPessoa = Pessoa::new;

        System.out.println(instanciaPessoa.get());
    }
}

class Pessoa{
    private final String nome;
    private final Integer idade;

    public Pessoa(){
        nome = "Robson";
        idade = 25;
    }


    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}
