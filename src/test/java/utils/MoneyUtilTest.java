package utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyUtilTest {


    @Test
    void moneyTest() {
        String money = MoneyUtil.format(1000);
        assertEquals("$1000.00", money);
    }
    @Test
    void negativeMoneyTest() {
        String money = MoneyUtil.format(-1000.00);

        assertEquals("-$1000.00", money);
    }
    @Test
    void symbolMoneyTest() {
        String money = MoneyUtil.format(1000,"€");
        assertEquals("€1000.00", money);
    }
    @Test
    void negativeSymbolMoneyTest() {
        String money = MoneyUtil.format(-1000,"€");
        assertEquals("-€1000.00", money);
    }


    @Test
     void nullSymbolMoneyTest() {
       assertThrows(IllegalArgumentException.class, () -> MoneyUtil.format(-1000,null));

    }
}