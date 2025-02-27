package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.e2e;

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

/**
 * Classe de test E2E pour les opérations sur les sondages.
 *
 * Cette classe teste les fonctionnalités CRUD des sondages via des requêtes HTTP.
 */
@SpringBootTest(classes = MySurveyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class SondageE2ETest {

    @Autowired
    private MockMvc mockMvc; // MockMvc pour simuler des requêtes HTTP

    @Autowired
    private ObjectMapper objectMapper; // Mapper pour la sérialisation/désérialisation JSON

    private ParticipantDto createdParticipant; // Participant créé pour les tests
    private SondageDto createdSondage; // Sondage créé pour les tests

    // Constante pour éviter la duplication de l'URL de sondage
    private static final String BASE_URL_SONDAGE = "/api/sondage/";

    /**
     * Méthode exécutée avant chaque test.
     * Elle initialise les données nécessaires à chaque test.
     */
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

        String response = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL_SONDAGE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSondage)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        createdSondage = objectMapper.readValue(response, SondageDto.class);
    }

    /**
     * Teste la création d'un nouveau sondage.
     * Vérifie que le sondage est correctement créé et que les détails sont corrects.
     */
    @Test
    void testCreateSondage() throws Exception {
        // Vérifier la création d'un sondage
        SondageDto newSondage = new SondageDto();
        newSondage.setCreateBy(createdParticipant.getParticipantId());
        newSondage.setNom("Nouveau sondage");
        newSondage.setDescription("Ce sondage est pour tester la création.");

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL_SONDAGE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSondage)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value("Nouveau sondage"))
                .andExpect(jsonPath("$.description").value("Ce sondage est pour tester la création."));
    }

    /**
     * Teste la récupération de tous les sondages.
     * Vérifie que la réponse contient un tableau de sondages.
     */
    @Test
    void testGetSondages() throws Exception {
        // Tester la récupération de sondages
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_SONDAGE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]").isArray());
    }

    /**
     * Teste la mise à jour d'un sondage existant.
     * Vérifie que les détails du sondage sont mis à jour correctement.
     */
    @Test
    void testUpdateSondage() throws Exception {
        // Mettre à jour le sondage créé dans setUp
        createdSondage.setNom("Sondage de satisfaction mis à jour");
        createdSondage.setDescription("Mise à jour du sondage pour évaluer la satisfaction des utilisateurs.");

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL_SONDAGE + createdSondage.getSondageId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createdSondage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Sondage de satisfaction mis à jour"))
                .andExpect(jsonPath("$.description").value("Mise à jour du sondage pour évaluer la satisfaction des utilisateurs."));
    }

    /**
     * Teste la suppression d'un sondage existant.
     * Vérifie que la suppression est effectuée avec succès.
     */
    @Test
    void testDeleteSondage() throws Exception {
        // Tester la suppression du sondage créé dans setUp
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL_SONDAGE + createdSondage.getSondageId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
