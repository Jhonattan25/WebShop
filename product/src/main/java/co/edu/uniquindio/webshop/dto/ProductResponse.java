package co.edu.uniquindio.webshop.dto;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record ProductResponse(long id,String name, String description, BigDecimal price, int stock, String imageUrl){

}