package testUnitaire.models;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Commentaire;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParticipantTest {

    private Participant participant;
    private Commentaire mockCommentaire;
    private Sondage mockSondage;
    private DateSondee mockDateSondee;

    @BeforeEach
    void setUp() {
        // Create mock objects for related entities with meaningful names
        mockCommentaire = mock(Commentaire.class);
        mockSondage = mock(Sondage.class);
        mockDateSondee = mock(DateSondee.class);

        // Initialize Participant with related objects
        participant = new Participant(1L, "John", "Doe");
        participant.setCommentaire(List.of(mockCommentaire));
        participant.setSondages(List.of(mockSondage));
        participant.setDateSondee(List.of(mockDateSondee));
    }

    @Test
    void testConstructorAndGetters() {
        // Verify that the constructor and getter methods work correctly
        assertAll("Test constructor and getters",
                () -> assertNotNull(participant),
                () -> assertEquals(1L, participant.getParticipantId()),
                () -> assertEquals("John", participant.getNom()),
                () -> assertEquals("Doe", participant.getPrenom()),
                () -> assertEquals(1, participant.getCommentaire().size()),
                () -> assertEquals(1, participant.getSondages().size()),
                () -> assertEquals(1, participant.getDateSondee().size())
        );
    }

    @Test
    void testEqualsAndHashCode() {
        // Create two participants with the same values
        Participant participant1 = new Participant(1L, "John", "Doe");
        Participant participant2 = new Participant(1L, "John", "Doe");

        // Test equality
        assertEquals(participant1, participant2, "Participants should be equal");

        // Test hashCode consistency
        assertEquals(participant1.hashCode(), participant2.hashCode(), "Hash codes should match for equal participants");
    }

    @Test
    void testToString() {
        // Test the toString method for expected output
        String expectedToString = "Participant{participantId=1, nom='John', prenom='Doe'}";
        assertEquals(expectedToString, participant.toString(), "toString output should match");
    }
}
