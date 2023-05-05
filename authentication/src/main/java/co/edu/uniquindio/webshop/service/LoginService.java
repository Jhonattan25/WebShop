package co.edu.uniquindio.webshop.service;

import co.edu.uniquindio.webshop.dto.LoginDTO;
import co.edu.uniquindio.webshop.dto.NewUserDTO;
import co.edu.uniquindio.webshop.dto.TokenDTO;
import co.edu.uniquindio.webshop.service.interfaces.OAuthFeingClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final OAuthFeingClient oAuthFeingClient;

    /*
    * Para hacer el login (inicio de sesión) simplemente puede hacer una petición al endpoint
    *  http://localhost:9090/realms/tutorial-api/protocol/openid-connect/token con los datos necesarios.
    *
    * */
    public TokenDTO login(LoginDTO loginDTO){
        //MultivaluedMap<String, String> formData = new MultivaluedHashMap<>();

        //formData.add("grant_type","password");
        //formData.add("client_id","springboot-keycloak-client");
        //formData.add("username",loginDTO.username());
        //formData.add("password",loginDTO.password());

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

        //String form = "password=admin&grant_type=password&client_id=springboot-keycloak-client&username=globaladmin";

        System.out.println("FORM: "+ formData);

       try {
           TokenDTO tokenDTO = oAuthFeingClient.getToken(formData.toString());
           return tokenDTO;
       }catch (Exception e){
           System.out.println("ERROR: "+e.getMessage());
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
        return true;
    }
}
