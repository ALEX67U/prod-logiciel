package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.controllers;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.controllers.ParticipantController;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.ParticipantDto;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour le contrôleur ParticipantController.
 *
 * Cette classe teste les méthodes liées aux opérations sur les participants.
 */
@ExtendWith(MockitoExtension.class)
class ParticipantControllerTest {

    @Mock
    private ParticipantService service; // Service pour gérer les opérations liées aux participants

    @Mock
    private ModelMapper mapper; // Mapper pour les conversions entre Participant et ParticipantDto

    @InjectMocks
    private ParticipantController controller; // Instance de ParticipantController à tester

    private Participant participant; // Objet Participant pour les tests
    private ParticipantDto participantDto; // Objet ParticipantDto pour les tests

    /**
     * Configuration préalable à l'exécution de chaque test.
     * Initialise les objets Participant et ParticipantDto.
     */
    @BeforeEach
    void setUp() {
        participant = new Participant(1L, "Doe", "John"); // Initialisation d'un nouveau Participant
        participantDto = new ParticipantDto(); // Initialisation d'un nouveau ParticipantDto
        participantDto.setParticipantId(1L); // Affectation de l'ID au DTO
        participantDto.setNom("Doe"); // Affectation du nom au DTO
        participantDto.setPrenom("John"); // Affectation du prénom au DTO
    }

    /**
     * Teste la méthode get pour récupérer un ParticipantDto par ID.
     * Vérifie que le DTO retourné correspond bien à l'entité récupérée.
     */
    @Test
    void getParticipantById_ShouldReturnParticipantDto() {
        when(service.getById(1L)).thenReturn(participant); // Simule le retour du participant par ID
        when(mapper.map(participant, ParticipantDto.class)).thenReturn(participantDto); // Simule le mapping

        ParticipantDto result = controller.get(1L); // Appel de la méthode

        // Vérifications
        assertNotNull(result);
        assertEquals(participantDto.getParticipantId(), result.getParticipantId());
        assertEquals(participantDto.getNom(), result.getNom());
        assertEquals(participantDto.getPrenom(), result.getPrenom());
    }

    /**
     * Teste la méthode get pour récupérer tous les participants.
     * Vérifie que la liste retournée contient les ParticipantsDto corrects.
     */
    @Test
    void getAllParticipants_ShouldReturnListOfParticipantDto() {
        List<Participant> participants = Arrays.asList(participant); // Création d'une liste de participants
        when(service.getAll()).thenReturn(participants); // Simule le retour de tous les participants
        when(mapper.map(participant, ParticipantDto.class)).thenReturn(participantDto); // Simule le mapping

        List<ParticipantDto> result = controller.get(); // Appel de la méthode

        // Vérifications
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(participantDto.getNom(), result.get(0).getNom());
    }

    /**
     * Teste la méthode create pour créer un nouveau participant.
     * Vérifie que le ParticipantDto retourné correspond au participant créé.
     */
    @Test
    void createParticipant_ShouldReturnCreatedParticipantDto() {
        when(mapper.map(participantDto, Participant.class)).thenReturn(participant); // Simule le mapping du DTO vers l'entité
        when(service.create(participant)).thenReturn(participant); // Simule la création du participant
        when(mapper.map(participant, ParticipantDto.class)).thenReturn(participantDto); // Simule le mapping inverse

        ParticipantDto result = controller.create(participantDto); // Appel de la méthode

        // Vérifications
        assertNotNull(result);
        assertEquals(participantDto.getNom(), result.getNom());
    }

    /**
     * Teste la méthode update pour mettre à jour un participant existant.
     * Vérifie que le ParticipantDto retourné correspond au participant mis à jour.
     */
    @Test
    void updateParticipant_ShouldReturnUpdatedParticipantDto() {
        when(mapper.map(participantDto, Participant.class)).thenReturn(participant); // Simule le mapping
        when(service.update(1L, participant)).thenReturn(participant); // Simule la mise à jour du participant
        when(mapper.map(participant, ParticipantDto.class)).thenReturn(participantDto); // Simule le mapping inverse

        ParticipantDto result = controller.update(1L, participantDto); // Appel de la méthode

        // Vérifications
        assertNotNull(result);
        assertEquals(participantDto.getNom(), result.getNom());
    }

    /**
     * Teste la méthode delete pour supprimer un participant.
     * Vérifie que la méthode delete du service est appelée avec le bon ID.
     */
    @Test
    void deleteParticipant_ShouldCallServiceDelete() {
        assertDoesNotThrow(() -> controller.delete(1L)); // Vérifie que l'appel ne lance pas d'exception
        verify(service, times(1)).delete(1L); // Vérifie que le service a bien été appelé une fois pour supprimer le participant
    }
}
