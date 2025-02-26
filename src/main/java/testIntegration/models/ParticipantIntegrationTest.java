package testIntegration.models;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.MySurveyApplication;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.ParticipantRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = MySurveyApplication.class)
@Transactional
class ParticipantIntegrationTest {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ParticipantRepository participantRepository;

    private Participant participant1;

    @BeforeEach
    void setUp() {
        // Création et sauvegarde de participants avant chaque test
        participant1 = new Participant(null, "John", "Doe");
        participant1 = participantService.create(participant1);
    }

    @Test
    void testParticipantInsert() {
        // Vérification de la persistance des participants créés dans setUp()
        Participant retrievedParticipant = participantRepository.findById(participant1.getParticipantId()).orElse(null);

        // Vérification que retrievedParticipant n'est pas null avant d'accéder à ses propriétés
        assertThat(retrievedParticipant).isNotNull();
        if (retrievedParticipant != null) {
            assertThat(retrievedParticipant.getNom()).isEqualTo("John");
            assertThat(retrievedParticipant.getPrenom()).isEqualTo("Doe");
        }
    }

    @Test
    void testParticipantUpdate() {
        // Mise à jour d'un participant
        participant1.setNom("Paul");
        Participant updatedParticipant = participantService.update(participant1.getParticipantId(), participant1);

        // Vérification de la mise à jour
        assertThat(updatedParticipant).isNotNull();
        assertThat(updatedParticipant.getNom()).isEqualTo("Paul");
        assertThat(updatedParticipant.getPrenom()).isEqualTo("Doe");
    }

    @Test
    void testParticipantDelete() {
        // Suppression d'un participant
        participantService.delete(participant1.getParticipantId());

        // Vérification que le participant n'existe plus
        Participant retrievedParticipant = participantRepository.findById(participant1.getParticipantId()).orElse(null);
        assertThat(retrievedParticipant).isNull();
    }
}
