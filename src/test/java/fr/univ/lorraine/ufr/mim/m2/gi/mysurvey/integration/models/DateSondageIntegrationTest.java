package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.integration.models;

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

    private Sondage existingSondage; // Renommé pour éviter le conflit
    private DateSondage existingDateSondage; // Renommé pour éviter le conflit
    private DateSondee existingDateSondee; // Renommé pour éviter le conflit

    @BeforeEach
    void setUp() {
        existingSondage = new Sondage();
        existingSondage.setNom("Sondage Test");
        existingSondage = sondageRepository.save(existingSondage); // Sauvegarde le sondage

        existingDateSondee = new DateSondee();
        existingDateSondee.setChoix("DISPONIBLE"); // Assurez-vous d'initialiser cette valeur

        existingDateSondage = new DateSondage();
        existingDateSondage.setDate(new Date());
        existingDateSondage.setSondage(existingSondage);
        existingDateSondage.setDateSondee(Collections.singletonList(existingDateSondee));
    }

    @Test
    void testCreateDateSondage() {
        // Sauvegarder la DateSondage dans la base de données
        DateSondage savedDateSondage = dateSondageRepository.save(existingDateSondage);

        // Vérification après la sauvegarde
        assertNotNull(savedDateSondage.getDateSondageId()); // Assurez-vous que l'ID est généré
        assertEquals(existingDateSondage.getDate(), savedDateSondage.getDate());
        assertEquals(existingSondage.getNom(), savedDateSondage.getSondage().getNom());
        assertEquals(existingDateSondage.getDateSondee(), savedDateSondage.getDateSondee());
    }

    @Test
    void testUpdateDateSondage() {
        // Créer un sondage
        Sondage newSondage = new Sondage();
        // Configurez votre sondage ici si nécessaire

        // Créer et sauvegarder la date de sondage
        DateSondage newDateSondage = new DateSondage();
        newDateSondage.setDate(new Date()); // Ou une date spécifique
        newDateSondage.setSondage(newSondage);

        // Sauvegarder le sondage d'abord si vous avez une contrainte de clé étrangère
        Sondage savedSondage = sondageRepository.save(newSondage);
        newDateSondage.setSondage(savedSondage);
        DateSondage savedDateSondage = dateSondageRepository.save(newDateSondage);

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
        DateSondage savedDateSondage = dateSondageRepository.save(existingDateSondage);

        // Suppression
        dateSondageRepository.delete(savedDateSondage);

        // Vérification que la DateSondage n'existe plus
        assertFalse(dateSondageRepository.findById(savedDateSondage.getDateSondageId()).isPresent());
    }
}
