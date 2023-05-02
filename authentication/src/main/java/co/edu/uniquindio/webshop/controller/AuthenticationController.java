package co.edu.uniquindio.webshop.controller;

import co.edu.uniquindio.webshop.dto.LoginDTO;
import co.edu.uniquindio.webshop.dto.NewUserDTO;
import co.edu.uniquindio.webshop.dto.Response;
import co.edu.uniquindio.webshop.dto.TokenDTO;
import co.edu.uniquindio.webshop.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final LoginService loginService;

    @PostMapping("/signin")
    public ResponseEntity<Response<TokenDTO>> login(@RequestBody LoginDTO loginDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>("Login correcto", loginService.login(loginDTO)) );
    }


    @PostMapping("/refresh")
    public ResponseEntity<Response<TokenDTO>> refresh(@RequestBody TokenDTO token) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>("", loginService.refresh(token)) );
    }


    @PostMapping("/signup")
    public ResponseEntity<Response<String>> createUser(@RequestBody NewUserDTO newUserDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>(loginService.createUser(newUserDTO) ? "Creado correctamente": "Error", "") );
    }

}
