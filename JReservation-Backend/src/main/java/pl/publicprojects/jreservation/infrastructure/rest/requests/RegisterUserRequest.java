package pl.publicprojects.jreservation.infrastructure.rest.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterUserRequest {

    @Pattern(
            regexp = "^[a-zA-Z0-9_]{3,16}$",
            message="Nickname should have correct format (^[a-zA-Z0-9_]{3,16}$)"
    )
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 16)
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 50)
    private String password;
}
