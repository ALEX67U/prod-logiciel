package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.dtos;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.SondageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour le DTO SondageDto.
 *
 * Cette classe teste les méthodes de getters et setters, ainsi que
 * les méthodes equals et hashCode de SondageDto.
 */
class SondageDtoTest {

    private SondageDto sondageDto; // Instance de SondageDto pour les tests

    private String nom; // Nom du sondage
    private String description; // Description du sondage

    /**
     * Configuration préalable à l'exécution de chaque test.
     * Initialise un SondageDto et des valeurs de nom et description par défaut.
     */
    @BeforeEach
    void setUp() {
        sondageDto = new SondageDto(); // Création d'un SondageDto
        nom = "Sondage 2025"; // Initialisation du nom
        description = "Description du sondage"; // Initialisation de la description
    }

    /**
     * Teste les méthodes de getters et setters pour l'ID du sondage.
     */
    @Test
    void testGetSetSondageId() {
        Long sondageId = 1L;
        sondageDto.setSondageId(sondageId); // Assigne l'ID du sondage
        assertEquals(sondageId, sondageDto.getSondageId()); // Vérifie la valeur
    }

    /**
     * Teste les méthodes de getters et setters pour le nom du sondage.
     */
    @Test
    void testGetSetNom() {
        sondageDto.setNom(nom); // Assigne le nom
        assertEquals(nom, sondageDto.getNom()); // Vérifie la valeur
    }

    /**
     * Teste les méthodes de getters et setters pour la description du sondage.
     */
    @Test
    void testGetSetDescription() {
        sondageDto.setDescription(description); // Assigne la description
        assertEquals(description, sondageDto.getDescription()); // Vérifie la valeur
    }

    /**
     * Teste les méthodes de getters et setters pour la date de fin du sondage.
     */
    @Test
    void testGetSetFin() {
        Date fin = new Date(); // Crée une nouvelle date
        sondageDto.setFin(fin); // Assigne la date de fin
        assertEquals(fin, sondageDto.getFin()); // Vérifie la valeur
    }

    /**
     * Teste les méthodes de getters et setters pour l'état de clôture du sondage.
     */
    @Test
    void testGetSetCloture() {
        Boolean cloture = true; // Indique que le sondage est clôturé
        sondageDto.setCloture(cloture); // Assigne l'état de clôture
        assertEquals(cloture, sondageDto.getCloture()); // Vérifie la valeur
    }

    /**
     * Teste les méthodes de getters et setters pour l'ID de l'utilisateur qui a créé le sondage.
     */
    @Test
    void testGetSetCreateBy() {
        Long createBy = 100L; // ID de l'utilisateur
        sondageDto.setCreateBy(createBy); // Assigne l'ID de l'utilisateur
        assertEquals(createBy, sondageDto.getCreateBy()); // Vérifie la valeur
    }

    /**
     * Teste les méthodes equals et hashCode de SondageDto.
     * Vérifie que deux objets avec les mêmes valeurs sont considérés comme égaux.
     */
    @Test
    void testEqualsAndHashCode() {
        SondageDto sondageDto1 = new SondageDto();
        sondageDto1.setSondageId(1L);
        sondageDto1.setNom(nom);
        sondageDto1.setDescription(description);
        sondageDto1.setFin(new Date());
        sondageDto1.setCloture(true);
        sondageDto1.setCreateBy(100L);

        SondageDto sondageDto2 = new SondageDto();
        sondageDto2.setSondageId(1L);
        sondageDto2.setNom(nom);
        sondageDto2.setDescription(description);
        sondageDto2.setFin(new Date());
        sondageDto2.setCloture(true);
        sondageDto2.setCreateBy(100L);

        assertEquals(sondageDto1, sondageDto2); // Vérifie l'égalité des deux DTO
        assertEquals(sondageDto1.hashCode(), sondageDto2.hashCode()); // Vérifie l'égalité des hashCodes
    }

    /**
     * Teste l'inégalité entre deux SondageDto.
     * Modifie un des DTO pour s'assurer qu'ils ne sont plus égaux.
     */
    @Test
    void testNotEquals() {
        SondageDto sondageDto1 = new SondageDto();
        sondageDto1.setSondageId(1L);
        sondageDto1.setNom(nom);

        SondageDto sondageDto2 = new SondageDto();
        sondageDto2.setSondageId(2L); // Modifie l'ID du second DTO

        assertNotEquals(sondageDto1, sondageDto2); // Vérifie qu'ils ne sont plus égaux
    }
}
