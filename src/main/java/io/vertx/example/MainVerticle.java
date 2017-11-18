package io.vertx.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    vertx.deployVerticle("io.vertx.example.KafkaVerticle");
    vertx.deployVerticle("io.vertx.example.ServerVerticle");
  }
}
