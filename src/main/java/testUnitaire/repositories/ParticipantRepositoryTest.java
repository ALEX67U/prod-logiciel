package testUnitaire.repositories;


import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.ParticipantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParticipantRepositoryTest {

    @Mock
    private ParticipantRepository participantRepository; // Mock du repository

    @InjectMocks
    private ParticipantRepositoryTest participantRepositoryTest; // Injecter le mock

    private Participant participant;

    @BeforeEach
    void setUp() {
        // Initialisation des objets
        MockitoAnnotations.openMocks(this);

        participant = new Participant();
        participant.setParticipantId(1L); // Définir un ID
        participant.setNom("Test Participant");
    }

    @Test
    void testFindById() {
        // Configuration du mock pour renvoyer un participant spécifique
        when(participantRepository.findById(1L)).thenReturn(java.util.Optional.of(participant));

        // Appel de la méthode du repository
        Participant result = participantRepository.findById(1L).orElse(null);

        // Vérification des résultats
        assertNotNull(result, "Le participant ne doit pas être null");
        assertEquals(1L, result.getParticipantId(), "L'ID du participant ne correspond pas");
        assertEquals("Test Participant", result.getNom(), "Le nom du participant ne correspond pas");
    }
}
