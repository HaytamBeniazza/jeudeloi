package org.example.data;

import org.example.model.business.Color;
import org.example.model.business.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfoPlayerConvertorTest {

    @Test
    void fromDTO() {
        InfoPlayerPOJO infoPlayerPOJO = new InfoPlayerPOJO();
        infoPlayerPOJO.setId(0);
        infoPlayerPOJO.setName("Tom");
        infoPlayerPOJO.setColor(1);
        infoPlayerPOJO.setPosition(1);
        infoPlayerPOJO.setScore(0);
        InfoPlayerConvertor infoPlayerConvertor = new InfoPlayerConvertor();
        Pawn pawn = infoPlayerConvertor.fromDTO(infoPlayerPOJO);

        assertNotNull(pawn);
        assertEquals(0, pawn.getId());
        assertEquals("Tom", pawn.getName());
        assertEquals(0, pawn.getScore());
        assertEquals(1, pawn.getPosition());
        assertEquals(1, pawn.getColor().getValue());
    }

    @Test
    void toDTO() {
        Pawn pawn = new Pawn("Tom", 2, 0, Color.RED, 2);
        InfoPlayerConvertor infoPlayerConvertor = new InfoPlayerConvertor();
        InfoPlayerPOJO infoPlayerPOJO = infoPlayerConvertor.toDTO(pawn);

        assertNotNull(infoPlayerPOJO);
        assertEquals(0, infoPlayerPOJO.getId());
        assertEquals("Tom", infoPlayerPOJO.getName());
        assertEquals(2, infoPlayerPOJO.getScore());
        assertEquals(2, infoPlayerPOJO.getPosition());
        assertEquals(0, infoPlayerPOJO.getColor());
    }
}