package pl.publicprojects.jreservation.tests.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.publicprojects.jreservation.domain.exception.exceptions.ProductNotAvailableException;
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
    private ProductInfo createSimpleProduct(int amount, int addFirstTimeValue, int addSecondTimeValue) {
        return ProductFactory.createProduct(
                "test",
                "",
                "",
                new BigDecimal("5.10"),
                "PLN",
                amount,
                LocalDateTime.ofInstant(Instant.now().plus(addFirstTimeValue, ChronoUnit.MINUTES), ZoneId.systemDefault()),
                LocalDateTime.ofInstant(Instant.now().plus(addSecondTimeValue, ChronoUnit.MINUTES), ZoneId.systemDefault())
        );
    }

    @Test
    public void productInfoReserveTest() {
        //Arrange
        ProductInfo info = this.createSimpleProduct(10, -10, 10);

        //Act & Assert
        Assertions.assertDoesNotThrow(() -> info.reserve(LocalDateTime.now()));
        Assertions.assertEquals(9, info.getAmount());
    }
    /**
     * Try to reserve product if amount is 0
     */
    @Test
    public void reserveTooMuchProductsTest() {
        //Arrange
        ProductInfo info = this.createSimpleProduct(0, -10, 10);
        //Act & Assert
        Assertions.assertThrows(ProductNotAvailableException.class, () -> info.reserve(LocalDateTime.now()));
    }

    /**
     * Try to reserve product when its unavailable because of date
     */
    @Test
    public void isUnavailableByDateTest() {
        //Arrange
        ProductInfo info = this.createSimpleProduct(10, -20, -10);

        //Act & Assert
        Assertions.assertThrows(ProductNotAvailableException.class, () -> info.reserve(LocalDateTime.now()));
    }

}
