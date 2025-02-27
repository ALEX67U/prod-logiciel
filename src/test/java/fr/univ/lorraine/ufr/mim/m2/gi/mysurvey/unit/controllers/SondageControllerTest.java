package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.controllers;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.controllers.SondageController;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.SondageDto;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.SondageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour le contrôleur SondageController.
 *
 * Cette classe teste les méthodes liées aux opérations sur les sondages.
 */
@ExtendWith(MockitoExtension.class)
class SondageControllerTest {

    @Mock
    private SondageService service; // Service pour gérer les opérations liées aux sondages

    @Mock
    private ModelMapper mapper; // Mapper pour les conversions entre Sondage et SondageDto

    @InjectMocks
    private SondageController controller; // Instance de SondageController à tester

    private Sondage sondage; // Objet Sondage pour les tests
    private SondageDto sondageDto; // Objet SondageDto pour les tests

    /**
     * Configuration préalable à l'exécution de chaque test.
     * Initialise les objets Sondage et SondageDto.
     */
    @BeforeEach
    void setUp() {
        sondage = new Sondage(); // Initialisation d'un nouveau Sondage
        sondageDto = new SondageDto(); // Initialisation d'un nouveau SondageDto
    }

    /**
     * Teste la méthode get pour récupérer un SondageDto par ID.
     * Vérifie que le DTO retourné correspond bien à l'entité récupérée.
     */
    @Test
    void getSondageById_ShouldReturnSondageDto() {
        when(service.getById(1L)).thenReturn(sondage); // Simule le retour du sondage par ID
        when(mapper.map(sondage, SondageDto.class)).thenReturn(sondageDto); // Simule le mapping

        SondageDto result = controller.get(1L); // Appel de la méthode

        // Vérifications
        assertNotNull(result);
        verify(service, times(1)).getById(1L); // Vérifie que le service a été appelé avec le bon ID
    }

    /**
     * Teste la méthode get pour récupérer tous les sondages.
     * Vérifie que la liste retournée contient les SondageDto corrects.
     */
    @Test
    void getAllSondages_ShouldReturnListOfSondageDto() {
        List<Sondage> sondages = Arrays.asList(sondage); // Création d'une liste de sondages
        when(service.getAll()).thenReturn(sondages); // Simule le retour de tous les sondages
        when(mapper.map(sondage, SondageDto.class)).thenReturn(sondageDto); // Simule le mapping

        List<SondageDto> result = controller.get(); // Appel de la méthode

        // Vérifications
        assertNotNull(result);
        assertEquals(1, result.size()); // Vérifie que la taille de la liste est correcte
        verify(service, times(1)).getAll(); // Vérifie que le service a été appelé pour récupérer tous les sondages
    }

    /**
     * Teste la méthode create pour créer un nouveau sondage.
     * Vérifie que le SondageDto retourné correspond au sondage créé.
     */
    @Test
    void createSondage_ShouldReturnCreatedSondageDto() {
        when(mapper.map(sondageDto, Sondage.class)).thenReturn(sondage); // Simule le mapping du DTO vers l'entité
        when(service.create(any(), any())).thenReturn(sondage); // Simule la création du sondage
        when(mapper.map(sondage, SondageDto.class)).thenReturn(sondageDto); // Simule le mapping inverse

        SondageDto result = controller.create(sondageDto); // Appel de la méthode

        // Vérifications
        assertNotNull(result);
        verify(service, times(1)).create(any(), any()); // Vérifie que la méthode create du service a été appelée
    }

    /**
     * Teste la méthode update pour mettre à jour un sondage existant.
     * Vérifie que le SondageDto retourné correspond au sondage mis à jour.
     */
    @Test
    void updateSondage_ShouldReturnUpdatedSondageDto() {
        when(mapper.map(sondageDto, Sondage.class)).thenReturn(sondage); // Simule le mapping
        when(service.update(1L, sondage)).thenReturn(sondage); // Simule la mise à jour du sondage
        when(mapper.map(sondage, SondageDto.class)).thenReturn(sondageDto); // Simule le mapping inverse

        SondageDto result = controller.update(1L, sondageDto); // Appel de la méthode

        // Vérifications
        assertNotNull(result);
        verify(service, times(1)).update(1L, sondage); // Vérifie que la méthode update du service a été appelée
    }

    /**
     * Teste la méthode delete pour supprimer un sondage.
     * Vérifie que la méthode delete du service est appelée avec le bon ID.
     */
    @Test
    void deleteSondage_ShouldCallServiceDelete() {
        assertDoesNotThrow(() -> controller.delete(1L)); // Vérifie que l'appel ne lance pas d'exception
        verify(service, times(1)).delete(1L); // Vérifie que la méthode delete du service a bien été appelée
    }
}
