package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondee;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.DateSondeeRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.DateSondeeService;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.DateSondageService;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Date;
import java.util.List;

/**
 * Classe de test pour le DateSondeeService.
 *
 * Cette classe teste les fonctionnalités de gestion des dates sondées.
 */
public class DateSondeeServiceTest {

    @Mock
    private DateSondeeRepository repository; // Mock du repository pour les dates sondées

    @Mock
    private DateSondageService dateSondageService; // Mock du service pour les dates de sondage

    @Mock
    private ParticipantService participantService; // Mock du service pour les participants

    @InjectMocks
    private DateSondeeService dateSondeeService; // Service à tester

    /**
     * Méthode exécutée avant chaque test.
     * Elle initialise les mocks nécessaires aux tests.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    /**
     * Teste la méthode create du DateSondeeService.
     * Vérifie que la création d'une date sondée fonctionne correctement
     * lorsque le sondage est ouvert.
     */
    @Test
    void testCreate() {
        // Données de test
        Long sondageId = 1L;
        Long participantId = 1L;
        DateSondee dateSondee = new DateSondee();
        DateSondage dateSondage = new DateSondage();
        dateSondage.setSondage(new Sondage());
        dateSondage.getSondage().setCloture(false); // Sondage ouvert

        // Configuration des mocks
        when(dateSondageService.getById(sondageId)).thenReturn(dateSondage);
        when(participantService.getById(participantId)).thenReturn(new Participant());
        when(repository.save(dateSondee)).thenReturn(dateSondee);

        // Appel de la méthode
        DateSondee result = dateSondeeService.create(sondageId, participantId, dateSondee);

        // Vérifications des résultats
        assertNotNull(result, "Le résultat ne doit pas être null");
        verify(repository).save(dateSondee); // Vérifier que la méthode save a bien été appelée
    }

    /**
     * Teste la méthode create du DateSondeeService.
     * Vérifie que la création d'une date sondée ne fonctionne pas
     * lorsque le sondage est clôturé.
     */
    @Test
    void testCreateClotureTrue() {
        // Données de test
        Long sondageId = 1L;
        Long participantId = 1L;
        DateSondee dateSondee = new DateSondee();
        DateSondage dateSondage = new DateSondage();
        dateSondage.setSondage(new Sondage());
        dateSondage.getSondage().setCloture(true); // Clôture est true, ne doit pas créer la dateSondee

        // Configuration des mocks
        when(dateSondageService.getById(sondageId)).thenReturn(dateSondage);

        // Appel de la méthode
        DateSondee result = dateSondeeService.create(sondageId, participantId, dateSondee);

        // Vérifications des résultats
        assertNull(result, "Le résultat doit être null car le sondage est clôturé");
        verify(repository, never()).save(dateSondee); // La méthode save ne doit pas être appelée
    }

    /**
     * Teste la méthode bestDate du DateSondeeService.
     * Vérifie que les meilleures dates sont correctement récupérées
     * à partir du repository.
     */
    @Test
    void testBestDate() {
        // Données de test
        Long sondageId = 1L;
        List<Date> expectedDates = List.of(new Date(), new Date());
        when(repository.bestDate(sondageId)).thenReturn(expectedDates); // Configuration du mock

        // Appel de la méthode
        List<Date> result = dateSondeeService.bestDate(sondageId);

        // Vérifications des résultats
        assertNotNull(result, "La liste des meilleures dates ne doit pas être null");
        assertEquals(2, result.size(), "La taille de la liste doit être 2");
    }

    /**
     * Teste la méthode maybeBestDate du DateSondeeService.
     * Vérifie que les meilleures dates possibles sont correctement
     * récupérées à partir du repository.
     */
    @Test
    void testMaybeBestDate() {
        // Données de test
        Long sondageId = 1L;
        List<Date> expectedDates = List.of(new Date(), new Date());
        when(repository.maybeBestDate(sondageId)).thenReturn(expectedDates); // Configuration du mock

        // Appel de la méthode
        List<Date> result = dateSondeeService.maybeBestDate(sondageId);

        // Vérifications des résultats
        assertNotNull(result, "La liste des meilleures dates possibles ne doit pas être null");
        assertEquals(2, result.size(), "La taille de la liste doit être 2");
    }
}
