package pl.publicprojects.jreservation.infrastructure.rest.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginUserRequest {
    @NotBlank(message = "Username cannot be blank")
    String username;
    @NotBlank(message = "Password cannot be blank")
    String password;
}
