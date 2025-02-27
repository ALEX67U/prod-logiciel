package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.models;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Choix;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondee;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour la classe DateSondee.
 *
 * Cette classe teste les fonctionnalités de construction, de récupération,
 * et de mise à jour des propriétés de l'objet DateSondee.
 */
class DateSondeeTest {

    private DateSondee dateSondee;
    private DateSondage dateSondage;
    private Participant participant;

    /**
     * Configuration initiale avant chaque test.
     * Crée des objets simulés pour les entités associées et initialise DateSondee.
     */
    @BeforeEach
    void setUp() {
        // Création d'objets simulés pour les entités associées
        dateSondage = mock(DateSondage.class);
        participant = mock(Participant.class);

        // Initialisation de DateSondee
        dateSondee = new DateSondee(1L, dateSondage, participant, Choix.DISPONIBLE);
    }

    /**
     * Teste le constructeur et les accesseurs de DateSondee.
     * Vérifie que les valeurs sont correctement définies lors de l'initialisation.
     */
    @Test
    void testConstructorAndGetters() {
        // Vérifier que le constructeur et les accesseurs fonctionnent comme prévu
        assertNotNull(dateSondee);
        assertEquals(1L, dateSondee.getDateSondeeId());
        assertEquals(dateSondage, dateSondee.getDateSondage());
        assertEquals(participant, dateSondee.getParticipant());
        assertEquals(Choix.DISPONIBLE.toString(), dateSondee.getChoix()); // Choix est converti en String
    }

    /**
     * Teste le setter et l'accesseur pour le choix.
     * Vérifie que la mise à jour du choix fonctionne correctement.
     */
    @Test
    void testSetChoix() {
        // Teste le setter et l'accesseur pour 'choix'
        dateSondee.setChoix(Choix.PEUTETRE.toString());
        assertEquals(Choix.PEUTETRE.toString(), dateSondee.getChoix());
    }

    /**
     * Teste la gestion des valeurs de choix invalides.
     * Vérifie que l'appel d'une valeur invalide lance une IllegalArgumentException.
     */
    @Test
    void testSetInvalidChoix() {
        // Teste une valeur de choix invalide (doit lancer IllegalArgumentException)
        assertThrows(IllegalArgumentException.class, () -> {
            dateSondee.setChoix("INVALID");
        });
    }

    /**
     * Teste les accesseurs et les mutateurs de DateSondee.
     * Vérifie que les valeurs peuvent être définies et récupérées correctement.
     */
    @Test
    void testSettersAndGetters() {
        // Test des mutateurs et accesseurs
        dateSondee.setDateSondeeId(2L);
        dateSondee.setDateSondage(dateSondage);
        dateSondee.setParticipant(participant);
        dateSondee.setChoix(Choix.INDISPONIBLE.toString());

        assertEquals(2L, dateSondee.getDateSondeeId());
        assertEquals(dateSondage, dateSondee.getDateSondage());
        assertEquals(participant, dateSondee.getParticipant());
        assertEquals(Choix.INDISPONIBLE.toString(), dateSondee.getChoix());
    }
}
