package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberGameTest {
    NumberGame numberGame;


    @BeforeEach
    void setUp(){
        numberGame = new NumberGame();
    }@Test
    void start() {
        numberGame.setNumber(3);
        String[] strArr = new String[]{"1","2","fish"};
        assertEquals(3, numberGame.start().length);
        assertArrayEquals(strArr, numberGame.start());
        numberGame.setNumber(5);
        String[] strArr1 = new String[]{"1","2","fish","4","bus"};
        assertArrayEquals(strArr1, numberGame.start());
        numberGame.setNumber(15);
        String[] strArr2 = new String[]{"1","2","fish","4","bus","fish","7","8","fish","bus","11","fish","13","14","fish-bus"};
        assertArrayEquals(strArr2, numberGame.start());
        numberGame.setNumber(100);

        assertEquals("fish", numberGame.start()[98]);
        assertEquals("fish-bus", numberGame.start()[44]);
    }
}