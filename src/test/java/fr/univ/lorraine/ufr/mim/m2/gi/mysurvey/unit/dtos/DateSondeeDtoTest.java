package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.dtos;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.DateSondeeDto;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Choix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour le DTO DateSondeeDto.
 *
 * Cette classe teste les méthodes de getters et setters ainsi que
 * les méthodes equals et hashCode de DateSondeeDto.
 */
class DateSondeeDtoTest {

    private DateSondeeDto dateSondeeDto1; // Premier objet DateSondeeDto pour les tests
    private DateSondeeDto dateSondeeDto2; // Deuxième objet DateSondeeDto pour les tests

    /**
     * Configuration préalable à l'exécution de chaque test.
     * Initialise les objets DateSondeeDto avec des valeurs par défaut.
     */
    @BeforeEach
    void setUp() {
        dateSondeeDto1 = new DateSondeeDto(); // Création du premier DateSondeeDto
        dateSondeeDto1.setDateSondeeId(1L); // Initialisation des propriétés
        dateSondeeDto1.setParticipant(100L);
        dateSondeeDto1.setChoix(Choix.DISPONIBLE.toString());

        dateSondeeDto2 = new DateSondeeDto(); // Création du second DateSondeeDto
        dateSondeeDto2.setDateSondeeId(1L); // Initialisation des propriétés
        dateSondeeDto2.setParticipant(100L);
        dateSondeeDto2.setChoix(Choix.DISPONIBLE.toString());
    }

    /**
     * Teste les méthodes de getters et setters de DateSondeeDto.
     * Vérifie que les valeurs sont correctement assignées et retournées.
     */
    @Test
    void testGettersAndSetters() {
        assertEquals(1L, dateSondeeDto1.getDateSondeeId()); // Vérifie l'ID de la date sondée
        assertEquals(100L, dateSondeeDto1.getParticipant()); // Vérifie l'ID du participant
        assertEquals(Choix.DISPONIBLE.toString(), dateSondeeDto1.getChoix()); // Vérifie le choix
    }

    /**
     * Teste les méthodes equals et hashCode de DateSondeeDto.
     * Vérifie que deux objets avec les mêmes valeurs sont considérés comme égaux.
     */
    @Test
    void testEqualsAndHashCode() {
        assertEquals(dateSondeeDto1, dateSondeeDto2); // Vérifie l'égalité des deux DTO
        assertEquals(dateSondeeDto1.hashCode(), dateSondeeDto2.hashCode()); // Vérifie l'égalité des hashCodes
    }

    /**
     * Teste l'inégalité entre deux DateSondeeDto.
     * Modifie un des DTO pour s'assurer qu'ils ne sont plus égaux.
     */
    @Test
    void testNotEquals() {
        dateSondeeDto2.setChoix(Choix.INDISPONIBLE.toString()); // Modifie le choix du second DTO
        assertNotEquals(dateSondeeDto1, dateSondeeDto2); // Vérifie qu'ils ne sont plus égaux
    }
}
