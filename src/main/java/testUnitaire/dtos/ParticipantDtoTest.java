package testUnitaire.dtos;


import fr.univ.lorraine.ufr.mim.m2.gi.mysurvey.dtos.ParticipantDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantDtoTest {

    private ParticipantDto participantDto;

    @BeforeEach
    void setUp() {
        participantDto = new ParticipantDto();
    }

    @Test
    void testGetSetParticipantId() {
        Long participantId = 1L;
        participantDto.setParticipantId(participantId);
        assertEquals(participantId, participantDto.getParticipantId());
    }

    @Test
    void testGetSetNom() {
        String nom = "Dupont";
        participantDto.setNom(nom);
        assertEquals(nom, participantDto.getNom());
    }

    @Test
    void testGetSetPrenom() {
        String prenom = "Jean";
        participantDto.setPrenom(prenom);
        assertEquals(prenom, participantDto.getPrenom());
    }

    @Test
    void testEqualsAndHashCode() {
        ParticipantDto participantDto1 = new ParticipantDto();
        participantDto1.setParticipantId(1L);
        participantDto1.setNom("Dupont");
        participantDto1.setPrenom("Jean");

        ParticipantDto participantDto2 = new ParticipantDto();
        participantDto2.setParticipantId(1L);
        participantDto2.setNom("Dupont");
        participantDto2.setPrenom("Jean");

        assertEquals(participantDto1, participantDto2);
        assertEquals(participantDto1.hashCode(), participantDto2.hashCode());
    }

    @Test
    void testNotEquals() {
        ParticipantDto participantDto1 = new ParticipantDto();
        participantDto1.setParticipantId(1L);
        participantDto1.setNom("Dupont");
        participantDto1.setPrenom("Jean");

        ParticipantDto participantDto2 = new ParticipantDto();
        participantDto2.setParticipantId(2L); // different participantId

        assertNotEquals(participantDto1, participantDto2);
    }

    @Test
    void testToString() {
        participantDto.setParticipantId(1L);
        participantDto.setNom("Dupont");
        participantDto.setPrenom("Jean");

        String expectedToString = "ParticipantDto{participantId=1, nom='Dupont', prenom='Jean'}";
        assertEquals(expectedToString, participantDto.toString());
    }
}
