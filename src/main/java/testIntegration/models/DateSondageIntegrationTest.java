package testIntegration.models;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.MySurveyApplication;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.DateSondee;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Sondage;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.DateSondageRepository;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.repositories.SondageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MySurveyApplication.class)
@Transactional
class DateSondageIntegrationTest {

    @Autowired
    private DateSondageRepository dateSondageRepository;

    @Autowired
    private SondageRepository sondageRepository; // Ajout du repository Sondage

    private Sondage sondage;
    private DateSondage dateSondage;
    private DateSondee dateSondee;

    @BeforeEach
    void setUp() {
        sondage = new Sondage();
        sondage.setNom("Sondage Test");
        sondage = sondageRepository.save(sondage); // Sauvegarde le sondage

        dateSondee = new DateSondee();
        dateSondee.setChoix("DISPONIBLE"); // Assurez-vous d'initialiser cette valeur

        dateSondage = new DateSondage();
        dateSondage.setDate(new Date());
        dateSondage.setSondage(sondage);
        dateSondage.setDateSondee(Collections.singletonList(dateSondee));
    }


    @Test
    void testCreateDateSondage() {
        // Sauvegarder la DateSondage dans la base de données
        DateSondage savedDateSondage = dateSondageRepository.save(dateSondage);

        // Vérification après la sauvegarde
        assertNotNull(savedDateSondage.getDateSondageId()); // Assurez-vous que l'ID est généré
        assertEquals(dateSondage.getDate(), savedDateSondage.getDate());
        assertEquals(sondage.getNom(), savedDateSondage.getSondage().getNom());
        assertEquals(dateSondage.getDateSondee(), savedDateSondage.getDateSondee());
    }

    @Test
    void testUpdateDateSondage() {
        // Créer un sondage
        Sondage sondage = new Sondage();
        // Configurez votre sondage ici si nécessaire

        // Créer et sauvegarder la date de sondage
        DateSondage dateSondage = new DateSondage();
        dateSondage.setDate(new Date()); // Ou une date spécifique
        dateSondage.setSondage(sondage);

        // Sauvegarder le sondage d'abord si vous avez une contrainte de clé étrangère
        Sondage savedSondage = sondageRepository.save(sondage);
        dateSondage.setSondage(savedSondage);
        DateSondage savedDateSondage = dateSondageRepository.save(dateSondage);

        // Modifier la date de sondage
        Date newDate = new Date(); // ou une date différente pour la mise à jour
        savedDateSondage.setDate(newDate);

        // Sauvegarder les modifications
        DateSondage updatedDateSondage = dateSondageRepository.save(savedDateSondage);

        // Vérifiez que la mise à jour a réussi
        assertEquals(newDate, updatedDateSondage.getDate());
    }



    @Test
    void testDeleteDateSondage() {
        // Sauvegarder la DateSondage dans la base de données
        DateSondage savedDateSondage = dateSondageRepository.save(dateSondage);

        // Suppression
        dateSondageRepository.delete(savedDateSondage);

        // Vérification que la DateSondage n'existe plus
        assertFalse(dateSondageRepository.findById(savedDateSondage.getDateSondageId()).isPresent());
    }
}
