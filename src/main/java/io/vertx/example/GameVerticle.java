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
        array.add(new JsonObject().put("type", "start"  ).put("owner", "nobody").put("price", ""    ).put("rent", ""   ).put("color", "#fff"   ).put("label", "Los"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "40"  ).put("rent", "4"  ).put("color", "#0c1d65").put("label", "Badstrasse"));
        array.add(new JsonObject().put("type", "card"   ).put("owner", ""      ).put("price", ""    ).put("rent", ""   ).put("color", "#fff"   ).put("label", "Gemeinschaft"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "80"  ).put("rent", "8"  ).put("color", "#0c1d65").put("label", "Turmstrasse"));
        array.add(new JsonObject().put("type", "tax"    ).put("owner", "nobody").put("price", "200" ).put("rent", ""   ).put("color", "#fff"   ).put("label", "Steuer"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "500" ).put("rent", "50" ).put("color", "#fff"   ).put("label", "Suedbahnhof"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "120" ).put("rent", "12" ).put("color", "#6ca1f1").put("label", "Chausseestrasse"));
        array.add(new JsonObject().put("type", "card"   ).put("owner", "nobody").put("price", ""    ).put("rent", ""   ).put("color", "#fff"   ).put("label", "Ereignis"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "120" ).put("rent", "12" ).put("color", "#6ca1f1").put("label", "Elisenstrasse"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "160" ).put("rent", "16" ).put("color", "#6ca1f1").put("label", "Poststrasse"));
        array.add(new JsonObject().put("type", "nothing").put("owner", "nobody").put("price", ""    ).put("rent", ""   ).put("color", "#fff"   ).put("label", "Zu Besuch"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "200" ).put("rent", "20" ).put("color", "#3e1f41").put("label", "Seestrasse"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "80"  ).put("rent", "8"  ).put("color", "#fff"   ).put("label", "Kraftwerk"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "200" ).put("rent", "20" ).put("color", "#3e1f41").put("label", "Hafenstrasse"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "240" ).put("rent", "24" ).put("color", "#3e1f41").put("label", "Neue Strasse"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "500" ).put("rent", "50" ).put("color", "#fff"   ).put("label", "Westbahnhof"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "280" ).put("rent", "28" ).put("color", "#dd8426").put("label", "Muenchner Strasse"));
        array.add(new JsonObject().put("type", "card"   ).put("owner", ""      ).put("price", ""    ).put("rent", ""   ).put("color", "#fff"   ).put("label", "Gemeinschaft"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "280" ).put("rent", "28" ).put("color", "#dd8426").put("label", "Wiener Strasse"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "320" ).put("rent", "32" ).put("color", "#dd8426").put("label", "Berliner Strasse"));
        array.add(new JsonObject().put("type", "nothing").put("owner", "nobody").put("price", ""    ).put("rent", ""   ).put("color", "#fff"   ).put("label", "Frei Parken"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "360" ).put("rent", "36" ).put("color", "#cc2e3c").put("label", "Theaterstrasse"));
        array.add(new JsonObject().put("type", "card"   ).put("owner", "nobody").put("price", ""    ).put("rent", ""   ).put("color", "#fff"   ).put("label", "Ereignis"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "360" ).put("rent", "36" ).put("color", "#cc2e3c").put("label", "Museumsstrasse"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "400" ).put("rent", "40" ).put("color", "#cc2e3c").put("label", "Opernplatz"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "500" ).put("rent", "50" ).put("color", "#fff"   ).put("label", "Nordbahnhof"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "440" ).put("rent", "44" ).put("color", "#e4cd3f").put("label", "Lessingstrasse"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "440" ).put("rent", "44" ).put("color", "#e4cd3f").put("label", "Schillerstrasse"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "80"  ).put("rent", "8"  ).put("color", "#fff"   ).put("label", "Wasserwerk"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "480" ).put("rent", "48" ).put("color", "#e4cd3f").put("label", "Goethestrasse"));
        array.add(new JsonObject().put("type", "prison" ).put("owner", "nobody").put("price", ""    ).put("rent", ""   ).put("color", "#fff"   ).put("label", "Gefaengnis"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "520" ).put("rent", "52" ).put("color", "#1e6351").put("label", "Rathausplatz"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "520" ).put("rent", "52" ).put("color", "#1e6351").put("label", "Hauptstrasse"));
        array.add(new JsonObject().put("type", "street" ).put("owner", ""      ).put("price", ""    ).put("rent", ""   ).put("color", "#fff"   ).put("label", "Gemeinschaft"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "560" ).put("rent", "50" ).put("color", "#1e6351").put("label", "Bahnhofsstrasse"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "500" ).put("rent", "50" ).put("color", "#fff"   ).put("label", "Hauptbahnhof"));
        array.add(new JsonObject().put("type", "card"   ).put("owner", "nobody").put("price", ""    ).put("rent", ""   ).put("color", "#fff"   ).put("label", "Ereignis"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "700" ).put("rent", "70" ).put("color", "#1244b7").put("label", "Parkstrasse"));
        array.add(new JsonObject().put("type", "tax"    ).put("owner", "nobody").put("price", "200" ).put("rent", ""   ).put("color", "#fff"   ).put("label", "Steuer"));
        array.add(new JsonObject().put("type", "street" ).put("owner", "nobody").put("price", "1000").put("rent", "100").put("color", "#1244b7").put("label", "Schlossallee"));



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
/*                                int cube1 = 1;
                                int cube2 = 2;*/
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
                                        if (tile.getString("type").equals("tax")) {
                                            int tax = Integer.parseInt(tile.getString("price"));
                                            if (currentMoney < tax) {
                                                eb.publish("game.over", new JsonObject().put("game", "over"));
                                            } else {
                                                int newMoney2 = currentMoney - tax;
                                                player.put("money", newMoney2);
                                            }
                                        }
                                        if (tile.getString("type").equals("card")) {
                                            // todo karten ...
                                        }
                                    } else {
                                        if (tile.getString("type").equals("street")
                                                && !result.getJsonObject(0).getMap().get("owner").equals("nobody")
                                                && !result.getJsonObject(0).getMap().get("owner").equals(player.getMap().get("name"))) {
                                            int rent = Integer.parseInt(tile.getString("rent"));
                                            if (currentMoney < rent) {
                                                eb.publish("game.over", new JsonObject().put("game", "over"));
                                            } else {
                                                int newMoney2 = currentMoney - rent;
                                                player.put("money", newMoney2);

                                                String owner = result.getJsonObject(0).getMap().get("owner").toString();
                                                JsonObject findPlayerByNameQuery = new JsonObject().put("collection", "players").put("name", owner);

                                                JsonObject mongoMessage2 = new JsonObject();
                                                JsonObject addRent = new JsonObject().put("$inc", new JsonObject().put("money", rent));

                                                mongoMessage2.put("collection", "players");
                                                mongoMessage2.put("findQuery", findPlayerByNameQuery);
                                                mongoMessage2.put("findUpdate", addRent);

                                                eb.send("updateCollection", mongoMessage2, res5 -> {
                                                    eb.publish("rent.collected", new JsonObject().put("name", owner).put("rent", rent));
                                                });
                                            }
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
