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

@ExtendWith(MockitoExtension.class)
class SondageControllerTest {

    @Mock
    private SondageService service;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private SondageController controller;

    private Sondage sondage;
    private SondageDto sondageDto;

    @BeforeEach
    void setUp() {
        sondage = new Sondage();
        sondageDto = new SondageDto();
    }

    @Test
    void getSondageById_ShouldReturnSondageDto() {
        when(service.getById(1L)).thenReturn(sondage);
        when(mapper.map(sondage, SondageDto.class)).thenReturn(sondageDto);

        SondageDto result = controller.get(1L);

        assertNotNull(result);
        verify(service, times(1)).getById(1L);
    }

    @Test
    void getAllSondages_ShouldReturnListOfSondageDto() {
        List<Sondage> sondages = Arrays.asList(sondage);
        when(service.getAll()).thenReturn(sondages);
        when(mapper.map(sondage, SondageDto.class)).thenReturn(sondageDto);

        List<SondageDto> result = controller.get();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(service, times(1)).getAll();
    }

    @Test
    void createSondage_ShouldReturnCreatedSondageDto() {
        when(mapper.map(sondageDto, Sondage.class)).thenReturn(sondage);
        when(service.create(any(), any())).thenReturn(sondage);
        when(mapper.map(sondage, SondageDto.class)).thenReturn(sondageDto);

        SondageDto result = controller.create(sondageDto);

        assertNotNull(result);
        verify(service, times(1)).create(any(), any());
    }

    @Test
    void updateSondage_ShouldReturnUpdatedSondageDto() {
        when(mapper.map(sondageDto, Sondage.class)).thenReturn(sondage);
        when(service.update(1L, sondage)).thenReturn(sondage);
        when(mapper.map(sondage, SondageDto.class)).thenReturn(sondageDto);

        SondageDto result = controller.update(1L, sondageDto);

        assertNotNull(result);
        verify(service, times(1)).update(1L, sondage);
    }

    @Test
    void deleteSondage_ShouldCallServiceDelete() {
        assertDoesNotThrow(() -> controller.delete(1L));
        verify(service, times(1)).delete(1L);
    }
}
