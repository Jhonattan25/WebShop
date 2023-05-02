package co.edu.uniquindio.webshop.dto;

import java.math.BigDecimal;

public record NewUserDTO(String name, String description, BigDecimal price, int stock, String imageUrl){

}