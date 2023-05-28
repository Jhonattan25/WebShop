package co.edu.uniquindio.webshop.service.excepciones;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}