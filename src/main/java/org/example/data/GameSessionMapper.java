package org.example.data;

import org.example.controler.GameSession;
import org.example.model.business.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for GameSession between controller record and JPA entity
 */
public class GameSessionMapper {

    /**
     * Convert from JPA entity to controller record
     * @param entity the JPA entity
     * @param dice the dice
     * @return the controller record
     */
    public static GameSession toRecord(org.example.model.business.GameSession entity, Dice dice) {
        return new GameSession(
            entity.getId(),
            entity.getPlayers(),
            entity.getBoard(),
            dice,
            entity.getDeck()
        );
    }

    /**
     * Convert from controller record to JPA entity
     * @param record the controller record
     * @return the JPA entity
     */
    public static org.example.model.business.GameSession toEntity(GameSession record) {
        org.example.model.business.GameSession entity = new org.example.model.business.GameSession();
        entity.setId(record.id());
        entity.setBoard(record.board());
        entity.setPlayers(record.players());
        entity.setDeck(record.questionDeck());
        entity.setNbFaces(record.dice().getNbFaces());
        return entity;
    }
} 