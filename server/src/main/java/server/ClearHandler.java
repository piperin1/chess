package server;

import service.UserService;
import spark.Request;
import spark.Response;

public class ClearHandler {
    private final UserService userService;

    public ClearHandler(UserService userService) {
        this.userService = userService;
    }

    public Object handle(Request request, Response response) {
        userService.clear();
        //Add gameService.clear() here
        response.status(200);
        return "";
    }

}
