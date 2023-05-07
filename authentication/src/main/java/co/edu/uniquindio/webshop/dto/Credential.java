package co.edu.uniquindio.webshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Credential(String type, boolean temporary, String value) {
}
