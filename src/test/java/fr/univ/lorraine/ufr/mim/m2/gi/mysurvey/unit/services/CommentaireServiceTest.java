package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.services;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Commentaire;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.CommentaireRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.CommentaireService;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.SondageService;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour le CommentaireService.
 *
 * Cette classe teste les fonctionnalités de gestion des commentaires.
 */
public class CommentaireServiceTest {

    @Mock
    private CommentaireRepository commentaireRepository; // Mock du repository pour les commentaires

    @Mock
    private SondageService sondageService; // Mock du service pour les sondages

    @Mock
    private ParticipantService participantService; // Mock du service pour les participants

    @InjectMocks
    private CommentaireService commentaireService; // Service à tester

    private Commentaire commentaire1;
    private Commentaire commentaire2;

    /**
     * Méthode exécutée avant chaque test.
     * Elle initialise les mocks et crée des objets Commentaire pour les tests.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks

        // Création de commentaires pour les tests
        commentaire1 = new Commentaire();
        commentaire1.setCommentaire("Premier commentaire");

        commentaire2 = new Commentaire();
        commentaire2.setCommentaire("Deuxième commentaire");
    }

    /**
     * Teste la méthode getBySondageId du CommentaireService.
     * Vérifie que les commentaires sont correctement récupérés par ID de sondage.
     */
    @Test
    void testGetBySondageId() {
        // Configurer le mock pour la méthode getAllBySondage
        when(commentaireRepository.getAllBySondage(1L)).thenReturn(Arrays.asList(commentaire1, commentaire2));

        // Appel de la méthode dans le service
        var result = commentaireService.getBySondageId(1L);

        // Vérification des résultats
        assertEquals(2, result.size(), "Le nombre de commentaires doit être 2");
        assertEquals("Premier commentaire", result.get(0).getCommentaire(), "Le message du premier commentaire ne correspond pas");
        assertEquals("Deuxième commentaire", result.get(1).getCommentaire(), "Le message du deuxième commentaire ne correspond pas");

        // Vérifier que la méthode getAllBySondage a bien été appelée
        verify(commentaireRepository, times(1)).getAllBySondage(1L);
    }

    /**
     * Teste la méthode addCommantaire du CommentaireService.
     * Vérifie que l'ajout d'un commentaire fonctionne correctement.
     */
    @Test
    void testAddCommentaire() {
        // Données de test
        Long sondageId = 1L;
        Long participantId = 1L;
        Commentaire commentaire = new Commentaire();
        Sondage sondage = new Sondage();
        Participant participant = new Participant();

        // Simuler le comportement des services
        when(sondageService.getById(sondageId)).thenReturn(sondage);
        when(participantService.getById(participantId)).thenReturn(participant);
        when(commentaireRepository.save(commentaire)).thenReturn(commentaire);

        // Appel de la méthode addCommentaire
        Commentaire result = commentaireService.addCommantaire(sondageId, participantId, commentaire);

        // Vérifications des résultats
        assertNotNull(result, "Le commentaire ne doit pas être null");
        assertEquals(sondage, result.getSondage(), "Le sondage doit être correctement attribué");
        assertEquals(participant, result.getParticipant(), "Le participant doit être correctement attribué");
    }

    /**
     * Teste la méthode update du CommentaireService.
     * Vérifie que la mise à jour d'un commentaire fonctionne correctement.
     */
    @Test
    void testUpdate() {
        // Données de test
        Long commentaireId = 1L;
        Commentaire commentaire = new Commentaire();
        commentaire.setCommentaireId(commentaireId);
        Sondage sondage = new Sondage();
        Participant participant = new Participant();
        commentaire.setSondage(sondage);
        commentaire.setParticipant(participant);

        // Simuler le comportement de la méthode de mise à jour
        when(commentaireRepository.findById(commentaireId)).thenReturn(Optional.of(commentaire));
        when(commentaireRepository.save(commentaire)).thenReturn(commentaire);

        // Appel de la méthode update
        Commentaire result = commentaireService.update(commentaireId, commentaire);

        // Vérifications des résultats
        assertNotNull(result, "Le commentaire mis à jour ne doit pas être null");
        assertEquals(commentaireId, result.getCommentaireId(), "L'ID du commentaire doit être le même");
    }

    /**
     * Teste la méthode delete du CommentaireService.
     * Vérifie que la suppression d'un commentaire fonctionne correctement.
     */
    @Test
    void testDelete() {
        // Simuler un commentaire existant
        when(commentaireRepository.findById(1L)).thenReturn(Optional.of(commentaire1));

        // Appeler la méthode delete
        int result = commentaireService.delete(1L);

        // Vérification du résultat
        assertEquals(1, result, "Le commentaire doit être supprimé");

        // Vérifier que la méthode deleteById a bien été appelée
        verify(commentaireRepository, times(1)).deleteById(1L);
    }

    /**
     * Teste la méthode delete du CommentaireService lorsque le commentaire n'est pas trouvé.
     * Vérifie que la suppression retourne 0 si le commentaire n'existe pas.
     */
    @Test
    void testDeleteNotFound() {
        // Simuler l'absence du commentaire
        when(commentaireRepository.findById(1L)).thenReturn(Optional.empty());

        // Appeler la méthode delete
        int result = commentaireService.delete(1L);

        // Vérification du résultat
        assertEquals(0, result, "Le commentaire n'a pas été trouvé et n'a donc pas été supprimé");

        // Vérifier que deleteById n'a pas été appelé
        verify(commentaireRepository, times(0)).deleteById(1L);
    }
}
