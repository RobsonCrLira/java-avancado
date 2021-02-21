package interfacesFuncionais;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Interacoes {
    public static void main(String[] args) {
        String[] nomes = {"João","Paulo","Oliveira", "Santos", "Instrutor", "Java"};
        Integer[] numeros = {1,2,3,4,5,6};
//        imprimeNomesFiltrados(nomes);
//        imprimeTodos(nomes);
        imprimeDobro(numeros);
    }

    public static void imprimeNomesFiltrados(String... nomes){
        String nomesParaImprimir= "";
        for (int i = 0; i < nomes.length; i++){
            if (nomes[i].equals("Paulo")){
                nomesParaImprimir+=""+nomes[i];
            }
        }

        String imprimePorStream  = Stream.of(nomes)
                .filter(nome -> nome.equals("Paulo"))
                .collect(Collectors.joining());

        System.out.println("Imprime For: "+nomesParaImprimir);
        System.out.println("Imprime Stream: "+imprimePorStream);
    }

    public static void imprimeTodos(String... nomes){
        for (String nome :nomes){
            System.out.println("Imprime por for: "+nome);
        }

        Stream.of(nomes)
                .forEach(nome ->System.out.println("Imprimido por ForEach: "+nome));
    }

    public static void imprimeDobro(Integer... numeros){
        for (Integer numero: numeros){
            System.out.println(numero*2);
        }

        Stream.of(numeros).map(numero -> numero*2)
                .forEach(System.out::println);
    }

}
