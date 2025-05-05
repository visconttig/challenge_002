import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int chosenOption = 0;
        int amountToConvert = 0;
        CurrencyConverter cConverter;


        try (Scanner sc = new Scanner(System.in)) {
            do {
                chosenOption = showMenu(sc);
                printMessage(String.format("\t\tChosen option: %d.%n", chosenOption));
                printMessage("Enter the amount to convert: ");
                amountToConvert = askNumber(sc);
                printMessage(String.format("\t\tAmount to convert: %d.%n", amountToConvert));
                /* Set currency pairs */
                cConverter = new CurrencyConverter("USD");
                cConverter.getHttpValue();
                /* Show currency valuation */
            } while (chosenOption != 7);
        } catch (IOException e){
            printMessage(String.format("An error ocurred: %s.%n", e.getMessage()));
        }

    }

    public static int showMenu(Scanner sc) throws IOException {
        String header = "*".repeat(46);
        var menu = String.format("%s%n%s%n%n" +
                                         "*** Welcome to the Currency Converter App! ***%n" +
                                         "%n1) Dolar =>> Argentinian Peso%n" +
                                         "2) Argentinian Peso =>> Dolar%n" +
                                         "3) Dolar =>> Brazilian Royal%n" +
                                         "4) Brazilian Royal =>> Dolar%n" +
                                         "5) Dolar =>> Colombian Peso%n" +
                                         "6) Colombian Peso =>> Dolar%n" +
                                         "7) Exit %n%n" +
                                         "Choose an option to continue: ", header, header);
        printMessage(menu);
        return askNumber(sc);
    }

    public static int askNumber(Scanner sc) throws IOException {
        return sc.nextInt();
    }

    public static void printMessage(String message){
        System.out.print(message);
    }

}
