package pl.publicprojects.jreservation.application.helper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import pl.publicprojects.jreservation.domain.user.User;
import pl.publicprojects.jreservation.infrastructure.config.ConfigProperties;
import pl.publicprojects.jreservation.infrastructure.time.TimeManager;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtHelper {

    private Key key;
    private final TimeManager timeManager;
    private final ConfigProperties properties;

    public JwtHelper(
            TimeManager timeManager,
            ConfigProperties properties
    ) {
        this.timeManager = timeManager;
        this.properties = properties;
    }

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(this.properties.getJwtSecret()
                .getBytes(StandardCharsets.UTF_8));
    }

    public ResponseCookie generateJwtCookieFromUser(User user) {
        String tokenString = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(Date.from(this.timeManager.now()))
                .setExpiration(
                        Date.from(
                                this.timeManager.now()
                                        .plusSeconds(this.properties.getJwtExpirationSeconds())
                        )
                )
                .signWith(this.key, SignatureAlgorithm.HS256)
                .compact();

        return ResponseCookie.from("token", tokenString)
                .path("/")
                .maxAge(this.properties.getJwtExpirationSeconds())
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
