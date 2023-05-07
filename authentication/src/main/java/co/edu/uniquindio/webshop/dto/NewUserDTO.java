package co.edu.uniquindio.webshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NewUserDTO(String username, String enabled, String email, boolean emailVerified,
                         String firstName, String lastName,ArrayList<Credential> credentials){

}