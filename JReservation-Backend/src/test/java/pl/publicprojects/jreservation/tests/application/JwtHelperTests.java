package pl.publicprojects.jreservation.tests.application;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseCookie;
import pl.publicprojects.jreservation.application.helper.JwtHelper;
import pl.publicprojects.jreservation.domain.user.User;
import pl.publicprojects.jreservation.helper.FakeTimeManager;
import pl.publicprojects.jreservation.infrastructure.config.ConfigProperties;
import pl.publicprojects.jreservation.infrastructure.time.TimeManager;
import pl.publicprojects.jreservation.infrastructure.time.TimeManagerImpl;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class JwtHelperTests {

    @Test
    public void incorrectTokenValidateTest() {
        //Arrange
        TimeManager timeManager = new TimeManagerImpl();
        ConfigProperties configProperties = new ConfigProperties();
        JwtHelper jwtHelper = new JwtHelper(timeManager, configProperties);

        //Act
        boolean validationResult = jwtHelper.isValid("broken_token");

        //Assert
        assertFalse(validationResult);
    }

    @Test
    public void expiredTokenValidateTest() {
        //Arrange
        TimeManager fakeTimeManager = new FakeTimeManager(Instant.now().minusSeconds(100000000));
        JwtHelper oldTimeJwtHelper = new JwtHelper(fakeTimeManager, new ConfigProperties());
        oldTimeJwtHelper.init();

        User user = new User(
                "Testowy",
                "jakis@gmail.com",
                "haslo123",
                Date.from(fakeTimeManager.now())
        ); //TODO: Add user factory

        ResponseCookie tokenCookie = oldTimeJwtHelper.generateJwtCookieFromUser(user);
        JwtHelper normalJwtHelper = new JwtHelper(new TimeManagerImpl(), new ConfigProperties());

        //Act
        boolean validationResult = normalJwtHelper.isValid(tokenCookie.getValue());

        //Assert
        assertFalse(validationResult);
    }
}
