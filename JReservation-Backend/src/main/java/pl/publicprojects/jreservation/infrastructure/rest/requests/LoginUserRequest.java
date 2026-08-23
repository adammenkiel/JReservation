package pl.publicprojects.jreservation.infrastructure.rest.requests;

import lombok.Getter;

@Getter
public class LoginUserRequest {
    String username;
    String password;
}
