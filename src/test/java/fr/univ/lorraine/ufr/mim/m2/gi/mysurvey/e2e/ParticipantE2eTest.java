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

@SpringBootTest(classes = MySurveyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class ParticipantE2eTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASE_URL_PARTICIPANT = "/api/participant/";
    private static final String PARTICIPANT_FIRST_NAME = "Charlie";
    private static final String PARTICIPANT_LAST_NAME = "Brown";
    private Long participantId; // ID du participant créé pour les tests

    @BeforeEach
    void setup() throws Exception {
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

        ParticipantDto createdParticipant = objectMapper.readValue(response, ParticipantDto.class);
        participantId = createdParticipant.getParticipantId();
    }

    @Test
    void testCreateParticipant() throws Exception {
        ParticipantDto newParticipant = new ParticipantDto();
        newParticipant.setNom("Smith");
        newParticipant.setPrenom("Alice");

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL_PARTICIPANT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newParticipant)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value("Smith"))
                .andExpect(jsonPath("$.prenom").value("Alice"));
    }

    @Test
    void testGetParticipants() throws Exception {
        // Vérifier que le participant existe via son ID
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_PARTICIPANT + participantId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.participantId").value(participantId))
                .andExpect(jsonPath("$.nom").value(PARTICIPANT_LAST_NAME))
                .andExpect(jsonPath("$.prenom").value(PARTICIPANT_FIRST_NAME));
    }

    @Test
    void testUpdateParticipant() throws Exception {
        ParticipantDto updatedParticipant = new ParticipantDto();
        updatedParticipant.setParticipantId(participantId);
        updatedParticipant.setNom("Doe");
        updatedParticipant.setPrenom("Paul");

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL_PARTICIPANT + participantId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedParticipant)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Doe"))
                .andExpect(jsonPath("$.prenom").value("Paul"));
    }

    @Test
    void testDeleteParticipant() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL_PARTICIPANT + participantId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }
}
