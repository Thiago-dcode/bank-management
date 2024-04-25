package exceptions;

public class DuplicateEntityException extends Exception{
    public DuplicateEntityException(){
        super("Duplicate Entity");
    }
    public DuplicateEntityException(String message){
        super(message);
    }
}
