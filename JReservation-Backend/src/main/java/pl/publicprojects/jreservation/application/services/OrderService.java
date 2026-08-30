package pl.publicprojects.jreservation.application.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.publicprojects.jreservation.infrastructure.repositories.ProductRepository;

@Service
public class OrderService {

    private final ProductRepository productRepository;

    public OrderService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
