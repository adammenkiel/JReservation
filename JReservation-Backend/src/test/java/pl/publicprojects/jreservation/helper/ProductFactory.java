package pl.publicprojects.jreservation.helper;

import pl.publicprojects.jreservation.domain.product.Cost;
import pl.publicprojects.jreservation.domain.product.ProductInfo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProductFactory {
    public static ProductInfo createProduct(
            String name,
            String shortDescription,
            String description,
            BigDecimal money,
            String currency,
            int amount,
            LocalDateTime start,
            LocalDateTime end
    ) {
        return new ProductInfo(
                UUID.randomUUID(),
                name,
                shortDescription,
                description,
                new Cost(money, currency),
                amount,
                start,
                end
        );
    }
}
