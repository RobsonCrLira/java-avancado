package interfacesFuncionais;

public class funcaoAltaOrdem {
    public static void main(String[] args) {
        Calculo soma = (a, b) -> a+b;
        Calculo subtracao = (a, b) -> a-b;
        Calculo multiplicacao = (a, b) -> a*b;
        Calculo divisao = (a, b) -> a/b;

        System.out.println(executarOperacao(soma,1,3));
        System.out.println(executarOperacao(subtracao,1,3));
        System.out.println(executarOperacao(multiplicacao,5,3));
        System.out.println(executarOperacao(divisao,6,3));
    }

    public static int executarOperacao(Calculo calculo, int a, int b){
        return calculo.calcula(a,b);
    }
}

interface Calculo {
    public int calcula(int a, int b);
}


/*  Definição de função de alta ordem

RECEBE UMA FUNÇÃO POR UM PARAMETRO
OU
QUE RETORNA OUTRA FUNÇÃO POR PARAMETRO

* */