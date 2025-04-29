import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConverter {
    String base_code = "USD";
    String target_code = "CLP";
    double conversion_rate = 00.00;

    String EXCHANGE_API_KEY = System.getenv("EXCHANGE_API_KEY");



       public CurrencyConverter(){
           //
       }



       public void getHttpValue(){
            try {

                URI url = new URI("https://v6.exchangerate-api.com/v6/" +
                                          EXCHANGE_API_KEY +
                                          "/pair" +
                                          "/" + base_code +
                                          "/" + target_code);

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
                // close
            }
       }

}
