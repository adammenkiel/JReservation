package pl.publicprojects.jreservation.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.publicprojects.jreservation.domain.exception.exceptions.ProductNotExistsException;
import pl.publicprojects.jreservation.domain.payment.Wallet;
import pl.publicprojects.jreservation.domain.product.Cost;
import pl.publicprojects.jreservation.domain.product.ProductInfo;
import pl.publicprojects.jreservation.domain.user.User;
import pl.publicprojects.jreservation.infrastructure.repositories.UserRepository;

import java.util.UUID;

@Service
public class OrderService {

    private final UserService userService;
    private final ProductService productService;

    public OrderService(
            UserService userService,
            ProductService productService
    ) {
        this.userService = userService;
        this.productService = productService;
    }

    //TODO: Function should be support pessimistic locking!!!
    @Transactional
    public void orderProduct(String username, UUID productId) {
        ProductInfo product = this.productService.getProductByUUID(productId);
        Cost cost = product.getCost();

        User user = (User) this.userService.loadUserByUsername(username);

        Wallet wallet = user.getWalletByCurrency(cost.getCurrency());
        wallet.deductFunds(cost);

        product.reserve();

        this.userService.saveUser(user);
        this.productService.saveProduct(product);
    }
}
