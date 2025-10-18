package server;

import service.GameService;
import service.UserService;
import spark.Request;
import spark.Response;

public class ClearHandler {
    private final UserService userService;
    private final GameService gameService;

    public ClearHandler(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    public Object handle(Request request, Response response) {
        userService.clear();
        gameService.clear();
        response.status(200);
        return "";
    }

}
