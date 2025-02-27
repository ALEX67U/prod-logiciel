package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.repositories;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.ParticipantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Classe de test pour le ParticipantRepository.
 *
 * Cette classe teste les méthodes du repository relatives aux participants.
 */
public class ParticipantRepositoryTest {

    @Mock
    private ParticipantRepository participantRepository; // Mock du repository pour les participants

    private Participant participant;

    /**
     * Méthode exécutée avant chaque test.
     * Elle initialise les mocks et crée un objet Participant pour les tests.
     */
    @BeforeEach
    void setUp() {
        // Initialisation de Mockito
        MockitoAnnotations.openMocks(this);

        // Création d'un participant avec des valeurs par défaut
        participant = new Participant();
        participant.setParticipantId(1L); // Définir un ID
        participant.setNom("Test Participant");
    }

    /**
     * Teste la méthode findById du ParticipantRepository.
     * Vérifie que la méthode renvoie un Optional vide si le participant n'est pas trouvé.
     */
    @Test
    void testFindByIdWhenParticipantNotFound() {
        // Configuration du mock pour renvoyer un participant vide lorsque l'ID ne correspond à aucun participant
        when(participantRepository.findById(2L)).thenReturn(Optional.empty());

        // Appel de la méthode dans le repository
        Optional<Participant> resultOptional = participantRepository.findById(2L);

        // Vérification que le participant n'est pas présent dans le résultat
        assertFalse(resultOptional.isPresent(), "Le participant ne doit pas être présent");
    }

    /**
     * Teste la méthode findById du ParticipantRepository.
     * Vérifie que la méthode renvoie le participant attendu lorsqu'il est trouvé.
     */
    @Test
    void testFindByIdWhenParticipantFound() {
        // Configuration du mock pour renvoyer le participant créé
        when(participantRepository.findById(1L)).thenReturn(Optional.of(participant));

        // Appel de la méthode dans le repository
        Optional<Participant> resultOptional = participantRepository.findById(1L);

        // Vérification que le participant est présent dans le résultat
        assertTrue(resultOptional.isPresent(), "Le participant doit être présent");

        // Vérification que le participant retourné est le bon
        assertEquals(participant, resultOptional.get(), "Le participant retourné ne correspond pas à celui attendu");
    }
}
