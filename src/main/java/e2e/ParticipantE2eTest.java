package e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.MySurveyApplication;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.ParticipantDto;
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

@SpringBootTest(classes = MySurveyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class ParticipantE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Constantes pour éviter la duplication de chaînes
    private static final String BASE_URL_PARTICIPANT = "/api/participant/";
    private static final String PARTICIPANT_FIRST_NAME = "Charlie";
    private static final String PARTICIPANT_LAST_NAME = "Brown";
    private static final String UPDATED_FIRST_NAME = "Paul";
    private static final String UPDATED_LAST_NAME = "Doe";

    @Test
    void testCreateParticipant() throws Exception {
        ParticipantDto newParticipant = new ParticipantDto();
        newParticipant.setNom(PARTICIPANT_LAST_NAME);
        newParticipant.setPrenom(PARTICIPANT_FIRST_NAME);

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL_PARTICIPANT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newParticipant)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value(PARTICIPANT_LAST_NAME))
                .andExpect(jsonPath("$.prenom").value(PARTICIPANT_FIRST_NAME));
    }

    @Test
    void testGetParticipants() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_PARTICIPANT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]").isArray());
    }

    @Test
    void testUpdateParticipant() throws Exception {
        // Créer un participant d'abord
        ParticipantDto newParticipant = new ParticipantDto();
        newParticipant.setNom(PARTICIPANT_LAST_NAME);
        newParticipant.setPrenom(PARTICIPANT_FIRST_NAME);

        String response = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL_PARTICIPANT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newParticipant)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Extraire l'ID du participant créé
        ParticipantDto createdParticipant = objectMapper.readValue(response, ParticipantDto.class);

        // Mettre à jour le participant
        createdParticipant.setNom(UPDATED_LAST_NAME);
        createdParticipant.setPrenom(UPDATED_FIRST_NAME);

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL_PARTICIPANT + createdParticipant.getParticipantId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createdParticipant)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value(UPDATED_LAST_NAME));
    }

    @Test
    void testDeleteParticipant() throws Exception {
        // Supprimer un participant par ID (doit être remplacé par un ID existant dans votre base de données pour les tests)
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL_PARTICIPANT + "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
