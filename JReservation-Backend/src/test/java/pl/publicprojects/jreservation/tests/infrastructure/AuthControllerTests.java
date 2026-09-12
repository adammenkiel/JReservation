package pl.publicprojects.jreservation.tests.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.HashMap;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    //TODO: Test isn't good because it's important to correct Arrange section
    //TODO: User must exists before send request to /auth/login
    @Test
    public void loginTest() {
        //Arrange
        var bodyMap = new HashMap<>();
        bodyMap.put("username", "adammenkiel");
        bodyMap.put("password", "haslo");

        //Act
        List<String> responseCookies = this.restTemplate.postForEntity(
            "http://localhost:" + port + "/auth/login",
            bodyMap,
            String.class
        ).getHeaders().get("set-cookie");

        //Assert
        Assertions.assertNotNull(responseCookies);
        Assertions.assertFalse(responseCookies.isEmpty());
    }

    /**
     * Tests registering new user when username of user is very long
     */
    @Test
    public void registerWithTooLongUsernameTest() {}

    /**
     * Tests registering new user when password is very long
     */
    @Test
    public void registerWithTooLongPasswordTest() {}

    /**
     * Tests registering new user when mail have an incorrect format
     */
    @Test
    public void registerWithIncorrectMailTest() {}

    /**
     * Tests registering new user when username is empty
     */
    @Test
    public void registerWithEmptyUsernameTest() {}

    /**
     * Tests registering new user when mail is empty
     */
    @Test
    public void registerWithEmptyMailTest() {}

    /**
     * Tests registering new user when password is empty
     */
    @Test
    public void registerWithEmptyPasswordTest() {}

    /**
     * Tests registering new user when username is short
     */
    @Test
    public void registerWithShortUsernameTest() {}

    /**
     * Tests registering new user when password is short
     */
    @Test
    public void registerWithShortPasswordTest() {}

}
