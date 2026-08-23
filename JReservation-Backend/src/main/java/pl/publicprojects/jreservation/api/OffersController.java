package pl.publicprojects.jreservation.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.publicprojects.jreservation.domain.reservation.ProductInfo;
import pl.publicprojects.jreservation.infrastructure.repositories.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
