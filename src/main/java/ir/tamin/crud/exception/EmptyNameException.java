package ir.tamin.crud.exception;

public class EmptyNameException extends RuntimeException{

    public EmptyNameException() {
        super();
    }

    public EmptyNameException(String message) {
        super(message);
    }

    public EmptyNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
