package org.example.apirest.providers.mallorca_playas_laravel_api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.example.apirest.security.JwtKeyProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class LaravelRolesApi {

    private final static String BASE_URL = "/roles";

    private final WebClient laravelApi;
    private final JwtKeyProvider jwtKeyProvider;

    public void notifyRoleApproved(Object id){

        String token = Jwts.builder()
                .setSubject(id.toString())
                .signWith(jwtKeyProvider.getSigningKey(), SignatureAlgorithm.HS256) // Firma el token con la clave secreta
                .compact(); // Compacta el token en una cadena

        laravelApi.post()
                .uri(BASE_URL+"/notify")
                .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
    }

}
