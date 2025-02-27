package fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.unit.models;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.models.Choix;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChoixTest {

    @Test
    void testEnumValues() {
        // Vérifier les valeurs de l'enum
        assertEquals(Choix.DISPONIBLE, Choix.valueOf("DISPONIBLE"));
        assertEquals(Choix.INDISPONIBLE, Choix.valueOf("INDISPONIBLE"));
        assertEquals(Choix.PEUTETRE, Choix.valueOf("PEUTETRE"));
    }

    @Test
    void testEnumLength() {
        // Vérifier que l'enum a bien 3 valeurs
        assertEquals(3, Choix.values().length);
    }

    @Test
    void testEnumContains() {
        // Vérifier si l'énum contient bien certaines valeurs
        assertTrue(Choix.DISPONIBLE != null);
        assertTrue(Choix.INDISPONIBLE != null);
        assertTrue(Choix.PEUTETRE != null);
    }

    @Test
    void testInvalidEnum() {
        // Tester le cas où une valeur invalide est passée à valueOf
        assertThrows(IllegalArgumentException.class, () -> {
            Choix.valueOf("INVALID");
        });
    }
}
