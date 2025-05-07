import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CurrencyConverter {
    private Currency currency;
    Gson gson;
    Map<String, String> currencyNames = new HashMap<>();



       public CurrencyConverter(){
            currency = new Currency();
            gson = new GsonBuilder().setPrettyPrinting().create();
            currencyNames.put("ARS", "Argentinian Peso");
            currencyNames.put("BOB", "Bolivian Peso");
            currencyNames.put("BRL", "Brazilian Real");
            currencyNames.put("CLP", "Chilean Peso");
            currencyNames.put("COP", "Colombian Peso");
            currencyNames.put("USD", "U.S. Dollar");
       }


       private String getApiKey() throws NoAPIavailableException {
        final String EXCHANGE_API_KEY = System.getenv("EXCHANGE_API_KEY");

           if(EXCHANGE_API_KEY != null){
               Main.printMessage("API ready...");
               if((System.getenv("TESTING_MODE").equalsIgnoreCase("true"))){
                   System.out.printf("####################################%n" +
                                             "### API KEY: %s###.%n" +
                                             "####################################%n",EXCHANGE_API_KEY);
               }
           } else {
               throw new NoAPIavailableException("Something went wrong with the API, please check it");
           }
        return EXCHANGE_API_KEY;
       }



       private Currency getHttpExchangeRate(String base_code){
            try {
                String EXCHANGE_API_KEY = getApiKey();

                URI url = new URI("https://v6.exchangerate-api.com/v6/" +
                                          EXCHANGE_API_KEY +
                                            "/latest" +
                                          "/" + base_code);
                HttpClient client = HttpClient.newBuilder()
                        .build();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(url).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                currency = gson.fromJson(response.body(), Currency.class);

            } catch (IOException | InterruptedException e){
                Main.printMessage(String.format("An error ocurred: %s.%n", e));
            } catch (URISyntaxException | NullPointerException | NoAPIavailableException e){
                Main.printMessage(String.format("There was an error: %s.%n", e));
            } finally {
                //
            }

            return currency;
       }

       public void getExchange(String base_code, String target_code, int amountToConvert){

            double exchangeRate = getHttpExchangeRate(base_code).getConversionRate(target_code);
            currency.setTargetCode(target_code);

            double convertedValue = amountToConvert * exchangeRate;
            String message = String.format("%,d %s%s %s $ %,.4f %s%s.%n",
                                           amountToConvert,
                                           currencyNames.get(base_code),
                                           getPluralS(amountToConvert),
                                           getArticle(amountToConvert),
                                           convertedValue,
                                           currencyNames.get(target_code),
                                           getPluralS(convertedValue));
            Main.printMessage(message);

       }

       private boolean getIsPlural(double amount){
           return (amount != 1) ;
       }

       private String getPluralS(double amount){
           return getIsPlural(amount) ? ("s") : ("");
       }

       private String getArticle(double amount){
           return getIsPlural(amount) ? ("are") : ("is");
       }

}
