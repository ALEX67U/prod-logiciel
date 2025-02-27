package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.models;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Commentaire;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour la classe Participant.
 *
 * Cette classe teste les fonctionnalités de construction, de récupération,
 * d'égalité et de représentation sous forme de chaîne de l'objet Participant.
 */
class ParticipantTest {

    private Participant participant;
    private Commentaire mockCommentaire;
    private Sondage mockSondage;
    private DateSondee mockDateSondee;

    /**
     * Configuration initiale avant chaque test.
     * Crée des objets simulés pour les entités associées et initialise Participant.
     */
    @BeforeEach
    void setUp() {
        // Créer des objets simulés pour les entités associées avec des noms significatifs
        mockCommentaire = mock(Commentaire.class);
        mockSondage = mock(Sondage.class);
        mockDateSondee = mock(DateSondee.class);

        // Initialiser Participant avec les objets associés
        participant = new Participant(1L, "John", "Doe");
        participant.setCommentaire(List.of(mockCommentaire));
        participant.setSondages(List.of(mockSondage));
        participant.setDateSondee(List.of(mockDateSondee));
    }

    /**
     * Teste le constructeur et les accesseurs de Participant.
     * Vérifie que les valeurs sont correctement définies lors de l'initialisation.
     */
    @Test
    void testConstructorAndGetters() {
        // Vérifier que le constructeur et les méthodes d'accesseurs fonctionnent correctement
        assertAll("Test constructeur et accesseurs",
                () -> assertNotNull(participant),
                () -> assertEquals(1L, participant.getParticipantId()),
                () -> assertEquals("John", participant.getNom()),
                () -> assertEquals("Doe", participant.getPrenom()),
                () -> assertEquals(1, participant.getCommentaire().size()),
                () -> assertEquals(1, participant.getSondages().size()),
                () -> assertEquals(1, participant.getDateSondee().size())
        );
    }

    /**
     * Teste l'égalité et le code de hachage de Participant.
     * Vérifie que deux participants avec les mêmes valeurs sont considérés comme égaux.
     */
    @Test
    void testEqualsAndHashCode() {
        // Créer deux participants avec les mêmes valeurs
        Participant participant1 = new Participant(1L, "John", "Doe");
        Participant participant2 = new Participant(1L, "John", "Doe");

        // Tester l'égalité
        assertEquals(participant1, participant2, "Les participants devraient être égaux");

        // Tester la cohérence du code de hachage
        assertEquals(participant1.hashCode(), participant2.hashCode(), "Les codes de hachage devraient correspondre pour des participants égaux");
    }

    /**
     * Teste la méthode toString de Participant.
     * Vérifie que la sortie correspond à la représentation attendue de l'objet.
     */
    @Test
    void testToString() {
        // Tester la méthode toString pour une sortie attendue
        String expectedToString = "Participant{participantId=1, nom='John', prenom='Doe'}";
        assertEquals(expectedToString, participant.toString(), "La sortie de toString devrait correspondre");
    }
}
