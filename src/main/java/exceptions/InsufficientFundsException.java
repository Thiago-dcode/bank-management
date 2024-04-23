package exceptions;

public class InsufficientFundsException extends  IllegalArgumentException{

    public InsufficientFundsException(){
        super("Insufficient Funds");
    }
    public InsufficientFundsException(String message){
        super(message);
    }
}
