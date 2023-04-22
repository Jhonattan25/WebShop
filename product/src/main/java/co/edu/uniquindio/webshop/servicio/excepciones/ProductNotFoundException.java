package co.edu.uniquindio.webshop.servicio.excepciones;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}