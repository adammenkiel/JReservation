package pl.publicprojects.jreservation.infrastructure.time;

import java.time.Instant;
import java.time.temporal.TemporalAccessor;

public interface TimeManager {
    Instant now();
    Instant from(TemporalAccessor accessor);
}
