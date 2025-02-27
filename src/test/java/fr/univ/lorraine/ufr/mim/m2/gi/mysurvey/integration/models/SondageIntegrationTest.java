package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.integration.models;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.MySurveyApplication;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Commentaire;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.CommentaireRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.DateSondageRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.ParticipantRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.SondageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Classe de test d'intégration pour l'entité Sondage.
 *
 * Cette classe teste les opérations CRUD (Create, Read, Update, Delete)
 * sur les sondages ainsi que les relations avec d'autres entités comme
 * Commentaire et DateSondage.
 */
@SpringBootTest(classes = MySurveyApplication.class)
@Transactional
class SondageIntegrationTest {

    @Autowired
    private SondageRepository sondageRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private DateSondageRepository dateSondageRepository;

    private Participant createBy; // Participant qui a créé le sondage
    private Sondage sondage; // Instance de Sondage pour les tests

    /**
     * Configuration préalable à l'exécution de chaque test.
     * Crée et sauvegarde un participant ainsi qu'un sondage.
     */
    @BeforeEach
    void setUp() {
        // Créer et sauvegarder un participant (créateur du sondage)
        createBy = new Participant();
        createBy.setNom("Admin");
        createBy.setPrenom("User");
        createBy = participantRepository.save(createBy); // Sauvegarde du participant

        // Créer et sauvegarder un sondage
        sondage = new Sondage();
        sondage.setNom("Sondage Test");
        sondage.setDescription("Description du sondage");
        sondage.setFin(new Date());
        sondage.setCloture(false);
        sondage.setCreateBy(createBy);
        sondage = sondageRepository.save(sondage); // Sauvegarde du sondage
    }

    /**
     * Teste la création et la récupération d'un sondage.
     * Vérifie que le sondage est bien sauvegardé et que ses attributs sont corrects.
     */
    @Test
    void testCreateAndRetrieveSondage() {
        // Vérifier que le sondage a bien été sauvegardé
        assertThat(sondage.getSondageId()).isNotNull();

        // Récupérer le sondage
        Sondage retrieved = sondageRepository.findById(sondage.getSondageId()).orElse(null);

        // Vérifier les valeurs
        assertThat(retrieved).isNotNull(); // Vérification de la non-nullité
        if (retrieved != null) {
            assertThat(retrieved.getNom()).isEqualTo("Sondage Test");
            assertThat(retrieved.getDescription()).isEqualTo("Description du sondage");
            assertThat(retrieved.getCreateBy()).isEqualTo(createBy);
        }
    }

    /**
     * Teste la mise à jour d'un sondage.
     * Vérifie que les modifications apportées au sondage sont correctement enregistrées.
     */
    @Test
    void testUpdateSondage() {
        // Modifier le sondage
        sondage.setNom("Sondage Modifié");
        sondage.setDescription("Nouvelle description");
        sondage.setCloture(true);
        sondage = sondageRepository.save(sondage); // Sauvegarde des modifications

        // Vérifier la mise à jour
        Sondage updated = sondageRepository.findById(sondage.getSondageId()).orElse(null);

        assertThat(updated).isNotNull(); // Vérification de la non-nullité
        if (updated != null) {
            assertThat(updated.getNom()).isEqualTo("Sondage Modifié");
            assertThat(updated.getDescription()).isEqualTo("Nouvelle description");
            assertThat(updated.getCloture()).isTrue();
        }
    }

    /**
     * Teste la suppression d'un sondage.
     * Vérifie que le sondage est bien supprimé de la base de données.
     */
    @Test
    void testDeleteSondage() {
        // Supprimer le sondage
        sondageRepository.delete(sondage);

        // Vérifier qu'il n'existe plus en base
        assertThat(sondageRepository.findById(sondage.getSondageId())).isEmpty();
    }

    /**
     * Teste les relations d'un sondage avec d'autres entités.
     * Vérifie que les commentaires et les dates de sondage associés sont correctement ajoutés.
     */
    @Test
    void testSondageRelationships() {
        // Ajouter un commentaire
        Commentaire commentaire = new Commentaire();
        commentaire.setSondage(sondage);
        commentaireRepository.save(commentaire); // Sauvegarder le commentaire
        sondage.getCommentaires().add(commentaire); // Ajouter à la liste de Sondage

        // Ajouter une date de sondage
        DateSondage dateSondage = new DateSondage();
        dateSondage.setSondage(sondage);
        dateSondageRepository.save(dateSondage); // Sauvegarder la date de sondage
        sondage.getDateSondage().add(dateSondage); // Ajouter à la liste de Sondage

        // Enregistrer à nouveau le sondage
        sondageRepository.save(sondage); // Assurez-vous que le sondage est mis à jour

        // Vérifier les relations
        Sondage retrieved = sondageRepository.findById(sondage.getSondageId()).orElse(null);

        assertThat(retrieved).isNotNull(); // Vérification de la non-nullité
        if (retrieved != null) {
            assertThat(retrieved.getCommentaires()).hasSize(1); // Vérifier qu'il y a un commentaire
            assertThat(retrieved.getDateSondage()).hasSize(1); // Vérifier qu'il y a une date de sondage
        }
    }
}
