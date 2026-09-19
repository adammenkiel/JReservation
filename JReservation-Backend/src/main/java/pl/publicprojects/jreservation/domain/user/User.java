package pl.publicprojects.jreservation.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.publicprojects.jreservation.domain.exception.exceptions.WalletNotFoundException;
import pl.publicprojects.jreservation.domain.payment.Wallet;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

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

    @NotNull
    private Date createdAccountTime;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapKey(name = "currency")
    private Map<String, Wallet> wallets = new HashMap<>();

    private static final Pattern STR_PATTERN = Pattern.compile("^[A-Za-z0-9]{3,16}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public User(String username, String email, String password, Date createdAccountTime) {
        this.uuid = UUID.nameUUIDFromBytes(username.getBytes(StandardCharsets.UTF_8));
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAccountTime = createdAccountTime;
        this.validate(username, email, password);
    }

    public User(String username, String email, String password, Date createdAccountTime, List<Wallet> wallets) {
        this.uuid = UUID.nameUUIDFromBytes(username.getBytes(StandardCharsets.UTF_8));
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAccountTime = createdAccountTime;
        this.wallets = new HashMap<>();
        wallets.forEach(wallet -> this.wallets.put(wallet.getCurrency(), wallet));
        this.validate(username, email, password);
    }

    private void validateString(String text) {
        if(!STR_PATTERN.matcher(text).matches()) {
            throw new RuntimeException();
        }
    }

    private void validateEmail(String text) {
        if(!EMAIL_PATTERN.matcher(text).matches()) {
            throw new RuntimeException();
        }
    }

    private void validatePassword(String password) {
        if(password.length() < 6) {
            throw new RuntimeException();
        }
    }

    private void validate(String username, String email, String password) {
        this.validateString(username);
        this.validateEmail(email);
        this.validatePassword(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public Wallet getWalletByCurrency(String currency) {
        if(!this.wallets.containsKey(currency)) {
            throw new WalletNotFoundException("You don't have wallet with currency: \"" + currency + "\"!");
        }
        return this.wallets.get(currency);
    }
}
