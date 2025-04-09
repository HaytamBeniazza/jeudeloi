package org.example.data;

import org.example.controler.GameSession;
import org.example.model.business.*;

import java.util.List;

public class GameConvertor implements Convertor<GameSession, GameSessionPOJO> {

    @Override
    public GameSession fromDTO(GameSessionPOJO dto) {
        QuestionDeckConvertor questionConvertor = new QuestionDeckConvertor();
        Deck<Question> deck = questionConvertor.fromDTO(dto.getDeck());

        BoardConvertor boardConvertor = new BoardConvertor();
        Board board = boardConvertor.fromDTO(dto.getBoard());

        InfoPlayerConvertor infoPlayerConvertor = new InfoPlayerConvertor();
        List<Pawn> pawns = dto.getPlayers().stream().map(infoPlayerConvertor::fromDTO).toList();

        return new GameSession(dto.getId(), pawns, board, new Dice(dto.getNbFaces()), deck);
    }

    @Override
    public GameSessionPOJO toDTO(GameSession element) {
        GameSessionPOJO pojo = new GameSessionPOJO();

        pojo.setId(element.id());
        pojo.setNbFaces(element.dice().getNbFaces());

        QuestionDeckConvertor questionConvertor = new QuestionDeckConvertor();
        QuestionDeckPOJO questionPOJO = questionConvertor.toDTO(element.questionDeck());
        pojo.setDeck(questionPOJO);

        BoardConvertor boardConvertor = new BoardConvertor();
        BoardPOJO boardPOJO = boardConvertor.toDTO(element.board());
        pojo.setBoard(boardPOJO);

        InfoPlayerConvertor infoPlayerConvertor = new InfoPlayerConvertor();
        List<InfoPlayerPOJO> players = element.players().stream().map(infoPlayerConvertor::toDTO).toList();
        pojo.setPlayers(players);

        return pojo;
    }
}
