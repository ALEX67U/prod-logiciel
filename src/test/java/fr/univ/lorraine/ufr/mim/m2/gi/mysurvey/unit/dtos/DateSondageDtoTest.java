package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.dtos;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.DateSondageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateSondageDtoTest {

    private DateSondageDto dateSondageDto1;
    private DateSondageDto dateSondageDto2;
    private Date testDate1;
    private Date testDate2;

    @BeforeEach
    void setUp() {
        testDate1 = new Date();
        testDate2 = new Date(testDate1.getTime() + 1000); // Assurer une différence de date

        dateSondageDto1 = new DateSondageDto();
        dateSondageDto1.setDateSondageId(1L);
        dateSondageDto1.setDate(testDate1);

        dateSondageDto2 = new DateSondageDto();
        dateSondageDto2.setDateSondageId(1L);
        dateSondageDto2.setDate(testDate1);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1L, dateSondageDto1.getDateSondageId());
        assertEquals(testDate1, dateSondageDto1.getDate());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(dateSondageDto1, dateSondageDto2);
        assertEquals(dateSondageDto1.hashCode(), dateSondageDto2.hashCode());
    }

    @Test
    void testNotEquals() {
        dateSondageDto2.setDate(testDate2);
        assertNotEquals(dateSondageDto1, dateSondageDto2);
    }
}
