package pl.publicprojects.jreservation.domain.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.publicprojects.jreservation.domain.exception.exceptions.ProductOutOfStockException;

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

    public boolean isAvailable() {
        LocalDateTime dateTime = LocalDateTime.now();
        return starts.isBefore(dateTime) && ends.isAfter(dateTime);
    }

    public void reserve() {
        if(this.amount <= 0) throw new ProductOutOfStockException("Product is currently out of stock!");
        this.amount = this.amount - 1;
    }
}
