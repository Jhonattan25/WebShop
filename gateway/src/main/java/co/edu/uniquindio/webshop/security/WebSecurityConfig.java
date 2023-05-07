package co.edu.uniquindio.webshop.security;


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
                    e.pathMatchers("/api/auth/**").permitAll()
                            .pathMatchers(HttpMethod.GET,"/api/product/all").permitAll()
                            .pathMatchers(HttpMethod.PUT,"/api/product/stock").hasAnyRole(ADMIN, USER)
                            .pathMatchers(HttpMethod.GET,"/api/product/{idProduct}").permitAll()
                            .pathMatchers(HttpMethod.PUT,"/api/product/{idProduct}").hasRole(ADMIN)
                            .pathMatchers(HttpMethod.DELETE,"/api/product/{idProduct}").hasRole(ADMIN)
                            .pathMatchers(HttpMethod.POST,"/api/product").hasRole(ADMIN)
                            .anyExchange().authenticated());

        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);
        http.csrf().disable();
        return http.build();
    }
}
