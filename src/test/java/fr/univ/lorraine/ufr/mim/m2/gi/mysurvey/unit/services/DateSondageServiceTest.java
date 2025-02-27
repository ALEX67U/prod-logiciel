package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.DateSondageRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.DateSondageService;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.SondageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.List;

/**
 * Classe de test pour le DateSondageService.
 *
 * Cette classe teste les fonctionnalités de gestion des dates de sondage.
 */
public class DateSondageServiceTest {

    @Mock
    private DateSondageRepository repository; // Mock du repository pour les dates de sondage

    @Mock
    private SondageService sondageService; // Mock du service pour les sondages

    @InjectMocks
    private DateSondageService dateSondageService; // Service à tester

    /**
     * Méthode exécutée avant chaque test.
     * Elle initialise les mocks nécessaires aux tests.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialiser les mocks
    }

    /**
     * Teste la méthode getById du DateSondageService.
     * Vérifie que la date de sondage est correctement récupérée par ID.
     */
    @Test
    void testGetById() {
        // Données de test
        Long id = 1L;
        DateSondage dateSondage = new DateSondage();
        when(repository.getById(id)).thenReturn(dateSondage); // Configuration du mock

        // Appel de la méthode
        DateSondage result = dateSondageService.getById(id);

        // Vérifications des résultats
        assertNotNull(result, "Le résultat ne doit pas être null");
        assertEquals(dateSondage, result, "La date de sondage retournée doit être la même que celle simulée");
    }

    /**
     * Teste la méthode getBySondageId du DateSondageService.
     * Vérifie que les dates de sondage sont correctement récupérées par ID de sondage.
     */
    @Test
    void testGetBySondageId() {
        // Données de test
        Long sondageId = 1L;
        List<DateSondage> dateSondages = List.of(new DateSondage(), new DateSondage());
        when(repository.getAllBySondage(sondageId)).thenReturn(dateSondages); // Configuration du mock

        // Appel de la méthode
        List<DateSondage> result = dateSondageService.getBySondageId(sondageId);

        // Vérifications des résultats
        assertNotNull(result, "La liste ne doit pas être null");
        assertEquals(2, result.size(), "La taille de la liste doit être égale à 2");
    }

    /**
     * Teste la méthode create du DateSondageService.
     * Vérifie que la création d'une date de sondage fonctionne correctement.
     */
    @Test
    void testCreate() {
        // Données de test
        Long sondageId = 1L;
        DateSondage dateSondage = new DateSondage();
        when(sondageService.getById(sondageId)).thenReturn(new fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage()); // Simuler le sondage
        when(repository.save(dateSondage)).thenReturn(dateSondage); // Configuration du mock

        // Appel de la méthode
        DateSondage result = dateSondageService.create(sondageId, dateSondage);

        // Vérifications des résultats
        assertNotNull(result, "La date de sondage ne doit pas être null");
        verify(sondageService).getById(sondageId); // Vérifie que le service sondage a bien été appelé
        verify(repository).save(dateSondage); // Vérifie que la méthode save a bien été appelée
    }

    /**
     * Teste la méthode delete du DateSondageService.
     * Vérifie que la suppression d'une date de sondage fonctionne correctement.
     */
    @Test
    void testDelete() {
        // Données de test
        Long id = 1L;
        DateSondage dateSondage = new DateSondage();
        when(repository.findById(id)).thenReturn(Optional.of(dateSondage)); // Configuration du mock

        // Appel de la méthode
        int result = dateSondageService.delete(id);

        // Vérifications des résultats
        assertEquals(1, result, "La suppression doit réussir et renvoyer 1");
        verify(repository).deleteById(id); // Vérifie que la suppression a bien eu lieu
    }

    /**
     * Teste la méthode delete du DateSondageService lorsque la date de sondage n'est pas trouvée.
     * Vérifie que la suppression retourne 0 si la date de sondage n'existe pas.
     */
    @Test
    void testDeleteNotFound() {
        // Données de test
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty()); // Configuration du mock

        // Appel de la méthode
        int result = dateSondageService.delete(id);

        // Vérifications des résultats
        assertEquals(0, result, "La suppression doit échouer et renvoyer 0");
        verify(repository, never()).deleteById(id); // Vérifie que la suppression n'a pas été appelée
    }
}
