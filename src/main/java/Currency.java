import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Currency {
    private String time_last_update_utc;
    private String base_code;
    private String target_code;
    Map<String, Double> conversion_rates;


    public Currency(){
        this("USD", "CLP");
    }

    public Currency(String base_code){
        this(base_code, "CLP");
    }


    public Currency(String base_code, String target_code){
        this.base_code = base_code;
        this.target_code = target_code;
    }

    public String getBaseCode(){
        return base_code;
    }

    public String getTargetCode(){
        return target_code;
    }

    public double getConversionRate(String target_code){
        if((conversion_rates == null) || !(conversion_rates.containsKey(target_code))){
            throw new IllegalArgumentException(String.format("Conversion rate for %s not available.", target_code));
        }
        return conversion_rates.get(target_code);
    }

    public Map<String, Double> getConversionRates(){
        return conversion_rates;
    }

    public void setBaseCode(String base_code){
        this.base_code = base_code;
    }

    public void setTargetCode(String target_code){
        this.target_code = target_code;
    }

    public void setConversionRates(Map<String, Double> conversion_rates){
        this.conversion_rates = conversion_rates;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
