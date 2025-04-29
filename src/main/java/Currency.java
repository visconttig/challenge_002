public class Currency {
    private String base_code;
    private String target_code;
    double conversion_rate;

    public Currency(){
        this("USD", "CLP");
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

    public double getConversionRate(){
        return conversion_rate;
    }

    public void setBaseCode(String base_code){
        this.base_code = base_code;
    }

    public void setTargetCode(String target_code){
        this.target_code = target_code;
    }
}
