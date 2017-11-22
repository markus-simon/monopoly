package io.vertx.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;


public class ServerVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        router.route().handler(StaticHandler.create());

        PermittedOptions inboundPermitted1 = new PermittedOptions().setAddress("play");
        PermittedOptions outboundPermitted1 = new PermittedOptions().setAddress("play");
        PermittedOptions inboundPermitted2 = new PermittedOptions().setAddress("played");
        PermittedOptions outboundPermitted2 = new PermittedOptions().setAddress("played");
        PermittedOptions inboundPermitted3 = new PermittedOptions().setAddress("save");
        PermittedOptions outboundPermitted3 = new PermittedOptions().setAddress("save");
        PermittedOptions inboundPermitted4 = new PermittedOptions().setAddress("init.board");
        PermittedOptions outboundPermitted4 = new PermittedOptions().setAddress("init.board");
        PermittedOptions inboundPermitted5 = new PermittedOptions().setAddress("player.joined");
        PermittedOptions outboundPermitted5 = new PermittedOptions().setAddress("player.joined");
        PermittedOptions inboundPermitted6 = new PermittedOptions().setAddress("player.left");
        PermittedOptions outboundPermitted6 = new PermittedOptions().setAddress("player.left");
        PermittedOptions inboundPermitted7 = new PermittedOptions().setAddress("find");
        PermittedOptions outboundPermitted7 = new PermittedOptions().setAddress("find");
        PermittedOptions inboundPermitted8 = new PermittedOptions().setAddress("remove");
        PermittedOptions outboundPermitted8 = new PermittedOptions().setAddress("remove");
        PermittedOptions inboundPermitted9 = new PermittedOptions().setAddress("buy");
        PermittedOptions outboundPermitted9 = new PermittedOptions().setAddress("buy");
        PermittedOptions inboundPermitted10 = new PermittedOptions().setAddress("bought");
        PermittedOptions outboundPermitted10 = new PermittedOptions().setAddress("bought");
        PermittedOptions inboundPermitted11 = new PermittedOptions().setAddress("init.players");
        PermittedOptions outboundPermitted11 = new PermittedOptions().setAddress("init.players");
        PermittedOptions inboundPermitted12 = new PermittedOptions().setAddress("init.players.done");
        PermittedOptions outboundPermitted12 = new PermittedOptions().setAddress("init.players.done");
        BridgeOptions options = new BridgeOptions()
                .addInboundPermitted(inboundPermitted1)
                .addOutboundPermitted(outboundPermitted1)
                .addInboundPermitted(inboundPermitted2)
                .addOutboundPermitted(outboundPermitted2)
                .addInboundPermitted(inboundPermitted3)
                .addOutboundPermitted(outboundPermitted3)
                .addInboundPermitted(inboundPermitted4)
                .addOutboundPermitted(outboundPermitted4)
                .addInboundPermitted(inboundPermitted5)
                .addOutboundPermitted(outboundPermitted5)
                .addInboundPermitted(inboundPermitted6)
                .addOutboundPermitted(outboundPermitted6)
                .addInboundPermitted(inboundPermitted7)
                .addOutboundPermitted(outboundPermitted7)
                .addInboundPermitted(inboundPermitted8)
                .addOutboundPermitted(outboundPermitted8)
                .addInboundPermitted(inboundPermitted9)
                .addOutboundPermitted(outboundPermitted9)
                .addInboundPermitted(inboundPermitted10)
                .addOutboundPermitted(outboundPermitted10)
                .addInboundPermitted(inboundPermitted11)
                .addOutboundPermitted(outboundPermitted11)
                .addInboundPermitted(inboundPermitted12)
                .addOutboundPermitted(outboundPermitted12);



        SockJSHandler ebHandler = SockJSHandler.create(vertx).bridge(options);
        router.route("/eventbus/*").handler(ebHandler);
/*        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        BridgeOptions options = new BridgeOptions();
        sockJSHandler.bridge(options);

        router.route("/eventbus/*").handler(sockJSHandler);
        router.route('/eventbus/*').handler(SockJSHandler.create(vertx).bridge(options).socketHandler());*/


        EventBus eb = vertx.eventBus();

        server.requestHandler(router::accept);

        server.websocketHandler(handler -> {
            JsonObject document = new JsonObject().put("textHandlerID", handler.textHandlerID()).put("collection", "players");
            eb.send("save", document, ar -> {
                if (ar.succeeded()) {
                    eb.publish("player.joined", handler.textHandlerID());
                }
            });
            handler.closeHandler(message ->{
                eb.send("remove", document, ar -> {
                    if (ar.succeeded()) {
                        eb.publish("player.left", handler.textHandlerID());
                    }
                });
                System.out.println("client disconnected "+handler.textHandlerID());
            });
        });

        server.listen(8088);
    }
}
