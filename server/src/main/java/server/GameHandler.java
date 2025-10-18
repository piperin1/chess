package server;
import com.google.gson.Gson;
import dataaccess.AlreadyTakenException;
import dataaccess.DataAccessException;
import dataaccess.UnauthorizedException;
import model.*;
import service.GameService;
import service.UserService;
import spark.Request;
import spark.Response;
import java.util.Map;

public class GameHandler {
    private final GameService gameService;
    private static final Gson gson = new Gson();

    public GameHandler(GameService gameService) {
        this.gameService = gameService;
    }

    public Object listGames(Request request, Response response) {
        try {
            String authToken = request.headers("authorization");
            if (authToken == null || authToken.isEmpty()) {
                response.status(400);
                return gson.toJson(Map.of("message", "Error: Missing Authorization header"));
            }
            Map<Integer, GameData> games = gameService.listGames(authToken);
            response.status(200);
            return gson.toJson(games);
        } catch (UnauthorizedException e) {
            response.status(403);
            return gson.toJson(Map.of("message", "Error: " + e.getMessage()));
        } catch (DataAccessException e) {
            response.status(500);
            return gson.toJson(Map.of("message", "Error: Internal server error"));
        }
    }

    public Object createGame(Request request, Response response) {
        try {
            String authToken = request.headers("authorization");
            if (authToken == null || authToken.isEmpty()) {
                response.status(400);
                return gson.toJson(Map.of("message", "Error: Missing Authorization header"));
            }

            Map<String, String> body = gson.fromJson(request.body(), Map.class);
            String gameName = body.get("gameName");
            if (gameName == null || gameName.isEmpty()) {
                response.status(400);
                return gson.toJson(Map.of("message", "Error: Missing game name"));
            }

            int gameID = gameService.createGame(authToken, gameName);
            response.status(200);
            return gson.toJson(Map.of("gameID", gameID));

        } catch (UnauthorizedException e) {
            response.status(401);
            return gson.toJson(Map.of("message", "Error: " + e.getMessage()));

        } catch (DataAccessException e) {
            response.status(500);
            return gson.toJson(Map.of("message", "Error: Internal server error"));
        }
    }

/*
    public Object joinGame(Request request, Response response) {

    }*/
}
