package app.domain.model.Exceptions;

public class InvalidParNameException extends IllegalArgumentException{

    public InvalidParNameException(){
        super();
    }

    public InvalidParNameException(String msg){
        super(msg);
    }
}
