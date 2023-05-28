package co.edu.uniquindio.webshop.service.interfaces;

import co.edu.uniquindio.webshop.dto.ResponseGetUserDTO;
import co.edu.uniquindio.webshop.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "keycloakRegister", url = "${ruta.keycloak}/admin/realms/webshop-api")
public interface UserFeingClient {
    @PostMapping("/users")
    void registerUser(@RequestHeader("Authorization") String token, @RequestBody UserDTO newUser);

    @GetMapping("/users/")
    List<ResponseGetUserDTO> getUserId(@RequestParam String username,
                                 @RequestHeader(value = "Authorization") String bearerToken);

    @PostMapping("/users/{userId}/role-mappings/realm")
    void setRole(@RequestHeader(value = "Authorization") String bearerToken, @PathVariable String userId,
                 @RequestBody List<Map<String, String>> role);
}
