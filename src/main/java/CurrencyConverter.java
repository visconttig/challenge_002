import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.Map;

public class CurrencyConverter {
    Currency currency;



       public CurrencyConverter(Currency currency){
           this.currency = currency;
       }

       private void printEnviromentVariables(){
           Map<String, String> env = System.getenv();
           System.out.println("ENVIROMENT VARIABLES: ");
           for(String envName : env.keySet()){
               System.out.printf("%s=%s", envName, env.get(envName));
           }
       }



       public void getHttpValue(){
            try {
                String EXCHANGE_API_KEY = System.getenv("EXCHANGE_API_KEY");
                if(EXCHANGE_API_KEY != null){
                    Main.printMessage("API ready...");
                } else {
                    Main.printMessage("Something went wrong with the API, please check it");
                    return;
                }


                URI url = new URI("https://v6.exchangerate-api.com/v6/" +
                                          EXCHANGE_API_KEY +
                                          "/pair" +
                                          "/" + currency.getBaseCode() +
                                          "/" + currency.getTargetCode());

                HttpClient client = HttpClient.newBuilder()
                        .build();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(url).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                Main.printMessage(String.format("Body: %s.%n", response.body()));
            } catch (IOException | InterruptedException e){
                Main.printMessage(String.format("An error ocurred: %s.%n", e));
            } catch (URISyntaxException | NullPointerException e){
                Main.printMessage(String.format("There was an error: %s.%n", e));
            } finally {
                //
            }
       }

}
