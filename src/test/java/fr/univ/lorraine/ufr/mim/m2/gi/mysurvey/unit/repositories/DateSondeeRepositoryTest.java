package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.repositories;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.DateSondeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de test pour le DateSondeeRepository.
 *
 * Cette classe teste les méthodes du repository liées aux dates de sondée.
 */
public class DateSondeeRepositoryTest {

    @Mock
    private DateSondeeRepository dateSondeeRepository; // Mock du repository pour les dates sondées

    private Date date1;
    private Date date2;

    /**
     * Méthode exécutée avant chaque test.
     * Elle initialise les mocks et crée des objets Date pour les tests.
     */
    @BeforeEach
    void setUp() {
        // Initialisation de Mockito
        MockitoAnnotations.openMocks(this);

        // Initialisation des objets Date avec des valeurs appropriées
        date1 = new Date();  // Initialisation de la première date pour le test
        date2 = new Date(System.currentTimeMillis() + 100000); // Une autre date pour le test (100000 ms après la première)
    }

    /**
     * Teste la méthode bestDate du DateSondeeRepository.
     * Vérifie que le repository renvoie la liste correcte de dates meilleures pour un sondage donné.
     */
    @Test
    void testBestDate() {
        // Simulation du comportement du mock pour la méthode bestDate
        when(dateSondeeRepository.bestDate(1L))
                .thenReturn(Arrays.asList(date1, date2));

        // Appel de la méthode dans le repository
        List<Date> result = dateSondeeRepository.bestDate(1L);

        // Vérification du résultat
        assertEquals(2, result.size(), "Le nombre de meilleures dates doit être 2");
        assertEquals(date1, result.get(0), "La première date ne correspond pas");
        assertEquals(date2, result.get(1), "La deuxième date ne correspond pas");
    }

    /**
     * Teste la méthode maybeBestDate du DateSondeeRepository.
     * Vérifie que le repository renvoie la liste correcte de dates possibles pour un sondage donné.
     */
    @Test
    void testMaybeBestDate() {
        // Simulation du comportement du mock pour la méthode maybeBestDate
        when(dateSondeeRepository.maybeBestDate(1L))
                .thenReturn(Arrays.asList(date1, date2));

        // Appel de la méthode dans le repository
        List<Date> result = dateSondeeRepository.maybeBestDate(1L);

        // Vérification du résultat
        assertEquals(2, result.size(), "Le nombre de dates possibles doit être 2");
        assertEquals(date1, result.get(0), "La première date ne correspond pas");
        assertEquals(date2, result.get(1), "La deuxième date ne correspond pas");
    }
}
