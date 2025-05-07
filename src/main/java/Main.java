import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<Integer, MenuOption> menuOptions = new LinkedHashMap<>();

    public static void main(String[] args){
        int chosenOption = 0;
        int amountToConvert = 0;
        CurrencyConverter cConverter;



        try (Scanner sc = new Scanner(System.in)) {
            while(true) {
                chosenOption = showMenu(sc);
                if(chosenOption == 7){
                    System.out.println("Exit...");
                    System.exit(0);
                }
                printMessage("Enter the amount to convert: ");
                amountToConvert = askNumber(sc);
                printMessage(String.format("\t\tAmount to convert: %d.%n", amountToConvert));
                cConverter = new CurrencyConverter();
                cConverter.getExchange(menuOptions.get(chosenOption).baseCode(), menuOptions.get(chosenOption).targetCode(), amountToConvert);
            }
        } catch (IOException e){
            printMessage(String.format("An error ocurred: %s.%n", e.getMessage()));
        }

    }

    public static int showMenu(Scanner sc) throws IOException {
        initializeMenuOptions();
        return askNumber(sc);
    }

    public static int askNumber(Scanner sc) throws IOException {
        return sc.nextInt();
    }

    public static void printMessage(String message){
        System.out.print(message);
    }

    public static void initializeMenuOptions(){
        menuOptions.put(1, new MenuOption(1, "USD", "ARS", "Dollar =>> Argentinian Peso"));
        menuOptions.put(2, new MenuOption(2, "ARS", "USD", "Argentinian Peso =>> Dollar"));
        menuOptions.put(3, new MenuOption(3, "USD", "BRL", "Dollar =>> Brazilian Real"));
        menuOptions.put(4, new MenuOption(4, "BRL", "USD", "Brazilian Real =>> Dollar"));
        menuOptions.put(5, new MenuOption(5, "USD", "COP", "Dollar =>> Colombian Peso"));
        menuOptions.put(6, new MenuOption(6, "COP","USD", "Colombian Peso =>> Dollar"));
        menuOptions.put(7, new MenuOption(7, null, null, "Exit"));

        String header = "*".repeat(46);
        var menuHeader = String.format("%s%n%s%n*** Welcome to the Currency Converter App! ***%n", header, header);
        var menuFooter = "Choose an option to continue: ";

        StringBuilder menu = new StringBuilder();
        menu.append(menuHeader);
        for(MenuOption option : menuOptions.values()){
            menu.append(String.format("%s:\t%s.%n", option.optionNumber(), option.label()));
        }
        menu.append(menuFooter);
        printMessage(menu.toString());

    }

}
