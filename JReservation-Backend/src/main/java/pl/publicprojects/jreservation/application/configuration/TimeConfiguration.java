package pl.publicprojects.jreservation.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.publicprojects.jreservation.infrastructure.time.TimeManager;
import pl.publicprojects.jreservation.infrastructure.time.TimeManagerImpl;

@Configuration
public class TimeConfiguration {
    @Bean
    public TimeManager createTimeManager() {
        return new TimeManagerImpl();
    }
}
