package pl.publicprojects.jreservation.tests.domain;

import org.junit.jupiter.api.Test;

public class WalletTests {


    /**
     * Try to deduct too much money, wallet money amount shouldn't be less than 0
     */
    @Test
    public void deductTooManyFundsTest() {

    }

    /**
     * Try to deduct money with incorrect currency
     * */
    @Test
    public void deductIncorrectCurrencyTest() {}

    /**
     * Happy way of deduct funds (currency is correct and Wallet have proper amount of money)
     * */
    @Test
    public void deductFundsTest() {

    }
}
