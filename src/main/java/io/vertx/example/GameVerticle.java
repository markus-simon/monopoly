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
        array.add(new JsonObject().put("id", 0 ).put("type", "start"     ).put("owner", "nobody").put("price", ""    ).put("rent", ""   ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Los"              ).put("label2", ""          ).put("label3", "Los"              ));
        array.add(new JsonObject().put("id", 1 ).put("type", "street"    ).put("owner", "nobody").put("price", "40"  ).put("rent", "4"  ).put("house", "40"  ).put("color", "#0c1d65").put("label1", "Badstrasse"       ).put("label2", ""          ).put("label3", "Badstrasse"       ));
        array.add(new JsonObject().put("id", 2 ).put("type", "card"      ).put("owner", ""      ).put("price", ""    ).put("rent", ""   ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Gemeinschafts-"   ).put("label2", "feld"      ).put("label3", "Gemeinschaft"     ));
        array.add(new JsonObject().put("id", 3 ).put("type", "street"    ).put("owner", "nobody").put("price", "80"  ).put("rent", "8"  ).put("house", "80"  ).put("color", "#0c1d65").put("label1", "Turmstrasse"      ).put("label2", ""          ).put("label3", "Turmstrasse"      ));
        array.add(new JsonObject().put("id", 4 ).put("type", "tax"       ).put("owner", ""      ).put("price", "200" ).put("rent", ""   ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Steuer"           ).put("label2", ""          ).put("label3", "Steuer"           ));
        array.add(new JsonObject().put("id", 5 ).put("type", "street"    ).put("owner", "nobody").put("price", "500" ).put("rent", "50" ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Suedbahnhof"      ).put("label2", ""          ).put("label3", "Suedbahnhof"      ));
        array.add(new JsonObject().put("id", 6 ).put("type", "street"    ).put("owner", "nobody").put("price", "120" ).put("rent", "12" ).put("house", "120" ).put("color", "#6ca1f1").put("label1", "Chaussee-"        ).put("label2", "strasse"   ).put("label3", "Chausseestrasse"  ));
        array.add(new JsonObject().put("id", 7 ).put("type", "card"      ).put("owner", ""      ).put("price", ""    ).put("rent", ""   ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Ereignis-"        ).put("label2", "feld"      ).put("label3", "Ereignis"         ));
        array.add(new JsonObject().put("id", 8 ).put("type", "street"    ).put("owner", "nobody").put("price", "120" ).put("rent", "12" ).put("house", "120" ).put("color", "#6ca1f1").put("label1", "Elisenstrasse"    ).put("label2", ""          ).put("label3", "Elisenstrasse"    ));
        array.add(new JsonObject().put("id", 9 ).put("type", "street"    ).put("owner", "nobody").put("price", "160" ).put("rent", "16" ).put("house", "160" ).put("color", "#6ca1f1").put("label1", "Poststrasse"      ).put("label2", ""          ).put("label3", "Poststrasse"      ));
        array.add(new JsonObject().put("id", 10).put("type", "prison"    ).put("owner", ""      ).put("price", ""    ).put("rent", ""   ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Zu Besuch"        ).put("label2", ""          ).put("label3", "Zu Besuch"        ));
        array.add(new JsonObject().put("id", 11).put("type", "street"    ).put("owner", "nobody").put("price", "200" ).put("rent", "20" ).put("house", "200" ).put("color", "#3e1f41").put("label1", "Seestrasse"       ).put("label2", ""          ).put("label3", "Seestrasse"       ));
        array.add(new JsonObject().put("id", 12).put("type", "street"    ).put("owner", "nobody").put("price", "80"  ).put("rent", "8"  ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Kraftwerk"        ).put("label2", ""          ).put("label3", "Kraftwerk"        ));
        array.add(new JsonObject().put("id", 13).put("type", "street"    ).put("owner", "nobody").put("price", "200" ).put("rent", "20" ).put("house", "200" ).put("color", "#3e1f41").put("label1", "Hafenstrasse"     ).put("label2", ""          ).put("label3", "Hafenstrasse"     ));
        array.add(new JsonObject().put("id", 14).put("type", "street"    ).put("owner", "nobody").put("price", "240" ).put("rent", "24" ).put("house", "240" ).put("color", "#3e1f41").put("label1", "Neue Strasse"     ).put("label2", ""          ).put("label3", "Neue Strasse"     ));
        array.add(new JsonObject().put("id", 15).put("type", "street"    ).put("owner", "nobody").put("price", "500" ).put("rent", "50" ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Westbahnhof"      ).put("label2", ""          ).put("label3", "Westbahnhof"      ));
        array.add(new JsonObject().put("id", 16).put("type", "street"    ).put("owner", "nobody").put("price", "280" ).put("rent", "28" ).put("house", "280" ).put("color", "#dd8426").put("label1", "Muenchner"        ).put("label2", "Strasse"   ).put("label3", "Muenchner Strasse"));
        array.add(new JsonObject().put("id", 17).put("type", "card"      ).put("owner", ""      ).put("price", ""    ).put("rent", ""   ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Gemeinschafts-"   ).put("label2", "feld"      ).put("label3", "Gemeinschaft"     ));
        array.add(new JsonObject().put("id", 18).put("type", "street"    ).put("owner", "nobody").put("price", "280" ).put("rent", "28" ).put("house", "280" ).put("color", "#dd8426").put("label1", "Wiener"           ).put("label2", "Strasse"   ).put("label3", "Wiener Strasse"   ));
        array.add(new JsonObject().put("id", 19).put("type", "street"    ).put("owner", "nobody").put("price", "320" ).put("rent", "32" ).put("house", "320" ).put("color", "#dd8426").put("label1", "Berliner"         ).put("label2", "Strasse"   ).put("label3", "Berliner Strasse" ));
        array.add(new JsonObject().put("id", 20).put("type", "nothing"   ).put("owner", ""      ).put("price", ""    ).put("rent", ""   ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Frei Parken"      ).put("label2", ""          ).put("label3", "Frei Parken"      ));
        array.add(new JsonObject().put("id", 21).put("type", "street"    ).put("owner", "nobody").put("price", "360" ).put("rent", "36" ).put("house", "360" ).put("color", "#cc2e3c").put("label1", "Theater-"         ).put("label2", "strasse"   ).put("label3", "Theaterstrasse"   ));
        array.add(new JsonObject().put("id", 22).put("type", "card"      ).put("owner", ""      ).put("price", ""    ).put("rent", ""   ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Ereignis-"        ).put("label2", "feld"      ).put("label3", "Ereignis"         ));
        array.add(new JsonObject().put("id", 23).put("type", "street"    ).put("owner", "nobody").put("price", "360" ).put("rent", "36" ).put("house", "360" ).put("color", "#cc2e3c").put("label1", "Museums-"         ).put("label2", "strasse"   ).put("label3", "Museumsstrasse"   ));
        array.add(new JsonObject().put("id", 24).put("type", "street"    ).put("owner", "nobody").put("price", "400" ).put("rent", "40" ).put("house", "400" ).put("color", "#cc2e3c").put("label1", "Opernplatz"       ).put("label2", ""          ).put("label3", "Opernplatz"       ));
        array.add(new JsonObject().put("id", 25).put("type", "street"    ).put("owner", "nobody").put("price", "500" ).put("rent", "50" ).put("house", "500" ).put("color", "#fff"   ).put("label1", "Nordbahnhof"      ).put("label2", ""          ).put("label3", "Nordbahnhof"      ));
        array.add(new JsonObject().put("id", 26).put("type", "street"    ).put("owner", "nobody").put("price", "440" ).put("rent", "44" ).put("house", "440" ).put("color", "#e4cd3f").put("label1", "Lessing-"         ).put("label2", "strasse"   ).put("label3", "Lessingstrasse"   ));
        array.add(new JsonObject().put("id", 27).put("type", "street"    ).put("owner", "nobody").put("price", "440" ).put("rent", "44" ).put("house", "440" ).put("color", "#e4cd3f").put("label1", "Schiller-"        ).put("label2", "strasse"   ).put("label3", "Schillerstrasse"  ));
        array.add(new JsonObject().put("id", 28).put("type", "street"    ).put("owner", "nobody").put("price", "80"  ).put("rent", "8"  ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Wasserwerk"       ).put("label2", ""          ).put("label3", "Wasserwerk"       ));
        array.add(new JsonObject().put("id", 29).put("type", "street"    ).put("owner", "nobody").put("price", "480" ).put("rent", "48" ).put("house", "480" ).put("color", "#e4cd3f").put("label1", "Goethestrasse"    ).put("label2", ""          ).put("label3", "Goethestrasse"    ));
        array.add(new JsonObject().put("id", 30).put("type", "gotoprison").put("owner", ""      ).put("price", ""    ).put("rent", ""   ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Gefaengnis"       ).put("label2", ""          ).put("label3", "Gefaengnis"       ));
        array.add(new JsonObject().put("id", 31).put("type", "street"    ).put("owner", "nobody").put("price", "520" ).put("rent", "52" ).put("house", "520" ).put("color", "#1e6351").put("label1", "Rathausplatz"     ).put("label2", ""          ).put("label3", "Rathausplatz"     ));
        array.add(new JsonObject().put("id", 32).put("type", "street"    ).put("owner", "nobody").put("price", "520" ).put("rent", "52" ).put("house", "520" ).put("color", "#1e6351").put("label1", "Hauptstrasse"     ).put("label2", ""          ).put("label3", "Hauptstrasse"     ));
        array.add(new JsonObject().put("id", 33).put("type", "street"    ).put("owner", ""      ).put("price", ""    ).put("rent", ""   ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Gemeinschafts-"   ).put("label2", "feld"      ).put("label3", "Gemeinschaft"     ));
        array.add(new JsonObject().put("id", 34).put("type", "street"    ).put("owner", "nobody").put("price", "560" ).put("rent", "50" ).put("house", "500" ).put("color", "#1e6351").put("label1", "Bahnhofs-"        ).put("label2", "strasse"   ).put("label3", "Bahnhofsstrasse"  ));
        array.add(new JsonObject().put("id", 35).put("type", "street"    ).put("owner", "nobody").put("price", "500" ).put("rent", "50" ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Hauptbahnhof"     ).put("label2", ""          ).put("label3", "Hauptbahnhof"     ));
        array.add(new JsonObject().put("id", 36).put("type", "card"      ).put("owner", ""      ).put("price", ""    ).put("rent", ""   ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Ereignis-"        ).put("label2", "feld"      ).put("label3", "Ereignis"         ));
        array.add(new JsonObject().put("id", 37).put("type", "street"    ).put("owner", "nobody").put("price", "700" ).put("rent", "70" ).put("house", "700" ).put("color", "#1244b7").put("label1", "Parkstrasse"      ).put("label2", ""          ).put("label3", "Parkstrasse"      ));
        array.add(new JsonObject().put("id", 38).put("type", "tax"       ).put("owner", ""      ).put("price", "200" ).put("rent", ""   ).put("house", ""    ).put("color", "#fff"   ).put("label1", "Steuer"           ).put("label2", ""          ).put("label3", "Steuer"           ));
        array.add(new JsonObject().put("id", 39).put("type", "street"    ).put("owner", "nobody").put("price", "1000").put("rent", "100").put("house", "1000").put("color", "#1244b7").put("label1", "Schlossallee"     ).put("label2", ""          ).put("label3", "Schlossallee"     ));

        eb.consumer("player.add", message -> {
            JsonObject player  = new JsonObject(message.body().toString()).put("collection", "players").put("position", "0").put("money", "2000").put("prison", false);
            eb.send("save", player, res -> {
                player.remove("session_id");
                eb.publish("player.joined", player);
                JsonObject findQuery  = new JsonObject().put("collection", "players");
                eb.send("findWithOptions", findQuery, res1 -> {
                    JsonArray playersFindResult = new JsonArray(res1.result().body().toString());

                    message.reply(playersFindResult);

/*                    if (playersFindResult.size() > 1) {
                        eb.publish("players.ready", playersFindResult);
                    }*/
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
                                if (player.getBoolean("prison").equals(true)) {
                                    if (cube1 == cube2) {
                                        player.put("prison", false);
                                    }
                                }
                                int currentPosition = Integer.parseInt(player.getMap().get("position").toString());
                                int newPosition     = currentPosition + cube1 + cube2;
                                int currentMoney = Integer.parseInt(player.getMap().get("money").toString());
                                int newMoney = currentMoney;
                                if (newPosition >= array.size()) {
                                    newPosition = newPosition - array.size();
                                    newMoney = currentMoney + 200;
                                }
                                player.put("money", newMoney);
                                player.put("position", newPosition);
                                data.put("position", newPosition);
                                data.put("cube1", cube1);
                                data.put("cube2", cube2);
                                JsonObject tile = new JsonObject(array.getValue(newPosition).toString());
                                JsonObject query = tile.copy();
                                query.clear().put("collection", "bought").put("label3", tile.getString("label3"));
                                eb.send("find", query, ar -> {
                                    JsonArray result = new JsonArray(ar.result().body().toString());
                                    JsonObject findStreetQuery = new JsonObject().put("collection", "bought").put("id", tile.getInteger("id"));
                                    eb.send("findOne", findStreetQuery, res7 -> {
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


                                                JsonObject street = new JsonObject(res7.result().body().toString());


                                                    int factor = 1;
                                                    if (street.getInteger("houses") > 1) {
                                                        factor = street.getInteger("houses");
                                                    }
                                                    int rent = Integer.parseInt(street.getString("rent")) * factor;

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
                                                .put("prison",   player.getMap().get("prison"))
                                        );

                                        mongoMessage.put("collection", "players");
                                        mongoMessage.put("findQuery", findPlayerQuery);
                                        mongoMessage.put("findUpdate", findUpdate);

                                        eb.send("updateCollection", mongoMessage, res4 -> {
                                            eb.publish("played", jsonReply);
                                            message.reply(player);
                                        });
                                    });
                                    if (tile.getString("type").equals("gotoprison") || player.getBoolean("prison").equals(true)) {
                                        data.put("position", 10);
                                        player.put("position", 10);
                                        player.put("prison", true);
                                        data.put("prison", true);
                                    }
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
                message.reply(json);
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
                    JsonObject tile   = new JsonObject(array.getValue(id).toString());
                    if (street.getInteger("houses") >= 5) {
                        message.reply("Es reicht ja wohl ...");
                    } else if (!street.isEmpty() && !tile.getString("house").isEmpty()) {
                        int currentMoney = player.getInteger("money");
                        if (currentMoney < Integer.parseInt(tile.getString("house"))) {
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
                                        int newMoney = currentMoney - Integer.parseInt(tile.getString("house"));
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
                    message.reply("ok");
                    eb.publish("offered.street", new JsonObject().put("id", id));
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
}
