package io.vertx.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.Random;

public class GameVerticle extends AbstractVerticle {

    private JsonArray array = new JsonArray();
    private static JsonArray allPlayers;
    private static boolean buyable;


    @Override
    public void start() throws Exception {

        EventBus eb     = vertx.eventBus();

        array.add(new JsonObject().put("id", 0 ).put("type", "start"     ).put("owner", "nobody").put("price", 0   ).put("rent", 0  ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Los"              ).put("label2", ""          ).put("label3", "Los"              ));
        array.add(new JsonObject().put("id", 1 ).put("type", "street"    ).put("owner", "nobody").put("price", 40  ).put("rent", 4  ).put("house", 40  ).put("color", "#0c1d65").put("label1", "Badstrasse"       ).put("label2", ""          ).put("label3", "Badstrasse"       ));
        array.add(new JsonObject().put("id", 2 ).put("type", "card"      ).put("owner", ""      ).put("price", 0   ).put("rent", 0  ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Gemeinschafts-"   ).put("label2", "feld"      ).put("label3", "Gemeinschaft"     ));
        array.add(new JsonObject().put("id", 3 ).put("type", "street"    ).put("owner", "nobody").put("price", 80  ).put("rent", 8  ).put("house", 80  ).put("color", "#0c1d65").put("label1", "Turmstrasse"      ).put("label2", ""          ).put("label3", "Turmstrasse"      ));
        array.add(new JsonObject().put("id", 4 ).put("type", "tax"       ).put("owner", ""      ).put("price", 200 ).put("rent", 0  ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Steuer"           ).put("label2", ""          ).put("label3", "Steuer"           ));
        array.add(new JsonObject().put("id", 5 ).put("type", "street"    ).put("owner", "nobody").put("price", 500 ).put("rent", 50 ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Suedbahnhof"      ).put("label2", ""          ).put("label3", "Suedbahnhof"      ));
        array.add(new JsonObject().put("id", 6 ).put("type", "street"    ).put("owner", "nobody").put("price", 120 ).put("rent", 12 ).put("house", 120 ).put("color", "#6ca1f1").put("label1", "Chaussee-"        ).put("label2", "strasse"   ).put("label3", "Chausseestrasse"  ));
        array.add(new JsonObject().put("id", 7 ).put("type", "card"      ).put("owner", ""      ).put("price", 0   ).put("rent", 0  ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Ereignis-"        ).put("label2", "feld"      ).put("label3", "Ereignis"         ));
        array.add(new JsonObject().put("id", 8 ).put("type", "street"    ).put("owner", "nobody").put("price", 120 ).put("rent", 12 ).put("house", 120 ).put("color", "#6ca1f1").put("label1", "Elisenstrasse"    ).put("label2", ""          ).put("label3", "Elisenstrasse"    ));
        array.add(new JsonObject().put("id", 9 ).put("type", "street"    ).put("owner", "nobody").put("price", 160 ).put("rent", 16 ).put("house", 160 ).put("color", "#6ca1f1").put("label1", "Poststrasse"      ).put("label2", ""          ).put("label3", "Poststrasse"      ));
        array.add(new JsonObject().put("id", 10).put("type", "prison"    ).put("owner", ""      ).put("price", 0   ).put("rent", 0  ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Zu Besuch"        ).put("label2", ""          ).put("label3", "Zu Besuch"        ));
        array.add(new JsonObject().put("id", 11).put("type", "street"    ).put("owner", "nobody").put("price", 200 ).put("rent", 20 ).put("house", 200 ).put("color", "#3e1f41").put("label1", "Seestrasse"       ).put("label2", ""          ).put("label3", "Seestrasse"       ));
        array.add(new JsonObject().put("id", 12).put("type", "street"    ).put("owner", "nobody").put("price", 80  ).put("rent", 8  ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Kraftwerk"        ).put("label2", ""          ).put("label3", "Kraftwerk"        ));
        array.add(new JsonObject().put("id", 13).put("type", "street"    ).put("owner", "nobody").put("price", 200 ).put("rent", 20 ).put("house", 200 ).put("color", "#3e1f41").put("label1", "Hafenstrasse"     ).put("label2", ""          ).put("label3", "Hafenstrasse"     ));
        array.add(new JsonObject().put("id", 14).put("type", "street"    ).put("owner", "nobody").put("price", 240 ).put("rent", 24 ).put("house", 240 ).put("color", "#3e1f41").put("label1", "Neue Strasse"     ).put("label2", ""          ).put("label3", "Neue Strasse"     ));
        array.add(new JsonObject().put("id", 15).put("type", "street"    ).put("owner", "nobody").put("price", 500 ).put("rent", 50 ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Westbahnhof"      ).put("label2", ""          ).put("label3", "Westbahnhof"      ));
        array.add(new JsonObject().put("id", 16).put("type", "street"    ).put("owner", "nobody").put("price", 280 ).put("rent", 28 ).put("house", 280 ).put("color", "#dd8426").put("label1", "Muenchner"        ).put("label2", "Strasse"   ).put("label3", "Muenchner Strasse"));
        array.add(new JsonObject().put("id", 17).put("type", "card"      ).put("owner", ""      ).put("price", 0   ).put("rent", 0  ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Gemeinschafts-"   ).put("label2", "feld"      ).put("label3", "Gemeinschaft"     ));
        array.add(new JsonObject().put("id", 18).put("type", "street"    ).put("owner", "nobody").put("price", 280 ).put("rent", 28 ).put("house", 280 ).put("color", "#dd8426").put("label1", "Wiener"           ).put("label2", "Strasse"   ).put("label3", "Wiener Strasse"   ));
        array.add(new JsonObject().put("id", 19).put("type", "street"    ).put("owner", "nobody").put("price", 320 ).put("rent", 32 ).put("house", 320 ).put("color", "#dd8426").put("label1", "Berliner"         ).put("label2", "Strasse"   ).put("label3", "Berliner Strasse" ));
        array.add(new JsonObject().put("id", 20).put("type", "nothing"   ).put("owner", ""      ).put("price", 0   ).put("rent", 0  ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Frei Parken"      ).put("label2", ""          ).put("label3", "Frei Parken"      ));
        array.add(new JsonObject().put("id", 21).put("type", "street"    ).put("owner", "nobody").put("price", 360 ).put("rent", 36 ).put("house", 360 ).put("color", "#cc2e3c").put("label1", "Theater-"         ).put("label2", "strasse"   ).put("label3", "Theaterstrasse"   ));
        array.add(new JsonObject().put("id", 22).put("type", "card"      ).put("owner", ""      ).put("price", 0   ).put("rent", 0  ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Ereignis-"        ).put("label2", "feld"      ).put("label3", "Ereignis"         ));
        array.add(new JsonObject().put("id", 23).put("type", "street"    ).put("owner", "nobody").put("price", 360 ).put("rent", 36 ).put("house", 360 ).put("color", "#cc2e3c").put("label1", "Museums-"         ).put("label2", "strasse"   ).put("label3", "Museumsstrasse"   ));
        array.add(new JsonObject().put("id", 24).put("type", "street"    ).put("owner", "nobody").put("price", 400 ).put("rent", 40 ).put("house", 400 ).put("color", "#cc2e3c").put("label1", "Opernplatz"       ).put("label2", ""          ).put("label3", "Opernplatz"       ));
        array.add(new JsonObject().put("id", 25).put("type", "street"    ).put("owner", "nobody").put("price", 500 ).put("rent", 50 ).put("house", 500 ).put("color", "#fff"   ).put("label1", "Nordbahnhof"      ).put("label2", ""          ).put("label3", "Nordbahnhof"      ));
        array.add(new JsonObject().put("id", 26).put("type", "street"    ).put("owner", "nobody").put("price", 440 ).put("rent", 44 ).put("house", 440 ).put("color", "#e4cd3f").put("label1", "Lessing-"         ).put("label2", "strasse"   ).put("label3", "Lessingstrasse"   ));
        array.add(new JsonObject().put("id", 27).put("type", "street"    ).put("owner", "nobody").put("price", 440 ).put("rent", 44 ).put("house", 440 ).put("color", "#e4cd3f").put("label1", "Schiller-"        ).put("label2", "strasse"   ).put("label3", "Schillerstrasse"  ));
        array.add(new JsonObject().put("id", 28).put("type", "street"    ).put("owner", "nobody").put("price", 80  ).put("rent", 8  ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Wasserwerk"       ).put("label2", ""          ).put("label3", "Wasserwerk"       ));
        array.add(new JsonObject().put("id", 29).put("type", "street"    ).put("owner", "nobody").put("price", 480 ).put("rent", 48 ).put("house", 480 ).put("color", "#e4cd3f").put("label1", "Goethestrasse"    ).put("label2", ""          ).put("label3", "Goethestrasse"    ));
        array.add(new JsonObject().put("id", 30).put("type", "gotoprison").put("owner", ""      ).put("price", 0   ).put("rent", 0  ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Gefaengnis"       ).put("label2", ""          ).put("label3", "Gefaengnis"       ));
        array.add(new JsonObject().put("id", 31).put("type", "street"    ).put("owner", "nobody").put("price", 520 ).put("rent", 52 ).put("house", 520 ).put("color", "#1e6351").put("label1", "Rathausplatz"     ).put("label2", ""          ).put("label3", "Rathausplatz"     ));
        array.add(new JsonObject().put("id", 32).put("type", "street"    ).put("owner", "nobody").put("price", 520 ).put("rent", 52 ).put("house", 520 ).put("color", "#1e6351").put("label1", "Hauptstrasse"     ).put("label2", ""          ).put("label3", "Hauptstrasse"     ));
        array.add(new JsonObject().put("id", 33).put("type", "street"    ).put("owner", ""      ).put("price", 0   ).put("rent", 0  ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Gemeinschafts-"   ).put("label2", "feld"      ).put("label3", "Gemeinschaft"     ));
        array.add(new JsonObject().put("id", 34).put("type", "street"    ).put("owner", "nobody").put("price", 560 ).put("rent", 50 ).put("house", 500 ).put("color", "#1e6351").put("label1", "Bahnhofs-"        ).put("label2", "strasse"   ).put("label3", "Bahnhofsstrasse"  ));
        array.add(new JsonObject().put("id", 35).put("type", "street"    ).put("owner", "nobody").put("price", 500 ).put("rent", 50 ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Hauptbahnhof"     ).put("label2", ""          ).put("label3", "Hauptbahnhof"     ));
        array.add(new JsonObject().put("id", 36).put("type", "card"      ).put("owner", ""      ).put("price", 0   ).put("rent", 0  ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Ereignis-"        ).put("label2", "feld"      ).put("label3", "Ereignis"         ));
        array.add(new JsonObject().put("id", 37).put("type", "street"    ).put("owner", "nobody").put("price", 700 ).put("rent", 70 ).put("house", 700 ).put("color", "#1244b7").put("label1", "Parkstrasse"      ).put("label2", ""          ).put("label3", "Parkstrasse"      ));
        array.add(new JsonObject().put("id", 38).put("type", "tax"       ).put("owner", ""      ).put("price", 200 ).put("rent", 0  ).put("house", 0   ).put("color", "#fff"   ).put("label1", "Steuer"           ).put("label2", ""          ).put("label3", "Steuer"           ));
        array.add(new JsonObject().put("id", 39).put("type", "street"    ).put("owner", "nobody").put("price", 1000).put("rent", 100).put("house", 1000).put("color", "#1244b7").put("label1", "Schlossallee"     ).put("label2", ""          ).put("label3", "Schlossallee"     ));

        eb.consumer("init.board", message -> {
            message.reply(array);
        });

        eb.consumer("player.add", message -> {
            JsonObject player  = new JsonObject(message.body().toString()).put("collection", "players").put("position", 0).put("money", 2000).put("prison", false);
            eb.send("save", player, res -> {
                player.remove("session_id");
                eb.publish("player.joined", player);
                JsonObject findQuery  = new JsonObject().put("collection", "players");
                eb.send("findWithOptions", findQuery, res1 -> {
                    JsonArray playersFindResult = new JsonArray(res1.result().body().toString());
                    message.reply(playersFindResult);
                });
            });
        });

        eb.consumer("start.game", message -> {
            JsonObject findPlayersQuery  = new JsonObject().put("collection", "players");
            eb.send("find", findPlayersQuery, res -> {

                JsonArray playersFindResult = new JsonArray(res.result().body().toString());

                allPlayers = playersFindResult;

                for (int i = 0 ; i < playersFindResult.size(); i++) {
                    JsonObject player = playersFindResult.getJsonObject(i);

                    JsonObject mongoMessage = new JsonObject();
                    JsonObject findUpdate = new JsonObject().put("$set", new JsonObject().put("next", i));

                    mongoMessage.put("collection", "players");
                    mongoMessage.put("findQuery", player);
                    mongoMessage.put("findUpdate", findUpdate);
                    eb.send("updateCollection", mongoMessage, res1 -> {
                        message.reply("ok");
                    });
                }
            });
            eb.publish("players.ready", new JsonObject());

        });

        eb.consumer("play", message -> {
            buyable = false;
            JsonObject json  = new JsonObject(message.body().toString());
            String sessionId = json.getString("session_id");
            JsonObject findPlayerQuery  = new JsonObject().put("collection", "players").put("session_id", sessionId);

            eb.send("findWithOptions", new JsonObject().put("collection", "cards").put("sort", new JsonObject().put("_id", -1)).put("limit", 1), res9 -> {
                JsonArray cardsFindResult = new JsonArray(res9.result().body().toString());
                JsonObject card = cardsFindResult.getJsonObject(0);

                JsonObject findBoughtStreetsQuery  = new JsonObject().put("collection", "bought");
                eb.send("find", findBoughtStreetsQuery, res10 -> {
                    JsonArray streets = new JsonArray(res10.result().body().toString());

                    eb.send("findOne", findPlayerQuery, res -> {

                        JsonObject player = new JsonObject(res.result().body().toString());

                        eb.send("findWithOptions", new JsonObject().put("collection", "players").put("sort", new JsonObject().put("next", -1))/*.put("limit", 1)*/, res1 -> {
                            JsonArray playersFindResult = new JsonArray(res1.result().body().toString());

                            int min = playersFindResult.getJsonObject(playersFindResult.size() - 1).getInteger("next");
                            int max = playersFindResult.getJsonObject(0).getInteger("next");

                            if (player.getInteger("next") == min) {

                                int cube1 = new Random().ints(1, (6 + 1)).limit(1).findFirst().getAsInt();
                                int cube2 = new Random().ints(1, (6 + 1)).limit(1).findFirst().getAsInt();

                                if (cube1 != cube2) {
                                    player.put("next", max + 1);
                                }

                                int currentPosition = player.getInteger("position");
                                int newPosition     = currentPosition + cube1 + cube2;

                                if (player.getBoolean("prison").equals(true)) {
                                    if (cube1 == cube2) {
                                        player.put("prison", false);
                                    }
                                    newPosition = currentPosition;
                                }

                                int currentMoney = player.getInteger("money");
                                int newMoney = currentMoney;
                                if (newPosition >= array.size()) {
                                    newPosition = newPosition - array.size();
                                    newMoney = currentMoney + 200;
                                }
                                player.put("money", newMoney);
                                player.put("position", newPosition);
                                JsonObject tile  = array.getJsonObject(newPosition);
                                JsonObject query = tile.copy();
                                query.clear().put("collection", "bought").put("label3", tile.getString("label3"));


                                eb.send("find", query, ar -> {
                                    JsonArray result = new JsonArray(ar.result().body().toString());


                                    JsonObject findStreetQuery = new JsonObject().put("collection", "bought").put("id", tile.getInteger("id"));
                                    eb.send("findOne", findStreetQuery, res7 -> {
                                        if (result.size() == 0) {
                                            if (tile.getString("type").equals("tax")) {
                                                int tax = tile.getInteger("price");
                                                if (currentMoney < tax) {
                                                    gameOver();
                                                } else {
                                                    int newMoney2 = currentMoney - tax;
                                                    player.put("money", newMoney2);
                                                }
                                            } else if (tile.getString("type").equals("card")) {
                                                processCard(player, card, streets);
                                                eb.send("remove", new JsonObject().put("collection", "cards").put("id", card.getInteger("id")));
                                                eb.publish("card.drawn", card);
                                            } else if (tile.getString("type").equals("street")
                                                    && tile.getString("owner").equals("nobody")
                                                    && tile.getInteger("price") <= player.getInteger("money")) {
                                                buyable = true;
                                            } else {
                                                buyable = false;
                                            }
                                        } else {
                                            buyable = false;
                                            if (tile.getString("type").equals("street")
                                                    && !result.getJsonObject(0).getMap().get("owner").equals("nobody")
                                                    && !result.getJsonObject(0).getMap().get("owner").equals(player.getMap().get("name"))) {

                                                JsonObject street = new JsonObject(res7.result().body().toString());
                                                int factor = 1;
                                                if (street.getInteger("houses") > 1) {
                                                    factor = street.getInteger("houses");
                                                }
                                                int rent = street.getInteger("rent") * factor;
                                                if (currentMoney < rent) {
                                                    gameOver();
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

                                        if (tile.getString("type").equals("gotoprison") || player.getBoolean("prison").equals(true)) {
                                            player.put("position", 10);
                                            player.put("prison", true);
                                        }

                                        JsonObject jsonPublish = new JsonObject()
                                                .put("position", player.getInteger("position"))
                                                .put("cube1", cube1)
                                                .put("cube2", cube2)
                                                .put("name", player.getString("name"))
                                                .put("prison", player.getBoolean("prison"));

                                        JsonObject jsonReply = jsonPublish
                                                .put("buyable", buyable)
                                                .put("money", player.getInteger("money"));

                                        JsonObject mongoMessage = new JsonObject();
                                        JsonObject findUpdate = new JsonObject().put("$set", new JsonObject()
                                                .put("money", player.getMap().get("money"))
                                                .put("position", player.getMap().get("position"))
                                                .put("prison", player.getMap().get("prison"))
                                                .put("next", player.getMap().get("next"))
                                        );

                                        mongoMessage.put("collection", "players");
                                        mongoMessage.put("findQuery", findPlayerQuery);
                                        mongoMessage.put("findUpdate", findUpdate);

                                        eb.send("updateCollection", mongoMessage, res4 -> {
                                            eb.publish("played", jsonPublish);
                                            message.reply(jsonReply);
                                        });
                                    });
                                });
                            } else {
                                message.reply("notok");
                            }
                        });
                    });
                });
            });
        });



        // buy
        eb.consumer("buy", message -> {
            JsonObject json = new JsonObject(message.body().toString());
            if (json.getInteger("wants").equals(0)) {
                message.reply(json);
                eb.publish("bought", new JsonObject().put("wants","-1").put("name", json.getString("name")));
            } else {

                JsonObject findPlayerQuery  = new JsonObject().put("collection", "players").put("session_id", json.getString("session_id"));
                eb.send("findOne", findPlayerQuery, res3 -> {

                    JsonObject player   = new JsonObject(res3.result().body().toString());
                    int currentMoney    = player.getInteger("money");
                    int currentPosition = player.getInteger("position");
                    JsonObject tile     = array.getJsonObject(currentPosition);
                    int newMoney        = currentMoney - tile.getInteger("price");
                    JsonObject query    = tile.copy();

                    query.clear().put("collection", "bought").put("label3", tile.getString("label3"));
                    eb.send("find", query, ar -> {
                        JsonArray result = new JsonArray(ar.result().body().toString());
                        if (result.size() == 0) {
                            if (tile.getString("type").equals("street") && tile.getString("owner").equals("nobody")) {
                                tile.put("collection", "bought").put("owner", player.getMap().get("name")).put("houses", 0);
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
                                                    .put("street", tile.getInteger("id"))
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




        //
        eb.consumer("get.streets", message -> {
            JsonObject json = new JsonObject(message.body().toString());
            String sessionId = json.getString("session_id");

            JsonObject findPlayerQuery  = new JsonObject().put("collection", "players").put("session_id", sessionId);
            eb.send("findOne", findPlayerQuery, res -> {
                JsonObject player = new JsonObject(res.result().body().toString());

                JsonObject findPlayerStreetsQuery  = new JsonObject().put("collection", "bought").put("owner", player.getString("name"));
                eb.send("find", findPlayerStreetsQuery, res1 -> {
                    JsonArray streets = new JsonArray(res1.result().body().toString());
                    message.reply(streets);
                });
            });
        });




        eb.consumer("get.cards", message -> {
            JsonObject json = new JsonObject(message.body().toString());
            String type = json.getString("type");
            JsonObject findQuery  = new JsonObject().put("collection", "cards").put("type", type);
            eb.send("findWithOptions", findQuery, res1 -> {
                JsonArray cardsFindResult = new JsonArray(res1.result().body().toString());
                message.reply(cardsFindResult);
            });
        });




        eb.consumer("buy.house", message -> {
            JsonObject json  = new JsonObject(message.body().toString());
            String sessionId = json.getString("session_id");
            int id           = json.getInteger("wants");
            JsonObject findPlayerQuery  = new JsonObject().put("collection", "players").put("session_id", sessionId);
            eb.send("findOne", findPlayerQuery, res -> {
                JsonObject player = new JsonObject(res.result().body().toString());
                JsonObject findPlayerStreetQuery  = new JsonObject().put("collection", "bought").put("owner", player.getString("name")).put("id", id);
                eb.send("findOne", findPlayerStreetQuery, res1 -> {
                    JsonObject street = new JsonObject(res1.result().body().toString());
                    JsonObject tile   = array.getJsonObject(id);
                    if (street.getInteger("houses") >= 5) {
                        message.reply("Es reicht ja wohl ...");
                    } else if (!street.isEmpty() && tile.getInteger("house") > 0) {
                        int currentMoney = player.getInteger("money");
                        if (currentMoney < tile.getInteger("house")) {
                            message.reply("no money, my dear!");
                        } else {
                            JsonObject findPlayerStreetsQuery  = new JsonObject().put("collection", "bought").put("owner", player.getString("name")).put("color", tile.getString("color"));
                            eb.send("find", findPlayerStreetsQuery, res2 -> {
                                JsonArray streets = new JsonArray(res2.result().body().toString());
                                if (streetsComplete(streets.size(), street.getString("color"))) {
                                    boolean flag = false;
                                    float sum    = 0;
                                    for (int i = 0; i < streets.size(); i++) {
                                        sum = sum + streets.getJsonObject(i).getInteger("houses");
                                        if (streets.getJsonObject(i).getInteger("houses") > street.getInteger("houses")) {
                                            flag = true;
                                        }
                                    }
                                    if (sum / streets.size() == streets.getJsonObject(0).getInteger("houses")) {
                                        flag = true;
                                    }
                                    if (flag) {
                                        int newMoney = currentMoney - tile.getInteger("house");
                                        JsonObject mongoMessage = new JsonObject();
                                        JsonObject findUpdate = new JsonObject().put("$set", new JsonObject().put("money", newMoney));
                                        mongoMessage.put("collection", "players");
                                        mongoMessage.put("findQuery", findPlayerQuery);
                                        mongoMessage.put("findUpdate", findUpdate);
                                        eb.send("updateCollection", mongoMessage, res4 -> {
                                            JsonObject mongoMessage3 = new JsonObject();
                                            JsonObject addHouse = new JsonObject().put("$inc", new JsonObject().put("houses", 1));
                                            mongoMessage3.put("collection", street.getString("collection"));
                                            mongoMessage3.put("findQuery", street);
                                            mongoMessage3.put("findUpdate", addHouse);
                                            eb.send("updateCollection", mongoMessage3, res6 -> {
                                                message.reply("ok");
                                                eb.publish("bought.house", new JsonObject().put("id", id).put("house", street.getInteger("houses")));
                                            });
                                        });
                                    } else {
                                        message.reply("bitte erst woanners!");
                                    }
                                } else {
                                    message.reply("uhhhh .... noch nicht alle, wa!?");
                                }
                            });
                        }
                    } else {
                        message.reply("uhhhh .... nicht deine!?");
                    }
                });
            });
        });

        eb.consumer("offer.street", message -> {
            JsonObject json = new JsonObject(message.body().toString());
            String sessionId = json.getString("session_id");
            int id = json.getInteger("offers");

            JsonObject findPlayerQuery = new JsonObject().put("collection", "players").put("session_id", sessionId);
            eb.send("findOne", findPlayerQuery, res -> {
                JsonObject player = new JsonObject(res.result().body().toString());

                JsonObject findPlayerStreetQuery = new JsonObject().put("collection", "bought").put("owner", player.getString("name")).put("id", id);
                eb.send("findOne", findPlayerStreetQuery, res1 -> {
                    JsonObject street = new JsonObject(res1.result().body().toString());
                    street.put("collection", "offer").put("rejected", 0);
                    eb.send("save", street, res2 -> {
                        message.reply("ok");
                        eb.publish("offered.street", new JsonObject().put("id", id));
                    });

                });
            });
        });



        eb.consumer("reoffer.street", message -> {
            JsonObject json = new JsonObject(message.body().toString());
//            String sessionId = json.getString("session_id"); // TODO
            String name        = json.getString("name");
            int id             = json.getInteger("offers");
            String streetOwner = json.getString("streetOwner");
            eb.publish("reoffered.street", new JsonObject().put("id", id).put("player", name).put("streetOwner", streetOwner));
        });



        eb.consumer("accept.offer", message -> {
            JsonObject json = new JsonObject(message.body().toString());

            JsonObject findStreetsQuery = new JsonObject().put("collection", "bought").put("id", new JsonObject().put("$in", json.getJsonArray("ids")));

            eb.send("find", findStreetsQuery, (Handler<AsyncResult<Message<JsonArray>>>) res1 -> {
                JsonArray street = res1.result().body();


                JsonObject offer    = street.getJsonObject(0);
                JsonObject reoffer  = street.getJsonObject(1);

                String ownerOffer   = offer.getString("owner");
                String ownerReoffer = reoffer.getString("owner");

                JsonObject mongoMessage = new JsonObject();
                mongoMessage.put("collection", "bought");
                mongoMessage.put("findQuery", offer);
                mongoMessage.put("findUpdate", offer.clear().put("owner", ownerReoffer));
                eb.send("updateCollection", mongoMessage, res -> {

                });

                JsonObject mongoMessage2 = new JsonObject();
                mongoMessage2.put("collection", "bought");
                reoffer.remove("_id");
                mongoMessage2.put("findQuery", reoffer);
                mongoMessage2.put("findUpdate", reoffer.clear().put("owner", ownerOffer));
                eb.send("updateCollection", mongoMessage2, res -> {

                });


                JsonArray newStreets = new JsonArray().add(offer).add(reoffer);
                eb.publish("offer.accepted", new JsonObject().put("id", newStreets));

            });
        });



        eb.consumer("reject.offer", message -> {
            JsonObject json = new JsonObject(message.body().toString());
            JsonObject findStreetQuery = new JsonObject().put("collection", "offer").put("id", json.getInteger("id"));
            eb.send("findOne", findStreetQuery, res1 -> {
                JsonObject street = new JsonObject(res1.result().body().toString());
                int rejectCounter = street.getInteger("rejected") + 1;

                eb.send("save", street.put("rejected", rejectCounter), res2 -> {
                    if (street.getInteger("rejected") == (allPlayers.size() - 1)) {
                        eb.publish("offer.rejected", new JsonObject().put("id", street.getInteger("id")));
                        eb.send("remove", street);
                    } else {
                        message.reply("ok");
                    }
                });
            });
        });
    }


    private boolean streetsComplete(int size, String color) {

        JsonArray tiles = new JsonArray(array.getList());

        int tilesSize = 0;
        for (int i = 0 ; i < tiles.size(); i++) {
            JsonObject tile = array.getJsonObject(i);
            if (tile.getString("color").equals(color)) {
                tilesSize++;
            }
        }
        if (size == tilesSize) {
            return true;
        }
        return false;
    }

    private JsonObject processCard(JsonObject player, JsonObject card, JsonArray streets) {

        EventBus eb     = vertx.eventBus();

        String action = card.getString("action");
        if (action.equals("get")) {
            int newMoney;
            String fromto = card.getString("fromto");
            if (fromto.equals("all")) {
                newMoney = player.getInteger("money") + ((allPlayers.size() - 1) * Integer.parseInt(card.getString("value")));
                for (int i = 0 ; i < allPlayers.size(); i++) {
                    JsonObject currentPlayer = allPlayers.getJsonObject(i);
                    String currentPlayerName = currentPlayer.getString("name");
                    if (!currentPlayerName.equals(player.getString("name"))) {

                        int amount = Integer.parseInt(card.getString("value"));

                        JsonObject findPlayerByNameQuery = new JsonObject().put("collection", "players").put("name", currentPlayerName);

                        JsonObject mongoMessage = new JsonObject();
                        JsonObject collectMoney = new JsonObject().put("$inc", new JsonObject().put("money", -amount));

                        mongoMessage.put("collection", "players");
                        mongoMessage.put("findQuery", findPlayerByNameQuery);
                        mongoMessage.put("findUpdate", collectMoney);

                        eb.send("updateCollection", mongoMessage, res -> {
                            eb.publish("rent.collected", new JsonObject().put("name", currentPlayerName).put("rent", amount));
                        });
                    }
                }
            } else {
                newMoney = player.getInteger("money") + Integer.parseInt(card.getString("value"));
            }
            player.put("money", newMoney);
        } else if (action.equals("put")) {
            int newMoney;
            if (card.getString("value").indexOf('*') >= 0) {
                int houses = 0;
                for (int i = 0 ; i < streets.size(); i++) {
                    JsonObject street = streets.getJsonObject(i);
                    if (street.getString("owner").equals(player.getString("name"))) {
                        houses += street.getInteger("houses");
                    }
                }
                newMoney = player.getInteger("money") - (houses * Integer.parseInt(card.getString("value").substring(1, card.getString("value").length())));
            } else {
                newMoney = player.getInteger("money") - Integer.parseInt(card.getString("value"));
            }
            if (player.getInteger("money") < newMoney) {
                gameOver();
            }
            player.put("money", newMoney);
        } else if (action.equals("position")) {
            int position;
            if (card.getString("value").indexOf('+') >= 0) {
                position = player.getInteger("position") + Integer.parseInt(card.getString("value"));
            } else if(card.getString("value").indexOf('-') >= 0) {
                position = player.getInteger("position") - Integer.parseInt(card.getString("value").substring(1, card.getString("value").length()));
            } else {
                position = Integer.parseInt(card.getString("value"));
            }
            player.put("position", position);

            buyable = true;
            JsonObject tile = new JsonObject(array.getValue(position).toString());
            if (!tile.getString("type").equals("street")) {
                buyable = false;
            }
            for (int i = 0 ; i < streets.size(); i++) {
                JsonObject street = streets.getJsonObject(i);
                if (street.getInteger("id") == position || street.getInteger("price") >= player.getInteger("money")) {
                    buyable = false;

                    int factor = 1;
                    if (street.getInteger("houses") > 1) {
                        factor = street.getInteger("houses");
                    }
                    int rent         = street.getInteger("rent") * factor;
                    int currentMoney = player.getInteger("money");

                    if (currentMoney < rent) {
                        gameOver();
                    } else {
                        int newMoney2 = currentMoney - rent;
                        player.put("money", newMoney2);

                        String owner = street.getString("owner");
                        JsonObject findPlayerByNameQuery = new JsonObject().put("collection", "players").put("name", owner);

                        JsonObject mongoMessage2 = new JsonObject();
                        JsonObject addRent = new JsonObject().put("$inc", new JsonObject().put("money", rent));

                        mongoMessage2.put("collection", "players");
                        mongoMessage2.put("findQuery", findPlayerByNameQuery);
                        mongoMessage2.put("findUpdate", addRent);

                        eb.send("updateCollection", mongoMessage2, res -> {
                            eb.publish("rent.collected", new JsonObject().put("name", owner).put("rent", rent));
                        });
                    }
                    break;
                }
            }
        } else if (action.equals("prison")) {
            player.put("position", 10);
            player.put("prison", true);
        } else if (action.equals("free")) {
            player.put("prison", false);
        }

        return player;
    }

    private void gameOver() {
        EventBus eb = vertx.eventBus();
        eb.publish("game.over", new JsonObject().put("game", "over"));
    }
}
