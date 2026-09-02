package pl.publicprojects.jreservation.helper;

import pl.publicprojects.jreservation.infrastructure.time.TimeManager;

import java.time.Instant;
import java.time.temporal.TemporalAccessor;


public class FakeTimeManager implements TimeManager {

    private final Instant timeNow;

    public FakeTimeManager(Instant timeNow) {
        this.timeNow = timeNow;
    }

    @Override
    public Instant now() {
        return this.timeNow;
    }

    @Override
    public Instant from(TemporalAccessor accessor) {
        return Instant.from(accessor);
    }
}
