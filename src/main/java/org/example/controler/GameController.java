package org.example.controler;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.config.ConfigurationManager;
import org.example.data.GameSessionMapper;
import org.example.model.business.*;
import org.example.model.technical.ClassicalBoardGenerator;
import org.example.repository.GameSessionRepository;
import org.example.repository.QuestionRepository;
import org.example.repository.jpa.JpaGameSessionRepository;
import org.example.repository.jpa.JpaQuestionRepository;
import org.example.stockage.DAOException;
import org.example.stockage.DBAccessException;
import org.example.stockage.SQLScriptDB;
import org.example.view.View;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * General game controller
 */
public class GameController {

    /**
     * Link with the view
     */
    private final View view;

    /**
     * Game session
     */
    private final GameSession session;

    /**
     * Entity Manager Factory
     */
    private final EntityManagerFactory emf;

    /**
     * Game Session Repository
     */
    private final GameSessionRepository gameSessionRepository;

    /**
     * Question Repository
     */
    private final QuestionRepository questionRepository;

    /**
     * The constructor
     * @param playerNames the name of the players
     */
    public GameController(View view, List<String> playerNames) {
       this(view, playerNames, new Dice());
    }

    /**
     * The constructor
     * @param playerNames the name of the players
     * @param dice the dice
     */
    public GameController(View view, List<String> playerNames, Dice dice) {
        try {
            this.emf = Persistence.createEntityManagerFactory("game-persistence-unit");
            this.gameSessionRepository = new JpaGameSessionRepository(emf);
            this.questionRepository = new JpaQuestionRepository(emf);
            
            initDatabase();
            List<Question> questions = questionRepository.findAll();
            ClassicalBoardGenerator boardGenerator = new ClassicalBoardGenerator();
            
            // Create and persist the game session in a single transaction
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            // Create and persist the board and its cases first
            Board board = boardGenerator.generateBoard();
            for (Case c : board.getCases()) {
                em.persist(c.getEffect()); // Persist the effect first
                em.persist(c); // Then persist the case
            }
            em.persist(board); // Finally persist the board
            
            // Create and persist the deck
            QuestionDeck deck = new QuestionDeck(questions);
            em.persist(deck);
            
            // Create and persist the players
            List<Pawn> players = createPlayers(playerNames);
            for (Pawn player : players) {
                em.persist(player);
            }
            
            // Create and persist the game session
            org.example.model.business.GameSession gameSession = new org.example.model.business.GameSession(
                dice.getNbFaces(),
                board,
                deck,
                players
            );
            em.persist(gameSession);
            
            em.getTransaction().commit();
            em.close();
            
            this.session = GameSessionMapper.toRecord(gameSession, dice);
            this.view = view;
        } catch (DBAccessException | DAOException e) {
            throw new GameError(e);
        }
    }

    /**
     * The round for one player
     * @param p a player
     */
    public void playerRound(Pawn p) {
        Objects.requireNonNull(p);
        sendMessage("Player "+p.getName()+" on case "+p.getPosition()+" roll the dice.");
        int number = p.rollDice(this.session.dice());
        sendMessage(p.getName()+" go on "+number+" cases.");

        if (p.getPosition() + number > this.session.board().getSize()) {
            sendMessage(p.getName()+" get out of the board.");
            p.setPosition(this.session.board().getSize() - number);
            return;
        }

        p.setPosition(p.getPosition() + number);

        if (p.getPosition() + number < this.session.board().getSize()) {
            Case c = this.session.board().getCase(p.getPosition());
            sendMessage(p.getName()+" is on a new case.");
            Optional<String> res = c.effect(p);
            if (res.isPresent()) {
                applyQuestionEffect(p);
            }
        }
        
        // Update the game session in the database
        try {
            org.example.model.business.GameSession gameSession = GameSessionMapper.toEntity(this.session);
            gameSessionRepository.update(gameSession);
        } catch (DAOException e) {
            throw new GameError(e);
        }
    }

    /**
     * Apply the question effect to a player
     * @param p the player
     */
    public void applyQuestionEffect(Pawn p) {
        Objects.requireNonNull(p);
        Question q = session.questionDeck().drawCard();
        Objects.requireNonNull(q);
        String answer = view.playerAnswerToQuestion(p.getName(), q.getAskedQuestion());
        if (!q.checkAnswer(answer)) {
            p.setPosition(p.getPosition() - 3);
            sendMessage(p.getName()+" give a wrong answer.");
        }
        else
            sendMessage(p.getName()+" give a valid answer.");
            
        // Update the game session in the database
        try {
            org.example.model.business.GameSession gameSession = GameSessionMapper.toEntity(this.session);
            gameSessionRepository.update(gameSession);
        } catch (DAOException e) {
            throw new GameError(e);
        }
    }

    /**
     * Main game loop
     */
    public void runGame() {
        Pawn winner = null;
        while(winner == null) {
            for (Pawn p : session.players()) {
                playerRound(p);
                if (p.getPosition() == session.board().getSize()) {
                    winner = p;
                    break;
                }
            }
        }
       sendMessage(winner.getName()+" win the game.");
    }

    /**
     * Send message to the view
     * @param message the message
     */
    public void sendMessage(String message) {
        view.display(message);
    }

    /**
     * Get the list of questions
     * @return the list of questions
     */
    public List<Question> loadQuestionList() {
        try {
            return questionRepository.findAll();
        } catch (DAOException e) {
            throw new GameError(e);
        }
    }

    /**
     * Accessor to the session
     */
    public GameSession getGameSession() {
        return session;
    }

    /**
     * Create human player from a list of names
     * @param playerNames the list of names
     * @return the list of players
     */
    public List<Pawn> createPlayers(List<String> playerNames) {
        Objects.requireNonNull(playerNames);
        return playerNames
                .stream()
                .map(playerName -> new Pawn(playerName, Color.BLUE)).toList();
    }

    /**
     * Initialize the database and initialize
     * @throws DBAccessException if access fails
     */
    public void initDatabase() throws DBAccessException {
        if (ConfigurationManager.getInstance().isInitDatabase()) {
            SQLScriptDB.runScriptOnDatabase("/question_db.sql");
        }
    }

    /**
     * Close the entity manager factory
     */
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
