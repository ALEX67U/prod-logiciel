package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.dtos;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.CommentaireDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour le DTO CommentaireDto.
 *
 * Cette classe teste les méthodes de getters et setters ainsi que
 * les méthodes equals et hashCode de CommentaireDto.
 */
class CommentaireDtoTest {

    private CommentaireDto commentaireDto1; // Premier objet CommentaireDto pour les tests
    private CommentaireDto commentaireDto2; // Deuxième objet CommentaireDto pour les tests

    private String commentaire; // Commentaire utilisé pour initialiser les DTO

    /**
     * Configuration préalable à l'exécution de chaque test.
     * Initialise les objets CommentaireDto et le commentaire.
     */
    @BeforeEach
    void setUp() {
        commentaire = "Ceci est un commentaire"; // Initialisation d'un commentaire exemple
        commentaireDto1 = new CommentaireDto(); // Création d'un nouveau CommentaireDto
        commentaireDto1.setCommentaireId(1L); // Initialisation des propriétés
        commentaireDto1.setCommentaire(commentaire);
        commentaireDto1.setParticipant(100L);

        commentaireDto2 = new CommentaireDto(); // Création d'un second CommentaireDto
        commentaireDto2.setCommentaireId(1L); // Initialisation des propriétés
        commentaireDto2.setCommentaire(commentaire);
        commentaireDto2.setParticipant(100L);
    }

    /**
     * Teste les méthodes de getters et setters de CommentaireDto.
     * Vérifie que les valeurs sont correctement assignées et retournées.
     */
    @Test
    void testGettersAndSetters() {
        assertEquals(1L, commentaireDto1.getCommentaireId()); // Vérifie l'ID du commentaire
        assertEquals(commentaire, commentaireDto1.getCommentaire()); // Vérifie le texte du commentaire
        assertEquals(100L, commentaireDto1.getParticipant()); // Vérifie l'ID du participant
    }

    /**
     * Teste les méthodes equals et hashCode de CommentaireDto.
     * Vérifie que deux objets avec les mêmes valeurs sont considérés comme égaux.
     */
    @Test
    void testEqualsAndHashCode() {
        assertEquals(commentaireDto1, commentaireDto2); // Vérifie l'égalité des deux DTO
        assertEquals(commentaireDto1.hashCode(), commentaireDto2.hashCode()); // Vérifie l'égalité des hashCodes
    }

    /**
     * Teste l'inégalité entre deux CommentaireDto.
     * Modifie un des DTO pour s'assurer qu'ils ne sont plus égaux.
     */
    @Test
    void testNotEquals() {
        commentaireDto2.setCommentaire("Un autre commentaire"); // Modifie le commentaire
        assertNotEquals(commentaireDto1, commentaireDto2); // Vérifie qu'ils ne sont plus égaux
    }
}
