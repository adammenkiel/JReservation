package pl.publicprojects.jreservation.infrastructure.rest.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.UUID;
@Getter
public class PaymentRequest {
    @NotBlank(message = "Product UUID cannot be blank!")
    UUID productUUID;
}
