package com.bht.MediTrack.Entities;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class VitaldatenTest {

    Vitaldaten vitaldaten;

    private static String PATTERN = "^[0-9a-fA-F]{4}-[0-9a-fA-F]{5}$";
    private static Pattern REGEX = Pattern.compile(PATTERN);

    /*
    @Before
    public void setUp() throws Exception {
        Vitaldaten vitaldaten = new Vitaldaten();
    }
*/
    @Test
    void testSetId(){
        Vitaldaten vitaldaten = new Vitaldaten();
        String testid = "2024-12e4a";

        vitaldaten.setId(testid);
        assertEquals(testid, vitaldaten.getId(),"getter error");

        assertTrue(REGEX.matcher(vitaldaten.getId()).matches(), "REGEX error");

    }

    @Test
    void testSetHerzfrequenz(){
        Vitaldaten vitaldaten = new Vitaldaten();

           byte expectedHerzfrequenz = 75;
           vitaldaten.setHerzfrequenz(expectedHerzfrequenz);

            byte actualHerzfrequenz = vitaldaten.getHerzfrequenz();
            assertEquals(expectedHerzfrequenz, actualHerzfrequenz, "set/get error");



    }

    @Test
    void testToString() {
        Vitaldaten vitaldaten = new Vitaldaten(
                "12345",
                (byte) 75,
                (byte) 16,
                (byte) 120,
                (byte) 80,
                (byte) 37,
                new Date()
        );

        String expected = "Vitaldaten{id='12345', herzfrequenz=75, atemfrequenz=16, systolisch=120, diastolisch=80, temperatur=37, datum=" + vitaldaten.getDatum() + "}";
        String actual = vitaldaten.toString();
        assertEquals(expected, actual);
    }
}