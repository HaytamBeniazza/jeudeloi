package org.example.controler;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.config.ConfigurationManager;
import org.example.config.DatabaseConfig;
import org.example.data.GameSessionMapper;
import org.example.factory.*;
import org.example.model.business.*;
import org.example.model.technical.ClassicalBoardGenerator;
import org.example.repository.GameSessionRepository;
import org.example.repository.QuestionRepository;
import org.example.repository.RepositoryProvider;
import org.example.repository.jpa.JpaGameSessionRepository;
import org.example.repository.jpa.JpaQuestionRepository;
import org.example.stockage.DatabaseConnectionProvider;
import org.example.stockage.*;
import org.example.view.View;

import java.util.ArrayList;
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
    private final org.example.controler.GameSession session;

    /**
     * Entity Manager Factory
     */
    private final EntityManagerFactory emf;

    /**
     * Database Connection Provider
     */
    private final DatabaseConnectionProvider connectionProvider;

    /**
     * Database Configuration
     */
    private final DatabaseConfig config;

    /**
     * DAO Factory
     */
    private final DAOFactory daoFactory;

    /**
     * Game Session Repository
     */
    private final GameSessionRepository gameSessionRepository;

    /**
     * Question Repository
     */
    private final QuestionRepository questionRepository;

    /**
     * Game Entity Factory
     */
    private final GameEntityFactory gameEntityFactory;

    /**
     * Repository Factory
     */
    private final RepositoryFactory repositoryFactory;

    /**
     * Board Factory
     */
    private final BoardFactory boardFactory;

    /**
     * Question Factory
     */
    private final QuestionFactory questionFactory;

    private final SQLScriptDB sqlScriptDB;

    private final DatabaseInitializer databaseInitializer;
    private final RepositoryProvider repositoryProvider;

    /**
     * Constructor
     * @param emf the entity manager factory
     * @param connectionProvider the database connection provider
     * @param config the database configuration
     * @param view the view implementation
     * @param databaseInitializer the database initializer
     * @param repositoryProvider the repository provider
     * @param gameEntityFactory the game entity factory
     * @param boardFactory the board factory
     * @param questionFactory the question factory
     */
    public GameController(
            EntityManagerFactory emf,
            DatabaseConnectionProvider connectionProvider,
            DatabaseConfig config,
            View view,
            DatabaseInitializer databaseInitializer,
            RepositoryProvider repositoryProvider,
            GameEntityFactory gameEntityFactory,
            BoardFactory boardFactory,
            QuestionFactory questionFactory
    ) {
        this.emf = emf;
        this.connectionProvider = connectionProvider;
        this.config = config;
        this.view = view;
        this.databaseInitializer = databaseInitializer;
        this.repositoryProvider = repositoryProvider;
        this.gameEntityFactory = gameEntityFactory;
        this.boardFactory = boardFactory;
        this.questionFactory = questionFactory;
        
        this.gameSessionRepository = repositoryProvider.createGameSessionRepository();
        this.questionRepository = repositoryProvider.createQuestionRepository();
        
        this.sqlScriptDB = new SQLScriptDB(connectionProvider);
        this.daoFactory = new DAOFactory(emf, connectionProvider, config);
        this.repositoryFactory = new RepositoryFactory(emf);
        
        try {
            initDatabase();
        } catch (DBAccessException e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
        
        // Get number of players
        int numPlayers = view.getNumberOfPlayers();
        List<String> playerNames = new ArrayList<>();
        
        // Get player names
        for (int i = 0; i < numPlayers; i++) {
            String playerName = view.getPlayerName(i + 1);
            playerNames.add(playerName);
        }
        
        // Initialize game with players
        this.session = initializeGame(playerNames, new Dice());
    }

    /**
     * Initialize the game with players
     * @param playerNames the list of player names
     * @param dice the dice
     * @return the game session
     */
    private org.example.controler.GameSession initializeGame(List<String> playerNames, Dice dice) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Create and persist players first
            List<Pawn> players = new ArrayList<>();
            Color[] colors = Color.values();
            for (int i = 0; i < playerNames.size(); i++) {
                Pawn pawn = gameEntityFactory.createPawn(playerNames.get(i), colors[i % colors.length]);
                em.persist(pawn);
                players.add(pawn);
            }
            em.flush(); // Ensure players are persisted before creating the game session

            // Create and persist questions first
            List<Question> questions = questionRepository.findAll();
            if (questions.isEmpty()) {
                questions = createDefaultQuestions();
                for (Question question : questions) {
                    em.persist(question);
                }
            }

            // Create and persist the deck
            QuestionDeck questionDeck = new QuestionDeck(questions);
            em.persist(questionDeck);
            em.flush(); // Ensure the deck is persisted before creating the game session

            // Create game board
            Board board = boardFactory.createBoard();
            // Persist each case and its effect
            for (Case c : board.getCases()) {
                if (c.getEffect() != null) {
                    em.persist(c.getEffect());
                }
                em.persist(c);
            }
            em.persist(board);
            em.flush(); // Ensure the board and its cases are persisted before creating the game session

            // Create business model game session
            org.example.model.business.GameSession businessGameSession = new org.example.model.business.GameSession(
                dice.getNbFaces(),
                board,
                questionDeck,
                players
            );

            // Persist the game session
            em.persist(businessGameSession);
            
            // Commit the transaction
            em.getTransaction().commit();
            
            // Convert to controller record
            return GameSessionMapper.toRecord(businessGameSession, dice);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new GameError(e);
        } finally {
            em.close();
        }
    }

    private List<Question> createDefaultQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", "Paris"));
        questions.add(new Question("What is 2+2?", "4"));
        questions.add(new Question("What is the largest planet in our solar system?", "Jupiter"));
        return questions;
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
        sendMessage(p.getName()+" is on case "+p.getPosition());

        // Check for win condition immediately after moving
        if (p.getPosition() == this.session.board().getSize()) {
            return; // Player has won, no need to continue the round
        }

        // Ask a question on every turn
        applyQuestionEffect(p);
        
        // Check for case effects only if not at the end position
        if (p.getPosition() < this.session.board().getSize()) {
            Case c = this.session.board().getCase(p.getPosition());
            Optional<String> res = c.effect(p);
            if (res.isPresent()) {
                sendMessage(res.get());
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
        QuestionDeck questionDeck = session.questionDeck();
        Question q = questionDeck.drawCard();
        Objects.requireNonNull(q);
        String answer = view.playerAnswerToQuestion(p.getName(), q.getAskedQuestion());
        if (!q.checkAnswer(answer)) {
            p.setPosition(p.getPosition() - 3);
            sendMessage(p.getName()+" give a wrong answer.");
        }
        else {
            p.setScore(p.getScore() + 1); // Increment score for correct answer
            sendMessage(p.getName()+" give a valid answer.");
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
     * Main game loop
     */
    public void runGame() {
        sendMessage("Game starting with " + session.players().size() + " players!");
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
        sendMessage(winner.getName()+" wins the game!");
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
    public org.example.controler.GameSession getGameSession() {
        return session;
    }

    /**
     * Initialize the database and initialize
     * @throws DBAccessException if access fails
     */
    public void initDatabase() throws DBAccessException {
        if (config.isInitDatabase()) {
            sqlScriptDB.executeScript("/question_db.sql");
            // Ensure the database is properly initialized
            EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                // Check if we have a deck with the correct type
                List<?> decks = em.createQuery("SELECT d FROM Deck d WHERE d.deckType = 'QUESTION'").getResultList();
                if (decks.isEmpty()) {
                    // If no deck exists, create one
                    QuestionDeck deck = new QuestionDeck();
                    em.persist(deck);
                }
                em.getTransaction().commit();
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw new DBAccessException("Failed to initialize database", e);
            } finally {
                em.close();
            }
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
