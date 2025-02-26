package tests.testUnitaire.repositories;


import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.ParticipantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ParticipantRepositoryTest {

    @Mock
    private ParticipantRepository participantRepository; // Mock du repository


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
    void testFindByIdWhenParticipantNotFound() {
        // Configuration du mock pour renvoyer un participant vide
        when(participantRepository.findById(2L)).thenReturn(java.util.Optional.empty());

        // Appel de la méthode du repository
        Optional<Participant> resultOptional = participantRepository.findById(2L);

        // Vérification que le participant n'est pas présent
        assertFalse(resultOptional.isPresent(), "Le participant ne doit pas être présent");
    }

}
