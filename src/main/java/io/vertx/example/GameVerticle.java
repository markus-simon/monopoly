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
        array.add(new JsonObject().put("type", "card"   ).put("owner", ""      ).put("price", ""    ).put("color", "#fff"   ).put("label", "Gemeinschaft"));
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
        array.add(new JsonObject().put("type", "card"   ).put("owner", ""      ).put("price", ""    ).put("color", "#fff"   ).put("label", "Gemeinschaft"));
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
        array.add(new JsonObject().put("type", "street" ).put("owner", ""      ).put("price", ""    ).put("color", "#fff"   ).put("label", "Gemeinschaft"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "4500").put("color", "#1e6351").put("label", "Bahnhofsstraße"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "4500").put("color", "#fff"   ).put("label", "Hauptbahnhof"));
        array.add(new JsonObject().put("type", "card"   ).put("owner", "nobody").put("price", ""    ).put("color", "#fff"   ).put("label", "Ereignis"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "7000").put("color", "#1244b7").put("label", "Parkstraße"));
        array.add(new JsonObject().put("type", "pay"    ).put("owner", "nobody").put("price", "2000").put("color", "#fff"   ).put("label", "Steuer"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "8000").put("color", "#1244b7").put("label", "Schlossallee"));



        eb.consumer("player.add", message -> {
            JsonObject player  = new JsonObject(message.body().toString()).put("collection", "players").put("position", "0").put("money", "2000");
            eb.send("save", player, res -> {
                message.reply(player);
                player.remove("session_id");
                eb.publish("player.joined", player);
                JsonObject findQuery  = new JsonObject().put("collection", "players");
                eb.send("findWithOptions", findQuery, res1 -> {
                    JsonArray playersFindResult = new JsonArray(res1.result().body().toString());
                    if (playersFindResult.size() > 1) {
                        eb.publish("players.ready", playersFindResult);
                    }
                });
            });
        });

        eb.consumer("init.board", message -> {
            message.reply(array);
        });

        eb.consumer("play", message -> {
            JsonObject json = new JsonObject(message.body().toString());
            String werwillspielen = json.getString("session_id");
            JsonObject findQuery  = new JsonObject().put("collection", "weristdran").put("session_id", werwillspielen);
            eb.send("find", findQuery, res -> {
                JsonArray werIstDranFindResult = new JsonArray(res.result().body().toString());
                if (werIstDranFindResult.size() == 0) {
                    eb.send("remove", new JsonObject().put("collection", "weristdran"), res1 -> {
                        eb.send("save", findQuery, res2 -> {

                            Map<String, Object> data = new HashMap<>();
                            json.getMap().forEach((key, value) -> {
                                data.put(key, value);
                            });

                            JsonObject findPlayerQuery  = new JsonObject().put("collection", "players").put("session_id", werwillspielen);
                            eb.send("findOne", findPlayerQuery, res3 -> {

                                JsonObject player  = new JsonObject(res3.result().body().toString());
                                int cube1 = new Random().ints(1, (6 + 1)).limit(1).findFirst().getAsInt();
                                int cube2 = new Random().ints(1, (6 + 1)).limit(1).findFirst().getAsInt();
                                int currentPosition = Integer.parseInt(player.getMap().get("position").toString());
                                int newPosition     = currentPosition + cube1 + cube2;

                                int currentMoney = Integer.parseInt(player.getMap().get("money").toString());
                                int newMoney = currentMoney;
                                if (newPosition >= array.size()) {
                                    newPosition = newPosition - array.size();
                                    newMoney = currentMoney + 2000;
                                }
                                player.put("money", newMoney);
                                player.put("position", newPosition);
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
                                        if (tile.getString("type").equals("street")
                                                && tile.getString("owner").equals("nobody")
                                                && Integer.parseInt(tile.getString("price")) <= Integer.parseInt(player.getMap().get("money").toString())) {
                                            buyable = true;
                                        } else {
                                            buyable = false;
                                        }
                                    }
                                    data.put("buyable", buyable);
                                    JsonObject jsonPublish = new JsonObject(data);
                                    JsonObject jsonReply = new JsonObject(data);
                                    jsonPublish.remove("session_id");

                                    JsonObject mongoMessage = new JsonObject();
                                    JsonObject findUpdate = new JsonObject().put("$set", new JsonObject()
                                            .put("money",    player.getMap().get("money"))
                                            .put("position", player.getMap().get("position"))
                                    );

                                    mongoMessage.put("collection", "players");
                                    mongoMessage.put("findQuery", findPlayerQuery);
                                    mongoMessage.put("findUpdate", findUpdate);

                                    eb.send("updateCollection", mongoMessage, res4 -> {
                                        eb.publish("played", jsonReply);
                                        message.reply(player);
                                    });
                                });
                            });
                        });
                    });
                } else {
                    message.reply("notok");
                }
            });
        });



        // buy
        eb.consumer("buy", message -> {
            JsonObject json = new JsonObject(message.body().toString());
            if (json.getInteger("wants").equals(0)) {
                eb.publish("bought", new JsonObject().put("wants","-1").put("name", json.getString("name")));
            } else {

                JsonObject findPlayerQuery  = new JsonObject().put("collection", "players").put("session_id", json.getString("session_id"));
                eb.send("findOne", findPlayerQuery, res3 -> {

                    JsonObject player = new JsonObject(res3.result().body().toString());
                    int currentMoney = Integer.parseInt(player.getMap().get("money").toString());
                    int currentPosition = Integer.parseInt(player.getMap().get("position").toString());
                    JsonObject tile = new JsonObject(array.getValue(currentPosition).toString());
                    int newMoney = currentMoney - Integer.parseInt(tile.getMap().get("price").toString());
                    JsonObject query = tile.copy();
                    query.clear().put("collection", "bought").put("label", tile.getString("label"));
                    eb.send("find", query, ar -> {
                        JsonArray result = new JsonArray(ar.result().body().toString());
                        if (result.size() == 0) {
                            if (tile.getString("type").equals("street") && tile.getString("owner").equals("nobody")) {
                                String label = tile.getString("label");
                                tile.put("collection", "bought").put("owner", player.getMap().get("name"));
                                eb.send("save", tile, ar1 -> {
                                    if (ar1.succeeded()) {


                                        JsonObject mongoMessage = new JsonObject();
                                        JsonObject findUpdate = new JsonObject().put("$set", new JsonObject().put("money", newMoney));

                                        mongoMessage.put("collection", "players");
                                        mongoMessage.put("findQuery", findPlayerQuery);
                                        mongoMessage.put("findUpdate", findUpdate);

                                        eb.send("updateCollection", mongoMessage, res4 -> {
                                            eb.publish("bought", new JsonObject()
                                                    .put("wants", currentPosition)
                                                    .put("name", player.getMap().get("name"))
                                                    .put("color", player.getMap().get("color"))
                                            );
                                            player.put("money", newMoney);
                                            message.reply(player);

                                        });

                                    }

                                });
                            }
                        } else {
                            message.reply("error");
                        }
                    });
                });
            }
        });
    }
}
