package pl.publicprojects.jreservation.infrastructure.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.publicprojects.jreservation.domain.product.ProductInfo;
import pl.publicprojects.jreservation.domain.reservation.Reservation;
import pl.publicprojects.jreservation.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    @Query("SELECT reservation FROM Reservation reservation " +
            "WHERE reservation.user = :user AND reservation.productInfo = :product")
    Optional<Reservation> getReservation(
            @Param("user") User user,
            @Param("product") ProductInfo productInfo
    );

    @Modifying
    @Query(value =
        """
        WITH
                deleted AS (DELETE FROM reservation WHERE :time > reservation_start_time + INTERVAL '5 minutes' RETURNING product_id),
                amo AS (SELECT product_id, COUNT(*) AS cnt FROM deleted GROUP BY product_id)
        UPDATE products prod SET amount = prod.amount + amo.cnt FROM amo WHERE prod.product_id = amo.product_id
        """, nativeQuery = true)
    void deleteExpiredTransactions(@Param("time") LocalDateTime localDateTime);

    @Modifying
    @Query("DELETE FROM Reservation res WHERE res.uuid = :uuid")
    int deleteReservation(@Param("uuid") UUID uuid);
}
