package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.integration.models;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.MySurveyApplication;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Choix;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondee;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.DateSondeeRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.DateSondageRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.ParticipantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Classe de test d'intégration pour les entités DateSondee.
 *
 * Cette classe teste les opérations CRUD (Create, Read, Update, Delete) sur les
 * entités DateSondee et leur interaction avec les repositories associés.
 */
@SpringBootTest(classes = MySurveyApplication.class)
@Transactional
class DateSondeeIntegrationTest {

    @Autowired
    private DateSondeeRepository dateSondeeRepository;

    @Autowired
    private DateSondageRepository dateSondageRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    private DateSondage dateSondage; // Instance de DateSondage utilisée pour les tests
    private Participant participant; // Instance de Participant utilisée pour les tests

    /**
     * Configuration préalable à l'exécution de chaque test.
     * Crée un DateSondage et un Participant avec des valeurs valides.
     */
    @BeforeEach
    void setUp() {
        // Créer un DateSondage
        dateSondage = new DateSondage();
        dateSondageRepository.save(dateSondage); // Sauvegarde de la date de sondage

        // Créer un participant avec des valeurs valides
        participant = new Participant();
        participant.setNom("Pierre");
        participant.setPrenom("Jean"); // Assurez-vous que le prénom est défini (champ requis)
        participant = participantRepository.save(participant); // Sauvegarde du participant en base
    }

    /**
     * Teste la création et la récupération d'une DateSondee.
     * Vérifie que la DateSondee est correctement sauvegardée en base de données.
     */
    @Test
    void testCreateAndRetrieveDateSondee() {
        // Créer une DateSondee et la sauvegarder
        DateSondee dateSondee = new DateSondee(null, dateSondage, participant, Choix.DISPONIBLE);
        DateSondee savedDateSondee = dateSondeeRepository.save(dateSondee);

        // Vérifier que l'objet a bien été sauvegardé
        assertThat(savedDateSondee).isNotNull();
        assertThat(savedDateSondee.getDateSondeeId()).isNotNull(); // Vérifie que l'ID est généré
        assertThat(savedDateSondee.getChoix()).isEqualTo(Choix.DISPONIBLE.name()); // Vérifie que le choix est correct
        assertThat(savedDateSondee.getDateSondage()).isEqualTo(dateSondage); // Vérifie l'association avec DateSondage
        assertThat(savedDateSondee.getParticipant()).isEqualTo(participant); // Vérifie l'association avec Participant
    }

    /**
     * Teste la mise à jour d'une DateSondee.
     * Vérifie que le choix peut être modifié et sauvegardé.
     */
    @Test
    void testUpdateDateSondee() {
        // Insérer une DateSondee
        DateSondee dateSondee = new DateSondee(null, dateSondage, participant, Choix.DISPONIBLE);
        DateSondee savedDateSondee = dateSondeeRepository.save(dateSondee);

        // Modifier le choix
        savedDateSondee.setChoix(Choix.INDISPONIBLE.name()); // Modification du choix
        DateSondee updatedDateSondee = dateSondeeRepository.save(savedDateSondee);

        // Vérifier que la mise à jour a bien été effectuée
        assertThat(updatedDateSondee.getChoix()).isEqualTo(Choix.INDISPONIBLE.name()); // Vérifie que le choix a été mis à jour
    }

    /**
     * Teste la suppression d'une DateSondee.
     * Vérifie que la DateSondee peut être supprimée de la base de données.
     */
    @Test
    void testDeleteDateSondee() {
        // Insérer une DateSondee
        DateSondee dateSondee = new DateSondee(null, dateSondage, participant, Choix.PEUTETRE);
        DateSondee savedDateSondee = dateSondeeRepository.save(dateSondee);

        // Supprimer la DateSondee
        dateSondeeRepository.delete(savedDateSondee);

        // Vérifier que l'entité a bien été supprimée
        assertThat(dateSondeeRepository.findById(savedDateSondee.getDateSondeeId())).isEmpty(); // Vérifie que l'objet n'existe plus
    }

}
