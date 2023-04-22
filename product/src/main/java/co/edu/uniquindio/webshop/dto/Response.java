package co.edu.uniquindio.webshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response<T>{

    private String mensaje;
    private T dato;

    public Response(String mensaje) {
        this.mensaje = mensaje;
    }
}