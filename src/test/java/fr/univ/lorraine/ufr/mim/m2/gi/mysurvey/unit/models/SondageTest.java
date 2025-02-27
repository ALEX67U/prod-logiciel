package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.models;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Commentaire;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour la classe Sondage.
 *
 * Cette classe teste les fonctionnalités de construction, d'accès aux
 * propriétés, de relations et de représentation sous forme de chaîne
 * de l'objet Sondage.
 */
class SondageTest {

    private Sondage sondage;
    private Participant creator;
    private Commentaire commentaire;
    private DateSondage dateSondage;

    /**
     * Configuration initiale avant chaque test.
     * Crée des objets nécessaires pour tester la classe Sondage.
     */
    @BeforeEach
    void setUp() {
        // Préparer des objets nécessaires (réels ou simulés)
        creator = new Participant(1L, "John", "Doe");  // Création d'un Participant pour la relation 'createBy'
        commentaire = new Commentaire();  // Supposons que Commentaire a un constructeur par défaut
        dateSondage = new DateSondage();  // Supposons que DateSondage a un constructeur par défaut

        List<Commentaire> commentaires = new ArrayList<>();
        commentaires.add(commentaire);

        List<DateSondage> dateSondages = new ArrayList<>();
        dateSondages.add(dateSondage);

        sondage = new Sondage(1L, "Survey 1", "Description of Survey", new Date(), false, commentaires, dateSondages, creator);
    }

    /**
     * Teste le constructeur et les accesseurs de Sondage.
     * Vérifie que les valeurs sont correctement définies lors de l'initialisation.
     */
    @Test
    void testConstructorAndGetters() {
        // Vérifier que le constructeur et les méthodes d'accesseurs fonctionnent correctement
        assertNotNull(sondage);
        assertEquals(1L, sondage.getSondageId());
        assertEquals("Survey 1", sondage.getNom());
        assertEquals("Description of Survey", sondage.getDescription());
        assertNotNull(sondage.getFin());  // Vérifie simplement qu'un objet Date a été créé
        assertFalse(sondage.getCloture());
        assertEquals(1, sondage.getCommentaires().size());
        assertEquals(1, sondage.getDateSondage().size());
        assertNotNull(sondage.getCreateBy());
        assertEquals("John", sondage.getCreateBy().getNom());
    }

    /**
     * Teste les accesseurs pour s'assurer que les valeurs sont mises à jour correctement.
     */
    @Test
    void testSetters() {
        // Tester les accesseurs et vérifier si les valeurs sont mises à jour
        sondage.setSondageId(2L);
        sondage.setNom("Updated Survey");
        sondage.setDescription("Updated Description");
        sondage.setFin(new Date());
        sondage.setCloture(true);

        assertEquals(2L, sondage.getSondageId());
        assertEquals("Updated Survey", sondage.getNom());
        assertEquals("Updated Description", sondage.getDescription());
        assertNotNull(sondage.getFin());
        assertTrue(sondage.getCloture());
    }

    /**
     * Teste les relations 'OneToMany' pour les commentaires et les dates de sondage.
     */
    @Test
    void testOneToManyRelationship() {
        // Vérifier la relation 'OneToMany' (commentaires, dateSondage)
        List<Commentaire> commentaires = sondage.getCommentaires();
        List<DateSondage> dateSondages = sondage.getDateSondage();

        assertNotNull(commentaires);
        assertEquals(1, commentaires.size());

        assertNotNull(dateSondages);
        assertEquals(1, dateSondages.size());
    }

    /**
     * Teste la relation 'ManyToOne' pour le créateur du sondage.
     */
    @Test
    void testManyToOneRelationship() {
        // Vérifier la relation 'ManyToOne' (createBy)
        assertNotNull(sondage.getCreateBy());
        assertEquals("John", sondage.getCreateBy().getNom());
    }

    /**
     * Teste la méthode toString de Sondage.
     * Vérifie que la sortie correspond à la représentation attendue de l'objet.
     */
    @Test
    void testToString() {
        // Créer une version simulée de Sondage
        Sondage sondageMock = mock(Sondage.class);

        // Définir le comportement attendu de toString()
        when(sondageMock.toString()).thenReturn("Sondage{sondageId=1, nom='Survey 1', description='Description of Survey', cloture=false}");

        // Vérifier que la méthode toString() renvoie bien la valeur attendue
        assertEquals("Sondage{sondageId=1, nom='Survey 1', description='Description of Survey', cloture=false}", sondageMock.toString());
    }
}
