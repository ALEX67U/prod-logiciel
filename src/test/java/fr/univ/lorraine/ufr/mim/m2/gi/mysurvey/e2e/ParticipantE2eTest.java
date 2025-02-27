package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.MySurveyApplication;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.ParticipantDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Classe de test E2E (End-to-End) pour l'entité Participant.
 * Ces tests permettent de vérifier l'intégration complète des API REST liées aux participants.
 */
@SpringBootTest(classes = MySurveyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class ParticipantE2eTest {

    @Autowired
    private MockMvc mockMvc; // Permet d'exécuter des requêtes HTTP simulées

    @Autowired
    private ObjectMapper objectMapper; // Permet de convertir les objets Java en JSON et inversement

    private static final String BASE_URL_PARTICIPANT = "/api/participant/";
    private static final String PARTICIPANT_FIRST_NAME = "Charlie";
    private static final String PARTICIPANT_LAST_NAME = "Brown";
    private Long participantId; // ID du participant créé pour les tests

    /**
     * Méthode exécutée avant chaque test.
     * Elle crée un participant et stocke son ID pour utilisation dans les tests.
     */
    @BeforeEach
    void setup() throws Exception {
        ParticipantDto newParticipant = new ParticipantDto();
        newParticipant.setNom(PARTICIPANT_LAST_NAME);
        newParticipant.setPrenom(PARTICIPANT_FIRST_NAME);

        // Création d'un participant via une requête HTTP POST
        String response = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL_PARTICIPANT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newParticipant)))
                .andExpect(status().isCreated()) // Vérifie que le participant a bien été créé
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Récupération de l'ID du participant créé pour les tests suivants
        ParticipantDto createdParticipant = objectMapper.readValue(response, ParticipantDto.class);
        participantId = createdParticipant.getParticipantId();
    }

    /**
     * Test de la création d'un participant.
     * Vérifie que l'API REST crée un participant et retourne les bonnes valeurs.
     */
    @Test
    void testCreateParticipant() throws Exception {
        ParticipantDto newParticipant = new ParticipantDto();
        newParticipant.setNom("Smith");
        newParticipant.setPrenom("Alice");

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL_PARTICIPANT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newParticipant)))
                .andExpect(status().isCreated()) // Vérifie que la requête a abouti avec un statut HTTP 201
                .andExpect(jsonPath("$.nom").value("Smith")) // Vérifie le nom
                .andExpect(jsonPath("$.prenom").value("Alice")); // Vérifie le prénom
    }

    /**
     * Test de récupération d'un participant par son ID.
     * Vérifie que l'API retourne bien les informations du participant.
     */
    @Test
    void testGetParticipants() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_PARTICIPANT + participantId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Vérifie que la requête retourne un statut 200
                .andExpect(jsonPath("$.participantId").value(participantId)) // Vérifie l'ID du participant
                .andExpect(jsonPath("$.nom").value(PARTICIPANT_LAST_NAME)) // Vérifie le nom
                .andExpect(jsonPath("$.prenom").value(PARTICIPANT_FIRST_NAME)); // Vérifie le prénom
    }

    /**
     * Test de mise à jour d'un participant existant.
     * Vérifie que les nouvelles informations sont bien enregistrées.
     */
    @Test
    void testUpdateParticipant() throws Exception {
        ParticipantDto updatedParticipant = new ParticipantDto();
        updatedParticipant.setParticipantId(participantId);
        updatedParticipant.setNom("Doe");
        updatedParticipant.setPrenom("Paul");

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL_PARTICIPANT + participantId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedParticipant)))
                .andExpect(status().isOk()) // Vérifie que la requête a réussi
                .andExpect(jsonPath("$.nom").value("Doe")) // Vérifie le nouveau nom
                .andExpect(jsonPath("$.prenom").value("Paul")); // Vérifie le nouveau prénom
    }

    /**
     * Test de suppression d'un participant.
     * Vérifie que la suppression est bien effectuée.
     */
    @Test
    void testDeleteParticipant() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL_PARTICIPANT + participantId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // Vérifie que le participant est supprimé avec succès

    }
}