package pl.publicprojects.jreservation.infrastructure.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.publicprojects.jreservation.domain.payment.Wallet;
import pl.publicprojects.jreservation.domain.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> getWalletByWalletUuid(UUID walletId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT wallet FROM Wallet wallet WHERE wallet.user = :user AND wallet.currency = :currency")
    Optional<Wallet> getUserWalletWithLock(@Param("user") User user, @Param("currency") String currency);
}
