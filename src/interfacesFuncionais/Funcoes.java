package interfacesFuncionais;

import java.util.function.Function;

public class Funcoes {
    public static void main(String[] args) {


//        Function<String,String> retornaNomeAoContrario = texto -> new StringBuilder(texto).reverse().toString();
//        Function<String,Integer> converterStringParaInteiroECalcularODobro = string ->Integer.valueOf(string) * 2;
        Function<String,Integer> converterStringParaInteiro = Integer::valueOf; //
//        System.out.println(retornaNomeAoContrario.apply("Robson"));
//        System.out.println(converterStringParaInteiroECalcularODobro.apply("20"));
        System.out.println(converterStringParaInteiro.apply("20"));
    }
}
