package server;

import dataaccess.*;
import spark.*;
import service.*;


public class Server {
    UserDAO userDAO = new MemoryUserDAO();
    AuthDAO authDAO = new MemoryAuthDAO();

    UserService userService = new UserService(userDAO, authDAO);

    ClearHandler clearHandler = new ClearHandler(userService);
    UserHandler userHandler = new UserHandler(userService);

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");
        Spark.delete("/db", clearHandler::handle);
        Spark.post("/user", userHandler::register);
        Spark.post("/session", userHandler::login);
        Spark.delete("/session", userHandler::logout);
        /*
        Spark.post("/game",);
        Spark.get("/game",);
        Spark.put("/game",);
        Spark.exception(DataAccessException.class,);*/
        //Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
