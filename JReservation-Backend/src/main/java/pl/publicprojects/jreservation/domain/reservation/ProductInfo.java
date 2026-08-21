package pl.publicprojects.jreservation.domain.reservation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(
        name = "products"
)
public class ProductInfo {
    @Id
    @NotBlank
    UUID productId;

    @NotBlank
    int cost;

    @NotBlank
    int amount;

    @NotBlank
    Date starts;

    @NotBlank
    Date ends;
}
