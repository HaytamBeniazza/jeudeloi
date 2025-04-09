package org.example.model.business;

import java.util.Optional;

/**
 * Ask a question effect
 */
public class QuestionEffect implements CaseEffect {

    @Override
    public Optional<String> effect(Pawn p) {
        return Optional.of("Question on player " + p.getName());
    }
}
