package io.vertx.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

import java.util.logging.Logger;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.deployVerticle("io.vertx.example.ServerVerticle");
        vertx.deployVerticle("io.vertx.example.GameVerticle");
        vertx.deployVerticle("io.vertx.example.PersistorVerticle");
    }
}
