package e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.MySurveyApplication;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.ParticipantDto;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.SondageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = MySurveyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class SondageE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ParticipantDto createdParticipant;
    private SondageDto createdSondage;

    @BeforeEach
    void setUp() throws Exception {
        // Créer un participant pour les tests
        ParticipantDto newParticipant = new ParticipantDto();
        newParticipant.setNom("Charlie");
        newParticipant.setPrenom("Brown");

        String participantResponse = mockMvc.perform(MockMvcRequestBuilders.post("/api/participant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newParticipant)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        createdParticipant = objectMapper.readValue(participantResponse, ParticipantDto.class);

        // Créer un sondage avec le participant créé
        SondageDto newSondage = new SondageDto();
        newSondage.setCreateBy(createdParticipant.getParticipantId());
        newSondage.setNom("Sondage de satisfaction");
        newSondage.setDescription("Ce sondage vise à évaluer la satisfaction des utilisateurs.");

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/sondage/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSondage)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        createdSondage = objectMapper.readValue(response, SondageDto.class);
    }

    @Test
    void testCreateSondage() throws Exception {
        // Verifier la création d'un sondage
        SondageDto newSondage = new SondageDto();
        newSondage.setCreateBy(createdParticipant.getParticipantId());
        newSondage.setNom("Nouveau sondage");
        newSondage.setDescription("Ce sondage est pour tester la création.");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/sondage/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSondage)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value("Nouveau sondage"))
                .andExpect(jsonPath("$.description").value("Ce sondage est pour tester la création."));
    }

    @Test
    void testGetSondages() throws Exception {
        // Tester la récupération de sondages
        mockMvc.perform(MockMvcRequestBuilders.get("/api/sondage/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]").isArray());
    }

    @Test
    void testUpdateSondage() throws Exception {
        // Mettre à jour le sondage créé dans setUp
        createdSondage.setNom("Sondage de satisfaction mis à jour");
        createdSondage.setDescription("Mise à jour du sondage pour évaluer la satisfaction des utilisateurs.");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/sondage/" + createdSondage.getSondageId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createdSondage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Sondage de satisfaction mis à jour"))
                .andExpect(jsonPath("$.description").value("Mise à jour du sondage pour évaluer la satisfaction des utilisateurs."));
    }

    @Test
    void testDeleteSondage() throws Exception {
        // Tester la suppression du sondage créé dans setUp
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/sondage/" + createdSondage.getSondageId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
