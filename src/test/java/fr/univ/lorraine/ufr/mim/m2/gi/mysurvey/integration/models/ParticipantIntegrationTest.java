package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.integration.models;

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

/**
 * Classe de test d'intégration pour l'entité Participant.
 *
 * Cette classe teste les opérations CRUD (Create, Read, Update, Delete)
 * sur les participants via le service et le repository associés.
 */
@SpringBootTest(classes = MySurveyApplication.class)
@Transactional
class ParticipantIntegrationTest {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ParticipantRepository participantRepository;

    private Participant participant1; // Instance de Participant utilisée pour les tests

    /**
     * Configuration préalable à l'exécution de chaque test.
     * Crée et sauvegarde un participant avec des valeurs valides.
     */
    @BeforeEach
    void setUp() {
        // Création et sauvegarde de participants avant chaque test
        participant1 = new Participant(null, "John", "Doe");
        participant1 = participantService.create(participant1); // Création via le service
    }

    /**
     * Teste l'insertion d'un participant.
     * Vérifie que le participant créé est bien persistant dans la base de données.
     */
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

    /**
     * Teste la mise à jour d'un participant.
     * Vérifie que les informations du participant sont mises à jour correctement.
     */
    @Test
    void testParticipantUpdate() {
        // Mise à jour d'un participant
        participant1.setNom("Paul");
        Participant updatedParticipant = participantService.update(participant1.getParticipantId(), participant1); // Mise à jour via le service

        // Vérification de la mise à jour
        assertThat(updatedParticipant).isNotNull();
        assertThat(updatedParticipant.getNom()).isEqualTo("Paul");
        assertThat(updatedParticipant.getPrenom()).isEqualTo("Doe");
    }

    /**
     * Teste la suppression d'un participant.
     * Vérifie que le participant est bien supprimé de la base de données.
     */
    @Test
    void testParticipantDelete() {
        // Suppression d'un participant
        participantService.delete(participant1.getParticipantId());

        // Vérification que le participant n'existe plus
        Participant retrievedParticipant = participantRepository.findById(participant1.getParticipantId()).orElse(null);
        assertThat(retrievedParticipant).isNull(); // Vérifie que l'objet n'existe plus
    }
}
