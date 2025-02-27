package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.models;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Choix;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondee;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DateSondeeTest {

    private DateSondee dateSondee;
    private DateSondage dateSondage;
    private Participant participant;

    @BeforeEach
    void setUp() {
        // Creating mock objects for related entities
        dateSondage = mock(DateSondage.class);
        participant = mock(Participant.class);

        // Initialize DateSondee
        dateSondee = new DateSondee(1L, dateSondage, participant, Choix.DISPONIBLE);
    }

    @Test
    void testConstructorAndGetters() {
        // Verify the constructor and getters are working as expected
        assertNotNull(dateSondee);
        assertEquals(1L, dateSondee.getDateSondeeId());
        assertEquals(dateSondage, dateSondee.getDateSondage());
        assertEquals(participant, dateSondee.getParticipant());
        assertEquals("DISPONIBLE", dateSondee.getChoix()); // Choix is converted to String
    }

    @Test
    void testSetChoix() {
        // Test the setter and getter for 'choix'
        dateSondee.setChoix("PEUTETRE");
        assertEquals("PEUTETRE", dateSondee.getChoix());
    }

    @Test
    void testSetInvalidChoix() {
        // Test invalid choix value (should throw IllegalArgumentException)
        assertThrows(IllegalArgumentException.class, () -> {
            dateSondee.setChoix("INVALID");
        });
    }

    @Test
    void testSettersAndGetters() {
        // Test setters and getters
        dateSondee.setDateSondeeId(2L);
        dateSondee.setDateSondage(dateSondage);
        dateSondee.setParticipant(participant);
        dateSondee.setChoix("INDISPONIBLE");

        assertEquals(2L, dateSondee.getDateSondeeId());
        assertEquals(dateSondage, dateSondee.getDateSondage());
        assertEquals(participant, dateSondee.getParticipant());
        assertEquals("INDISPONIBLE", dateSondee.getChoix());
    }
}
