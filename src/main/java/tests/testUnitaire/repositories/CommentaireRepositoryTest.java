package tests.testUnitaire.repositories;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Commentaire;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.CommentaireRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.CommentaireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentaireRepositoryTest {

    @Mock
    private CommentaireRepository commentaireRepository;  // Mock du repository

    @InjectMocks
    private CommentaireService commentaireService;  // Service utilisant le repository

    private Commentaire commentaire1;
    private Commentaire commentaire2;

    @BeforeEach
    void setUp() {
        // Initialisation de Mockito et des objets Commentaire
        MockitoAnnotations.openMocks(this);

        commentaire1 = new Commentaire();
        commentaire1.setCommentaire("Premier commentaire");  // Utilisation de setCommentaire

        commentaire2 = new Commentaire();
        commentaire2.setCommentaire("Deuxième commentaire");  // Utilisation de setCommentaire
    }

    @Test
    void testGetAllBySondage() {
        // Configuration du comportement du mock pour simuler la méthode getAllBySondage
        when(commentaireRepository.getAllBySondage(1L))
                .thenReturn(Arrays.asList(commentaire1, commentaire2));

        // Appel de la méthode dans le service
        List<Commentaire> result = commentaireService.getBySondageId(1L);  // Appel du service

        // Vérification des résultats
        assertEquals(2, result.size(), "Le nombre de commentaires doit être 2");
        assertEquals("Premier commentaire", result.get(0).getCommentaire(), "Le message du premier commentaire ne correspond pas");
        assertEquals("Deuxième commentaire", result.get(1).getCommentaire(), "Le message du deuxième commentaire ne correspond pas");
    }
}
