insert into decks_questions (current_cards, discarded_cards) values (array[1,2], array[3]);
insert into info_players (name, score, color) values ('Tom', 1, 4);
insert into info_players (name, score, color) values ('Nana', 2, 5);
insert into boards (size, symbols) values (4, 'EERE');
insert into game_sessions (nb_faces, board_id, deck_question_id) values (6, 0, 0);
insert into game_players (game_id, player_id, position) values (0, 0, 3);
insert into game_players (game_id, player_id, position) values (0, 1, 6);