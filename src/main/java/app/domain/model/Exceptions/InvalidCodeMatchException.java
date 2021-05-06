package app.domain.model.Exceptions;

public class InvalidCodeMatchException extends IllegalArgumentException{

    public InvalidCodeMatchException(){
        super();
    }

    public InvalidCodeMatchException(String msg){
        super(msg);
    }
}
