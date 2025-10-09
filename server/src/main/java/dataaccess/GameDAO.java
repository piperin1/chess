package dataaccess;
import model.GameData;
import java.util.Collection;

public interface GameDAO {
    void createGame(GameData gameData) throws DataAccessException;
    GameData getGame(String gameID) throws DataAccessException;
    Collection<GameData> listGames() throws DataAccessException;
    void updateGame(GameData gameData) throws DataAccessException;
    void clear();
}
