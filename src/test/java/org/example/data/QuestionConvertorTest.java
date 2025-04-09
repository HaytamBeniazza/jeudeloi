package org.example.data;

import org.example.model.business.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionConvertorTest {

    @Test
    void fromDTO() {
        QuestionDTO questionDTO = new QuestionDTO(-1, "What ?", "yes");
        QuestionConvertor convertor = new QuestionConvertor();
        Question question = convertor.fromDTO(questionDTO);
        assertNotNull(question);
        assertEquals(questionDTO.question(), question.getAskedQuestion());
        assertEquals(questionDTO.answer(), question.getAnswer());
        assertEquals(questionDTO.id(), question.getId());
    }

    @Test
    void toDTO() {
        Question question = new Question(-1, "What ?", "yes");
        QuestionConvertor convertor = new QuestionConvertor();
        QuestionDTO questionDTO = convertor.toDTO(question);
        assertNotNull(questionDTO);
        assertEquals(question.getAskedQuestion(), questionDTO.question());
        assertEquals(question.getAnswer(), questionDTO.answer());
        assertEquals(question.getId(), questionDTO.id());
    }
}