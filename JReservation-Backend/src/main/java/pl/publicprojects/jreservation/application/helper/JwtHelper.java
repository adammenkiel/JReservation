package pl.publicprojects.jreservation.application.helper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import pl.publicprojects.jreservation.domain.authentication.User;
import pl.publicprojects.jreservation.infrastructure.time.TimeManager;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtHelper {

    private Key key;
    private final TimeManager timeManager;

    public JwtHelper(
            TimeManager timeManager
    ) {
        this.timeManager = timeManager;
    }

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor("Temporary keyTemporary keyTemporary keyTemporary keyTemporary keyTemporary keyTemporary keyTemporary keyTemporary key"
                .getBytes(StandardCharsets.UTF_8)); // to change
    }

    //TODO: Add some properties into configuration
    public ResponseCookie generateJwtCookieFromUser(User user) {
        String tokenString = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(Date.from(this.timeManager.now()))
                .setExpiration(
                        Date.from(this.timeManager.now().plusSeconds(3600))
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

    public boolean isValid(String tokenString) {
        try {
            this.getTokenContent(tokenString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTokenContent(String tokenString) {
        return Jwts.parserBuilder()
                .setSigningKey(this.key)
                .build()
                .parseClaimsJws(tokenString)
                .getBody()
                .getSubject();
    }
}
