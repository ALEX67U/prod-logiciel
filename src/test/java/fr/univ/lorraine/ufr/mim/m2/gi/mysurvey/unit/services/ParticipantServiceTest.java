package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.ParticipantRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

/**
 * Classe de test pour le ParticipantService.
 *
 * Cette classe teste les fonctionnalités de gestion des participants.
 */
public class ParticipantServiceTest {

    @Mock
    private ParticipantRepository repository; // Mock du repository pour les participants

    @InjectMocks
    private ParticipantService participantService; // Service à tester

    /**
     * Méthode exécutée avant chaque test.
     * Elle initialise les mocks nécessaires aux tests.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    /**
     * Teste la méthode getById du ParticipantService.
     * Vérifie que le service récupère correctement un participant par son ID.
     */
    @Test
    void testGetById() {
        // Données de test
        Long participantId = 1L;
        Participant participant = new Participant();
        participant.setParticipantId(participantId);
        when(repository.getById(participantId)).thenReturn(participant);

        // Appel de la méthode
        Participant result = participantService.getById(participantId);

        // Vérifications des résultats
        assertNotNull(result, "Le participant ne doit pas être null");
        assertEquals(participantId, result.getParticipantId(), "L'ID du participant ne correspond pas");
    }

    /**
     * Teste la méthode getAll du ParticipantService.
     * Vérifie que le service récupère correctement tous les participants.
     */
    @Test
    void testGetAll() {
        // Données de test
        List<Participant> participants = List.of(new Participant(), new Participant());
        when(repository.findAll()).thenReturn(participants);

        // Appel de la méthode
        List<Participant> result = participantService.getAll();

        // Vérifications des résultats
        assertNotNull(result, "La liste des participants ne doit pas être null");
        assertEquals(2, result.size(), "La taille de la liste des participants doit être 2");
    }

    /**
     * Teste la méthode create du ParticipantService.
     * Vérifie que le service crée correctement un participant.
     */
    @Test
    void testCreate() {
        // Données de test
        Participant participant = new Participant();
        when(repository.save(participant)).thenReturn(participant);

        // Appel de la méthode
        Participant result = participantService.create(participant);

        // Vérifications des résultats
        assertNotNull(result, "Le participant créé ne doit pas être null");
        verify(repository).save(participant); // Vérifier que save() a bien été appelé
    }

    /**
     * Teste la méthode update du ParticipantService.
     * Vérifie que le service met à jour correctement un participant existant.
     */
    @Test
    void testUpdate() {
        // Données de test
        Long participantId = 1L;
        Participant participantToUpdate = new Participant();
        participantToUpdate.setParticipantId(participantId);
        when(repository.findById(participantId)).thenReturn(Optional.of(new Participant()));
        when(repository.save(participantToUpdate)).thenReturn(participantToUpdate);

        // Appel de la méthode
        Participant result = participantService.update(participantId, participantToUpdate);

        // Vérifications des résultats
        assertNotNull(result, "Le participant mis à jour ne doit pas être null");
        assertEquals(participantId, result.getParticipantId(), "L'ID du participant mis à jour ne correspond pas");
        verify(repository).save(participantToUpdate); // Vérifier que save() a bien été appelé
    }

    /**
     * Teste la méthode update du ParticipantService.
     * Vérifie que la mise à jour échoue lorsque le participant n'existe pas.
     */
    @Test
    void testUpdateNotFound() {
        // Données de test
        Long participantId = 1L;
        Participant participantToUpdate = new Participant();
        when(repository.findById(participantId)).thenReturn(Optional.empty());

        // Appel de la méthode
        Participant result = participantService.update(participantId, participantToUpdate);

        // Vérifications des résultats
        assertNull(result, "Le résultat doit être null si le participant n'existe pas");
        verify(repository, never()).save(participantToUpdate); // save() ne doit pas être appelé
    }

    /**
     * Teste la méthode delete du ParticipantService.
     * Vérifie que le service supprime correctement un participant existant.
     */
    @Test
    void testDelete() {
        // Données de test
        Long participantId = 1L;
        when(repository.findById(participantId)).thenReturn(Optional.of(new Participant()));

        // Appel de la méthode
        int result = participantService.delete(participantId);

        // Vérifications des résultats
        assertEquals(1, result, "La suppression doit renvoyer 1 si le participant a été supprimé");
        verify(repository).deleteById(participantId); // Vérifier que deleteById() a bien été appelé
    }

    /**
     * Teste la méthode delete du ParticipantService.
     * Vérifie que la suppression échoue lorsque le participant n'existe pas.
     */
    @Test
    void testDeleteNotFound() {
        // Données de test
        Long participantId = 1L;
        when(repository.findById(participantId)).thenReturn(Optional.empty());

        // Appel de la méthode
        int result = participantService.delete(participantId);

        // Vérifications des résultats
        assertEquals(0, result, "La suppression doit renvoyer 0 si le participant n'est pas trouvé");
        verify(repository, never()).deleteById(participantId); // deleteById() ne doit pas être appelé
    }
}
