package pl.publicprojects.jreservation.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.publicprojects.jreservation.domain.exception.exceptions.ProductNotExistsException;
import pl.publicprojects.jreservation.domain.product.ProductInfo;
import pl.publicprojects.jreservation.domain.user.User;

import java.util.UUID;

@Service
public class OrderService {

    private final ProductService productService;

    public OrderService(
            ProductService productService
    ) {
        this.productService = productService;
    }

    @Transactional
    public void orderProduct(User user, ProductInfo product) {

    }

    public void orderProduct(User user, UUID productId) {
        this.orderProduct(user, this.productService.getProductByUUID(productId));
    }
}
