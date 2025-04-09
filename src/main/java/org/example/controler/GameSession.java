package org.example.controler;

import org.example.model.business.Board;
import org.example.model.business.Dice;
import org.example.model.business.Pawn;
import org.example.model.business.Question;
import org.example.model.business.Deck;

import java.util.List;

/**
 * Session of a game
 *
 * @param id the identifier of the session
 * @param players The list of players
 * @param board   The board of the game
 * @param dice    The dice
 * @param questionDeck The deck of questions cards
 */
public record GameSession(Long id, List<Pawn> players, Board board, Dice dice, Deck<Question> questionDeck) {
}
