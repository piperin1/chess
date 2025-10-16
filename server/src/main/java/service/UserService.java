package service;
import dataaccess.AuthDAO;
import dataaccess.UserDAO;

public class UserService {
    private final UserDAO userDAO;
    private final AuthDAO authDAO;

    public UserService(UserDAO userDAO, AuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    //public RegisterResult register(RegisterRequest registerRequest);
   // public LoginResult login(LoginRequest loginRequest);
    //public void logout(LogoutRequest logoutRequest);
}
