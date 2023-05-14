package co.edu.uniquindio.webshop.dto;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record UserDTO(String username, boolean enabled, String firstName, String lastName,
                      String email, boolean emailVerified, List<Map<String, Object>> credentials){
}
