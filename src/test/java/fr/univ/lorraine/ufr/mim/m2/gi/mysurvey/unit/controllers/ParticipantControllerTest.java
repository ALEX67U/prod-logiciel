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

@ExtendWith(MockitoExtension.class)
class ParticipantControllerTest {

    @Mock
    private ParticipantService service;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private ParticipantController controller;

    private Participant participant;
    private ParticipantDto participantDto;

    @BeforeEach
    void setUp() {
        participant = new Participant(1L, "Doe", "John");
        participantDto = new ParticipantDto();
        participantDto.setParticipantId(1L);
        participantDto.setNom("Doe");
        participantDto.setPrenom("John");
    }

    @Test
    void getParticipantById_ShouldReturnParticipantDto() {
        when(service.getById(1L)).thenReturn(participant);
        when(mapper.map(participant, ParticipantDto.class)).thenReturn(participantDto);

        ParticipantDto result = controller.get(1L);

        assertNotNull(result);
        assertEquals(participantDto.getParticipantId(), result.getParticipantId());
        assertEquals(participantDto.getNom(), result.getNom());
        assertEquals(participantDto.getPrenom(), result.getPrenom());
    }

    @Test
    void getAllParticipants_ShouldReturnListOfParticipantDto() {
        List<Participant> participants = Arrays.asList(participant);
        when(service.getAll()).thenReturn(participants);
        when(mapper.map(participant, ParticipantDto.class)).thenReturn(participantDto);

        List<ParticipantDto> result = controller.get();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(participantDto.getNom(), result.get(0).getNom());
    }

    @Test
    void createParticipant_ShouldReturnCreatedParticipantDto() {
        when(mapper.map(participantDto, Participant.class)).thenReturn(participant);
        when(service.create(participant)).thenReturn(participant);
        when(mapper.map(participant, ParticipantDto.class)).thenReturn(participantDto);

        ParticipantDto result = controller.create(participantDto);

        assertNotNull(result);
        assertEquals(participantDto.getNom(), result.getNom());
    }

    @Test
    void updateParticipant_ShouldReturnUpdatedParticipantDto() {
        when(mapper.map(participantDto, Participant.class)).thenReturn(participant);
        when(service.update(1L, participant)).thenReturn(participant);
        when(mapper.map(participant, ParticipantDto.class)).thenReturn(participantDto);

        ParticipantDto result = controller.update(1L, participantDto);

        assertNotNull(result);
        assertEquals(participantDto.getNom(), result.getNom());
    }

    @Test
    void deleteParticipant_ShouldCallServiceDelete() {

        assertDoesNotThrow(() -> controller.delete(1L));
        // Vérification que la méthode delete a bien été appelée une fois
        verify(service, times(1)).delete(1L);
    }
}
