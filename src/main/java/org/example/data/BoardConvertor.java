package org.example.data;

import org.example.model.business.*;


/**
 * Board to BoardPOJO convertor
 */
public class BoardConvertor implements Convertor<Board, BoardPOJO> {

    /**
     * Create the case given a symbol
     * @param symbol the case symbol
     * @param position the position of the symbol
     * @return the corresponding case (with its effect)
     */
    public Case createCase(Character symbol, int position) {
        switch (symbol) {
            case 'B' -> {
                Case caseBackEffect = new Case(position);
                caseBackEffect.setCaseEffect(new BackEffect());
                return caseBackEffect;
            }
            case 'Q' -> {
                Case caseQuestion = new Case(position);
                caseQuestion.setCaseEffect(new QuestionEffect());
                return caseQuestion;
            }
            case 'R' -> {
                Case caseReturnEffect = new Case(position);
                caseReturnEffect.setCaseEffect(new ReturnEffect());
                return caseReturnEffect;
            }
            default -> {
                return new Case(position);
            }
        }
    }

    /**
     * Get the symbol from given case
     * @param c the case
     * @return the corresponding symbol
     */
    public Character toSymbol(Case c) {
        switch (c.getCaseEffect()) {
            case BackEffect _ -> { return 'B'; }
            case QuestionEffect _ -> { return 'Q'; }
            case ReturnEffect _ -> { return 'R'; }
            case null, default -> { return 'E'; }
        }
    }

    @Override
    public Board fromDTO(BoardPOJO dto) {
        Case[] cases = new Case[dto.getSize()];
        for (int i = 0; i < dto.getSize(); i++) {
            cases[i] = createCase(dto.getBoard().charAt(i), i);
        }
        return new Board(dto.getId(), cases);
    }

    @Override
    public BoardPOJO toDTO(Board element) {
        BoardPOJO dto = new BoardPOJO();
        dto.setSize(element.getCases().length);
        dto.setId(element.getId());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dto.getSize(); i++) {
            builder.append(toSymbol(element.getCases()[i]));
        }
        dto.setBoard(builder.toString());
        return dto;
    }
}
