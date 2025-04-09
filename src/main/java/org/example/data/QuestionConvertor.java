package org.example.data;

import org.example.model.business.Question;

public class QuestionConvertor implements Convertor<Question, QuestionDTO> {

    @Override
    public Question fromDTO(QuestionDTO dto) {
        return new Question(dto.id(), dto.question(), dto.answer());
    }

    @Override
    public QuestionDTO toDTO(Question q) {
        return new QuestionDTO(q.getId(), q.getAskedQuestion(), q.getAnswer());
    }
}
