package org.example.apirest.providers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.example.apirest.security.JwtKeyProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class MallorcaPlayasProvider {

    private final WebClient mallorcaPlayasLaravelApi;
    private final JwtKeyProvider jwtKeyProvider;

    public void notifyRoleApproved(Object id){

        String token = Jwts.builder()
                .setSubject(id.toString())
                .signWith(jwtKeyProvider.getSigningKey(), SignatureAlgorithm.HS256) // Firma el token con la clave secreta
                .compact(); // Compacta el token en una cadena

        mallorcaPlayasLaravelApi.post()
                .uri("/roles/notify")
                .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
    }

}
