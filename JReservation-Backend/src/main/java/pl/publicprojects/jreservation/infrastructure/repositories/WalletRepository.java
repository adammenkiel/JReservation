package pl.publicprojects.jreservation.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.publicprojects.jreservation.domain.payment.Wallet;
import pl.publicprojects.jreservation.domain.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> getWalletByWalletUuid(UUID walletId);
}
