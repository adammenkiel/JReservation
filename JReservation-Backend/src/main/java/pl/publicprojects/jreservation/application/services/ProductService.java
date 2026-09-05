package pl.publicprojects.jreservation.application.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.publicprojects.jreservation.domain.product.ProductInfo;
import pl.publicprojects.jreservation.infrastructure.repositories.ProductRepository;
import pl.publicprojects.jreservation.infrastructure.time.TimeManager;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final TimeManager timeManager;

    public ProductService(
            ProductRepository productRepository,
            TimeManager timeManager
    ) {
        this.productRepository = productRepository;
        this.timeManager = timeManager;
    }

    public List<ProductInfo> getAvailableProductsPage(Pageable pageable) {
        return this.productRepository.getAvailableProductsPage(
                LocalDateTime.ofInstant(this.timeManager.now(), ZoneId.systemDefault()),
                pageable
        );
    }
}
