package pl.publicprojects.jreservation.domain.payment;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.publicprojects.jreservation.domain.user.User;

import java.math.BigDecimal;
import java.util.UUID;


@Table(
        name="wallet"
)
@Getter
@Setter
public class Wallet {
    @Id
    UUID walletUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    User user;

    @Column(precision = 19, scale=2, nullable = false)
    @NotNull
    BigDecimal balance;

    @NotBlank
    String currency;
}
