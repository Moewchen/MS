package com.bht.MediTrack.Entities;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class VitaldatenTest {

    //Vitaldaten vitaldaten;

    private static final String UUID_PATTERN = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    private static final Pattern UUID_REGEX = Pattern.compile(UUID_PATTERN);

    private static final String HERZ_PATTERN = "^(0|[1-9][0-9]?|1[0-9]{2}|2[0-1][0-9]|220)$";
    private static final Pattern HERZ_REGEX = Pattern.compile(HERZ_PATTERN);

    private static final String ATEM_PATTERN = "^(0|[1-9][0-9]?|70)$";
    private static final Pattern ATEM_REGEX = Pattern.compile(ATEM_PATTERN);

    private static final String SYS_PATTERN = "^(0|[1-9][0-9]?|1[0-9]{2}|2[0-9]{2}|300)$";
    private static final Pattern SYS_REGEX = Pattern.compile(SYS_PATTERN);

    private static final String DIAS_PATTERN = "^(0|[1-9][0-9]?|1[0-6][0-9]|170)$";
    private static final Pattern DIAS_REGEX = Pattern.compile(DIAS_PATTERN);

    private static final String TEMP_PATTERN = "^(0|[1-3][0-9]?|4[0-5])(\\.\\d)?$";
    private static final Pattern TEMP_REGEX = Pattern.compile(TEMP_PATTERN);
    //TODO Vitaldaten von - bis PATTERNS

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
        // max 220
        // min 0
        short expectedHerzfrequenz = 220;
        vitaldaten.setHerzfrequenz(expectedHerzfrequenz);

        short actualHerzfrequenz = vitaldaten.getHerzfrequenz();
        assertEquals(expectedHerzfrequenz, actualHerzfrequenz, "set/get error");

        assertTrue(HERZ_REGEX.matcher(String.valueOf(vitaldaten.getHerzfrequenz())).matches(), "REGEX error");

    }

    @Test
    void testSetAtemfrequenz(){
        Vitaldaten vitaldaten = new Vitaldaten();
        // max 70
        // min 0
        byte expectedAtemfrequenz = 15;
        vitaldaten.setAtemfrequenz(expectedAtemfrequenz);

        byte actualAtemfrequenz = vitaldaten.getAtemfrequenz();
        assertEquals(expectedAtemfrequenz, actualAtemfrequenz, "set/get error");

        assertTrue(ATEM_REGEX.matcher(String.valueOf(vitaldaten.getAtemfrequenz())).matches(), "REGEX error");
    }

    @Test
    void testSetSystolisch(){
        Vitaldaten vitaldaten = new Vitaldaten();
        // max 300
        // min 0
        short expectedSystolisch = 120;
        vitaldaten.setSystolisch(expectedSystolisch);

        short actualSystolisch = vitaldaten.getSystolisch();
        assertEquals(expectedSystolisch, actualSystolisch, "set/get error");

        assertTrue(SYS_REGEX.matcher(String.valueOf(vitaldaten.getSystolisch())).matches(), "REGEX error");

    }

    @Test
    void testSetDiastolisch(){
        Vitaldaten vitaldaten = new Vitaldaten();
        // max 170
        // min 0
        short expectedDiastolisch = 80;
        vitaldaten.setDiastolisch(expectedDiastolisch);

        short actualDiastolisch = vitaldaten.getDiastolisch();
        assertEquals(expectedDiastolisch, actualDiastolisch, "set/get error");

        assertTrue(DIAS_REGEX.matcher(String.valueOf(vitaldaten.getDiastolisch())).matches(), "REGEX error");

    }

    @Test
    void testSetTemperatur(){
        Vitaldaten vitaldaten = new Vitaldaten();
        // max 45.9f
        // min 0
        float expectedTemperatur = 36.9f;
        vitaldaten.setTemperatur(expectedTemperatur);

        float actualTemperatur = vitaldaten.getTemperatur();
        assertEquals(expectedTemperatur, actualTemperatur, "set/get error");

        assertTrue(TEMP_REGEX.matcher(String.valueOf(vitaldaten.getTemperatur())).matches(), "REGEX error");

    }

    @Test
    void testSetDatum(){
        Vitaldaten vitaldaten = new Vitaldaten();
        Date testDate = new Date();

        vitaldaten.setDatum(testDate);

        Date actualDate = vitaldaten.getDatum();

        assertEquals(testDate, actualDate, "set/get error");
    }

    // Hier Ideen von LLM huggingface

    /*
    Test Set Datum with Null:

    Set the Date to Null: Set the date to null using the setDatum method.
    Get the Date: Retrieve the date using the getDatum method.
    Assert Null: Use assertNull to assert that the date is null.
     */
    @Test
    void testSetDatumWithNull() {
        // Create an instance of Vitaldaten
        Vitaldaten vitaldaten = new Vitaldaten();

        // Set the Date to null
        vitaldaten.setDatum(null);

        // Get the Date Using GetDate
        Date actualDate = vitaldaten.getDatum();

        // Assert that the date is null
        assertNull(actualDate, "The date should be null");
    }

    /*
    Test Set Datum with Different Dates:

    Create Two Different Dates: Create two different Date objects.
    Set and Get the First Date: Set the first date and assert it is set correctly.
    Set and Get the Second Date: Set the second date and assert it is set correctly.
     */

    @Test
    void testSetDatumWithDifferentDates() {
        // Create an instance of Vitaldaten
        Vitaldaten vitaldaten = new Vitaldaten();

        // Create two different Date objects
        Date testDate1 = new Date();
        Date testDate2 = new Date(testDate1.getTime() + 1000); // 1 second later

        // Set the first Date
        vitaldaten.setDatum(testDate1);

        // Get the Date and assert it is the first date
        Date actualDate1 = vitaldaten.getDatum();
        assertEquals(testDate1, actualDate1, "The first date should be set correctly");

        // Set the second Date
        vitaldaten.setDatum(testDate2);

        // Get the Date and assert it is the second date
        Date actualDate2 = vitaldaten.getDatum();
        assertEquals(testDate2, actualDate2, "The second date should be set correctly");
    }

    @Test
    void testToString() {
        UUID id = UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3");
        Vitaldaten vitaldaten = new Vitaldaten(
                 id,
                (short) 75,
                (byte) 16,
                (short) 120,
                (short) 80,
                (float) 37,
                new Date()
        );

        String expected = "Vitaldaten{id='e58ed763-928c-4155-bee9-fdbaaadc15f3', herzfrequenz=75, atemfrequenz=16, systolisch=120, diastolisch=80, temperatur=37.0, datum=" + vitaldaten.getDatum() + "}";
        String actual = vitaldaten.toString();
        assertEquals(expected, actual);
    }
}