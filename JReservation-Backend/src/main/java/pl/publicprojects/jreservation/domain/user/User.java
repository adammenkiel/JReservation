package pl.publicprojects.jreservation.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.publicprojects.jreservation.domain.payment.Wallet;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class User implements UserDetails {
    @Id
    @NotNull
    private UUID uuid;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private Date createdAccountTime;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Wallet> wallets = new ArrayList<>();

    public User(String username, String email, String password, Date createdAccountTime) {
        this.uuid = UUID.nameUUIDFromBytes(username.getBytes(StandardCharsets.UTF_8));
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAccountTime = createdAccountTime;
    }

    public User(String username, String email, String password, Date createdAccountTime, List<Wallet> wallets) {
        this.uuid = UUID.nameUUIDFromBytes(username.getBytes(StandardCharsets.UTF_8));
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAccountTime = createdAccountTime;
        this.wallets = wallets;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
