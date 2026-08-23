package pl.publicprojects.jreservation.infrastructure.rest.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterUserRequest {
    private String username;
    private String email;
    private String password;
}
