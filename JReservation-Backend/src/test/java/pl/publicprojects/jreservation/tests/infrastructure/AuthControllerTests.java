package pl.publicprojects.jreservation.tests.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestConstructor;
import pl.publicprojects.jreservation.application.services.AuthService;
import pl.publicprojects.jreservation.infrastructure.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class AuthControllerTests {
    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate;
    private final AuthService authService;
    private final UserRepository userRepository;

    public AuthControllerTests(
            TestRestTemplate restTemplate,
            AuthService authService,
            UserRepository userRepository
    ) {
        this.restTemplate = restTemplate;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    private void unregisterByKeys(String username, String email) {
        var byUsername = this.userRepository.getUserByUsername(username);
        byUsername.ifPresent(this.authService::unregisterUser);
        var byEmail = this.userRepository.getUserByEmail(email);
        byEmail.ifPresent(this.authService::unregisterUser);
    }

    public void reregister(String username, String email, String password) {
        try {
            this.unregisterByKeys(username, email);
            this.authService.registerUser(username, email, password);
        } catch (Exception ignored) {}
    }

    @Test
    public void loginTest() {
        //Arrange
        String username = "adammenkiel";
        String email = "test@mail.com";
        String password = "haslo123";
        this.reregister(username, email, password);

        var bodyMap = new HashMap<>();
        bodyMap.put("username", username);
        bodyMap.put("password", password);
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
     * Tries to log in to data that does not exist
     */
    @Test
    public void loginUserNotExists() {
        //Arrange
        String username = "lolek123";
        String email = "test1234@mail.com";
        String password = "haslo123";
        this.unregisterByKeys(username, email);

        var bodyMap = new HashMap<>();
        bodyMap.put("username", username);
        bodyMap.put("password", password);

        //Act
        int statusCode = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/auth/login",
                bodyMap,
                String.class
        ).getStatusCode().value();

        //Assert
        Assertions.assertEquals(401, statusCode);
    }

    @Test
    public void loginWithIncorrectPassword() {
        //Arrange
        String username = "lolek123";
        String email = "test1234@mail.com";
        String password = "haslo123";
        String wrongPassword = "wrong_password";
        this.reregister(username, email, password);

        var bodyMap = new HashMap<>();
        bodyMap.put("username", username);
        bodyMap.put("password", wrongPassword);

        //Act
        int statusCode = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/auth/login",
                bodyMap,
                String.class
        ).getStatusCode().value();

        //Assert
        Assertions.assertEquals(401, statusCode);
    }

    /**
     * Tries to register to someone's username (if username is already exists)
     */
    @Test
    public void registerToExistUsernameData() {
        //Arrange
        String username = "lolek123";
        String email = "test1234@mail.com";
        String password = "haslo1234";
        String anotherEmail = "3kk4@gmail.com";
        String anotherPassword = "haslo123";
        this.reregister(username, email, password);

        var bodyMap = new HashMap<>();
        bodyMap.put("username", username);
        bodyMap.put("email", anotherEmail);
        bodyMap.put("password", anotherPassword);

        //Act
        int statusCode = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/auth/register",
                bodyMap,
                String.class
        ).getStatusCode().value();

        //Assert
        Assertions.assertEquals(401, statusCode);
    }

    /**
     * Tries to register to someone's email (if email is already exists)
     */
    @Test
    public void registerToExistEmailData() {
        //Arrange
        String username = "lolek123";
        String email = "test1234@mail.com";
        String password = "haslo12345";
        String anotherUsername = "lolek1234";
        String anotherPassword = "haslo123";
        this.reregister(username, email, password);

        var bodyMap = new HashMap<>();
        bodyMap.put("username", anotherUsername);
        bodyMap.put("email", email);
        bodyMap.put("password", anotherPassword);

        //Act
        int statusCode = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/auth/register",
                bodyMap,
                String.class
        ).getStatusCode().value();

        //Assert
        Assertions.assertEquals(401, statusCode);
    }
    /**
     * Tests registering new user when username of user is very long
     */
    @Test
    public void registerWithTooLongUsernameTest() {
        //Arrange
        String username = "gdjkgkjfldjglksdjlgjdlgkjdfsgggdfggfddfgdfgdgf";
        String email = "test@mail.com";
        String password = "haslo12345";

        this.unregisterByKeys(username, email);

        var bodyMap = new HashMap<>();
        bodyMap.put("username", username);
        bodyMap.put("email", email);
        bodyMap.put("password", password);
        //Act
        int value = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/auth/register",
                bodyMap,
                String.class
        ).getStatusCode().value();

        //Assert
        Assertions.assertEquals(400, value);
    }

    /**
     * Tests registering new user when password is very long
     */
    @Test
    public void registerWithTooLongPasswordTest() {
        //Arrange
        String username = "tester2";
        String email = "test3123@mail.com";
        String password = "haslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslohaslo";

        this.unregisterByKeys(username, email);

        var bodyMap = new HashMap<>();
        bodyMap.put("username", username);
        bodyMap.put("email", email);
        bodyMap.put("password", password);
        //Act
        int value = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/auth/register",
                bodyMap,
                String.class
        ).getStatusCode().value();

        //Assert
        Assertions.assertEquals(400, value);
    }

    /**
     * Tests registering new user when mail have an incorrect format
     */
    @Test
    public void registerWithIncorrectMailTest() {
        //Arrange
        String username = "tester2";
        String email = "test3123@mail,com";
        String password = "haslo12345";

        this.unregisterByKeys(username, email);

        var bodyMap = new HashMap<>();
        bodyMap.put("username", username);
        bodyMap.put("email", email);
        bodyMap.put("password", password);
        //Act
        int value = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/auth/register",
                bodyMap,
                String.class
        ).getStatusCode().value();

        //Assert
        Assertions.assertEquals(400, value);
    }

    /**
     * Tests registering new user when username is empty
     */
    @Test
    public void registerWithEmptyUsernameTest() {
        //Arrange
        String username = "tester2";
        String email = "test3123@mail.com";
        String password = "haslo12345";

        this.unregisterByKeys(username, email);

        var bodyMap = new HashMap<>();
        bodyMap.put("email", email);
        bodyMap.put("password", password);
        //Act
        int value = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/auth/register",
                bodyMap,
                String.class
        ).getStatusCode().value();

        //Assert
        Assertions.assertEquals(400, value);
    }

    /**
     * Tests registering new user when mail is empty
     */
    @Test
    public void registerWithEmptyMailTest() {
        //Arrange
        String username = "tester2";
        String email = "test3123@mail.com";
        String password = "haslo12345";

        this.unregisterByKeys(username, email);

        var bodyMap = new HashMap<>();
        bodyMap.put("username", username);
        bodyMap.put("password", password);
        //Act
        int value = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/auth/register",
                bodyMap,
                String.class
        ).getStatusCode().value();

        //Assert
        Assertions.assertEquals(400, value);
    }

    /**
     * Tests registering new user when password is empty
     */
    @Test
    public void registerWithEmptyPasswordTest() {
        //Arrange
        String username = "tester2";
        String email = "test3123@mail.com";
        String password = "haslo12345";

        this.unregisterByKeys(username, email);

        var bodyMap = new HashMap<>();
        bodyMap.put("username", username);
        bodyMap.put("email", email);
        //Act
        int value = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/auth/register",
                bodyMap,
                String.class
        ).getStatusCode().value();

        //Assert
        Assertions.assertEquals(400, value);
    }

    /**
     * Tests registering new user when username is short
     */
    @Test
    public void registerWithShortUsernameTest() {
        //Arrange
        String username = "t";
        String email = "test3123@mail.com";
        String password = "haslo12345";

        this.unregisterByKeys(username, email);

        var bodyMap = new HashMap<>();
        bodyMap.put("username", username);
        bodyMap.put("email", email);
        bodyMap.put("password", password);
        //Act
        int value = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/auth/register",
                bodyMap,
                String.class
        ).getStatusCode().value();

        //Assert
        Assertions.assertEquals(400, value);
    }

    /**
     * Tests registering new user when password is short
     */
    @Test
    public void registerWithShortPasswordTest() {
        //Arrange
        String username = "trsfd";
        String email = "test3123@mail.com";
        String password = "haslo";

        this.unregisterByKeys(username, email);

        var bodyMap = new HashMap<>();
        bodyMap.put("username", username);
        bodyMap.put("email", email);
        bodyMap.put("password", password);
        //Act
        int value = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/auth/register",
                bodyMap,
                String.class
        ).getStatusCode().value();

        //Assert
        Assertions.assertEquals(400, value);
    }

    /**
     * Tests if incorrect symbols will be accepted
     */
    @Test
    public void registerWithIncorrectSymbolsTest() {}
}
