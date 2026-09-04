package pl.publicprojects.jreservation.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.publicprojects.jreservation.application.helper.JwtHelper;
import pl.publicprojects.jreservation.infrastructure.rest.filters.AuthFilter;
import pl.publicprojects.jreservation.infrastructure.time.TimeManager;
import pl.publicprojects.jreservation.infrastructure.time.TimeManagerImpl;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class AuthConfiguration {

    private final AuthFilter authFilter;

    public AuthConfiguration(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public AuthenticationManager createAuthManagerBean(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder createPasswordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource configureCors() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        //TODO: It should be added into configuration
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173"));
        corsConfiguration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                        auth -> auth.requestMatchers(
                                "/auth/login", "/auth/register"
                        ).permitAll().anyRequest().authenticated()
                )
                .cors(
                        corsConfigurer -> corsConfigurer.configurationSource(
                                this.configureCors()
                        ))
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(this.authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
