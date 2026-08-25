package pl.publicprojects.jreservation.domain.helper;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import pl.publicprojects.jreservation.domain.authentication.User;

@Component
public class JwtHelper {
    public ResponseCookie generateJwtCookieFromUser(User user) {
        return null;
    }
}
