package pl.publicprojects.jreservation.infrastructure.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.publicprojects.jreservation.application.services.ReservationService;

import java.util.logging.Logger;

@Component
public class ExpireScheduler {

    private final ReservationService reservationService;

    public ExpireScheduler(
            ReservationService reservationService
    ) {
        this.reservationService = reservationService;
    }

    @Scheduled(fixedRate = 60_000)
    public void removeExpiredReservationScheduler() {
        this.reservationService.validateExpiredReservations();
    }
}
