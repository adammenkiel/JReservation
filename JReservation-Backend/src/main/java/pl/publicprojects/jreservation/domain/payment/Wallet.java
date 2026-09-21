package pl.publicprojects.jreservation.domain.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import pl.publicprojects.jreservation.domain.product.Cost;
import pl.publicprojects.jreservation.domain.user.User;

import java.math.BigDecimal;
import java.util.UUID;


@Table(
        name="wallet"
)
@Getter
@Setter
@Entity
public class Wallet {
    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    UUID walletUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    @JsonIgnore
    User user;

    @Column(precision = 19, scale=2, nullable = false)
    @NotNull
    BigDecimal balance;

    @NotBlank
    String currency;

    public Wallet() {}

    public Wallet(User user, BigDecimal balance, String currency) {
        this.user = user;
        this.balance = balance;
        this.currency = currency;
    }
    public void deductFunds(Cost cost) {
        var after = this.balance.subtract(cost.getCost());
        if(after.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException(); // TODO: Correct
        }
        if(!this.currency.equals(cost.getCurrency())) {
            throw new RuntimeException();
        }
        this.balance = balance.subtract(cost.getCost());
    }
}
