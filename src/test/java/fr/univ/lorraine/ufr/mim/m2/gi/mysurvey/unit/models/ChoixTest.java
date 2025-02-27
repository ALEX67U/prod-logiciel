package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.models;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Choix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour l'énumération Choix.
 *
 * Cette classe teste les différentes fonctionnalités de l'énumération Choix,
 * y compris les valeurs, la longueur, et la gestion des exceptions.
 */
class ChoixTest {

    /**
     * Teste les valeurs de l'énumération Choix.
     * Vérifie que les valeurs des énumérations sont correctement renvoyées.
     */
    @Test
    void testEnumValues() {
        assertEquals(Choix.DISPONIBLE, Choix.valueOf("DISPONIBLE")); // Vérifie la valeur DISPONIBLE
        assertEquals(Choix.INDISPONIBLE, Choix.valueOf("INDISPONIBLE")); // Vérifie la valeur INDISPONIBLE
        assertEquals(Choix.PEUTETRE, Choix.valueOf("PEUTETRE")); // Vérifie la valeur PEUTETRE
    }

    /**
     * Teste la longueur de l'énumération Choix.
     * Vérifie que l'énumération contient bien 3 valeurs.
     */
    @Test
    void testEnumLength() {
        assertEquals(3, Choix.values().length); // Vérifie qu'il y a 3 valeurs dans l'énum
    }

    /**
     * Teste si l'énumération Choix contient certaines valeurs.
     * Vérifie que chaque valeur de l'énum n'est pas nulle.
     */
    @Test
    void testEnumContains() {
        assertTrue(Choix.DISPONIBLE != null); // Vérifie que DISPONIBLE n'est pas null
        assertTrue(Choix.INDISPONIBLE != null); // Vérifie que INDISPONIBLE n'est pas null
        assertTrue(Choix.PEUTETRE != null); // Vérifie que PEUTETRE n'est pas null
    }

    /**
     * Teste le comportement lors de la tentative d'accès à une valeur invalide.
     * Vérifie que le passage d'une valeur invalide à valueOf lève une exception.
     */
    @Test
    void testInvalidEnum() {
        assertThrows(IllegalArgumentException.class, () -> {
            Choix.valueOf("INVALID"); // Tente d'accéder à une valeur invalide
        });
    }
}
