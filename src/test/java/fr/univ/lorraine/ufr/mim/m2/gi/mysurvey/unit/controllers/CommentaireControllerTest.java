package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.controllers;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.controllers.CommentaireController;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.CommentaireDto;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Commentaire;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.CommentaireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentaireControllerTest {

    @Mock
    private CommentaireService commentaireService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CommentaireController commentaireController;

    private Commentaire commentaire;
    private CommentaireDto commentaireDto;

    @BeforeEach
    void setUp() {
        commentaire = new Commentaire();
        commentaireDto = new CommentaireDto();
    }

    @Test
    void testUpdateCommentaire() {
        Long id = 1L;

        // Simuler le mapping DTO -> Entity
        when(modelMapper.map(commentaireDto, Commentaire.class)).thenReturn(commentaire);
        when(commentaireService.update(id, commentaire)).thenReturn(commentaire);
        when(modelMapper.map(commentaire, CommentaireDto.class)).thenReturn(commentaireDto);

        // Appel de la méthode
        CommentaireDto result = commentaireController.update(id, commentaireDto);

        // Vérifications
        assertNotNull(result, "Le résultat ne doit pas être nul.");
        assertEquals(commentaireDto, result, "Le commentaire retourné ne correspond pas à celui attendu.");

        // Vérification des appels
        verify(modelMapper, times(1)).map(commentaireDto, Commentaire.class);
        verify(commentaireService, times(1)).update(id, commentaire);
        verify(modelMapper, times(1)).map(commentaire, CommentaireDto.class);
    }

    @Test
    void testDeleteCommentaire() {
        Long id = 1L;

        // Appel de la méthode
        commentaireController.delete(id);

        // Vérifier que le service a bien été appelé une fois
        verify(commentaireService, times(1)).delete(id);
    }
}
