package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.SondageRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.ParticipantService;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.SondageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

/**
 * Classe de test pour le SondageService.
 *
 * Cette classe teste les fonctionnalités de gestion des sondages.
 */
public class SondageServiceTest {

    @Mock
    private SondageRepository repository; // Mock du repository pour les sondages

    @Mock
    private ParticipantService participantService; // Mock du service des participants

    @InjectMocks
    private SondageService sondageService; // Service à tester

    /**
     * Méthode exécutée avant chaque test.
     * Elle initialise les mocks nécessaires aux tests.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    /**
     * Teste la méthode getById du SondageService.
     * Vérifie que le service récupère correctement un sondage par son ID.
     */
    @Test
    void testGetById() {
        // Données de test
        Long sondageId = 1L;
        Sondage sondage = new Sondage();
        sondage.setSondageId(sondageId);
        when(repository.getById(sondageId)).thenReturn(sondage);

        // Appel de la méthode
        Sondage result = sondageService.getById(sondageId);

        // Vérifications des résultats
        assertNotNull(result, "Le sondage ne doit pas être null");
        assertEquals(sondageId, result.getSondageId(), "L'ID du sondage ne correspond pas");
    }

    /**
     * Teste la méthode getAll du SondageService.
     * Vérifie que le service récupère correctement tous les sondages.
     */
    @Test
    void testGetAll() {
        // Données de test
        List<Sondage> sondages = List.of(new Sondage(), new Sondage());
        when(repository.findAll()).thenReturn(sondages);

        // Appel de la méthode
        List<Sondage> result = sondageService.getAll();

        // Vérifications des résultats
        assertNotNull(result, "La liste des sondages ne doit pas être null");
        assertEquals(2, result.size(), "La taille de la liste des sondages doit être 2");
    }

    /**
     * Teste la méthode create du SondageService.
     * Vérifie que le service crée correctement un sondage pour un participant existant.
     */
    @Test
    void testCreate() {
        // Données de test
        Long participantId = 1L;
        Sondage sondage = new Sondage();
        when(participantService.getById(participantId)).thenReturn(new Participant());
        when(repository.save(sondage)).thenReturn(sondage);

        // Appel de la méthode
        Sondage result = sondageService.create(participantId, sondage);

        // Vérifications des résultats
        assertNotNull(result, "Le sondage créé ne doit pas être null");
        verify(repository).save(sondage); // Vérifier que save() a bien été appelé
    }

    /**
     * Teste la méthode update du SondageService.
     * Vérifie que le service met à jour correctement un sondage existant.
     */
    @Test
    void testUpdate() {
        // Données de test
        Long sondageId = 1L;
        Sondage sondageToUpdate = new Sondage();
        sondageToUpdate.setSondageId(sondageId);
        when(repository.findById(sondageId)).thenReturn(Optional.of(new Sondage()));
        when(repository.save(sondageToUpdate)).thenReturn(sondageToUpdate);

        // Appel de la méthode
        Sondage result = sondageService.update(sondageId, sondageToUpdate);

        // Vérifications des résultats
        assertNotNull(result, "Le sondage mis à jour ne doit pas être null");
        assertEquals(sondageId, result.getSondageId(), "L'ID du sondage mis à jour ne correspond pas");
        verify(repository).save(sondageToUpdate); // Vérifier que save() a bien été appelé
    }

    /**
     * Teste la méthode update du SondageService.
     * Vérifie que la mise à jour échoue lorsque le sondage n'existe pas.
     */
    @Test
    void testUpdateNotFound() {
        // Données de test
        Long sondageId = 1L;
        Sondage sondageToUpdate = new Sondage();
        when(repository.findById(sondageId)).thenReturn(Optional.empty());

        // Appel de la méthode
        Sondage result = sondageService.update(sondageId, sondageToUpdate);

        // Vérifications des résultats
        assertNull(result, "Le résultat doit être null si le sondage n'existe pas");
        verify(repository, never()).save(sondageToUpdate); // save() ne doit pas être appelé
    }

    /**
     * Teste la méthode delete du SondageService.
     * Vérifie que le service supprime correctement un sondage existant.
     */
    @Test
    void testDelete() {
        // Données de test
        Long sondageId = 1L;
        when(repository.findById(sondageId)).thenReturn(Optional.of(new Sondage()));

        // Appel de la méthode
        int result = sondageService.delete(sondageId);

        // Vérifications des résultats
        assertEquals(1, result, "La suppression doit renvoyer 1 si le sondage a été supprimé");
        verify(repository).deleteById(sondageId); // Vérifier que deleteById() a bien été appelé
    }

    /**
     * Teste la méthode delete du SondageService.
     * Vérifie que la suppression échoue lorsque le sondage n'existe pas.
     */
    @Test
    void testDeleteNotFound() {
        // Données de test
        Long sondageId = 1L;
        when(repository.findById(sondageId)).thenReturn(Optional.empty());

        // Appel de la méthode
        int result = sondageService.delete(sondageId);

        // Vérifications des résultats
        assertEquals(0, result, "La suppression doit renvoyer 0 si le sondage n'est pas trouvé");
        verify(repository, never()).deleteById(sondageId); // deleteById() ne doit pas être appelé
    }
}
