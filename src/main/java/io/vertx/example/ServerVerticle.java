package io.vertx.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
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
        // get proper static webroot path
        // new File("").getCanonicalPath()
        router.route().handler(StaticHandler.create());

        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        PermittedOptions inboundPermitted1 = new PermittedOptions().setAddress("topics.list");
        PermittedOptions outboundPermitted1 = new PermittedOptions().setAddress("topics.list");
        PermittedOptions inboundPermitted2 = new PermittedOptions().setAddress("kafka.producer.message");
        PermittedOptions outboundPermitted2 = new PermittedOptions().setAddress("kafka.producer.message");
        BridgeOptions options = new BridgeOptions()
                .addInboundPermitted(inboundPermitted1)
                .addOutboundPermitted(outboundPermitted1)
                .addInboundPermitted(inboundPermitted2)
                .addOutboundPermitted(outboundPermitted2);
        sockJSHandler.bridge(options);
        router.route("/eventbus/*").handler(sockJSHandler);

        server.requestHandler(router::accept).listen(8080);
    }
}
