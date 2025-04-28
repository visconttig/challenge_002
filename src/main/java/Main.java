import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        try (Scanner sc = new Scanner(System.in)) {
            System.out.printf("Chosen option: %d.%n", showMenu(sc));
        } catch (IOException e){
            System.out.printf("An error ocurred: %s.%n", e.getMessage());
        }

    }

    public static int showMenu(Scanner sc) throws IOException {
        var menu = String.format("*** Welcome to the Currency Converter App! ***%n" +
                                         "%n1) Dolar =>> Argentinian Peso%n" +
                                         "2) Argentinian Peso =>> Dolar%n" +
                                         "3) Dolar =>> Brazilian Royal%n" +
                                         "4) Brazilian Royal =>> Dolar%n" +
                                         "5) Dolar =>> Colombian Peso%n" +
                                         "6) Colombian Peso =>> Dolar%n" +
                                         "7) Exit %n%n" +
                                         "Choose an option to continue: ");
        System.out.print(menu);
        return sc.nextInt();
    }
}
