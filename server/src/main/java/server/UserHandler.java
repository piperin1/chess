package server;
import spark.Request;
import spark.Response;
import service.UserService;


public class UserHandler {
    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Object register(Request request, Response response) {

    }

}
