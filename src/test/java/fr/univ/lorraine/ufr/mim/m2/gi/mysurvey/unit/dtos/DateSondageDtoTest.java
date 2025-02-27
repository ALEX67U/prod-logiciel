package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.dtos;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.DateSondageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour le DTO DateSondageDto.
 *
 * Cette classe teste les méthodes de getters et setters ainsi que
 * les méthodes equals et hashCode de DateSondageDto.
 */
class DateSondageDtoTest {

    private DateSondageDto dateSondageDto1; // Premier objet DateSondageDto pour les tests
    private DateSondageDto dateSondageDto2; // Deuxième objet DateSondageDto pour les tests
    private Date testDate1; // Date de test pour le premier DTO
    private Date testDate2; // Date de test pour le second DTO

    /**
     * Configuration préalable à l'exécution de chaque test.
     * Initialise les objets DateSondageDto et les dates de test.
     */
    @BeforeEach
    void setUp() {
        testDate1 = new Date(); // Initialisation de la première date de test
        testDate2 = new Date(testDate1.getTime() + 1000); // Assure une différence de date de 1 seconde

        dateSondageDto1 = new DateSondageDto(); // Création du premier DateSondageDto
        dateSondageDto1.setDateSondageId(1L); // Initialisation des propriétés
        dateSondageDto1.setDate(testDate1);

        dateSondageDto2 = new DateSondageDto(); // Création du second DateSondageDto
        dateSondageDto2.setDateSondageId(1L); // Initialisation des propriétés
        dateSondageDto2.setDate(testDate1);
    }

    /**
     * Teste les méthodes de getters et setters de DateSondageDto.
     * Vérifie que les valeurs sont correctement assignées et retournées.
     */
    @Test
    void testGettersAndSetters() {
        assertEquals(1L, dateSondageDto1.getDateSondageId()); // Vérifie l'ID de la date de sondage
        assertEquals(testDate1, dateSondageDto1.getDate()); // Vérifie la date
    }

    /**
     * Teste les méthodes equals et hashCode de DateSondageDto.
     * Vérifie que deux objets avec les mêmes valeurs sont considérés comme égaux.
     */
    @Test
    void testEqualsAndHashCode() {
        assertEquals(dateSondageDto1, dateSondageDto2); // Vérifie l'égalité des deux DTO
        assertEquals(dateSondageDto1.hashCode(), dateSondageDto2.hashCode()); // Vérifie l'égalité des hashCodes
    }

    /**
     * Teste l'inégalité entre deux DateSondageDto.
     * Modifie un des DTO pour s'assurer qu'ils ne sont plus égaux.
     */
    @Test
    void testNotEquals() {
        dateSondageDto2.setDate(testDate2); // Modifie la date du second DTO
        assertNotEquals(dateSondageDto1, dateSondageDto2); // Vérifie qu'ils ne sont plus égaux
    }
}
