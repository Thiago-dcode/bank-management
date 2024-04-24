package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {


    @Test
    void getCurrentDate() {

        String date = DateUtils.getCurrentDate();
        assertEquals("24/04/2024",date);
        String date1 = DateUtils.getCurrentDate("dd/MM/yyyy HH:mm:ss");
        System.out.println(date1);
    }
}