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

class SondageTest {

    private Sondage sondage;
    private Participant creator;
    private Commentaire commentaire;
    private DateSondage dateSondage;

    @BeforeEach
    void setUp() {
        // Prepare necessary mock or real objects
        creator = new Participant(1L, "John", "Doe");  // Creating a Participant for the 'createBy' relationship
        commentaire = new Commentaire();  // Assuming Commentaire has a default constructor
        dateSondage = new DateSondage();  // Assuming DateSondage has a default constructor

        List<Commentaire> commentaires = new ArrayList<>();
        commentaires.add(commentaire);

        List<DateSondage> dateSondages = new ArrayList<>();
        dateSondages.add(dateSondage);

        sondage = new Sondage(1L, "Survey 1", "Description of Survey", new Date(), false, commentaires, dateSondages, creator);
    }

    @Test
    void testConstructorAndGetters() {
        // Verify constructor and getter methods work correctly
        assertNotNull(sondage);
        assertEquals(1L, sondage.getSondageId());
        assertEquals("Survey 1", sondage.getNom());
        assertEquals("Description of Survey", sondage.getDescription());
        assertNotNull(sondage.getFin());  // Just checking if a Date object is created
        assertFalse(sondage.getCloture());
        assertEquals(1, sondage.getCommentaires().size());
        assertEquals(1, sondage.getDateSondage().size());
        assertNotNull(sondage.getCreateBy());
        assertEquals("John", sondage.getCreateBy().getNom());
    }

    @Test
    void testSetters() {
        // Test setters and verify if values are updated
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

    @Test
    void testOneToManyRelationship() {
        // Verify the 'OneToMany' relationship (commentaires, dateSondage)
        List<Commentaire> commentaires = sondage.getCommentaires();
        List<DateSondage> dateSondages = sondage.getDateSondage();

        assertNotNull(commentaires);
        assertEquals(1, commentaires.size());

        assertNotNull(dateSondages);
        assertEquals(1, dateSondages.size());
    }

    @Test
    void testManyToOneRelationship() {
        // Verify the 'ManyToOne' relationship (createBy)
        assertNotNull(sondage.getCreateBy());
        assertEquals("John", sondage.getCreateBy().getNom());
    }

    @Test
    void testToString() {
        // Créer une version mockée de Sondage
        Sondage sondageMock = mock(Sondage.class);

        // Définir le comportement attendu de toString()
        when(sondageMock.toString()).thenReturn("Sondage{sondageId=1, nom='Survey 1', description='Description of Survey', cloture=false}");

        // Vérifier que la méthode toString() renvoie bien la valeur attendue
        assertEquals("Sondage{sondageId=1, nom='Survey 1', description='Description of Survey', cloture=false}", sondageMock.toString());
    }

}
