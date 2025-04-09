package org.example.data;

import org.example.model.business.Deck;
import org.example.model.business.Question;

import java.util.List;

public class QuestionDeckConvertor implements Convertor<Deck<Question>, QuestionDeckPOJO> {

    @Override
    public Deck<Question> fromDTO(QuestionDeckPOJO dto) {
        return new Deck<>(dto.getId(), null, dto.getCurrentCards(), dto.getDiscardCards());
    }

    @Override
    public QuestionDeckPOJO toDTO(Deck<Question> element) {
        QuestionDeckPOJO deckPOJO = new QuestionDeckPOJO();
        deckPOJO.setId(element.getId());
        List<Integer> cards = element.getCurrentCards();
        List<Integer> discards = element.getDiscardedCards();
        deckPOJO.setCurrentCards(cards);
        deckPOJO.setDiscardCards(discards);
        return deckPOJO;
    }
}
