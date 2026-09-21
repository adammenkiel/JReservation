package pl.publicprojects.jreservation.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.publicprojects.jreservation.domain.exception.exceptions.ReservationNotExistsException;
import pl.publicprojects.jreservation.domain.payment.Wallet;
import pl.publicprojects.jreservation.domain.product.ProductInfo;
import pl.publicprojects.jreservation.domain.reservation.Reservation;
import pl.publicprojects.jreservation.domain.user.User;
import pl.publicprojects.jreservation.infrastructure.time.TimeManager;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class PaymentService {

    private final TimeManager timeManager;
    private final UserService userService;
    private final ProductService productService;
    private final ReservationService reservationService;
    private final WalletService walletService;

    public PaymentService(
            TimeManager timeManager,
            UserService userService,
            ProductService productService,
            WalletService walletService,
            ReservationService reservationService
    ) {
        this.timeManager = timeManager;
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
                .orElseThrow(() -> new ReservationNotExistsException("This reservation is expired or invalid!"));

        if(reservation.isExpired(LocalDateTime.ofInstant(this.timeManager.now(), ZoneId.systemDefault()))) {
            throw new ReservationNotExistsException("Reservation is expired!");
        }

        Wallet wallet = this.walletService.getUserWalletWithLock(user, productInfo.getCost().getCurrency());
        wallet.deductFunds(productInfo.getCost());

        this.reservationService.removeReservation(reservation);
        this.walletService.save(wallet);
    }
}
