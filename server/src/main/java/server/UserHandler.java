package server;
import com.google.gson.Gson;
import dataaccess.AlreadyTakenException;
import dataaccess.DataAccessException;
import dataaccess.UnauthorizedException;
import model.AuthData;
import model.UserData;
import service.UserService;
import spark.Request;
import spark.Response;
import java.util.Map;

public class UserHandler {
    private final UserService userService;
    private static final Gson gson = new Gson();

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Object register(Request request, Response response) {
        try {
            UserData user = gson.fromJson(request.body(), UserData.class);
            if (user.username() == null || user.password() == null || user.email() == null) {
                response.status(400);
                return gson.toJson(Map.of("message", "Missing required fields"));
            }
            AuthData auth = userService.register(user);
            response.status(200);
            return gson.toJson(auth);
        } catch (AlreadyTakenException e) {
            response.status(403);
            return gson.toJson(Map.of("message", e.getMessage()));
        } catch (DataAccessException e) {
            response.status(500);
            return gson.toJson(Map.of("message", "Internal server error"));
        }
    }

    public Object login(Request request, Response response) {
        try {
            UserData user = gson.fromJson(request.body(), UserData.class);
            if (user.username() == null || user.password() == null) {
                response.status(400);
                return gson.toJson(Map.of("message", "Missing username or password"));
            }
            AuthData auth = userService.login(user);
            response.status(200);
            return gson.toJson(auth);
        } catch (UnauthorizedException e) {
            response.status(401);
            return gson.toJson(Map.of("message", e.getMessage()));
        } catch (DataAccessException e) {
            response.status(500);
            return gson.toJson(Map.of("message", "Internal server error"));
        }
    }

    public Object logout(Request request, Response response) {
        try {
            String authToken = request.headers("authorization");
            if (authToken == null || authToken.isEmpty()) {
                response.status(400);
                return gson.toJson(Map.of("message", "Missing Authorization header"));
            }
            userService.logout(authToken);
            response.status(200);
            return gson.toJson(Map.of());
        } catch (UnauthorizedException e) {
            response.status(401);
            return gson.toJson(Map.of("message", e.getMessage()));
        } catch (DataAccessException e) {
            response.status(500);
            return gson.toJson(Map.of("message", "Internal server error"));
        }
    }
}
