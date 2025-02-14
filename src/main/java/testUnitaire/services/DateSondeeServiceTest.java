package testUnitaire.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondee;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Participant;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.DateSondeeRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.DateSondeeService;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.DateSondageService;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.services.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Date;
import java.util.List;

public class DateSondeeServiceTest {

    @Mock
    private DateSondeeRepository repository;

    @Mock
    private DateSondageService dateSondageService;

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private DateSondeeService dateSondeeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    @Test
    void testCreate() {
        // Given
        Long sondageId = 1L;
        Long participantId = 1L;
        DateSondee dateSondee = new DateSondee();
        DateSondage dateSondage = new DateSondage();
        dateSondage.setSondage(new Sondage());
        dateSondage.getSondage().setCloture(false);
        when(dateSondageService.getById(sondageId)).thenReturn(dateSondage);
        when(participantService.getById(participantId)).thenReturn(new Participant());
        when(repository.save(dateSondee)).thenReturn(dateSondee);

        // When
        DateSondee result = dateSondeeService.create(sondageId, participantId, dateSondee);

        // Then
        assertNotNull(result, "Le résultat ne doit pas être null");
        verify(repository).save(dateSondee); // Vérifier que la méthode save a bien été appelée
    }

    @Test
    void testCreateClotureTrue() {
        // Given
        Long sondageId = 1L;
        Long participantId = 1L;
        DateSondee dateSondee = new DateSondee();
        DateSondage dateSondage = new DateSondage();
        dateSondage.setSondage(new Sondage());
        dateSondage.getSondage().setCloture(true); // Clôture est true, ne doit pas créer la dateSondee
        when(dateSondageService.getById(sondageId)).thenReturn(dateSondage);

        // When
        DateSondee result = dateSondeeService.create(sondageId, participantId, dateSondee);

        // Then
        assertNull(result, "Le résultat doit être null car le sondage est clôturé");
        verify(repository, never()).save(dateSondee); // La méthode save ne doit pas être appelée
    }

    @Test
    void testBestDate() {
        // Given
        Long sondageId = 1L;
        List<Date> expectedDates = List.of(new Date(), new Date());
        when(repository.bestDate(sondageId)).thenReturn(expectedDates);

        // When
        List<Date> result = dateSondeeService.bestDate(sondageId);

        // Then
        assertNotNull(result, "La liste des meilleures dates ne doit pas être null");
        assertEquals(2, result.size(), "La taille de la liste doit être 2");
    }

    @Test
    void testMaybeBestDate() {
        // Given
        Long sondageId = 1L;
        List<Date> expectedDates = List.of(new Date(), new Date());
        when(repository.maybeBestDate(sondageId)).thenReturn(expectedDates);

        // When
        List<Date> result = dateSondeeService.maybeBestDate(sondageId);

        // Then
        assertNotNull(result, "La liste des meilleures dates possibles ne doit pas être null");
        assertEquals(2, result.size(), "La taille de la liste doit être 2");
    }
}
