package Java11;

import java.util.function.Function;

public class InferenciaLamba {
    public static void main(String[] args) {
        Function<Integer,Double> divisaoPor2 = (var numero) -> numero/2.0;

        System.out.println(divisaoPor2.apply(0));
    }
}
