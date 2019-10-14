package org.lkral.service;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.core.AbstractVerticle;

public class Main extends AbstractVerticle{
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.route(HttpMethod.POST, "/")
                .produces("application/json")
                .handler(rc -> {
                    HttpServerResponse response = rc.response();
                    JsonObject json = rc.getBodyAsJson();

                    System.out.println(json.getString("message"));
                    switch (json.getString("message")) {
                        case "Hello world!":
                            response.setStatusCode(200);
                            response.setStatusMessage("Hey!");
                            break;
                        case "I want to do something":
                            response.setStatusCode(950);
                            response.setStatusMessage("I am afraid you must be more concrete!");
                            break;
                        case "Send me this message":
                            response.setStatusCode(650);
                            response.setStatusMessage("Send me this message");
                            break;
                        default:
                            response.setStatusCode(666);
                            response.setStatusMessage("Can’t handle this request!");
                            break;
                    }
                    response.end("Message: " + json.getString("message") + "\nStatus code:" + response.getStatusCode() + "\nReturn message:" + response.getStatusMessage() + "\n");
                })
                .failureHandler(err -> {

                    final JsonObject json = new JsonObject()
                            .put("timestamp", System.nanoTime())
                            .put("status", err.statusCode())
                            .put("path", err.request().path())
                            .put("message", "I think you did something wrong\nRead about using in README.md\n");
                    err.response().end(json.encodePrettily());
                });

        httpServer
                .requestHandler(router::accept)
                .listen(4242);


    }
}