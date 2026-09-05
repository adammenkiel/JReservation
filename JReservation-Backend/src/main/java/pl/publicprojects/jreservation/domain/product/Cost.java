package pl.publicprojects.jreservation.domain.product;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Embeddable
public class Cost {

    private int cost;

    @NotBlank
    private String currency;

    protected Cost() {}

    public Cost(int cost, String currency) {
        this.cost = cost;
        this.currency = currency;
    }
}
