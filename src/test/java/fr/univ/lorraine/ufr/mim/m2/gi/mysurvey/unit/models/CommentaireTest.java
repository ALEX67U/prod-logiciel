package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.models;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Commentaire;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.CommentaireRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.ParticipantRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.SondageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la classe Commentaire.
 *
 * Cette classe teste les opérations CRUD pour la classe Commentaire en utilisant Mockito
 * pour simuler les interactions avec les dépôts.
 */
@ExtendWith(MockitoExtension.class)
class CommentaireTest {

    @Mock
    private CommentaireRepository commentaireRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private SondageRepository sondageRepository;

    @InjectMocks
    private Commentaire commentaire; // l'objet que nous testons

    private Sondage sondage;
    private Participant participant;

    /**
     * Configuration initiale avant chaque test.
     * Crée un Sondage et un Participant, et configure un Commentaire avec eux.
     */
    @BeforeEach
    void setUp() {
        sondage = new Sondage();
        sondage.setNom("Sondage Test");

        participant = new Participant();
        participant.setNom("Dupont");

        commentaire.setCommentaire("C'est un test.");
        commentaire.setSondage(sondage);
        commentaire.setParticipant(participant);
    }

    /**
     * Teste la création d'un commentaire.
     * Vérifie que le commentaire peut être sauvegardé et renvoyé correctement.
     */
    @Test
    void testCreateCommentaire() {
        // Simulation de la méthode save() du repository
        when(commentaireRepository.save(commentaire)).thenReturn(commentaire);

        // Sauvegarde du commentaire (ici, la méthode save est simulée)
        Commentaire savedCommentaire = commentaireRepository.save(commentaire);

        // Vérification du commentaire après "sauvegarde"
        assertNotNull(savedCommentaire);
        assertEquals("C'est un test.", savedCommentaire.getCommentaire());
        assertEquals(sondage, savedCommentaire.getSondage());
        assertEquals(participant, savedCommentaire.getParticipant());
    }

    /**
     * Teste la mise à jour d'un commentaire.
     * Vérifie que le commentaire peut être mis à jour correctement.
     */
    @Test
    void testUpdateCommentaire() {
        // Sauvegarde initiale avec la simulation
        when(commentaireRepository.save(commentaire)).thenReturn(commentaire);

        // Mise à jour du commentaire
        commentaire.setCommentaire("Commentaire modifié");
        Commentaire updatedCommentaire = commentaireRepository.save(commentaire);

        // Vérification de la mise à jour
        assertEquals("Commentaire modifié", updatedCommentaire.getCommentaire());
    }

    /**
     * Teste la suppression d'un commentaire.
     * Vérifie que la méthode delete est appelée sur le repository.
     */
    @Test
    void testDeleteCommentaire() {
        // Simulation de la méthode delete()
        doNothing().when(commentaireRepository).delete(commentaire);

        // Suppression du commentaire
        commentaireRepository.delete(commentaire);

        // Vérification que la méthode delete a bien été appelée
        verify(commentaireRepository, times(1)).delete(commentaire);
    }
}
