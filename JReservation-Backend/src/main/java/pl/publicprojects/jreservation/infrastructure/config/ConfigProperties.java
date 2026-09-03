package pl.publicprojects.jreservation.infrastructure.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ConfigProperties {
    @Value("${jwt.secret}")
    private String jwtSecret = "TestingTokenTestingTokenTestingTokenTestingTokenTestingTokenTestingTokenTestingToken";

    @Value("${jwt.expiration.seconds}")
    private long jwtExpirationSeconds = 3600;
}
