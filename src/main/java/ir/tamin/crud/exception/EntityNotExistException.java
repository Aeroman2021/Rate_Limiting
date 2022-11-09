package ir.tamin.crud.exception;

public class EntityNotExistException extends RuntimeException{
    public EntityNotExistException() {
        super();
    }

    public EntityNotExistException(String message) {
        super(message);
    }

    public EntityNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
