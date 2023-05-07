package co.edu.uniquindio.webshop.service;

import co.edu.uniquindio.webshop.dto.LoginDTO;
import co.edu.uniquindio.webshop.dto.NewUserDTO;
import co.edu.uniquindio.webshop.dto.TokenDTO;
import co.edu.uniquindio.webshop.service.interfaces.OAuthFeingClient;
import co.edu.uniquindio.webshop.service.interfaces.RegisterFeingClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final OAuthFeingClient oAuthFeingClient;
    private final RegisterFeingClient registerFeingClient;

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

        System.out.println("Credenciales: "+loginDTO);

        System.out.println("userName: "+loginDTO.username());
        System.out.println("password: "+loginDTO.password());

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
        return new TokenDTO("Hello");
    }

    /*
    * Para el registro: https://www.keycloak.org/docs-api/15.0/rest-api/index.html#_users_resource
    * */
    public Boolean createUser(NewUserDTO newUserDTO){

        LoginDTO loginDTO = getCredentials();
        String token = "Bearer "+login(loginDTO).accessToken();

        try {
            registerFeingClient.registerUser(token, newUserDTO);
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
}
