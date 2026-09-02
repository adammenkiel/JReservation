package pl.publicprojects.jreservation.infrastructure.time;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.TemporalAccessor;

public interface TimeManager {
    Instant now();
    Instant from(TemporalAccessor accessor);
}
