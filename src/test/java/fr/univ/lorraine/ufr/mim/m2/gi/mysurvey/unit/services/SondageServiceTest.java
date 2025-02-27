package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.SondageRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.ParticipantService;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.SondageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

public class SondageServiceTest {

    @Mock
    private SondageRepository repository;

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private SondageService sondageService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    @Test
    void testGetById() {
        // Given
        Long sondageId = 1L;
        Sondage sondage = new Sondage();
        sondage.setSondageId(sondageId);
        when(repository.getById(sondageId)).thenReturn(sondage);

        // When
        Sondage result = sondageService.getById(sondageId);

        // Then
        assertNotNull(result, "Le sondage ne doit pas être null");
        assertEquals(sondageId, result.getSondageId(), "L'ID du sondage ne correspond pas");
    }

    @Test
    void testGetAll() {
        // Given
        List<Sondage> sondages = List.of(new Sondage(), new Sondage());
        when(repository.findAll()).thenReturn(sondages);

        // When
        List<Sondage> result = sondageService.getAll();

        // Then
        assertNotNull(result, "La liste des sondages ne doit pas être null");
        assertEquals(2, result.size(), "La taille de la liste des sondages doit être 2");
    }

    @Test
    void testCreate() {
        // Given
        Long participantId = 1L;
        Sondage sondage = new Sondage();
        when(participantService.getById(participantId)).thenReturn(new Participant());
        when(repository.save(sondage)).thenReturn(sondage);

        // When
        Sondage result = sondageService.create(participantId, sondage);

        // Then
        assertNotNull(result, "Le sondage créé ne doit pas être null");
        verify(repository).save(sondage); // Vérifier que save() a bien été appelé
    }

    @Test
    void testUpdate() {
        // Given
        Long sondageId = 1L;
        Sondage sondageToUpdate = new Sondage();
        sondageToUpdate.setSondageId(sondageId);
        when(repository.findById(sondageId)).thenReturn(Optional.of(new Sondage()));
        when(repository.save(sondageToUpdate)).thenReturn(sondageToUpdate);

        // When
        Sondage result = sondageService.update(sondageId, sondageToUpdate);

        // Then
        assertNotNull(result, "Le sondage mis à jour ne doit pas être null");
        assertEquals(sondageId, result.getSondageId(), "L'ID du sondage mis à jour ne correspond pas");
        verify(repository).save(sondageToUpdate); // Vérifier que save() a bien été appelé
    }

    @Test
    void testUpdateNotFound() {
        // Given
        Long sondageId = 1L;
        Sondage sondageToUpdate = new Sondage();
        when(repository.findById(sondageId)).thenReturn(Optional.empty());

        // When
        Sondage result = sondageService.update(sondageId, sondageToUpdate);

        // Then
        assertNull(result, "Le résultat doit être null si le sondage n'existe pas");
        verify(repository, never()).save(sondageToUpdate); // save() ne doit pas être appelé
    }

    @Test
    void testDelete() {
        // Given
        Long sondageId = 1L;
        when(repository.findById(sondageId)).thenReturn(Optional.of(new Sondage()));

        // When
        int result = sondageService.delete(sondageId);

        // Then
        assertEquals(1, result, "La suppression doit renvoyer 1 si le sondage a été supprimé");
        verify(repository).deleteById(sondageId); // Vérifier que deleteById() a bien été appelé
    }

    @Test
    void testDeleteNotFound() {
        // Given
        Long sondageId = 1L;
        when(repository.findById(sondageId)).thenReturn(Optional.empty());

        // When
        int result = sondageService.delete(sondageId);

        // Then
        assertEquals(0, result, "La suppression doit renvoyer 0 si le sondage n'est pas trouvé");
        verify(repository, never()).deleteById(sondageId); // deleteById() ne doit pas être appelé
    }
}
