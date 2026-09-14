package pl.publicprojects.jreservation.tests.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.publicprojects.jreservation.domain.product.ProductInfo;
import pl.publicprojects.jreservation.helper.ProductFactory;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class ProductInfoTests {

    /**
     * The happy way of reserve product
     */
    @Test
    public void productInfoReserveTest() {
        //Arrange

        ProductInfo info = ProductFactory.createProduct(
                "test",
                "",
                "",
                new BigDecimal("5.10"),
                "PLN",
                10,
                LocalDateTime.ofInstant(Instant.now().minus(10, ChronoUnit.MINUTES), ZoneId.systemDefault()),
                LocalDateTime.ofInstant(Instant.now().plus(10, ChronoUnit.MINUTES), ZoneId.systemDefault())
        );

        //Act & Assert
        Assertions.assertDoesNotThrow(() -> info.reserve(LocalDateTime.now()));
    }
    /**
     * Try to reserve product if amount is 0
     */
    @Test
    public void reserveTooMuchProductsTest() {

    }

    /**
     * Happy way of is product available
     */
    @Test
    public void isProductAvailable() {

    }

    /**
     * Test if product is unavailable when date is incorrect
     */
    @Test
    public void isUnavailableByDateTest() {}

    /**
     * Test if product is unavailable when amount is 0
     */
    @Test
    public void isUnavailableByAmountTest() {}
}
