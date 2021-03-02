package Java11;

public class StringIsBlankExemplo {
    public static void main(String[] args) {
        String espaco = " ";
//        System.out.println(espaco == null && espaco.length() == 0 && espaco.chars().allMatch(c -> " "));
        System.out.println(espaco.isBlank());
    }

}
