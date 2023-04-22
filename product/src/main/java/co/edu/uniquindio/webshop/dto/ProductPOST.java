package co.edu.uniquindio.webshop.dto;

import java.math.BigDecimal;

public record ProductPOST(String name, String description, BigDecimal price,int stock,String imageUrl){

}