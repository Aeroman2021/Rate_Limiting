package ir.tamin.crud.exception;

public class PersonNotFoundException  extends RuntimeException{
    public PersonNotFoundException(Long customerId) {
        super("Customer with id " + customerId + " not found");
    }
}
