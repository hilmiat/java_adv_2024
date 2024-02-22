package id.my.hilmiat.sping_h2.exception;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(Long id){
        super("Person not found");
    }
}
