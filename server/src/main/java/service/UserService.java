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
        try {
            if (userDAO.getUser(user.username()) != null) {
                throw new Exception("User already exists");
            }

            userDAO.createUser(user);
            String authToken = UUID.randomUUID().toString();
            AuthData auth = new AuthData(authToken,user.username());
            authDAO.createAuth(auth);
            return auth;
        } catch (DataAccessException e) {
            throw new Exception("Error accessing user data");
        }
    }

    public AuthData login(UserData user) throws Exception {
        try {
            var existingUser = userDAO.getUser(user.username());
            if (existingUser == null) {
                throw new Exception("User does not exist");
            }

            if (!existingUser.password().equals(user.password())) {
                throw new Exception("Invalid password");
            }

            String authToken = UUID.randomUUID().toString();
            AuthData auth = new AuthData(authToken,user.username());
            authDAO.createAuth(auth);
            return auth;
        } catch (DataAccessException e) {
            throw new Exception("Error accessing user data");
        }
    }

    public void logout(String authToken) throws Exception {
        try {
            AuthData auth = authDAO.getAuth(authToken);
            if (auth == null) {
                throw new Exception("Invalid or expired auth token");
            }
            authDAO.deleteAuth(authToken);
        } catch (DataAccessException e) {
            throw new Exception("Error accessing auth data");
        }
    }
}
