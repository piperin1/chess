package service;
import dataaccess.*;
import model.UserData;
import model.AuthData;
import java.util.UUID;


public class UserService {
    private final UserDAO userDAO;
    private final AuthDAO authDAO;

    public UserService(UserDAO userDAO, AuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public AuthData register(UserData user) throws Exception {
        if (userDAO.getUser(user.username()) != null) {
            throw new Exception("User already exists");
        }

        userDAO.createUser(user);
        String authToken = UUID.randomUUID().toString();
        AuthData auth = new AuthData(user.username(), authToken);
        authDAO.createAuth(auth);
        return auth;
    }
}
