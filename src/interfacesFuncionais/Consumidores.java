package interfacesFuncionais;

import java.util.function.Consumer;

public class Consumidores {
    public static void main(String[] args) {
        Consumer<String> imprimeUmaFrase = System.out::println;
//        Consumer<String> imprimeUmaFrase = frase -> System.out.println(frase);

        imprimeUmaFrase.accept("Hello World");
    }
}
