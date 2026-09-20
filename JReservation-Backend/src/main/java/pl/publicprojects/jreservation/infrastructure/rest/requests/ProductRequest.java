package pl.publicprojects.jreservation.infrastructure.rest.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductRequest {
    @NotBlank
    String productUuid;
}
