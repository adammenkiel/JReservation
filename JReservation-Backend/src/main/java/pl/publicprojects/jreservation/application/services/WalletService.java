package pl.publicprojects.jreservation.application.services;

import org.springframework.stereotype.Service;
import pl.publicprojects.jreservation.domain.exception.exceptions.WalletNotFoundException;
import pl.publicprojects.jreservation.domain.payment.Wallet;
import pl.publicprojects.jreservation.domain.user.User;
import pl.publicprojects.jreservation.infrastructure.repositories.WalletRepository;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(
            WalletRepository walletRepository
    ) {
        this.walletRepository = walletRepository;
    }

    public Wallet getUserWalletWithLock(User user, String currency) {
        return this.walletRepository.getUserWalletWithLock(user, currency)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
    }

    public void save(Wallet wallet) {
        this.walletRepository.save(wallet);
    }
}
