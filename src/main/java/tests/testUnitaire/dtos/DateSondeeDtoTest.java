package tests.testUnitaire.dtos;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.DateSondeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateSondeeDtoTest {

    private DateSondeeDto dateSondeeDto1;
    private DateSondeeDto dateSondeeDto2;

    @BeforeEach
    void setUp() {
        dateSondeeDto1 = new DateSondeeDto();
        dateSondeeDto1.setDateSondeeId(1L);
        dateSondeeDto1.setParticipant(100L);
        dateSondeeDto1.setChoix("Oui");

        dateSondeeDto2 = new DateSondeeDto();
        dateSondeeDto2.setDateSondeeId(1L);
        dateSondeeDto2.setParticipant(100L);
        dateSondeeDto2.setChoix("Oui");
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1L, dateSondeeDto1.getDateSondeeId());
        assertEquals(100L, dateSondeeDto1.getParticipant());
        assertEquals("Oui", dateSondeeDto1.getChoix());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(dateSondeeDto1, dateSondeeDto2);
        assertEquals(dateSondeeDto1.hashCode(), dateSondeeDto2.hashCode());
    }

    @Test
    void testNotEquals() {
        dateSondeeDto2.setChoix("Non");
        assertNotEquals(dateSondeeDto1, dateSondeeDto2);
    }
}
