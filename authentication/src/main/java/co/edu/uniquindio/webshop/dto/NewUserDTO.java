package co.edu.uniquindio.webshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NewUserDTO(String username, String firstName, String lastName,
                         String email, String password){

}