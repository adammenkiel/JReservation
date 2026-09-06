package pl.publicprojects.jreservation.application.helper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import pl.publicprojects.jreservation.domain.exception.exceptions.AuthException;
import pl.publicprojects.jreservation.domain.exception.exceptions.CookieException;

import java.util.Arrays;
import java.util.Optional;

@Component
public class CookieHelper {

    public String getCookieValue(HttpServletRequest request, String cookieName) {
        if(request.getCookies() == null) {
            throw new CookieException("There is no required cookie!");
        }
        Optional<Cookie> optCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findFirst();

        if(optCookie.isEmpty()) {
            throw new CookieException("There is no required cookie!");
        }
        return optCookie.get().getValue();
    }

    public String loadTokenCookieValue(HttpServletRequest request) {
        try {
            return this.getCookieValue(request, "token");
        } catch (CookieException e) {
            throw new AuthException(e.getMessage());
        }
    }
}
