package pl.publicprojects.jreservation.infrastructure.rest.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.publicprojects.jreservation.infrastructure.repositories.ProductRepository;
import pl.publicprojects.jreservation.infrastructure.time.TimeManager;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/app")
public class OffersController {

    private final ProductRepository productRepository;
    private final TimeManager timeManager;

    public OffersController(
            ProductRepository productRepository,
            TimeManager timeManager
            ) {
        this.productRepository = productRepository;
        this.timeManager = timeManager;
    }

    @GetMapping("/offers/{page}")
    public ResponseEntity<?> offers(@PathVariable int page) {
        return ResponseEntity.ok(
                this.productRepository.getAvailableProductsPage(
                        LocalDateTime.ofInstant(this.timeManager.now(), ZoneId.systemDefault()),
                        PageRequest.of(page, 10)
                )
        );
    }
}
