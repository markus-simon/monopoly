package io.vertx.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.mongo.UpdateOptions;

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
                } else {
                    res.cause().printStackTrace();
                }
            });
        });

        eb.consumer("findOne", message -> {
            JsonObject document = new JsonObject(message.body().toString());
            String collection = document.getString("collection");
            client.findOne(collection, document, new JsonObject(), res -> {
                if (res.succeeded()) {
                    message.reply(res.result());
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

        eb.consumer("findWithOptions", message -> {
            JsonObject document = new JsonObject(message.body().toString());
            String collection = document.getString("collection");
            FindOptions findOptions = new FindOptions().setFields(new JsonObject().put("_id", false).put("name", true).put("color",true));
            client.findWithOptions(collection, document, findOptions, res -> {
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
                    message.reply("done");
                } else {
                    res.cause().printStackTrace();
                }
            });
        });

        eb.consumer("findOneAndUpdateWithOptions", message -> {
            JsonObject document = new JsonObject(message.body().toString());
            String collection           = document.getString("collection");
            JsonObject query            = document.getJsonObject("findQuery");
            JsonObject update           = document.getJsonObject("findUpdate");
            FindOptions findOptions     = new FindOptions();
            UpdateOptions updateOptions = new UpdateOptions().setUpsert(true);

            client.findOneAndUpdateWithOptions(collection, query, update, findOptions, updateOptions, res -> {
                if (res.succeeded()) {
                    message.reply("ok");
                } else {
                    res.cause().printStackTrace();
                }
            });
        });

        eb.consumer("updateCollection", message -> {
            JsonObject document = new JsonObject(message.body().toString());
            String collection   = document.getString("collection");
            JsonObject query    = document.getJsonObject("findQuery");
            JsonObject update   = document.getJsonObject("findUpdate");
            client.updateCollection(collection, query, update, res -> {
                if (res.succeeded()) {
                    message.reply("ok");
                } else {
                    res.cause().printStackTrace();
                }
            });
        });

        eb.send("remove",new JsonObject().put("collection", "bought"));
        eb.send("remove",new JsonObject().put("collection", "players"));
        eb.send("remove",new JsonObject().put("collection", "weristdran"));
    }

    private String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
