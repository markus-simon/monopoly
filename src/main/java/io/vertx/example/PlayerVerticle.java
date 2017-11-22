package io.vertx.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

import java.util.Random;

public class PlayerVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        EventBus eb = vertx.eventBus();
        eb.consumer("player.list", message -> {
            message.reply("ok");
        });
    }
}
