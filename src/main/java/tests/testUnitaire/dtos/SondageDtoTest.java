package tests.testUnitaire.dtos;

import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.SondageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SondageDtoTest {

    private SondageDto sondageDto;

    private String nom;
    private String description;
    @BeforeEach
    void setUp() {
        sondageDto = new SondageDto();
        nom = "Sondage 2025";
        description = "Description du sondage";
    }

    @Test
    void testGetSetSondageId() {
        Long sondageId = 1L;
        sondageDto.setSondageId(sondageId);
        assertEquals(sondageId, sondageDto.getSondageId());
    }

    @Test
    void testGetSetNom() {

        sondageDto.setNom(nom);
        assertEquals(nom, sondageDto.getNom());
    }

    @Test
    void testGetSetDescription() {

        sondageDto.setDescription(description);
        assertEquals(description, sondageDto.getDescription());
    }

    @Test
    void testGetSetFin() {
        Date fin = new Date();
        sondageDto.setFin(fin);
        assertEquals(fin, sondageDto.getFin());
    }

    @Test
    void testGetSetCloture() {
        Boolean cloture = true;
        sondageDto.setCloture(cloture);
        assertEquals(cloture, sondageDto.getCloture());
    }

    @Test
    void testGetSetCreateBy() {
        Long createBy = 100L;
        sondageDto.setCreateBy(createBy);
        assertEquals(createBy, sondageDto.getCreateBy());
    }

    @Test
    void testEqualsAndHashCode() {
        SondageDto sondageDto1 = new SondageDto();
        sondageDto1.setSondageId(1L);
        sondageDto1.setNom(nom);
        sondageDto1.setDescription(description);
        sondageDto1.setFin(new Date());
        sondageDto1.setCloture(true);
        sondageDto1.setCreateBy(100L);

        SondageDto sondageDto2 = new SondageDto();
        sondageDto2.setSondageId(1L);
        sondageDto2.setNom(nom);
        sondageDto2.setDescription(description);
        sondageDto2.setFin(new Date());
        sondageDto2.setCloture(true);
        sondageDto2.setCreateBy(100L);

        assertEquals(sondageDto1, sondageDto2);
        assertEquals(sondageDto1.hashCode(), sondageDto2.hashCode());
    }

    @Test
    void testNotEquals() {
        SondageDto sondageDto1 = new SondageDto();
        sondageDto1.setSondageId(1L);
        sondageDto1.setNom(nom);

        SondageDto sondageDto2 = new SondageDto();
        sondageDto2.setSondageId(2L); // Différent id

        assertNotEquals(sondageDto1, sondageDto2);
    }
}
