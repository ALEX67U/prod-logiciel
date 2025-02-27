package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.MySurveyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe de test E2E (End-to-End) pour les opérations liées aux dates de sondage.
 * Vérifie l'intégration et le bon fonctionnement de la suppression d'une date de sondage via l'API REST.
 */
@SpringBootTest(classes = MySurveyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class DateSondageE2eTest {

    @Autowired
    private MockMvc mockMvc; // Simule les requêtes HTTP pour tester l'API

    /**
     * Test la suppression d'une date de sondage.
     * Vérifie que l'API retourne un statut HTTP 200 (OK) après suppression.
     */
    @Test
    public void testDeleteParticipation() throws Exception {
        Long dateSondageId = 1L; // ID de la date de sondage à supprimer (valeur fictive)

        // Envoie une requête DELETE à l'API et vérifie la réponse
        mockMvc.perform(delete("/api/date/{id}", dateSondageId))
                .andExpect(status().isOk()); // Vérifie que la suppression est réussie
    }
}
