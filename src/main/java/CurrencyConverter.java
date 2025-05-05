import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CurrencyConverter {
    Currency currency;
    Gson gson;

        public CurrencyConverter(){
            this("USD");
        }

       public CurrencyConverter(String base_code){
            currency = new Currency(base_code);
            gson = new Gson();
       }


       private String getApiKey(){
        final String EXCHANGE_API_KEY = System.getenv("EXCHANGE_API_KEY");
        if((System.getenv("TESTING_MODE").equalsIgnoreCase("true"))){
            System.out.printf("####################################%n" +
                                      "### API KEY: %s###.%n" +
                                      "####################################%n",EXCHANGE_API_KEY);
        }
        return EXCHANGE_API_KEY;
       }



       public void getHttpValue(){
            try {
                String EXCHANGE_API_KEY = getApiKey();
                if(EXCHANGE_API_KEY != null){
                    Main.printMessage("API ready...");
                } else {
                    Main.printMessage("Something went wrong with the API, please check it");
                    return;
                }


                URI url = new URI("https://v6.exchangerate-api.com/v6/" +
                                          EXCHANGE_API_KEY +
                                            "/latest" +
                                          "/" + currency.getBaseCode());
                HttpClient client = HttpClient.newBuilder()
                        .build();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(url).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
                currency = gson1.fromJson(response.body(), Currency.class);
                System.out.println(currency);



            } catch (IOException | InterruptedException e){
                Main.printMessage(String.format("An error ocurred: %s.%n", e));
            } catch (URISyntaxException | NullPointerException e){
                Main.printMessage(String.format("There was an error: %s.%n", e));
            } finally {
                //
            }

       }

}
