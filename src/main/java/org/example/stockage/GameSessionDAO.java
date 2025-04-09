package org.example.stockage;

import org.example.data.BoardPOJO;
import org.example.data.GameSessionPOJO;
import org.example.data.InfoPlayerPOJO;
import org.example.data.QuestionDeckPOJO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * JDBC DAO for GameSession
 */
public class GameSessionDAO implements DAO<GameSessionPOJO> {

    /**
     * Integer array type
     */
    private static final String ARRAY_INTEGER_TYPE = "integer";

    private final DatabaseConnectionProvider connectionProvider;

    /**
     * Constructor
     * @param connectionProvider the database connection provider
     */
    public GameSessionDAO(DatabaseConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public Optional<GameSessionPOJO> get(int id) throws DAOException {
        Optional<GameSessionPOJO> gameSessionPOJO;
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select nb_faces, size, symbols, current_cards, discarded_cards, board_id, deck_question_id" +
                             " from game_sessions natural inner join boards natural inner join decks_questions" +
                             " where game_sessions.game_id=?");
             ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            GameSessionPOJO pojo;
            if (resultSet.next()) {
                pojo = createGamePOJOFromResult(resultSet);
                pojo.setId(id);
                resultSet.close();
            }
            else {
                resultSet.close();
                return Optional.empty();
            }

            List<InfoPlayerPOJO> players = getPlayersFromGame(connection, id);
            pojo.setPlayers(players);
            gameSessionPOJO = Optional.of(pojo);
        } catch (SQLException | DBAccessException e) {
            throw new DAOException("SELECT error", e);
        }
        return gameSessionPOJO;
    }

    @Override
    public Optional<GameSessionPOJO> get(Long id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public List<GameSessionPOJO> getAll() throws DAOException {
        List<GameSessionPOJO> listPojo = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select game_id, nb_faces, size, symbols, current_cards, discarded_cards, board_id, deck_question_id" +
                             " from game_sessions natural inner join boards natural inner join decks_questions");)
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GameSessionPOJO pojo = createGamePOJOFromResult(resultSet);
                pojo.setId(resultSet.getInt("game_id"));
                listPojo.add(pojo);
            }
            resultSet.close();

            for (GameSessionPOJO pojo : listPojo) {
                List<InfoPlayerPOJO> players = getPlayersFromGame(connection, pojo.getId());
                pojo.setPlayers(players);
            }
        }
        catch (SQLException | DBAccessException e) {
            throw new DAOException("SELECT error -- all games", e);
        }

        return listPojo;
    }

    @Override
    public Long create(GameSessionPOJO gameSessionPOJO) throws DAOException {
        // TODO
        return 0L;
    }

    @Override
    public void delete(GameSessionPOJO gameSessionPOJO) throws DAOException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement gameStatement = connection.prepareStatement("delete from game_sessions where game_id=?");
             PreparedStatement playerStatement = connection.prepareStatement("delete from game_players where game_id=?")
        ) {
            connection.setAutoCommit(false);
            playerStatement.setInt(1, gameSessionPOJO.getId());
            playerStatement.executeUpdate();
            gameStatement.setInt(1, gameSessionPOJO.getId());
            gameStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException | DBAccessException e) {
            throw new DAOException("DELETE error", e);
        }
    }

    @Override
    public void update(GameSessionPOJO gameSessionPOJO) throws DAOException {
        // TODO
    }

    /**
     * Convert the SQL Integer array to list
     *
     * @param array the SQL array
     * @return the list of integer
     */
    private List<Integer> intArrayToList(java.sql.Array array) throws SQLException {
        Object[] cArray = (Object[]) array.getArray();
        return Arrays.stream(cArray).map(c -> (Integer) c).toList();
    }

    /**
     * Create the game POJO from the result set
     * @param resultSet the result set
     * @return the corresponding game POJO
     * @throws SQLException if something happens
     */
    private GameSessionPOJO createGamePOJOFromResult (ResultSet resultSet) throws SQLException {
        GameSessionPOJO pojo = new GameSessionPOJO();
        pojo.setNbFaces(resultSet.getInt("nb_faces"));

        QuestionDeckPOJO deck = new QuestionDeckPOJO();
        deck.setId(resultSet.getInt("deck_question_id"));
        deck.setCurrentCards(intArrayToList(resultSet.getArray("current_cards")));
        deck.setDiscardCards(intArrayToList(resultSet.getArray("discarded_cards")));
        pojo.setDeck(deck);

        BoardPOJO board = new BoardPOJO();
        board.setId(resultSet.getInt("board_id"));
        board.setSize(resultSet.getInt("size"));
        board.setBoard(resultSet.getString("symbols"));
        pojo.setBoard(board);
        return pojo;
    }

    /**
     * Get players from a given game
     * @param connection the connection to the database
     * @param game_id the identifier of the game
     * @return the list of players
     * @throws DAOException if something happens
     */
    private List<InfoPlayerPOJO> getPlayersFromGame(Connection connection, int game_id) throws DAOException {
        List<InfoPlayerPOJO> players = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select player_name, color from game_players where game_id=?")) {
            preparedStatement.setInt(1, game_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                InfoPlayerPOJO player = new InfoPlayerPOJO();
                player.setName(resultSet.getString("player_name"));
                // Convert color string to integer
                String colorStr = resultSet.getString("color");
                int color = switch (colorStr.toLowerCase()) {
                    case "red" -> 0;
                    case "blue" -> 1;
                    case "green" -> 2;
                    case "yellow" -> 3;
                    default -> throw new DAOException("Invalid color: " + colorStr, new IllegalArgumentException("Invalid color value: " + colorStr));
                };
                player.setColor(color);
                players.add(player);
            }
            return players;
        } catch (SQLException e) {
            throw new DAOException("SELECT error -- players", e);
        }
    }
}
