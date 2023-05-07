package co.edu.uniquindio.webshop.service.interfaces;

import co.edu.uniquindio.webshop.dto.NewUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "keycloakRegister", url = "${ruta.keycloak}/admin/realms/tutorial-api")
public interface RegisterFeingClient {
    @PostMapping("/users")
    void registerUser(@RequestHeader("Authorization") String token, @RequestBody NewUserDTO newUser);
}
