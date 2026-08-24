package pl.publicprojects.jreservation.infrastructure.rest.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.publicprojects.jreservation.infrastructure.repositories.ProductRepository;

@RestController
@RequestMapping("/app")
public class OffersController {

    private final ProductRepository productRepository;

    public OffersController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/offers/{page}")
    public ResponseEntity<?> offers(@PathVariable int page) {
        return ResponseEntity.ok(
                productRepository.getProductsPage(PageRequest.of(page, 10))
        );
    }
}
