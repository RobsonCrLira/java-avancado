package Java11;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CollectionExemplo {
    public static void main(String[] args) {
//        List<String> nomes = Arrays.asList("Robson", "Jão", "Maria","Milena");
//        Collection<String> nomes = Arrays.asList("Robson", "Jão", "Maria","Milena");
//        Collection<String> nomes = List.of("Robson", "Jão", "Maria","Milena");
        Collection<String> nomes = Set.of("Robson", "Jão", "Maria","Milena");

        System.out.println(nomes);
    }
}
