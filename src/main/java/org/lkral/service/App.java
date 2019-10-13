package org.lkral.service;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class App {
    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();

        HttpServer httpServer = vertx.createHttpServer();

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.route(HttpMethod.POST, "/").handler(rc -> {
            JsonObject json = rc.getBodyAsJson();
            System.out.println(json.getString("message"));
            HttpServerResponse response = rc.response();
            if(json.getString("message").equals("Hello world!")){
                response.setStatusCode(200);
                response.setStatusMessage("Hey!");
            }

            else if(json.getString("message").equals("I want to do something")){
                response.setStatusCode(950);
                response.setStatusMessage("I am afraid you must be more concrete!");
            }

            else if(json.getString("message").equals("Send me this message")){
                response.setStatusCode(650);
                response.setStatusMessage("Send me this message");
            }

            else{
                response.setStatusCode(666);
                response.setStatusMessage("Can’t handle this request!");
            }
            response.end(Json.encodePrettily(response));
        });

        httpServer
                .requestHandler(router::accept)
                .listen(4242);



    }
}
