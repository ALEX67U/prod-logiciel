package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.integration.models;

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

/**
 * Classe de test d'intégration pour la gestion des entités Commentaire.
 * Vérifie les opérations CRUD sur la base de données.
 */
@SpringBootTest(classes = MySurveyApplication.class) // Charge l'application Spring Boot pour les tests d'intégration
@Transactional // Assure que chaque test est isolé et que les données sont restaurées après exécution
class CommentaireIntegrationTest {

    @Autowired
    private CommentaireRepository commentaireRepository; // Repository pour l'entité Commentaire

    @Autowired
    private ParticipantRepository participantRepository; // Repository pour l'entité Participant

    @Autowired
    private SondageRepository sondageRepository; // Repository pour l'entité Sondage

    private Sondage sondage;
    private Participant participant;
    private Commentaire commentaire;

    /**
     * Initialise les données avant chaque test :
     * - Création et sauvegarde d'un sondage
     * - Création et sauvegarde d'un participant
     * - Création d'un commentaire associé aux deux
     */
    @BeforeEach
    void setUp() {
        // Étape 1: Création et sauvegarde d'un sondage
        sondage = new Sondage();
        sondage.setNom("Sondage Test");
        sondage = sondageRepository.save(sondage); // Enregistrement en base de données

        // Étape 2: Création et sauvegarde d'un participant
        participant = new Participant();
        participant.setNom("Pierre");
        participant.setPrenom("Jean"); // S'assure que le prénom est bien défini (champ obligatoire)
        participant = participantRepository.save(participant);

        // Étape 3: Création du commentaire et association aux entités créées
        commentaire = new Commentaire();
        commentaire.setCommentaire("C'est un test.");
        commentaire.setSondage(sondage); // Associe le commentaire au sondage
        commentaire.setParticipant(participant);
    }

    /**
     * Teste la création et la sauvegarde d'un commentaire en base de données.
     * Vérifie que les informations sont bien enregistrées et récupérables.
     */
    @Test
    void testCreateCommentaire() {
        // Sauvegarde du commentaire
        Commentaire savedCommentaire = commentaireRepository.save(commentaire);

        // Vérifications après la sauvegarde
        assertNotNull(savedCommentaire.getCommentaireId(), "L'ID du commentaire doit être généré après la sauvegarde.");
        assertEquals("C'est un test.", savedCommentaire.getCommentaire(), "Le contenu du commentaire doit être correctement enregistré.");
        assertEquals(sondage.getSondageId(), savedCommentaire.getSondage().getSondageId(), "Le commentaire doit être rattaché au bon sondage.");
        assertEquals(participant.getParticipantId(), savedCommentaire.getParticipant().getParticipantId(), "Le commentaire doit être rattaché au bon participant.");
    }

    /**
     * Teste la mise à jour d'un commentaire en base de données.
     * Vérifie que la modification est bien enregistrée.
     */
    @Test
    void testUpdateCommentaire() {
        // Sauvegarde initiale du commentaire
        Commentaire savedCommentaire = commentaireRepository.save(commentaire);

        // Modification du contenu du commentaire
        savedCommentaire.setCommentaire("Commentaire modifié");
        Commentaire updatedCommentaire = commentaireRepository.save(savedCommentaire);

        // Vérification de la mise à jour
        assertEquals("Commentaire modifié", updatedCommentaire.getCommentaire(), "Le commentaire doit être mis à jour correctement.");
    }

    /**
     * Teste la suppression d'un commentaire en base de données.
     * Vérifie que l'entrée est bien supprimée.
     */
    @Test
    void testDeleteCommentaire() {
        // Sauvegarde du commentaire en base de données
        Commentaire savedCommentaire = commentaireRepository.save(commentaire);

        // Suppression du commentaire
        commentaireRepository.delete(savedCommentaire);

        // Vérification que le commentaire n'existe plus en base de données
        assertFalse(commentaireRepository.findById(savedCommentaire.getCommentaireId()).isPresent(),
                "Le commentaire doit être supprimé de la base de données.");
    }
}
