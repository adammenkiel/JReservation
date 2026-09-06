package pl.publicprojects.jreservation.domain.product;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Embeddable
public class Cost {

    private BigDecimal cost;

    @NotBlank
    private String currency;

    protected Cost() {}

    public Cost(BigDecimal cost, String currency) {
        this.cost = cost;
        this.currency = currency;
    }
}
