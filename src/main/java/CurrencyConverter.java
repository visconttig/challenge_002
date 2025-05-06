import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CurrencyConverter {
    private Currency currency;
    Gson gson;


       public CurrencyConverter(){
            currency = new Currency();
            gson = new GsonBuilder().setPrettyPrinting().create();
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

            Main.printMessage(String.format("%d %s are $ %.2f %s.%n", amountToConvert, base_code, (amountToConvert * exchangeRate), target_code));

       }

}
