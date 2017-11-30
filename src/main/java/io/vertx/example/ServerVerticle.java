package io.vertx.example;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.FileRegion;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.bridge.BridgeEventType;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.UserSessionHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.sstore.ClusteredSessionStore;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.sstore.SessionStore;


public class ServerVerticle extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(ServerVerticle.class);

    @Override
    public void start() throws Exception {

        EventBus eb = vertx.eventBus();


        // create bridge and add routes
        BridgeOptions bridgeOptions = new BridgeOptions()
                .addInboundPermitted(new PermittedOptions().setAddressRegex(".*"))
                .addOutboundPermitted(new PermittedOptions().setAddressRegex(".*"));

        SockJSHandler sockjsHandler = SockJSHandler.create(vertx).bridge(bridgeOptions, bridgeEvent -> {
            if (bridgeEvent.type() == BridgeEventType.SOCKET_CREATED) {

            }
            if (bridgeEvent.type() == BridgeEventType.SOCKET_CLOSED) {

            }
            if (bridgeEvent.type() == BridgeEventType.SEND) {

            }
            bridgeEvent.complete(true);
        });


        // add all handlers to all routes
        Router router = Router.router(vertx);


        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler
                .create(LocalSessionStore.create(vertx))
        );

        router.route("/").handler(routingContext -> {
            Session session = routingContext.session();
            Integer cnt = session.get("hitcount");
            cnt = (cnt == null ? 0 : cnt) + 1;
            session.put("hitcount", cnt);

            routingContext.next();
        });


        router.route("/eventbus/*").handler(ctx -> sockjsHandler.handle(ctx));
        router.route().handler(StaticHandler.create());

        // create server
        HttpServerOptions httpOptions = new HttpServerOptions()
                .setSsl(true)
                .setPemKeyCertOptions(
                    new PemKeyCertOptions()
                            .addKeyPath("/home/emmes/.ssh/neuron-key.pem")
                            .addCertPath("/home/emmes/.ssh/neuron-crt.pem")
/*                            .addKeyPath("/root/.ssh/neuron-key.pem")
                            .addCertPath("/root/.ssh/neuron-crt.pem")*/
                );
        HttpServer server = vertx.createHttpServer(httpOptions).requestHandler(router::accept);
        server.listen(22001);
    }
}
