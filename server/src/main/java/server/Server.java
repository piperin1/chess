package server;

import dataaccess.DataAccessException;
import spark.*;

public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        Spark.post("/user",);
        Spark.post("/session",);
        Spark.delete("/session",);
        Spark.post("/game",);
        Spark.get("/game",);
        Spark.put("/game",);
        Spark.delete("/db",);

        Spark.exception(DataAccessException.class,);
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
