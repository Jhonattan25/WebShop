package co.edu.uniquindio.webshop.service.excepciones;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}