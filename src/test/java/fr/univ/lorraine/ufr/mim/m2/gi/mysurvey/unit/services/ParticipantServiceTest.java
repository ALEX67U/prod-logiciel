package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.ParticipantRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

public class ParticipantServiceTest {

    @Mock
    private ParticipantRepository repository;

    @InjectMocks
    private ParticipantService participantService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    @Test
    void testGetById() {
        // Given
        Long participantId = 1L;
        Participant participant = new Participant();
        participant.setParticipantId(participantId);
        when(repository.getById(participantId)).thenReturn(participant);

        // When
        Participant result = participantService.getById(participantId);

        // Then
        assertNotNull(result, "Le participant ne doit pas être null");
        assertEquals(participantId, result.getParticipantId(), "L'ID du participant ne correspond pas");
    }

    @Test
    void testGetAll() {
        // Given
        List<Participant> participants = List.of(new Participant(), new Participant());
        when(repository.findAll()).thenReturn(participants);

        // When
        List<Participant> result = participantService.getAll();

        // Then
        assertNotNull(result, "La liste des participants ne doit pas être null");
        assertEquals(2, result.size(), "La taille de la liste des participants doit être 2");
    }

    @Test
    void testCreate() {
        // Given
        Participant participant = new Participant();
        when(repository.save(participant)).thenReturn(participant);

        // When
        Participant result = participantService.create(participant);

        // Then
        assertNotNull(result, "Le participant créé ne doit pas être null");
        verify(repository).save(participant); // Vérifier que save() a bien été appelé
    }

    @Test
    void testUpdate() {
        // Given
        Long participantId = 1L;
        Participant participantToUpdate = new Participant();
        participantToUpdate.setParticipantId(participantId);
        when(repository.findById(participantId)).thenReturn(Optional.of(new Participant()));
        when(repository.save(participantToUpdate)).thenReturn(participantToUpdate);

        // When
        Participant result = participantService.update(participantId, participantToUpdate);

        // Then
        assertNotNull(result, "Le participant mis à jour ne doit pas être null");
        assertEquals(participantId, result.getParticipantId(), "L'ID du participant mis à jour ne correspond pas");
        verify(repository).save(participantToUpdate); // Vérifier que save() a bien été appelé
    }

    @Test
    void testUpdateNotFound() {
        // Given
        Long participantId = 1L;
        Participant participantToUpdate = new Participant();
        when(repository.findById(participantId)).thenReturn(Optional.empty());

        // When
        Participant result = participantService.update(participantId, participantToUpdate);

        // Then
        assertNull(result, "Le résultat doit être null si le participant n'existe pas");
        verify(repository, never()).save(participantToUpdate); // save() ne doit pas être appelé
    }

    @Test
    void testDelete() {
        // Given
        Long participantId = 1L;
        when(repository.findById(participantId)).thenReturn(Optional.of(new Participant()));

        // When
        int result = participantService.delete(participantId);

        // Then
        assertEquals(1, result, "La suppression doit renvoyer 1 si le participant a été supprimé");
        verify(repository).deleteById(participantId); // Vérifier que deleteById() a bien été appelé
    }

    @Test
    void testDeleteNotFound() {
        // Given
        Long participantId = 1L;
        when(repository.findById(participantId)).thenReturn(Optional.empty());

        // When
        int result = participantService.delete(participantId);

        // Then
        assertEquals(0, result, "La suppression doit renvoyer 0 si le participant n'est pas trouvé");
        verify(repository, never()).deleteById(participantId); // deleteById() ne doit pas être appelé
    }
}
