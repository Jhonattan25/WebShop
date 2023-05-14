package co.edu.uniquindio.webshop.service;

import co.edu.uniquindio.webshop.dto.*;
import co.edu.uniquindio.webshop.service.interfaces.OAuthFeingClient;
import co.edu.uniquindio.webshop.service.interfaces.UserFeingClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class LoginService {

    private final OAuthFeingClient oAuthFeingClient;
    private final UserFeingClient userFeingClient;

    @Value("${admin.username}")
    private String userName;

    @Value("${admin.password}")
    private String password;


    /*
    * Para hacer el login (inicio de sesión) simplemente puede hacer una petición al endpoint
    *  http://localhost:9090/realms/tutorial-api/protocol/openid-connect/token con los datos necesarios.
    *
    * */
    public TokenDTO login(LoginDTO loginDTO){

        StringBuilder formData = new StringBuilder();

        formData.append("password=");
        formData.append(loginDTO.password());
        formData.append("&");
        formData.append("grant_type=");
        formData.append("password");
        formData.append("&");
        formData.append("client_id=");
        formData.append("springboot-keycloak-client");
        formData.append("&");
        formData.append("username=");
        formData.append(loginDTO.username());

       try {
           TokenDTO tokenDTO = oAuthFeingClient.getToken(formData.toString());
           return tokenDTO;
       }catch (Exception e){
           throw new RuntimeException("Hubo un error recuperando el token");
       }
    }

    public TokenDTO refresh(TokenDTO token){

        StringBuilder formData = new StringBuilder();

        formData.append("client_id=");
        formData.append("springboot-keycloak-client");
        formData.append("&");
        formData.append("refresh_token=");
        formData.append(token.refreshToken());
        formData.append("&");
        formData.append("grant_type=");
        formData.append("refresh_token");

        try {
            TokenDTO tokenDTO = oAuthFeingClient.getToken(formData.toString());
            return tokenDTO;
        }catch (Exception e){
            throw new RuntimeException("Hubo un error recuperando el token");
        }
    }

    /*
    * Para el registro: https://www.keycloak.org/docs-api/15.0/rest-api/index.html#_users_resource
    * */
    public Boolean createUser(NewUserDTO newUserDTO){

        LoginDTO loginDTO = getCredentials();
        String token = "Bearer "+login(loginDTO).accessToken();

        UserDTO userDTO = convertToUser(newUserDTO);

        try {

            userFeingClient.registerUser(token, userDTO);

            String userId = getUserId(newUserDTO.username(), token);

            setRoleUser(userId, token);

            return true;
        } catch (Exception e) {
            throw new RuntimeException("Hubo un error registrando el usuario");
        }
    }

    private LoginDTO getCredentials() {
        return LoginDTO.builder()
                .username(userName)
                .password(password)
                .build();
    }

    private UserDTO convertToUser(NewUserDTO newUserDTO) {

        Map<String, Object> credentials = new HashMap<>();
        credentials.put("type", "password");
        credentials.put("value", newUserDTO.password());
        credentials.put("temporary", false);

        return UserDTO.builder()
                .username(newUserDTO.username())
                .enabled(true)
                .firstName(newUserDTO.firstName())
                .lastName(newUserDTO.lastName())
                .email(newUserDTO.email())
                .emailVerified(true)
                .credentials(List.of(credentials))
                .build();

    }

    private String getUserId(String username, String tokenAdmin){

        try {
            List<ResponseGetUserDTO> listResponseGetUserDTO = userFeingClient.getUserId(username, tokenAdmin);
            return listResponseGetUserDTO.get(0).id();
        } catch (Exception e) {
            throw new RuntimeException("Hubo un error asignando el rol al usuario");
        }
    }

    private void setRoleUser(String userID, String tokenAdmin){

        Map<String, String> role = new HashMap<>();
        role.put("id", "17a75097-d54f-4860-a37b-837990008f3a");
        role.put("name", "app_user");

        try {
            userFeingClient.setRole(tokenAdmin, userID,  List.of(role));
        } catch (Exception e) {
            throw new RuntimeException("Hubo un error asignando el rol al usuario");
        }
    }
}
