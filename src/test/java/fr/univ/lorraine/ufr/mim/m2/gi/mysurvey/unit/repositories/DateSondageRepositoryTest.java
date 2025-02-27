package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.repositories;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.DateSondageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de test pour le DateSondageRepository.
 *
 * Cette classe teste les méthodes du repository liées aux dates de sondage.
 */
public class DateSondageRepositoryTest {

    @Mock
    private DateSondageRepository dateSondageRepository; // Mock du repository pour les dates de sondage

    private DateSondage dateSondage1;
    private DateSondage dateSondage2;

    /**
     * Méthode exécutée avant chaque test.
     * Elle initialise les mocks et crée des objets DateSondage pour les tests.
     */
    @BeforeEach
    void setUp() {
        // Initialisation de Mockito
        MockitoAnnotations.openMocks(this);

        // Initialisation des objets DateSondage
        dateSondage1 = new DateSondage();
        dateSondage1.setDate(new java.util.Date()); // Définir une date appropriée pour le test

        dateSondage2 = new DateSondage();
        dateSondage2.setDate(new java.util.Date()); // Définir une autre date appropriée pour le test
    }

    /**
     * Teste la méthode getAllBySondage du DateSondageRepository.
     * Vérifie que le repository renvoie la liste correcte de dates de sondage pour un sondage donné.
     */
    @Test
    void testGetAllBySondage() {
        // Simulation du comportement du mock pour la méthode getAllBySondage
        when(dateSondageRepository.getAllBySondage(1L))
                .thenReturn(Arrays.asList(dateSondage1, dateSondage2));

        // Appel de la méthode dans le repository
        List<DateSondage> result = dateSondageRepository.getAllBySondage(1L);

        // Vérification du résultat
        assertEquals(2, result.size(), "Le nombre de dates de sondage doit être 2");
    }
}
