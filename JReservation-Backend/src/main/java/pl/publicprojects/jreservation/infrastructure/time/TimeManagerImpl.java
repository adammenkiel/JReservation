package pl.publicprojects.jreservation.infrastructure.time;

import java.time.Instant;
import java.time.temporal.TemporalAccessor;

public class TimeManagerImpl implements TimeManager {
    @Override
    public Instant now() {
        return Instant.now();
    }

    @Override
    public Instant from(TemporalAccessor accessor) {
        return Instant.from(accessor);
    }
}
