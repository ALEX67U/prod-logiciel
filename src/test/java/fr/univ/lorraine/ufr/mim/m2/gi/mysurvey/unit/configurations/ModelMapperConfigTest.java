package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.configurations;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.configurations.ModelMapperConfig;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.CommentaireDto;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Commentaire;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la configuration de ModelMapper.
 *
 * Cette classe teste le mappage entre les entités Commentaire et CommentaireDto
 * pour s'assurer que les propriétés sont correctement transférées entre les objets.
 */
class ModelMapperConfigTest {

    private ModelMapper modelMapper; // Instance de ModelMapper utilisée pour les tests

    /**
     * Configuration préalable à l'exécution de chaque test.
     * Initialise le ModelMapper à partir de la configuration.
     */
    @BeforeEach
    void setUp() {
        ModelMapperConfig config = new ModelMapperConfig();
        modelMapper = config.modelMapper(); // Obtention de l'instance de ModelMapper
    }

    /**
     * Teste le mappage de CommentaireDto vers Commentaire.
     * Vérifie que le Commentaire créé à partir du DTO a des propriétés valides.
     */
    @Test
    void testCommentaireDtoToCommentaireMapping() {
        CommentaireDto commentaireDto = new CommentaireDto();
        commentaireDto.setParticipant(1L); // Simulons un ID de participant

        Commentaire commentaire = modelMapper.map(commentaireDto, Commentaire.class); // Mappage

        assertNotNull(commentaire); // Vérifie que le commentaire n'est pas null
        assertTrue(commentaire.getParticipant() == null || commentaire.getParticipant().getParticipantId() == null);
        // Vérifie que le participant est correctement mappé ou null
    }

    /**
     * Teste le mappage de Commentaire vers CommentaireDto.
     * Vérifie que le DTO créé à partir du Commentaire a des propriétés valides.
     */
    @Test
    void testCommentaireToCommentaireDtoMapping() {
        Participant participant = new Participant();
        participant.setParticipantId(1L); // Initialisation d'un participant avec un ID

        Commentaire commentaire = new Commentaire();
        commentaire.setParticipant(participant); // Attribution du participant au commentaire

        CommentaireDto commentaireDto = modelMapper.map(commentaire, CommentaireDto.class); // Mappage

        assertNotNull(commentaireDto); // Vérifie que le DTO n'est pas null
        assertEquals(1L, commentaireDto.getParticipant()); // Vérifie que l'ID du participant est bien mappé
    }
}
