package server;
import com.google.gson.Gson;
import dataaccess.AlreadyTakenException;
import dataaccess.DataAccessException;
import dataaccess.UnauthorizedException;
import model.*;
import service.GameService;
import spark.Request;
import spark.Response;

import java.util.Collection;
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
            Collection<GameData> games = gameService.listGames(authToken).values();
            response.status(200);
            return gson.toJson(Map.of("games", games));
        } catch (UnauthorizedException e) {
            response.status(401);
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

    public Object joinGame(Request request, Response response) {
        try {
            String authToken = request.headers("authorization");
            if (authToken == null || authToken.isEmpty()) {
                response.status(401);
                return gson.toJson(Map.of("message", "Error: Missing Authorization header"));
            }

            JoinGameRequest joinReq = gson.fromJson(request.body(), JoinGameRequest.class);
            if (joinReq == null || joinReq.playerColor == null || joinReq.gameID == 0) {
                response.status(400);
                return gson.toJson(Map.of("message", "Error: Missing required fields"));
            }

            gameService.joinGame(authToken, joinReq.playerColor.toUpperCase(), joinReq.gameID);
            response.status(200);
            return gson.toJson(Map.of()); // empty JSON on success

        } catch (UnauthorizedException e) {
            response.status(401);
            return gson.toJson(Map.of("message", "Error: " + e.getMessage()));
        } catch (AlreadyTakenException e) {
            response.status(403);
            return gson.toJson(Map.of("message", "Error: " + e.getMessage()));
        } catch (DataAccessException e) {
            response.status(500);
            return gson.toJson(Map.of("message", "Error: Internal server error"));
        } catch (IllegalArgumentException e) {
            response.status(400);
            return gson.toJson(Map.of("message", "Error: " + e.getMessage()));
        }
    }

    private static class JoinGameRequest {
        String playerColor;
        int gameID;
    }

}
