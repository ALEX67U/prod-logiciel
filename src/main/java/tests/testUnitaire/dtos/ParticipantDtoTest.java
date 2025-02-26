package tests.testUnitaire.dtos;


import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.ParticipantDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantDtoTest {

    private ParticipantDto participantDto;

    private String nom;
    private String prenom;

    @BeforeEach
    void setUp() {
        participantDto = new ParticipantDto();
        nom = "Dupont";
        prenom = "Jean";
    }

    @Test
    void testGetSetParticipantId() {
        Long participantId = 1L;
        participantDto.setParticipantId(participantId);
        assertEquals(participantId, participantDto.getParticipantId());
    }

    @Test
    void testGetSetNom() {

        participantDto.setNom(nom);
        assertEquals(nom, participantDto.getNom());
    }

    @Test
    void testGetSetPrenom() {

        participantDto.setPrenom(prenom);
        assertEquals(prenom, participantDto.getPrenom());
    }

    @Test
    void testEqualsAndHashCode() {
        ParticipantDto participantDto1 = new ParticipantDto();
        participantDto1.setParticipantId(1L);
        participantDto1.setNom(nom);
        participantDto1.setPrenom(prenom);

        ParticipantDto participantDto2 = new ParticipantDto();
        participantDto2.setParticipantId(1L);
        participantDto2.setNom(nom);
        participantDto2.setPrenom(prenom);

        assertEquals(participantDto1, participantDto2);
        assertEquals(participantDto1.hashCode(), participantDto2.hashCode());
    }

    @Test
    void testNotEquals() {
        ParticipantDto participantDto1 = new ParticipantDto();
        participantDto1.setParticipantId(1L);
        participantDto1.setNom(nom);
        participantDto1.setPrenom(prenom);

        ParticipantDto participantDto2 = new ParticipantDto();
        participantDto2.setParticipantId(2L); // different participantId

        assertNotEquals(participantDto1, participantDto2);
    }

    @Test
    void testToString() {
        participantDto.setParticipantId(1L);
        participantDto.setNom(nom);
        participantDto.setPrenom(prenom);

        String expectedToString = "ParticipantDto{participantId=1, nom='Dupont', prenom='Jean'}";
        assertEquals(expectedToString, participantDto.toString());
    }
}
