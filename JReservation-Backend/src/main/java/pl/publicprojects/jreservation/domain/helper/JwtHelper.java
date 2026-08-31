package pl.publicprojects.jreservation.domain.helper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import pl.publicprojects.jreservation.domain.authentication.User;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtHelper {

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor("Temporary keyTemporary keyTemporary keyTemporary keyTemporary keyTemporary keyTemporary keyTemporary keyTemporary key"
                .getBytes(StandardCharsets.UTF_8)); // to change
    }

    //TODO: Add some properties into configuration
    public ResponseCookie generateJwtCookieFromUser(User user) {
        String tokenString = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(
                        Date.from(Instant.now().plusSeconds(3600))
                )
                .signWith(this.key, SignatureAlgorithm.HS256)
                .compact();

        return ResponseCookie.from("token", tokenString)
                .path("/")
                .maxAge(3600)
                .httpOnly(true)
                .sameSite("Lax")
                .secure(false)
                .build();
    }
}
