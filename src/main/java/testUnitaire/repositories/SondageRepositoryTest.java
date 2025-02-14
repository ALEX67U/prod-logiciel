package testUnitaire.repositories;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.SondageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class SondageRepositoryTest {

    @Mock
    private SondageRepository sondageRepository; // Mock du repository

    @InjectMocks
    private SondageRepositoryTest sondageRepositoryTest; // Injecter le mock dans la classe de test

    private Sondage sondage;

    @BeforeEach
    void setUp() {
        // Initialisation des objets
        MockitoAnnotations.openMocks(this);

        sondage = new Sondage();
        sondage.setSondageId(1L); // Définir un ID
        sondage.setNom("Sondage Test");
        sondage.setDescription("Description du sondage");
    }

    @Test
    void testFindById() {
        // Configuration du mock pour renvoyer un sondage spécifique
        when(sondageRepository.findById(1L)).thenReturn(Optional.of(sondage));

        // Appel de la méthode du repository
        Optional<Sondage> result = sondageRepository.findById(1L);

        // Vérification des résultats
        assertTrue(result.isPresent(), "Le sondage doit être présent");
        assertEquals(1L, result.get().getSondageId(), "L'ID du sondage ne correspond pas");
        assertEquals("Sondage Test", result.get().getNom(), "Le nom du sondage ne correspond pas");
    }

    @Test
    void testSaveSondage() {
        // Test de la sauvegarde d'un sondage
        when(sondageRepository.save(sondage)).thenReturn(sondage);

        // Appel de la méthode save
        Sondage savedSondage = sondageRepository.save(sondage);

        // Vérification des résultats
        assertNotNull(savedSondage.getSondageId(), "L'ID du sondage ne doit pas être null");
        assertEquals("Sondage Test", savedSondage.getNom(), "Le nom du sondage ne correspond pas");
    }
}
