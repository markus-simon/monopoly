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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
            FindOptions findOptions = new FindOptions().setFields(new JsonObject().put("_id", false).put("name", true).put("color",true).put("next", true).put("id", true).put("type", true).put("value", true).put("action", true).put("fromto", true).put("content", true));
            if (document.containsKey("sort") && !document.getJsonObject("sort").isEmpty()) {
                findOptions.setSort(document.getJsonObject("sort"));
                document.remove("sort");
            }
            if (document.containsKey("limit")) {
                findOptions.setLimit(document.getInteger("limit"));
                document.remove("limit");
            }
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
        eb.send("remove",new JsonObject().put("collection", "offer"));
        eb.send("remove",new JsonObject().put("collection", "players"));
        eb.send("remove",new JsonObject().put("collection", "cards"), res -> {
            if(res.succeeded()) {
                JsonArray cards = new JsonArray();







                cards.add(new JsonObject().put("id", 0 ).put("type", "chance"   ).put("content", "Rücke vor bis zur Seestraße."                                                                  ).put("action", "position").put("value", "11" ).put("fromto", ""    ));
                cards.add(new JsonObject().put("id", 1 ).put("type", "chance"   ).put("content", "Du hast in einem Kreuzworträtselwettbewerb gewonnen."                                          ).put("action", "get"     ).put("value", "200").put("fromto", "bank"));
                cards.add(new JsonObject().put("id", 2 ).put("type", "chance"   ).put("content", "Miete und Anleihezinsen werden fällig."                                                        ).put("action", "get"     ).put("value", "300").put("fromto", "bank"));
/* x */         cards.add(new JsonObject().put("id", 3 ).put("type", "chance"   ).put("content", "Du kommst aus dem Gefängnis frei."                                                             ).put("action", "free"    ).put("value", "0"  ).put("fromto", ""    ));
                cards.add(new JsonObject().put("id", 4 ).put("type", "chance"   ).put("content", "Rücke bis auf Los vor."                                                                        ).put("action", "position").put("value", "0"  ).put("fromto", ""    ));
                cards.add(new JsonObject().put("id", 5 ).put("type", "chance"   ).put("content", "Die Bank zahlt die eine Dividende."                                                            ).put("action", "get"     ).put("value", "100").put("fromto", "bank"));
                cards.add(new JsonObject().put("id", 6 ).put("type", "chance"   ).put("content", "Rücke vor bis zu Schlossallee."                                                                ).put("action", "position").put("value", "39" ).put("fromto", ""    ));
                cards.add(new JsonObject().put("id", 7 ).put("type", "chance"   ).put("content", "Rücke vor bis zum Opernplatz."                                                                 ).put("action", "position").put("value", "24" ).put("fromto", ""    ));
                cards.add(new JsonObject().put("id", 9 ).put("type", "chance"   ).put("content", "Lasse alle Deine Häuser renovieren."                                                           ).put("action", "put"     ).put("value", "*50").put("fromto", "bank"));
                cards.add(new JsonObject().put("id", 8 ).put("type", "chance"   ).put("content", "Gehe 3 Felder zurück."                                                                         ).put("action", "position").put("value", "-3" ).put("fromto", ""    ));
                cards.add(new JsonObject().put("id", 10).put("type", "chance"   ).put("content", "Du wirst zu Strassenausbesserungsarbeiten herangezogen."                                       ).put("action", "put"     ).put("value", "80" ).put("fromto", "bank"));
                cards.add(new JsonObject().put("id", 11).put("type", "chance"   ).put("content", "Betrunken im Dienst."                                                                          ).put("action", "put"     ).put("value", "40" ).put("fromto", "bank"));
                cards.add(new JsonObject().put("id", 12).put("type", "chance"   ).put("content", "Strafe für zu schnelles Fahren."                                                               ).put("action", "put"     ).put("value", "30" ).put("fromto", "bank"));
                cards.add(new JsonObject().put("id", 13).put("type", "chance"   ).put("content", "Gehe in das Gefängnis. Begib Dich direkt dorthin."                                             ).put("action", "prison"  ).put("value", "0"  ).put("fromto", ""    ));
                cards.add(new JsonObject().put("id", 14).put("type", "chance"   ).put("content", "Zahle Schulgeld."                                                                              ).put("action", "put"     ).put("value", "30" ).put("fromto", "bank"));

                cards.add(new JsonObject().put("id", 0 ).put("type", "community").put("content", "Es ist dein Geburtstag."                                                                       ).put("action", "get"     ).put("value", "100").put("fromto", "all" ));
                cards.add(new JsonObject().put("id", 1 ).put("type", "community").put("content", "Aus Lagerverkäufen erhält Du DM 100."                                                          ).put("action", "get"     ).put("value", "100").put("fromto", "bank"));
                cards.add(new JsonObject().put("id", 2 ).put("type", "community").put("content", "Du erbst DM 200."                                                                              ).put("action", "get"     ).put("value", "200").put("fromto", "bank"));
                cards.add(new JsonObject().put("id", 3 ).put("type", "community").put("content", "Einkommenssteuerrückzahlung. Ziehe DM 400 ein."                                                ).put("action", "get"     ).put("value", "400").put("fromto", "bank"));
                cards.add(new JsonObject().put("id", 4 ).put("type", "community").put("content", "Du hast den zweiten Preis in einer Schönheitskonkurrenz gewonnen. Ziehe DM 200 ein."           ).put("action", "get"     ).put("value", "200").put("fromto", "bank"));
/* x */         cards.add(new JsonObject().put("id", 5 ).put("type", "community").put("content", "Rücke vor bis auf Los."                                                                        ).put("action", "position").put("value", "0"  ).put("fromto", ""    ));
/* x */         cards.add(new JsonObject().put("id", 6 ).put("type", "community").put("content", "Du kommst aus dem Gefängnis frei."                                                             ).put("action", "free"    ).put("value", "0"  ).put("fromto", ""    ));
                cards.add(new JsonObject().put("id", 7 ).put("type", "community").put("content", "Bank-Irrtum zu deinen Gunsten. Ziehe DM 400 ein."                                              ).put("action", "get"     ).put("value", "400").put("fromto", "bank"));
                cards.add(new JsonObject().put("id", 8 ).put("type", "community").put("content", "Die Jahresrente wird fällig. Ziehe DM 200 ein."                                                ).put("action", "get"     ).put("value", "200").put("fromto", "bank"));
                cards.add(new JsonObject().put("id", 9 ).put("type", "community").put("content", "Du erhältst auf Vorzugs-Aktien 7% Dividende. DM 500."                                          ).put("action", "get"     ).put("value", "500").put("fromto", "bank"));
                cards.add(new JsonObject().put("id", 10).put("type", "community").put("content", "Gehe zurück zu Badstraße."                                                                     ).put("action", "position").put("value", "1"  ).put("fromto", ""    ));
                cards.add(new JsonObject().put("id", 11).put("type", "community").put("content", "Zahle eine Strafe von DM 200 oder nimm eine Ereigniskarte."                                    ).put("action", "put"     ).put("value", "200").put("fromto", "bank"));
                cards.add(new JsonObject().put("id", 12).put("type", "community").put("content", "Gehe in das Gefängnis. Begib Dich direkt dorthin. Gehe nicht über Los. Ziehe nicht DM 200 ein.").put("action", "prison"  ).put("value", "0"  ).put("fromto", ""    ));
                cards.add(new JsonObject().put("id", 13).put("type", "community").put("content", "Zahle an das Krankenhaus DM 200."                                                              ).put("action", "put"     ).put("value", "200").put("fromto", "bank"));
                cards.add(new JsonObject().put("id", 14).put("type", "community").put("content", "Zahle deine Versicherungssumme. DM 100."                                                       ).put("action", "put"     ).put("value", "100").put("fromto", "bank"));
                cards.add(new JsonObject().put("id", 15).put("type", "community").put("content", "Arzt-Kosten. Zahle DM 100."                                                                    ).put("action", "put"     ).put("value", "100").put("fromto", "bank"));
                Collections.shuffle(cards.getList());
                for (int i = 0 ; i < cards.size(); i++) {
                    JsonObject card = cards.getJsonObject(i).put("collection", "cards");
                    eb.send("save", card);
                }
            }
        });
    }

    private String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
