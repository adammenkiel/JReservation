package pl.publicprojects.jreservation.infrastructure.services;

import org.springframework.stereotype.Component;
import pl.publicprojects.jreservation.infrastructure.repositories.ProductRepository;

@Component
public class OrderService {

    private final ProductRepository productRepository;

    public OrderService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
