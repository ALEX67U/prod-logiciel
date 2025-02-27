package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.controllers;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.controllers.DateSondageController;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.DateSondeeDto;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondee;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.DateSondageService;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.DateSondeeService;
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
 * Classe de test pour le contrôleur DateSondageController.
 *
 * Cette classe teste les méthodes de création et de suppression des dates de sondage.
 */
@ExtendWith(MockitoExtension.class)
class DateSondageControllerTest {

    @Mock
    private DateSondageService dateSondageService; // Service pour gérer les opérations liées aux dates de sondage

    @Mock
    private DateSondeeService dateSondeeService; // Service pour gérer les opérations liées aux dates sondées

    @Mock
    private ModelMapper modelMapper; // Mapper pour les conversions entre DateSondee et DateSondeeDto

    @InjectMocks
    private DateSondageController dateSondageController; // Instance de DateSondageController à tester

    private DateSondee dateSondee; // Objet DateSondee pour les tests
    private DateSondeeDto dateSondeeDto; // Objet DateSondeeDto pour les tests

    /**
     * Configuration préalable à l'exécution de chaque test.
     * Initialise les objets DateSondee et DateSondeeDto.
     */
    @BeforeEach
    void setUp() {
        dateSondee = new DateSondee(); // Initialisation d'un nouveau DateSondee
        dateSondeeDto = new DateSondeeDto(); // Initialisation d'un nouveau DateSondeeDto
    }

    /**
     * Teste la méthode de suppression d'une date de sondage dans le contrôleur.
     * Vérifie que le service de dates de sondage est appelé avec le bon ID.
     */
    @Test
    void testDeleteDateSondage() {
        Long id = 1L; // ID de la date de sondage à supprimer

        // Appel de la méthode du contrôleur
        dateSondageController.delete(id);

        // Vérifier que le service a bien été appelé
        verify(dateSondageService, times(1)).delete(id); // Vérifie que le service a été appelé pour supprimer la date de sondage
    }

    /**
     * Teste la méthode de création d'une participation à une date de sondage dans le contrôleur.
     * Vérifie que le DTO est correctement mappé et que le service est appelé avec les bons paramètres.
     */
    @Test
    void testCreateParticipation() {
        Long id = 1L; // ID de la date de sondage
        Long participantId = 2L; // ID du participant
        dateSondeeDto.setParticipant(participantId); // Affectation de l'ID du participant au DTO

        // Simuler le mapping DTO -> Entity
        when(modelMapper.map(dateSondeeDto, DateSondee.class)).thenReturn(dateSondee); // Simule le mapping du DTO vers l'entité
        when(dateSondeeService.create(id, participantId, dateSondee)).thenReturn(dateSondee); // Simule la création d'une participation
        when(modelMapper.map(dateSondee, DateSondeeDto.class)).thenReturn(dateSondeeDto); // Simule le mapping inverse

        // Appeler la méthode du contrôleur
        DateSondeeDto result = dateSondageController.createParticipation(id, dateSondeeDto);

        // Vérifications
        assertNotNull(result); // Vérifie que le résultat n'est pas null
        assertEquals(participantId, result.getParticipant()); // Vérifie que l'ID du participant est correct
        verify(modelMapper).map(dateSondeeDto, DateSondee.class); // Vérifie que le mapping a été effectué
        verify(dateSondeeService).create(id, participantId, dateSondee); // Vérifie que le service a été appelé pour créer la participation
        verify(modelMapper).map(dateSondee, DateSondeeDto.class); // Vérifie que le mapping inverse a été effectué
    }
}
