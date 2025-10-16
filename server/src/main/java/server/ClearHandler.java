package server;

import spark.Request;
import spark.Response;
import service.ClearService;

public class ClearHandler {
    private final ClearService clearService;

    public ClearHandler(ClearService clearService) {
        this.clearService = clearService;
    }

    public Object handle(Request request, Response response) {
        clearService.clear();
        response.status(200);
        return "";

    }

}
