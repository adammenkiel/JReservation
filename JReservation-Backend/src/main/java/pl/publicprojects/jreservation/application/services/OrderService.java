package pl.publicprojects.jreservation.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.publicprojects.jreservation.domain.product.ProductInfo;
import pl.publicprojects.jreservation.domain.user.User;

@Service
public class OrderService {
    @Transactional
    public void orderProduct(User user, ProductInfo product) {

    }
}
