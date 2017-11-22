package io.vertx.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.*;

public class GameVerticle extends AbstractVerticle {

    JsonArray array = new JsonArray();

    @Override
    public void start() throws Exception {
        EventBus eb = vertx.eventBus();
        array.add(new JsonObject().put("type", "start"  ).put("owner", "nobody").put("price", ""    ).put("color", "#fff"   ).put("label", "Los"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "1800").put("color", "#0c1d65").put("label", "Badstraße"));
        array.add(new JsonObject().put("type", "card"   ).put("owner", "nobody").put("price", ""    ).put("color", "#fff"   ).put("label", "Gemeinschaft"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "2000").put("color", "#0c1d65").put("label", "Turmstraße"));
        array.add(new JsonObject().put("type", "pay"    ).put("owner", "nobody").put("price", "2000").put("color", "#fff"   ).put("label", "Steuer"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "2000").put("color", "#fff"   ).put("label", "Südbahnhof"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "2200").put("color", "#6ca1f1").put("label", "Chausseestraße"));
        array.add(new JsonObject().put("type", "card"   ).put("owner", "nobody").put("price", ""    ).put("color", "#fff"   ).put("label", "Ereignis"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "2200").put("color", "#6ca1f1").put("label", "Elisenstraße"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "2400").put("color", "#6ca1f1").put("label", "Poststraße"));
        array.add(new JsonObject().put("type", "nothing").put("owner", "nobody").put("price", ""    ).put("color", "#fff"   ).put("label", "Zu Besuch"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "2400").put("color", "#3e1f41").put("label", "Seestraße"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "2400").put("color", "#fff"   ).put("label", "Kraftwerk"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "2900").put("color", "#3e1f41").put("label", "Hafenstraße"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "2900").put("color", "#3e1f41").put("label", "Neue Straße"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "2900").put("color", "#fff"   ).put("label", "Westbahnhof"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "3200").put("color", "#dd8426").put("label", "Münchner Straße"));
        array.add(new JsonObject().put("type", "card"   ).put("owner", "nobody").put("price", ""    ).put("color", "#fff"   ).put("label", "Gemeinschaft"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "3200").put("color", "#dd8426").put("label", "Wiener Straße"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "3200").put("color", "#dd8426").put("label", "Berliner Straße"));
        array.add(new JsonObject().put("type", "get"    ).put("owner", "nobody").put("price", ""    ).put("color", "#fff"   ).put("label", "Frei Parken"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "3200").put("color", "#cc2e3c").put("label", "Theaterstraße"));
        array.add(new JsonObject().put("type", "card"   ).put("owner", "nobody").put("price", ""    ).put("color", "#fff"   ).put("label", "Ereignis"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "3500").put("color", "#cc2e3c").put("label", "Museumsstraße"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "3500").put("color", "#cc2e3c").put("label", "Opernplatz"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "3500").put("color", "#fff"   ).put("label", "Nordbahnhof"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "3500").put("color", "#e4cd3f").put("label", "Lessingstraße"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "3500").put("color", "#e4cd3f").put("label", "Schillerstraße"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "3500").put("color", "#fff"   ).put("label", "Wasserwerk"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "4000").put("color", "#e4cd3f").put("label", "Göthestraße"));
        array.add(new JsonObject().put("type", "prison" ).put("owner", "nobody").put("price", ""    ).put("color", "#fff"   ).put("label", "Gefängnis"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "4000").put("color", "#1e6351").put("label", "Rathausplatz"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "4500").put("color", "#1e6351").put("label", "Hauptstraße"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", ""    ).put("color", "#fff"   ).put("label", "Gemeinschaft"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "4500").put("color", "#1e6351").put("label", "Bahnhofsstraße"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "4500").put("color", "#fff"   ).put("label", "Hauptbahnhof"));
        array.add(new JsonObject().put("type", "card"   ).put("owner", "nobody").put("price", ""    ).put("color", "#fff"   ).put("label", "Ereignis"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "7000").put("color", "#1244b7").put("label", "Parkstraße"));
        array.add(new JsonObject().put("type", "pay"    ).put("owner", "nobody").put("price", "2000").put("color", "#fff"   ).put("label", "Steuer"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "8000").put("color", "#1244b7").put("label", "Schlossallee"));




        eb.consumer("init.board", message -> {
/*
            eb.
*/
            message.reply(array);
        });

/*        eb.consumer("init.players", message -> {
            eb.send("find", new JsonObject().put("collection", "players"), ar -> {
                message.reply(ar.succeeded());
                JsonArray players = new JsonArray(ar.result().body().toString());
                eb.publish("init.players.done", players);
            });
        });*/


        eb.consumer("play", message -> {

            JsonObject json = new JsonObject(message.body().toString());
            Map<String, Object> data = new HashMap<>();
            json.getMap().forEach((key, value) -> {
                data.put(key, value);
            });

            int cube1           = new Random().ints(1, (6 + 1)).limit(1).findFirst().getAsInt();
            int cube2           = new Random().ints(1, (6 + 1)).limit(1).findFirst().getAsInt();
            int currentPosition = json.getInteger("position");
            int newPosition     = currentPosition + cube1 + cube2;

            if (newPosition >= array.size()) {
                newPosition = newPosition - array.size();
            }
            data.put("position", newPosition);
            data.put("cube1", cube1);
            data.put("cube2", cube2);
            JsonObject tile = new JsonObject(array.getValue(newPosition).toString());
            JsonObject query = tile.copy();
            query.clear().put("collection", "bought").put("label", tile.getString("label"));
            eb.send("find", query, ar -> {
                JsonArray result = new JsonArray(ar.result().body().toString());
                boolean buyable = false;
                if (result.size() == 0) {
                    if (tile.getString("type").equals("street") && tile.getString("owner").equals("nobody")) {
                        buyable = true;
                    } else {
                        buyable = false;
                    }
                }
                data.put("buyable", buyable);
                JsonObject jsonReply = new JsonObject(data);
                eb.publish("played", jsonReply);
                message.reply("ok");
            });
        });



        // buy
        eb.consumer("buy", message -> {
            JsonObject json = new JsonObject(message.body().toString());
            int currentPosition = json.getInteger("position");

            JsonObject tile = new JsonObject(array.getValue(currentPosition).toString());
            JsonObject query = tile.copy();
            query.clear().put("collection", "bought").put("label", tile.getString("label"));
            eb.send("find", query, ar -> {
                JsonArray result = new JsonArray(ar.result().body().toString());
                if (result.size() == 0) {
                    if (tile.getString("type").equals("street") && tile.getString("owner").equals("nobody")) {
                        String label = tile.getString("label");
                        tile.clear().put("collection", "bought").put("label", label).put("owner", json.getString("name"));
                        eb.send("save", tile, ar1 -> {
                            if (ar1.succeeded()) {
                                eb.publish("bought", tile);
                                message.reply("ok");
                            }
                        });
                    }
                } else {
                    message.reply("error");
                }
            });
        });
    }
}
