package co.edu.uniquindio.biblioteca.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@RequiredArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {


    public static final String ADMIN = "admin";
    public static final String USER = "user";
    private final JwtAuthConverter jwtAuthConverter;


    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {


        http.authorizeExchange( e ->
                    e.pathMatchers("/api/test/anonymous").permitAll()
                            .pathMatchers(HttpMethod.GET,"/api/product/all").permitAll()
                            .pathMatchers(HttpMethod.PUT,"/api/product/stock").hasRole(ADMIN)
                            .pathMatchers(HttpMethod.GET,"/api/product/{idProduct}").permitAll()
                            .pathMatchers(HttpMethod.PUT,"/api/product/{idProduct}").hasRole(ADMIN)
                            .pathMatchers(HttpMethod.DELETE,"/api/product/{idProduct}").hasRole(ADMIN)
                            .pathMatchers(HttpMethod.POST,"/api/product").hasAnyRole(ADMIN)
                            .anyExchange().authenticated());

        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);
        http.csrf().disable();
        return http.build();
    }
}
