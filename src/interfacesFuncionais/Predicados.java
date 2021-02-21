package interfacesFuncionais;

import java.util.function.Predicate;

public class Predicados {
    public static void main(String[] args) {
//        Predicate<String> estaVazio = valor ->valor.isEmpty();
        Predicate<String> estaVazio = String::isEmpty; //Metodo de referencia
        System.out.println(estaVazio.test(""));
        System.out.println(estaVazio.test("Robson"));

    }
}
