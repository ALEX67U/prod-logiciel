package tests.testIntegration.configurations;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.MySurveyApplication;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.CommentaireDto;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Commentaire;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MySurveyApplication.class) // Utilisez votre classe principale de l'application
class ModelMapperIntegrationIntegrationTest {

    @Autowired
    private ModelMapper modelMapper; // Injection de ModelMapper

    @Test
    void testCommentaireDtoToCommentaireMapping() {
        CommentaireDto commentaireDto = new CommentaireDto();
        commentaireDto.setParticipant(1L); // Simulons un ID de participant

        Commentaire commentaire = modelMapper.map(commentaireDto, Commentaire.class);

        assertNotNull(commentaire);
        assertTrue(commentaire.getParticipant() == null || commentaire.getParticipant().getParticipantId() == null);
    }

    @Test
    void testCommentaireToCommentaireDtoMapping() {
        Participant participant = new Participant();
        participant.setParticipantId(1L);

        Commentaire commentaire = new Commentaire();
        commentaire.setParticipant(participant);

        CommentaireDto commentaireDto = modelMapper.map(commentaire, CommentaireDto.class);

        assertNotNull(commentaireDto);
        assertEquals(1L, commentaireDto.getParticipant()); // Vérifie que l'ID du participant est bien mappé
    }
}
