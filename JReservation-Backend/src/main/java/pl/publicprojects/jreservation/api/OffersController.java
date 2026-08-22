package pl.publicprojects.jreservation.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.publicprojects.jreservation.domain.reservation.ProductInfo;
import pl.publicprojects.jreservation.infrastructure.repositories.ProductRepository;

@RestController
@RequestMapping("/app")
public class OffersController {

    private final ProductRepository productRepository;

    public OffersController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/offers/{page}")
    public ResponseEntity<?> offers(int page) {
        return ResponseEntity.ok(
                productRepository.getProductsPage(PageRequest.of(page, 10))
        );
    }
}
