package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.models;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondee;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DateSondageTest {

    @Mock
    private Sondage sondage; // Simule l'entité Sondage

    @Mock
    private DateSondee dateSondee; // Simule l'entité DateSondee

    @InjectMocks
    private DateSondage dateSondage; // L'objet que nous testons

    @BeforeEach
    void setUp() {
        // Initialisation des valeurs de l'entité DateSondage
        dateSondage = new DateSondage();
        dateSondage.setDate(new Date());
        dateSondage.setSondage(sondage);
        dateSondage.setDateSondee(Collections.singletonList(dateSondee));
    }

    @Test
    void testCreateDateSondage() {
        // Crée une nouvelle instance de DateSondage pour simuler la création
        DateSondage savedDateSondage = new DateSondage(dateSondage.getDateSondageId(), dateSondage.getDate(), sondage, dateSondage.getDateSondee());

        // Vérification que les valeurs de dateSondage sont correctement définies
        assertNotNull(savedDateSondage);
        assertEquals(dateSondage.getDate(), savedDateSondage.getDate());
        assertEquals(sondage, savedDateSondage.getSondage());
        assertEquals(dateSondage.getDateSondee(), savedDateSondage.getDateSondee());
    }

    @Test
    void testUpdateDateSondage() {
        // Initial date
        Date originalDate = dateSondage.getDate();

        // Simulate an update with a different date
        Date newDate = new Date(System.currentTimeMillis() + 100000); // Different date to simulate an update

        DateSondage updatedDateSondage = new DateSondage(dateSondage.getDateSondageId(), newDate, sondage, Collections.emptyList());

        // Mise à jour des valeurs
        updatedDateSondage.setDate(newDate);
        updatedDateSondage.setSondage(sondage);
        updatedDateSondage.setDateSondee(Collections.emptyList());

        // Vérification des mises à jour
        assertNotNull(updatedDateSondage);
        assertNotEquals(originalDate, updatedDateSondage.getDate()); // Now this assertion should pass
        assertEquals(sondage, updatedDateSondage.getSondage());
        assertTrue(updatedDateSondage.getDateSondee().isEmpty());
    }


    @Test
    void testDeleteDateSondage() {
        // Simuler la suppression de la relation sondage
        dateSondage.setSondage(null);

        // Vérification que la suppression a bien eu lieu
        assertNull(dateSondage.getSondage());
    }

    @Test
    void testGettersSetters() {
        // Test des getters et setters
        dateSondage.setDate(new Date());
        dateSondage.setSondage(sondage);
        dateSondage.setDateSondee(Collections.singletonList(dateSondee));

        assertNotNull(dateSondage.getDate());
        assertNotNull(dateSondage.getSondage());
        assertNotNull(dateSondage.getDateSondee());
    }
}
