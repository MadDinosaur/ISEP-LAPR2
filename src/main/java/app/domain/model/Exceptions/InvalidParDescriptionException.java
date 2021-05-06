package app.domain.model.Exceptions;

public class InvalidParDescriptionException extends IllegalArgumentException{

    public InvalidParDescriptionException(){
        super();
    }

    public InvalidParDescriptionException(String msg){
        super(msg);
    }
}
