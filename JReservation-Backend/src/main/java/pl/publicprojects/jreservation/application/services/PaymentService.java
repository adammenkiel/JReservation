package pl.publicprojects.jreservation.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.publicprojects.jreservation.domain.payment.Wallet;
import pl.publicprojects.jreservation.domain.product.ProductInfo;
import pl.publicprojects.jreservation.domain.reservation.Reservation;
import pl.publicprojects.jreservation.domain.user.User;

import java.util.UUID;

@Service
public class PaymentService {

    private final UserService userService;
    private final ProductService productService;
    private final ReservationService reservationService;
    private final WalletService walletService;

    public PaymentService(
            UserService userService,
            ProductService productService,
            WalletService walletService,
            ReservationService reservationService
    ) {
        this.userService = userService;
        this.productService = productService;
        this.walletService = walletService;
        this.reservationService = reservationService;
    }

    @Transactional
    public void buyProduct(String username, UUID productUuid) {
        User user = (User) this.userService.loadUserByUsername(username);
        ProductInfo productInfo = this.productService.getProductByUUID(productUuid);

        Reservation reservation = this.reservationService.getReservation(user, productInfo)
                .orElseThrow(); //TODO: correct - specific exception should be thrown

        Wallet wallet = this.walletService.getUserWalletWithLock(user, productInfo.getCost().getCurrency());
        wallet.deductFunds(productInfo.getCost());

        this.reservationService.removeReservation(reservation);
        this.walletService.save(wallet);
    }
}
