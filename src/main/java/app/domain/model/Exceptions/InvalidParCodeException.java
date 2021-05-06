package app.domain.model.Exceptions;

public class InvalidParCodeException extends IllegalArgumentException{

    public InvalidParCodeException(){
        super();
    }

    public InvalidParCodeException(String msg){
        super(msg);
    }
}
