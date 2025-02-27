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

@SpringBootTest(classes = MySurveyApplication.class)
@Transactional
class DateSondeeIntegrationTest {

    @Autowired
    private DateSondeeRepository dateSondeeRepository;

    @Autowired
    private DateSondageRepository dateSondageRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    private DateSondage dateSondage;
    private Participant participant;

    @BeforeEach
    void setUp() {
        // Créer un DateSondage et un Participant avec des valeurs valides
        dateSondage = new DateSondage();
        dateSondageRepository.save(dateSondage);

        // Étape 2: Sauvegarde d'un participant en base
        participant = new Participant();
        participant.setNom("Pierre");
        participant.setPrenom("Jean"); // Ensure that prenom is set (required field)
        participant = participantRepository.save(participant);

    }

    @Test
    void testCreateAndRetrieveDateSondee() {
        // Créer une DateSondee et la sauvegarder
        DateSondee dateSondee = new DateSondee(null, dateSondage, participant, Choix.DISPONIBLE);
        DateSondee savedDateSondee = dateSondeeRepository.save(dateSondee);

        // Vérifier que l'objet a bien été sauvegardé
        assertThat(savedDateSondee).isNotNull();
        assertThat(savedDateSondee.getDateSondeeId()).isNotNull();
        assertThat(savedDateSondee.getChoix()).isEqualTo(Choix.DISPONIBLE.name()); // Correction ici
        assertThat(savedDateSondee.getDateSondage()).isEqualTo(dateSondage);
        assertThat(savedDateSondee.getParticipant()).isEqualTo(participant);
    }

    @Test
    void testUpdateDateSondee() {
        // Insérer une DateSondee
        DateSondee dateSondee = new DateSondee(null, dateSondage, participant, Choix.DISPONIBLE);
        DateSondee savedDateSondee = dateSondeeRepository.save(dateSondee);

        // Modifier le choix
        savedDateSondee.setChoix(Choix.INDISPONIBLE.name()); // Correction ici
        DateSondee updatedDateSondee = dateSondeeRepository.save(savedDateSondee);

        // Vérifier que la mise à jour a bien été effectuée
        assertThat(updatedDateSondee.getChoix()).isEqualTo(Choix.INDISPONIBLE.name()); // Correction ici
    }

    @Test
    void testDeleteDateSondee() {
        // Insérer une DateSondee
        DateSondee dateSondee = new DateSondee(null, dateSondage, participant, Choix.PEUTETRE);
        DateSondee savedDateSondee = dateSondeeRepository.save(dateSondee);

        // Supprimer la DateSondee
        dateSondeeRepository.delete(savedDateSondee);

        // Vérifier que l'entité a bien été supprimée
        assertThat(dateSondeeRepository.findById(savedDateSondee.getDateSondeeId())).isEmpty();
    }

}
