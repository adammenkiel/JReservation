package pl.publicprojects.jreservation.infrastructure.rest.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.publicprojects.jreservation.application.helper.JwtHelper;
import pl.publicprojects.jreservation.application.services.UserService;
import pl.publicprojects.jreservation.domain.user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private final JwtHelper jwtHelper;
    private final UserService userService;

    public AuthFilter(
            JwtHelper jwtHelper,
            UserService userService
    ) {
        this.jwtHelper = jwtHelper;
        this.userService = userService;
    }

    //TODO: Consider move this logic or part of it into authService
    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException, UsernameNotFoundException {
        if(request.getCookies() == null) {
            response.sendError(403, "Authentication is required!");
            filterChain.doFilter(request, response);
            return;
        }
        Optional<Cookie> optCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("token"))
                .findFirst();

        if(optCookie.isEmpty()) {
            response.sendError(403, "Authentication is required!");
            filterChain.doFilter(request, response);
            return;
        }

        String tokenString = optCookie.get().getValue();
        if(!this.jwtHelper.isValid(tokenString)) {
            response.sendError(403, "Authentication token is incorrect!");
            filterChain.doFilter(request, response);
            return;
        }

        String username = this.jwtHelper.getTokenContent(tokenString);
        User user = (User) userService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                null,
                new ArrayList<>()
        );
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

    //TODO: Maybe correct it
    @Override
    protected boolean shouldNotFilter(
            @NotNull HttpServletRequest request
    ) throws ServletException {
        String[] endpoints = {"/auth/login", "/auth/register"};
        for(String endpoint : endpoints) {
            if(request.getServletPath().equals(endpoint))
                return true;
        }
        return false;
    }
}
