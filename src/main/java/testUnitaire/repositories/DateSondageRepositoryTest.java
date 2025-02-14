package testUnitaire.repositories;

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

public class DateSondageRepositoryTest {

    @Mock
    private DateSondageRepository dateSondageRepository; // Mock du repository

    private DateSondage dateSondage1;
    private DateSondage dateSondage2;

    @BeforeEach
    void setUp() {
        // Initialisation de Mockito
        MockitoAnnotations.openMocks(this);

        // Initialisation des objets DateSondage
        dateSondage1 = new DateSondage();
        dateSondage1.setDate(new java.util.Date()); // Ajoutez des valeurs appropriées ici

        dateSondage2 = new DateSondage();
        dateSondage2.setDate(new java.util.Date()); // Ajoutez des valeurs appropriées ici
    }

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
