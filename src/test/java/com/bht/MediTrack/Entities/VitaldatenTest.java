package com.bht.MediTrack.Entities;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class VitaldatenTest {

    //Vitaldaten vitaldaten;

    private static final String UUID_PATTERN = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    private static final Pattern UUID_REGEX = Pattern.compile(UUID_PATTERN);

    //TODO Vitaldaten von - bis PATTERNS

    /*
    @Before
    public void setUp() throws Exception {
        Vitaldaten vitaldaten = new Vitaldaten();
    }
*/
    @Test
    void testSetId(){
        Vitaldaten vitaldaten = new Vitaldaten();
        UUID testId = UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3");

        vitaldaten.setId(testId);
        assertEquals(testId, vitaldaten.getId(),"getter error");

        assertTrue(UUID_REGEX.matcher(vitaldaten.getId().toString()).matches(), "REGEX error");

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
        UUID id = UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3");
        Vitaldaten vitaldaten = new Vitaldaten(
                 id,
                (byte) 75,
                (byte) 16,
                (byte) 120,
                (byte) 80,
                (byte) 37,
                new Date()
        );

        String expected = "Vitaldaten{id='e58ed763-928c-4155-bee9-fdbaaadc15f3', herzfrequenz=75, atemfrequenz=16, systolisch=120, diastolisch=80, temperatur=37, datum=" + vitaldaten.getDatum() + "}";
        String actual = vitaldaten.toString();
        assertEquals(expected, actual);
    }
}