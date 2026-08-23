package pl.publicprojects.jreservation.domain.reservation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
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

    public ProductInfo() {}

    public ProductInfo(
            UUID productId,
            String name,
            String shortDescription,
            String description,
            int cost,
            int amount,
            LocalDateTime starts,
            LocalDateTime ends
    ) {
        this.productId = productId;
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.cost = cost;
        this.amount = amount;
        this.starts = starts;
        this.ends = ends;
    }

    public ProductInfo(
            String rawId,
            String name,
            String shortDescription,
            String description,
            int cost,
            int amount,
            LocalDateTime starts,
            LocalDateTime ends
    ) {
        this.productId = UUID.nameUUIDFromBytes(rawId.getBytes(StandardCharsets.UTF_8));
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.cost = cost;
        this.amount = amount;
        this.starts = starts;
        this.ends = ends;
    }

    public boolean isAvailable() {
        LocalDateTime dateTime = LocalDateTime.now();
        return starts.isAfter(dateTime) && ends.isBefore(dateTime);
    }

}
