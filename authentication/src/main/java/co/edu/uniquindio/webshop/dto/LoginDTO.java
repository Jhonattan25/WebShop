package co.edu.uniquindio.webshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@Builder
public record LoginDTO(String username, String password){

}