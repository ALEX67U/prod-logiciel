package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.dtos;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.ParticipantDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour le DTO ParticipantDto.
 *
 * Cette classe teste les méthodes de getters et setters, ainsi que
 * les méthodes equals, hashCode, et toString de ParticipantDto.
 */
class ParticipantDtoTest {

    private ParticipantDto participantDto; // Instance de ParticipantDto pour les tests

    private String nom; // Nom du participant
    private String prenom; // Prénom du participant

    /**
     * Configuration préalable à l'exécution de chaque test.
     * Initialise un ParticipantDto et des valeurs de nom et prénom par défaut.
     */
    @BeforeEach
    void setUp() {
        participantDto = new ParticipantDto(); // Création d'un ParticipantDto
        nom = "Dupont"; // Initialisation du nom
        prenom = "Jean"; // Initialisation du prénom
    }

    /**
     * Teste les méthodes de getters et setters pour l'ID du participant.
     */
    @Test
    void testGetSetParticipantId() {
        Long participantId = 1L;
        participantDto.setParticipantId(participantId); // Assigne l'ID
        assertEquals(participantId, participantDto.getParticipantId()); // Vérifie la valeur
    }

    /**
     * Teste les méthodes de getters et setters pour le nom du participant.
     */
    @Test
    void testGetSetNom() {
        participantDto.setNom(nom); // Assigne le nom
        assertEquals(nom, participantDto.getNom()); // Vérifie la valeur
    }

    /**
     * Teste les méthodes de getters et setters pour le prénom du participant.
     */
    @Test
    void testGetSetPrenom() {
        participantDto.setPrenom(prenom); // Assigne le prénom
        assertEquals(prenom, participantDto.getPrenom()); // Vérifie la valeur
    }

    /**
     * Teste les méthodes equals et hashCode de ParticipantDto.
     * Vérifie que deux objets avec les mêmes valeurs sont considérés comme égaux.
     */
    @Test
    void testEqualsAndHashCode() {
        ParticipantDto participantDto1 = new ParticipantDto();
        participantDto1.setParticipantId(1L);
        participantDto1.setNom(nom);
        participantDto1.setPrenom(prenom);

        ParticipantDto participantDto2 = new ParticipantDto();
        participantDto2.setParticipantId(1L);
        participantDto2.setNom(nom);
        participantDto2.setPrenom(prenom);

        assertEquals(participantDto1, participantDto2); // Vérifie l'égalité des deux DTO
        assertEquals(participantDto1.hashCode(), participantDto2.hashCode()); // Vérifie l'égalité des hashCodes
    }

    /**
     * Teste l'inégalité entre deux ParticipantDto.
     * Modifie un des DTO pour s'assurer qu'ils ne sont plus égaux.
     */
    @Test
    void testNotEquals() {
        ParticipantDto participantDto1 = new ParticipantDto();
        participantDto1.setParticipantId(1L);
        participantDto1.setNom(nom);
        participantDto1.setPrenom(prenom);

        ParticipantDto participantDto2 = new ParticipantDto();
        participantDto2.setParticipantId(2L); // Modifie l'ID du second DTO

        assertNotEquals(participantDto1, participantDto2); // Vérifie qu'ils ne sont plus égaux
    }

    /**
     * Teste la méthode toString de ParticipantDto.
     * Vérifie que la représentation sous forme de chaîne est correcte.
     */
    @Test
    void testToString() {
        participantDto.setParticipantId(1L);
        participantDto.setNom(nom);
        participantDto.setPrenom(prenom);

        String expectedToString = "ParticipantDto{participantId=1, nom='Dupont', prenom='Jean'}";
        assertEquals(expectedToString, participantDto.toString()); // Vérifie la sortie de toString
    }
}
