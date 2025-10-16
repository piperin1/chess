package service;
import dataaccess.MemoryGameDAO;
import dataaccess.MemoryUserDAO;
import dataaccess.MemoryAuthDAO;

public class ClearService {
    private final MemoryGameDAO gameDAO;
    private final MemoryAuthDAO authDAO;
    private final MemoryUserDAO userDAO;

    public ClearService(MemoryUserDAO userDAO, MemoryAuthDAO authDAO, MemoryGameDAO gameDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
        this.gameDAO = gameDAO;
    }

    public void clear() {
        userDAO.clear();
        authDAO.clear();
        gameDAO.clear();
    }
}
