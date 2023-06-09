package co.edu.uniquindio.webshop.service.interfaces;

import co.edu.uniquindio.webshop.dto.TokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "keycloakLogin", url = "${ruta.keycloak}/realms/webshop-api/protocol/openid-connect")
public interface OAuthFeingClient {
    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenDTO getToken(@RequestBody String formData);
}
