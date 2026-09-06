package pl.publicprojects.jreservation.infrastructure.rest.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.publicprojects.jreservation.application.services.ProductService;

@RestController
@RequestMapping("/app")
public class OffersController {

    private final ProductService productService;

    public OffersController(
            ProductService productService
    ) {
        this.productService = productService;
    }

    @GetMapping("/offers/{page}")
    public ResponseEntity<?> offers(@PathVariable int page) {
        return ResponseEntity.ok(
                this.productService.getAvailableProductsPage(
                        PageRequest.of(page, 10)
                )
        );
    }
}
