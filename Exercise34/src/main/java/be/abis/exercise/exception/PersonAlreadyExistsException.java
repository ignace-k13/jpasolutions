package be.abis.exercise.exception;

public class PersonAlreadyExistsException extends Exception {
    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
