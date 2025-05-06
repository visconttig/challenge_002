public class NoAPIavailableException extends Throwable {


    public NoAPIavailableException(){
        super();
    }

    public NoAPIavailableException(String message) {
        super(message);
    }

    public NoAPIavailableException(String message, Throwable cause){
        super(message, cause);
    }

    public NoAPIavailableException(Throwable cause){
        super(cause);
    }
}
