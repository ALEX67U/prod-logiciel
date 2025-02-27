package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.integration.configurations;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.MySurveyApplication;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.CommentaireDto;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Commentaire;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;

/**
 * Classe de test d'intégration pour le mappage des objets entre les DTOs et les entités.
 * Vérifie la conversion entre {@link CommentaireDto} et {@link Commentaire} à l'aide de {@link ModelMapper}.
 */
@SpringBootTest(classes = MySurveyApplication.class) // Charge le contexte de l'application Spring Boot
@Transactional // Assure que chaque test est isolé en annulant les modifications après exécution
class ModelMapperIntegrationIntegrationTest {

    @Autowired
    private ModelMapper modelMapper; // Injection de ModelMapper pour effectuer les conversions

    /**
     * Teste la conversion d'un CommentaireDto vers un Commentaire.
     * Vérifie que les champs essentiels sont correctement mappés.
     */
    @Test
    void testCommentaireDtoToCommentaireMapping() {
        // Création d'un CommentaireDto avec un ID de participant simulé
        CommentaireDto commentaireDto = new CommentaireDto();
        commentaireDto.setParticipant(1L);

        // Conversion en entité Commentaire
        Commentaire commentaire = modelMapper.map(commentaireDto, Commentaire.class);

        // Vérifications
        assertNotNull(commentaire, "L'objet Commentaire ne doit pas être null après le mapping.");
        assertTrue(commentaire.getParticipant() == null || commentaire.getParticipant().getParticipantId() == null,
                "Le Participant ne doit pas être automatiquement instancié, car ModelMapper ne peut pas deviner sa structure.");
    }

    /**
     * Teste la conversion d'un Commentaire en CommentaireDto.
     * Vérifie que l'ID du participant est bien transféré.
     */
    @Test
    void testCommentaireToCommentaireDtoMapping() {
        // Création d'un Participant avec un ID
        Participant participant = new Participant();
        participant.setParticipantId(1L);

        // Création d'un Commentaire avec un participant associé
        Commentaire commentaire = new Commentaire();
        commentaire.setParticipant(participant);

        // Conversion en DTO
        CommentaireDto commentaireDto = modelMapper.map(commentaire, CommentaireDto.class);

        // Vérifications
        assertNotNull(commentaireDto, "L'objet CommentaireDto ne doit pas être null après le mapping.");
        assertEquals(1L, commentaireDto.getParticipant(), "L'ID du participant doit être correctement mappé.");

    }
}