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

/**
 * Classe de test pour le contrôleur CommentaireController.
 *
 * Cette classe teste les méthodes du contrôleur, y compris la mise à jour
 * et la suppression des commentaires, en utilisant des objets simulés.
 */
@ExtendWith(MockitoExtension.class)
class CommentaireControllerTest {

    @Mock
    private CommentaireService commentaireService; // Service de gestion des commentaires

    @Mock
    private ModelMapper modelMapper; // Mapper pour les conversions entre Commentaire et CommentaireDto

    @InjectMocks
    private CommentaireController commentaireController; // Instance de CommentaireController à tester

    private Commentaire commentaire; // Objet Commentaire pour les tests
    private CommentaireDto commentaireDto; // Objet CommentaireDto pour les tests

    /**
     * Configuration préalable à l'exécution de chaque test.
     * Initialise les objets Commentaire et CommentaireDto.
     */
    @BeforeEach
    void setUp() {
        commentaire = new Commentaire(); // Initialisation d'un nouveau commentaire
        commentaireDto = new CommentaireDto(); // Initialisation d'un nouveau CommentaireDto
    }

    /**
     * Teste la méthode de mise à jour d'un commentaire dans le contrôleur.
     * Vérifie que le commentaire est correctement mis à jour et renvoyé.
     */
    @Test
    void testUpdateCommentaire() {
        Long id = 1L; // ID du commentaire à mettre à jour

        // Simuler le mapping DTO -> Entity
        when(modelMapper.map(commentaireDto, Commentaire.class)).thenReturn(commentaire);
        when(commentaireService.update(id, commentaire)).thenReturn(commentaire);
        when(modelMapper.map(commentaire, CommentaireDto.class)).thenReturn(commentaireDto);

        // Appel de la méthode
        CommentaireDto result = commentaireController.update(id, commentaireDto);

        // Vérifications
        assertNotNull(result, "Le résultat ne doit pas être nul."); // Vérifie que le résultat n'est pas null
        assertEquals(commentaireDto, result, "Le commentaire retourné ne correspond pas à celui attendu."); // Vérifie que le commentaire est le bon

        // Vérification des appels
        verify(modelMapper, times(1)).map(commentaireDto, Commentaire.class); // Vérifie que le mapping a été effectué
        verify(commentaireService, times(1)).update(id, commentaire); // Vérifie que le service a été appelé pour mettre à jour
        verify(modelMapper, times(1)).map(commentaire, CommentaireDto.class); // Vérifie que le mapping inverse a été effectué
    }

    /**
     * Teste la méthode de suppression d'un commentaire dans le contrôleur.
     * Vérifie que le service de commentaires est appelé avec le bon ID.
     */
    @Test
    void testDeleteCommentaire() {
        Long id = 1L; // ID du commentaire à supprimer

        // Appel de la méthode
        commentaireController.delete(id);

        // Vérifier que le service a bien été appelé une fois
        verify(commentaireService, times(1)).delete(id); // Vérifie que le service a été appelé pour supprimer le commentaire
    }
}
