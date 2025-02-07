package org.example.apirest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;

import java.util.Base64;
// Nota: Codigo para poder hacer la autentificacion por Google de Quasar
//  verificar y extraer el email de un token JWT de Google Sign-In
@Service
public class JwtService {
    // GOOGLE_CERTS_URL: URL donde Google publica las claves públicas para verificar los tokens JWT.
    private static final String GOOGLE_CERTS_URL = "https://www.googleapis.com/oauth2/v3/certs";
    // CLIENT_ID: El ID del cliente OAuth 2.0 que identifica la aplicación en Google.
    // esta clave la he sacado de la consola de google cloud
    // https://console.cloud.google.com/apis/credentials?inv=1&invt=Abo1XA&project=sunny-resolver-443420-e0
    private static final String CLIENT_ID = "974828485061-bic4bibg7nln4hsppirr258oel4ti11f.apps.googleusercontent.com";

    public String extractEmailFromJwt(String jwtToken) {
        try {
            SigningKeyResolverAdapter signingKeyResolver = getSigningKeyResolver();

            // Parsear y validar el token JWT
            Claims claims = Jwts.parserBuilder()
                    .setSigningKeyResolver(signingKeyResolver) // Usar la clave pública
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();

            // Validar que el token sea para tu aplicación
            String audience = claims.getAudience();
            if (!CLIENT_ID.equals(audience)) {
                throw new IllegalArgumentException("Invalid audience: " + audience);
            }

            // Retornar el email extraído del token
            return claims.get("email", String.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }

    private SigningKeyResolverAdapter getSigningKeyResolver() {
        return new SigningKeyResolverAdapter() {
            @Override
            public PublicKey resolveSigningKey(io.jsonwebtoken.JwsHeader header, Claims claims) {
                try {
                    // Obtener el `kid` del header del token
                    String kid = header.getKeyId();

                    // Descargar las claves públicas de Google
                    Map<String, Object> certs = new ObjectMapper().readValue(
                            new URL(GOOGLE_CERTS_URL), Map.class);

                    // Buscar la clave pública correspondiente al `kid`
                    for (Map<String, Object> key : (Iterable<Map<String, Object>>) certs.get("keys")) {
                        if (kid.equals(key.get("kid"))) {
                            // Decodificar los parámetros de la clave pública
                            byte[] modulusBytes = Base64.getUrlDecoder().decode((String) key.get("n"));
                            byte[] exponentBytes = Base64.getUrlDecoder().decode((String) key.get("e"));

                            // Crear la clave pública
                            RSAPublicKeySpec spec = new RSAPublicKeySpec(
                                    new java.math.BigInteger(1, modulusBytes),
                                    new java.math.BigInteger(1, exponentBytes)
                            );
                            return KeyFactory.getInstance("RSA").generatePublic(spec);
                        }
                    }
                    throw new IllegalArgumentException("No matching key found for kid: " + kid);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to resolve signing key", e);
                }
            }
        };
    }
}
