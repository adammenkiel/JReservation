package pl.publicprojects.jreservation.infrastructure.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.publicprojects.jreservation.domain.product.ProductInfo;
import pl.publicprojects.jreservation.domain.reservation.Reservation;
import pl.publicprojects.jreservation.domain.user.User;

import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    @Query("SELECT reservation FROM Reservation reservation " +
            "WHERE reservation.user == :user AND reservation.product == :product")
    Optional<Reservation> getReservation(
            @Param("user") User user,
            @Param("product") ProductInfo productInfo
    );
}
