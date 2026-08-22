package pl.publicprojects.jreservation.domain.reservation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(
        name = "products"
)
public class ProductInfo {
    @Id
    @NotNull
    UUID productId;

    @NotBlank
    String name;

    @NotBlank
    String shortDescription;

    @NotBlank
    String description;

    @NotNull
    int cost;

    @NotNull
    int amount;

    @NotNull
    LocalDateTime starts;

    @NotNull
    LocalDateTime ends;

    public boolean isAvailable() {
        LocalDateTime dateTime = LocalDateTime.now();
        return starts.isAfter(dateTime) && ends.isBefore(dateTime);
    }
}
