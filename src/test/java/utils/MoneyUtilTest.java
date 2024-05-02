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

    @Test
    void round(){
        assertEquals(5.49, MoneyUtil.round(5.4925467,2));
        assertEquals(0, MoneyUtil.round(0,2));
        assertEquals(123.235, MoneyUtil.round(123.2348456734513,3));
        assertEquals(123.234234543595, MoneyUtil.round(123.2342345435952314353656435745674535435345,12));
        assertEquals(123.2342345435952314353656435745674535435345, MoneyUtil.round(123.2342345435952314353656435745674535435345,0));
        assertEquals(-123.234, MoneyUtil.round(-123.2342345435952314353656435745674535435345,3));
    }
}