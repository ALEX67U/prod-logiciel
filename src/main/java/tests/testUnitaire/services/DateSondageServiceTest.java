package tests.testUnitaire.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.DateSondageRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.DateSondageService;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.SondageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.List;

public class DateSondageServiceTest {

    @Mock
    private DateSondageRepository repository;

    @Mock
    private SondageService sondageService;

    @InjectMocks
    private DateSondageService dateSondageService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialiser les mocks
    }

    @Test
    void testGetById() {
        // Given
        Long id = 1L;
        DateSondage dateSondage = new DateSondage();
        when(repository.getById(id)).thenReturn(dateSondage);

        // When
        DateSondage result = dateSondageService.getById(id);

        // Then
        assertNotNull(result, "Le résultat ne doit pas être null");
        assertEquals(dateSondage, result, "Le commentaire retourné doit être le même que celui simulé");
    }

    @Test
    void testGetBySondageId() {
        // Given
        Long sondageId = 1L;
        List<DateSondage> dateSondages = List.of(new DateSondage(), new DateSondage());
        when(repository.getAllBySondage(sondageId)).thenReturn(dateSondages);

        // When
        List<DateSondage> result = dateSondageService.getBySondageId(sondageId);

        // Then
        assertNotNull(result, "La liste ne doit pas être null");
        assertEquals(2, result.size(), "La taille de la liste doit être égale à 2");
    }

    @Test
    void testCreate() {
        // Given
        Long sondageId = 1L;
        DateSondage dateSondage = new DateSondage();
        when(sondageService.getById(sondageId)).thenReturn(new fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage());
        when(repository.save(dateSondage)).thenReturn(dateSondage);

        // When
        DateSondage result = dateSondageService.create(sondageId, dateSondage);

        // Then
        assertNotNull(result, "Le commentaire ne doit pas être null");
        verify(sondageService).getById(sondageId); // Vérifie que le service sondage a bien été appelé
        verify(repository).save(dateSondage);    // Vérifie que la méthode save a bien été appelée
    }

    @Test
    void testDelete() {
        // Given
        Long id = 1L;
        DateSondage dateSondage = new DateSondage();
        when(repository.findById(id)).thenReturn(Optional.of(dateSondage));

        // When
        int result = dateSondageService.delete(id);

        // Then
        assertEquals(1, result, "La suppression doit réussir et renvoyer 1");
        verify(repository).deleteById(id); // Vérifie que la suppression a bien eu lieu
    }

    @Test
    void testDeleteNotFound() {
        // Given
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // When
        int result = dateSondageService.delete(id);

        // Then
        assertEquals(0, result, "La suppression doit échouer et renvoyer 0");
        verify(repository, never()).deleteById(id); // Vérifie que la suppression n'a pas été appelée
    }
}

