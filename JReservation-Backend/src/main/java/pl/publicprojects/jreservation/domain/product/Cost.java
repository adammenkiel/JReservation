package pl.publicprojects.jreservation.domain.product;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Embeddable
public class Cost {

    @NotNull
    private BigDecimal cost;

    @NotNull
    @NotBlank
    private String currency;

    protected Cost() {}

    public Cost(BigDecimal cost, String currency) {
        this.cost = cost;
        this.currency = currency;
    }
}
