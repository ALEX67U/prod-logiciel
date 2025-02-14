package testUnitaire.dtos;


import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.CommentaireDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentaireDtoTest {

    private CommentaireDto commentaireDto1;
    private CommentaireDto commentaireDto2;

    @BeforeEach
    void setUp() {
        commentaireDto1 = new CommentaireDto();
        commentaireDto1.setCommentaireId(1L);
        commentaireDto1.setCommentaire("Ceci est un commentaire");
        commentaireDto1.setParticipant(100L);

        commentaireDto2 = new CommentaireDto();
        commentaireDto2.setCommentaireId(1L);
        commentaireDto2.setCommentaire("Ceci est un commentaire");
        commentaireDto2.setParticipant(100L);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1L, commentaireDto1.getCommentaireId());
        assertEquals("Ceci est un commentaire", commentaireDto1.getCommentaire());
        assertEquals(100L, commentaireDto1.getParticipant());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(commentaireDto1, commentaireDto2);
        assertEquals(commentaireDto1.hashCode(), commentaireDto2.hashCode());
    }

    @Test
    void testNotEquals() {
        commentaireDto2.setCommentaire("Un autre commentaire");
        assertNotEquals(commentaireDto1, commentaireDto2);
    }
}