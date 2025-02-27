package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.integration.models;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.MySurveyApplication;
import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Choix;
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

/**
 * Classe de test d'intégration pour les entités DateSondage.
 *
 * Cette classe teste les opérations CRUD (Create, Read, Update, Delete) sur les
 * entités DateSondage et leur interaction avec le repository associé.
 */
@SpringBootTest(classes = MySurveyApplication.class)
@Transactional
class DateSondageIntegrationTest {

    @Autowired
    private DateSondageRepository dateSondageRepository;

    @Autowired
    private SondageRepository sondageRepository; // Injection du repository pour manipuler les sondages

    private Sondage existingSondage; // Instance de sondage utilisée pour les tests
    private DateSondage existingDateSondage; // Instance de date de sondage utilisée pour les tests
    private DateSondee existingDateSondee; // Instance de date sondée utilisée pour les tests

    /**
     * Configuration préalable à l'exécution de chaque test.
     * Crée un sondage, une date de sondage, et une date sondée pour les tests.
     */
    @BeforeEach
    void setUp() {
        // Initialisation d'un sondage test
        existingSondage = new Sondage();
        existingSondage.setNom("Sondage Test");
        existingSondage = sondageRepository.save(existingSondage); // Sauvegarde du sondage en base

        // Création d'une réponse à la date sondée
        existingDateSondee = new DateSondee();
        existingDateSondee.setChoix(Choix.DISPONIBLE.toString()); // Définition du choix de disponibilité

        // Création d'une date de sondage associée au sondage
        existingDateSondage = new DateSondage();
        existingDateSondage.setDate(new Date()); // Définition de la date actuelle
        existingDateSondage.setSondage(existingSondage); // Association au sondage créé
        existingDateSondage.setDateSondee(Collections.singletonList(existingDateSondee)); // Ajout des réponses sondées
    }

    /**
     * Teste la création d'une DateSondage.
     * Vérifie que la date de sondage est correctement sauvegardée en base de données.
     */
    @Test
    void testCreateDateSondage() {
        // Sauvegarde d'une nouvelle date de sondage
        DateSondage savedDateSondage = dateSondageRepository.save(existingDateSondage);

        // Vérifications après l'enregistrement
        assertNotNull(savedDateSondage.getDateSondageId()); // Vérifie que l'ID est bien généré
        assertEquals(existingDateSondage.getDate(), savedDateSondage.getDate()); // Vérifie que la date est correcte
        assertEquals(existingSondage.getNom(), savedDateSondage.getSondage().getNom()); // Vérifie l'association au sondage
        assertEquals(existingDateSondage.getDateSondee(), savedDateSondage.getDateSondee()); // Vérifie la liste des réponses sondées
    }

    /**
     * Teste la mise à jour d'une DateSondage.
     * Vérifie que la date de sondage peut être modifiée et sauvegardée.
     */
    @Test
    void testUpdateDateSondage() {
        // Création d'un nouveau sondage
        Sondage newSondage = new Sondage();
        // Configuration du sondage si nécessaire

        // Création et sauvegarde d'une nouvelle date de sondage
        DateSondage newDateSondage = new DateSondage();
        newDateSondage.setDate(new Date()); // Définition de la date

        // Sauvegarde du sondage pour respecter les contraintes de clé étrangère
        Sondage savedSondage = sondageRepository.save(newSondage);
        newDateSondage.setSondage(savedSondage);
        DateSondage savedDateSondage = dateSondageRepository.save(newDateSondage);

        // Modification de la date de sondage
        Date newDate = new Date(); // Définition d'une nouvelle date
        savedDateSondage.setDate(newDate);

        // Sauvegarde de la mise à jour
        DateSondage updatedDateSondage = dateSondageRepository.save(savedDateSondage);

        // Vérification de la mise à jour
        assertEquals(newDate, updatedDateSondage.getDate()); // Vérifie que la date a bien été modifiée
    }

    /**
     * Teste la suppression d'une DateSondage.
     * Vérifie que la date de sondage peut être supprimée de la base de données.
     */
    @Test
    void testDeleteDateSondage() {
        // Sauvegarde d'une date de sondage pour le test de suppression
        DateSondage savedDateSondage = dateSondageRepository.save(existingDateSondage);

        // Suppression de la date de sondage
        dateSondageRepository.delete(savedDateSondage);

        // Vérification que l'entité a bien été supprimée
        assertFalse(dateSondageRepository.findById(savedDateSondage.getDateSondageId()).isPresent());
    }
}
