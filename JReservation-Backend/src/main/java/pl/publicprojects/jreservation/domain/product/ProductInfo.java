package pl.publicprojects.jreservation.domain.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.publicprojects.jreservation.domain.exception.exceptions.ProductNotAvailableException;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
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
    Cost cost;

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
            Cost cost,
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
            Cost cost,
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

    private boolean isAvailable(LocalDateTime dateNow) {
        return this.starts.isBefore(dateNow) && this.ends.isAfter(dateNow) && this.amount > 0;
    }

    public void reserve(LocalDateTime dateNow) {
        if(!this.isAvailable(dateNow)) throw new ProductNotAvailableException("Product isn't available!");
        this.amount = this.amount - 1;
    }
}
