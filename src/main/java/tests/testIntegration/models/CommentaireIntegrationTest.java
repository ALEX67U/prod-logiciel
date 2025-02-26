package tests.testIntegration.models;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.MySurveyApplication;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Commentaire;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.CommentaireRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.ParticipantRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.SondageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MySurveyApplication.class)
@Transactional
class CommentaireIntegrationTest {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private SondageRepository sondageRepository;

    private Sondage sondage;
    private Participant participant;
    private Commentaire commentaire;

    @BeforeEach
    void setUp() {
        // Étape 1: Sauvegarde d'un sondage en base
        sondage = new Sondage();
        sondage.setNom("Sondage Test");
        sondage = sondageRepository.save(sondage); // Assurez-vous que l'objet est enregistré en BDD

        // Étape 2: Sauvegarde d'un participant en base
        participant = new Participant();
        participant.setNom("Pierre");
        participant.setPrenom("Jean"); // Ensure that prenom is set (required field)
        participant = participantRepository.save(participant);

        // Étape 3: Création et association du commentaire
        commentaire = new Commentaire();
        commentaire.setCommentaire("C'est un test.");
        commentaire.setSondage(sondage); // Associe à un sondage déjà sauvegardé
        commentaire.setParticipant(participant);
    }

    @Test
    void testCreateCommentaire() {
        // Sauvegarde du commentaire
        Commentaire savedCommentaire = commentaireRepository.save(commentaire);

        // Vérification après sauvegarde
        assertNotNull(savedCommentaire.getCommentaireId()); // Vérifier que l'ID est généré
        assertEquals("C'est un test.", savedCommentaire.getCommentaire());
        assertEquals(sondage.getSondageId(), savedCommentaire.getSondage().getSondageId());
        assertEquals(participant.getParticipantId(), savedCommentaire.getParticipant().getParticipantId());
    }

    @Test
    void testUpdateCommentaire() {
        // Sauvegarde initiale du commentaire
        Commentaire savedCommentaire = commentaireRepository.save(commentaire);

        // Modification du commentaire
        savedCommentaire.setCommentaire("Commentaire modifié");
        Commentaire updatedCommentaire = commentaireRepository.save(savedCommentaire);

        // Vérification de la mise à jour
        assertEquals("Commentaire modifié", updatedCommentaire.getCommentaire());
    }

    @Test
    void testDeleteCommentaire() {
        // Sauvegarde du commentaire
        Commentaire savedCommentaire = commentaireRepository.save(commentaire);

        // Suppression
        commentaireRepository.delete(savedCommentaire);

        // Vérification que le commentaire n'existe plus
        assertFalse(commentaireRepository.findById(savedCommentaire.getCommentaireId()).isPresent());
    }
}
