package pl.publicprojects.jreservation.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.publicprojects.jreservation.domain.exception.exceptions.ReservationPendingException;
import pl.publicprojects.jreservation.domain.product.ProductInfo;
import pl.publicprojects.jreservation.domain.reservation.Reservation;
import pl.publicprojects.jreservation.domain.user.User;
import pl.publicprojects.jreservation.infrastructure.repositories.ReservationRepository;
import pl.publicprojects.jreservation.infrastructure.time.TimeManager;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {

    private final UserService userService;
    private final ProductService productService;
    private final ReservationRepository reservationRepository;
    private final TimeManager timeManager;

    public ReservationService(
            UserService userService,
            ProductService productService,
            ReservationRepository reservationRepository,
            TimeManager timeManager
    ) {
        this.userService = userService;
        this.productService = productService;
        this.reservationRepository = reservationRepository;
        this.timeManager = timeManager;
    }

    public Optional<Reservation> getReservation(User user, ProductInfo productInfo) {
        return this.reservationRepository.getReservation(user, productInfo);
    }

    private void saveReservation(User user, ProductInfo product) {
        if(this.getReservation(user, product).isPresent()) {
            throw new ReservationPendingException("You already reserved that product before and you're in payment process!");
        }
        this.reservationRepository.save(new Reservation(user, product, this.timeManager.now()));
    }

    @Transactional
    public void reserveProduct(String nickname, UUID productId) {
        User user = (User) this.userService.loadUserByUsername(nickname);
        ProductInfo product = this.productService.getProductByUUIDWithLock(productId);
        product.reserve();
        this.saveReservation(user, product);
        this.productService.saveProduct(product);
    }

    public void removeReservation(Reservation reservation) {
        this.reservationRepository.delete(reservation);
    }
}
