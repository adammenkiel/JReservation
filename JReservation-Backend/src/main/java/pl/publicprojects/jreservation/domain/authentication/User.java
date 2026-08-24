package pl.publicprojects.jreservation.domain.authentication;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Getter
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class User {
    @Id
    @NotNull
    private final UUID uuid;

    @NotBlank
    private final String username;

    @NotBlank
    private final String email;

    @NotBlank
    private final String password;

    public User(String username, String email, String password) {
        this.uuid = UUID.nameUUIDFromBytes(username.getBytes(StandardCharsets.UTF_8));
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
