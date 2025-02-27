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

@ExtendWith(MockitoExtension.class)
class DateSondageControllerTest {

    @Mock
    private DateSondageService dateSondageService;

    @Mock
    private DateSondeeService dateSondeeService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DateSondageController dateSondageController;

    private DateSondee dateSondee;
    private DateSondeeDto dateSondeeDto;

    @BeforeEach
    void setUp() {
        dateSondee = new DateSondee();
        dateSondeeDto = new DateSondeeDto();
    }

    @Test
    void testDeleteDateSondage() {
        Long id = 1L;

        // Appel de la méthode du contrôleur
        dateSondageController.delete(id);

        // Vérifier que le service a bien été appelé
        verify(dateSondageService, times(1)).delete(id);
    }

    @Test
    void testCreateParticipation() {
        Long id = 1L;
        Long participantId = 2L;
        dateSondeeDto.setParticipant(participantId);

        // Simuler le mapping DTO -> Entity
        when(modelMapper.map(dateSondeeDto, DateSondee.class)).thenReturn(dateSondee);
        when(dateSondeeService.create(id, participantId, dateSondee)).thenReturn(dateSondee);
        when(modelMapper.map(dateSondee, DateSondeeDto.class)).thenReturn(dateSondeeDto);

        // Appeler la méthode du contrôleur
        DateSondeeDto result = dateSondageController.createParticipation(id, dateSondeeDto);

        // Vérifications
        assertNotNull(result);
        assertEquals(participantId, result.getParticipant());
        verify(modelMapper).map(dateSondeeDto, DateSondee.class);
        verify(dateSondeeService).create(id, participantId, dateSondee);
        verify(modelMapper).map(dateSondee, DateSondeeDto.class);
    }
}
