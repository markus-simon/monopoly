package io.vertx.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PersistorVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        JsonObject mongoConfig = new JsonObject().put("db_name", "game");
        MongoClient client = MongoClient.createShared(vertx, mongoConfig);
        EventBus eb = vertx.eventBus();

        eb.consumer("save", message -> {
            JsonObject document = new JsonObject(message.body().toString());
            String collection = document.getString("collection");
            String message_created_at = document.getString("message_created_at");
            if (message_created_at != null ) {
                document.put("message_created_at", getCurrentLocalDateTimeStamp());
            }
            client.save(collection, document, res -> {
                if (res.succeeded()) {
                    message.reply("ok");
                    String id = res.result();
                    eb.publish("player.joined", id);
                } else {
                    res.cause().printStackTrace();
                }
            });
        });

        eb.consumer("find", message -> {
            JsonObject document = new JsonObject(message.body().toString());
            String collection = document.getString("collection");
            client.find(collection, document, res -> {
                if (res.succeeded()) {
                    JsonArray array = new JsonArray();
                    for (JsonObject json : res.result()) {
                        array.add(json);
                    }
                    message.reply(array);
                } else {
                    res.cause().printStackTrace();
                }
            });
        });



        eb.consumer("remove", message -> {
            JsonObject query = new JsonObject(message.body().toString());
            String collection = query.getString("collection");
            client.removeDocuments(collection, query, res -> {
                if (res.succeeded()) {
                    eb.publish("player.left", query);
                } else {
                    res.cause().printStackTrace();
                }
                message.reply("done");
            });
        });


        eb.send("remove",new JsonObject().put("collection", "players"));
    }


    private String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
