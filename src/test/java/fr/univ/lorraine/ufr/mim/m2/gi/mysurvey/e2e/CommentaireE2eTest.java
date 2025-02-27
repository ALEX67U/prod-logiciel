package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.MySurveyApplication;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.CommentaireDto;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Classe de test E2E (End-to-End) pour la gestion des commentaires dans un sondage.
 * Vérifie le bon fonctionnement de l'API pour la création, la mise à jour et la suppression de commentaires.
 */
@SpringBootTest(classes = MySurveyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class CommentaireE2eTest {

    @Autowired
    private MockMvc mockMvc; // Simule les requêtes HTTP pour tester l'API

    @Autowired
    private ObjectMapper objectMapper; // Convertit les objets en JSON et vice versa

    private ParticipantDto createdParticipant; // Participant utilisé dans les tests
    private SondageDto createdSondage; // Sondage utilisé dans les tests

    // Constantes pour les URLs et messages de test
    private static final String BASE_URL_SONDAGE = "/api/sondage/";
    private static final String BASE_URL_COMMENTAIRES = "/commentaires";
    private static final String EXCELLENT_PRODUIT_COMMENT = "C'est un excellent produit!";
    private static final String COMMENTAIRE_MIS_A_JOUR = "Commentaire mis à jour!";
    private static final String COMMENTAIRE_A_SUPPRIMER = "Commentaire à supprimer!";

    /**
     * Configuration avant chaque test :
     * - Création d'un participant
     * - Création d'un sondage associé au participant
     */
    @BeforeEach
    void setUp() throws Exception {
        // Création d'un participant pour les tests
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

        // Création d'un sondage avec le participant précédemment créé
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
     * Test de création d'un commentaire sur un sondage.
     * Vérifie que le commentaire est bien enregistré et retourné avec le bon contenu.
     */
    @Test
    void testCreateCommentaire() throws Exception {
        CommentaireDto newCommentaire = new CommentaireDto();
        newCommentaire.setCommentaire(EXCELLENT_PRODUIT_COMMENT);
        newCommentaire.setParticipant(createdParticipant.getParticipantId());

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL_SONDAGE + createdSondage.getSondageId() + BASE_URL_COMMENTAIRES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCommentaire)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.commentaire").value(EXCELLENT_PRODUIT_COMMENT));
    }

    /**
     * Test de mise à jour d'un commentaire existant.
     * Vérifie que la modification est bien prise en compte et retournée correctement.
     */
    @Test
    void testUpdateCommentaire() throws Exception {
        // Création d'un commentaire avant modification
        CommentaireDto newCommentaire = new CommentaireDto();
        newCommentaire.setCommentaire(EXCELLENT_PRODUIT_COMMENT);
        newCommentaire.setParticipant(createdParticipant.getParticipantId());

        String response = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL_SONDAGE + createdSondage.getSondageId() + BASE_URL_COMMENTAIRES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCommentaire)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        CommentaireDto createdCommentaire = objectMapper.readValue(response, CommentaireDto.class);
        createdCommentaire.setCommentaire(COMMENTAIRE_MIS_A_JOUR);

        // Mise à jour du commentaire
        mockMvc.perform(MockMvcRequestBuilders.put("/api/commentaire/" + createdCommentaire.getCommentaireId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createdCommentaire)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentaire").value(COMMENTAIRE_MIS_A_JOUR));
    }

    /**
     * Test de suppression d'un commentaire.
     * Vérifie que la suppression se fait sans erreur.
     */
    @Test
    void testDeleteCommentaire() throws Exception {
        // Création d'un commentaire avant suppression
        CommentaireDto newCommentaire = new CommentaireDto();
        newCommentaire.setCommentaire(COMMENTAIRE_A_SUPPRIMER);
        newCommentaire.setParticipant(createdParticipant.getParticipantId());

        String response = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL_SONDAGE + createdSondage.getSondageId() + BASE_URL_COMMENTAIRES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCommentaire)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        CommentaireDto createdCommentaire = objectMapper.readValue(response, CommentaireDto.class);

        // Suppression du commentaire
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/commentaire/" + createdCommentaire.getCommentaireId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
