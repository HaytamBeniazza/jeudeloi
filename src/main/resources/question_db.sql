-- Drop existing tables
DROP TABLE IF EXISTS current_cards;
DROP TABLE IF EXISTS discarded_cards;
DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS effect;
DROP TABLE IF EXISTS game_case;
DROP TABLE IF EXISTS board_cases;
DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS game_session;
DROP TABLE IF EXISTS pawn;
DROP TABLE IF EXISTS deck;

-- Create tables
CREATE TABLE deck (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    deck_type VARCHAR(31) NOT NULL
);

CREATE TABLE question (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    question_text VARCHAR(256) NOT NULL,
    answer VARCHAR(256) NOT NULL
);

CREATE TABLE effect (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(256) NOT NULL,
    dtype VARCHAR(31) NOT NULL
);

CREATE TABLE game_case (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    position INT NOT NULL,
    effect_id BIGINT,
    FOREIGN KEY (effect_id) REFERENCES effect(id)
);

CREATE TABLE board (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    size INT NOT NULL
);

CREATE TABLE board_cases (
    board_id BIGINT,
    cases_id BIGINT,
    PRIMARY KEY (board_id, cases_id),
    FOREIGN KEY (board_id) REFERENCES board(id),
    FOREIGN KEY (cases_id) REFERENCES game_case(id)
);

CREATE TABLE current_cards (
    deck_id BIGINT,
    card_id BIGINT,
    PRIMARY KEY (deck_id, card_id),
    FOREIGN KEY (deck_id) REFERENCES deck(id),
    FOREIGN KEY (card_id) REFERENCES question(id)
);

CREATE TABLE discarded_cards (
    deck_id BIGINT,
    card_id BIGINT,
    PRIMARY KEY (deck_id, card_id),
    FOREIGN KEY (deck_id) REFERENCES deck(id),
    FOREIGN KEY (card_id) REFERENCES question(id)
);

-- Insert default questions
INSERT INTO question (id, question_text, answer) VALUES
(1, 'What is 2+2?', '4'),
(2, 'What is the capital of France?', 'Paris'),
(3, 'What color is the sky?', 'Blue'),
(4, 'How many sides does a triangle have?', '3'),
(5, 'What is the largest planet in our solar system?', 'Jupiter'),
(6, 'What is the chemical symbol for gold?', 'Au'),
(7, 'Who painted the Mona Lisa?', 'Leonardo da Vinci'),
(8, 'What is the main language spoken in Brazil?', 'Portuguese'),
(9, 'How many continents are there?', '7'),
(10, 'What is the square root of 16?', '4'),
(11, 'What is the largest mammal in the world?', 'Blue whale'),
(12, 'What is the capital of Japan?', 'Tokyo'),
(13, 'How many colors are in a rainbow?', '7'),
(14, 'What is the chemical symbol for water?', 'H2O'),
(15, 'Who wrote "Romeo and Juliet"?', 'William Shakespeare');

-- Insert game effects
INSERT INTO effect (id, description, dtype) VALUES
(1, 'Move forward the same number again', 'GOOSE'),
(2, 'Move to the other end of the bridge', 'BRIDGE'),
(3, 'Skip one turn', 'INN'),
(4, 'Wait until another player lands here', 'WELL'),
(5, 'Go back to the beginning', 'MAZE'),
(6, 'Skip three turns', 'PRISON'),
(7, 'Go back to the start', 'DEATH');

-- Insert game cases
INSERT INTO game_case (id, position, effect_id) VALUES
(1, 5, 1),   -- Goose at position 5
(2, 6, 1),   -- Goose at position 6
(3, 9, 1),   -- Goose at position 9
(4, 14, 1),  -- Goose at position 14
(5, 18, 1),  -- Goose at position 18
(6, 23, 1),  -- Goose at position 23
(7, 27, 1),  -- Goose at position 27
(8, 32, 1),  -- Goose at position 32
(9, 36, 1),  -- Goose at position 36
(10, 41, 1), -- Goose at position 41
(11, 45, 1), -- Goose at position 45
(12, 50, 1), -- Goose at position 50
(13, 54, 1), -- Goose at position 54
(14, 59, 1), -- Goose at position 59
(15, 6, 2),  -- Bridge at position 6
(16, 19, 3), -- Inn at position 19
(17, 31, 4), -- Well at position 31
(18, 42, 5), -- Maze at position 42
(19, 52, 6), -- Prison at position 52
(20, 58, 7); -- Death at position 58

-- Insert board
INSERT INTO board (id, size) VALUES (1, 63);

-- Insert board cases
INSERT INTO board_cases (board_id, cases_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10),
(1, 11), (1, 12), (1, 13), (1, 14), (1, 15), (1, 16), (1, 17), (1, 18), (1, 19), (1, 20);

-- Insert question deck
INSERT INTO deck (id, deck_type) VALUES (1, 'QUESTION');

-- Insert current cards (all questions are in the deck initially)
INSERT INTO current_cards (deck_id, card_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10),
(1, 11), (1, 12), (1, 13), (1, 14), (1, 15);