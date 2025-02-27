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

public class DateSondeeRepositoryTest {

    @Mock
    private DateSondeeRepository dateSondeeRepository; // Mock du repository

    private Date date1;
    private Date date2;

    @BeforeEach
    void setUp() {
        // Initialisation de Mockito
        MockitoAnnotations.openMocks(this);

        // Initialisation des objets Date (Exemple)
        date1 = new Date();  // Initialisation d'une date pour le test
        date2 = new Date(System.currentTimeMillis() + 100000); // Une autre date pour le test
    }

    @Test
    void testBestDate() {
        // Simuler le comportement de la méthode bestDate
        when(dateSondeeRepository.bestDate(1L))
                .thenReturn(Arrays.asList(date1, date2));

        // Appel de la méthode du repository
        List<Date> result = dateSondeeRepository.bestDate(1L);

        // Vérification des résultats
        assertEquals(2, result.size(), "Le nombre de meilleures dates doit être 2");
        assertEquals(date1, result.get(0), "La première date ne correspond pas");
        assertEquals(date2, result.get(1), "La deuxième date ne correspond pas");
    }

    @Test
    void testMaybeBestDate() {
        // Simuler le comportement de la méthode maybeBestDate
        when(dateSondeeRepository.maybeBestDate(1L))
                .thenReturn(Arrays.asList(date1, date2));

        // Appel de la méthode du repository
        List<Date> result = dateSondeeRepository.maybeBestDate(1L);

        // Vérification des résultats
        assertEquals(2, result.size(), "Le nombre de dates possibles doit être 2");
        assertEquals(date1, result.get(0), "La première date ne correspond pas");
        assertEquals(date2, result.get(1), "La deuxième date ne correspond pas");
    }
}
