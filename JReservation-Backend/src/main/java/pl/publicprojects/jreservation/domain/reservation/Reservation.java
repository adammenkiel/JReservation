package pl.publicprojects.jreservation.domain.reservation;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import pl.publicprojects.jreservation.domain.product.ProductInfo;
import pl.publicprojects.jreservation.domain.user.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Table(
        name = "reservation",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "product_id"})
        }
)
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    public UUID uuid;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductInfo productInfo;

    @NotNull
    private LocalDateTime reservationStartTime;

    public Reservation(
            User user,
            ProductInfo productInfo,
            LocalDateTime reservationStartTime
    ) {
        this.user = user;
        this.productInfo = productInfo;
        this.reservationStartTime = reservationStartTime;
    }

    public Reservation(
            User user,
            ProductInfo productInfo,
            Instant instantReservationStartTime
    ) {
        this.user = user;
        this.productInfo = productInfo;
        this.reservationStartTime = LocalDateTime.ofInstant(instantReservationStartTime, ZoneId.systemDefault());
    }
}
