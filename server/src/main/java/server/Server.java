package server;

import dataaccess.*;
import spark.*;
import service.*;


public class Server {
    UserDAO userDAO;
    GameDAO gameDAO;
    AuthDAO authDAO;

    static ClearService clearService;
    static GameService gameService;
    static UserService userService;

    ClearHandler clearHandler = new ClearHandler(clearService);
    GameHandler gameHandler = new GameHandler();
    UserHandler userHandler = new UserHandler();

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");
        Spark.delete("/db", clearHandler::handle);
        // Register your endpoints and handle exceptions here.
        /*Spark.post("/user",);
        Spark.post("/session",);
        Spark.delete("/session",);
        Spark.post("/game",);
        Spark.get("/game",);
        Spark.put("/game",);

        Spark.exception(DataAccessException.class,);*/
        //This line initializes the server and can be removed once you have a functioning endpoint 
        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
