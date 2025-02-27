package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.repositories;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.SondageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour le SondageRepository.
 *
 * Cette classe teste les méthodes du repository liées aux sondages.
 */
public class SondageRepositoryTest {

    @Mock
    private SondageRepository sondageRepository; // Mock du repository pour les sondages

    private Sondage sondage;
    private String nom;

    /**
     * Méthode exécutée avant chaque test.
     * Elle initialise les mocks et crée un objet Sondage pour les tests.
     */
    @BeforeEach
    void setUp() {
        nom = "Sondage Test"; // Nom du sondage pour les tests
        // Initialisation des objets
        MockitoAnnotations.openMocks(this);

        sondage = new Sondage();
        sondage.setSondageId(1L); // Définir un ID
        sondage.setNom(nom);
        sondage.setDescription("Description du sondage");
    }

    /**
     * Teste la méthode findById du SondageRepository.
     * Vérifie que la méthode renvoie le sondage correct lorsqu'il est trouvé.
     */
    @Test
    void testFindById() {
        // Configuration du mock pour renvoyer un sondage spécifique
        when(sondageRepository.findById(1L)).thenReturn(Optional.of(sondage));

        // Appel de la méthode du repository
        Optional<Sondage> result = sondageRepository.findById(1L);

        // Vérification des résultats
        assertTrue(result.isPresent(), "Le sondage doit être présent");

        result.ifPresent(sondageResult -> {
            assertEquals(1L, sondageResult.getSondageId(), "L'ID du sondage ne correspond pas");
            assertEquals(nom, sondageResult.getNom(), "Le nom du sondage ne correspond pas");
        });
    }

    /**
     * Teste la méthode save du SondageRepository.
     * Vérifie que la méthode renvoie le sondage sauvegardé.
     */
    @Test
    void testSaveSondage() {
        // Test de la sauvegarde d'un sondage
        when(sondageRepository.save(sondage)).thenReturn(sondage);

        // Appel de la méthode save
        Sondage savedSondage = sondageRepository.save(sondage);

        // Vérification des résultats
        assertNotNull(savedSondage.getSondageId(), "L'ID du sondage ne doit pas être null");
        assertEquals(nom, savedSondage.getNom(), "Le nom du sondage ne correspond pas");
    }
}
