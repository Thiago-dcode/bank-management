package exceptions;

public class InvalidAmountException extends IllegalArgumentException {

    public InvalidAmountException() {
        super("Invalid amount");
    }
    public InvalidAmountException(String message) {
        super(message);
    }

}
