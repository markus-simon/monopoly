var eb = new EventBus(window.location.protocol + '//' + window.location.hostname + ':' + window.location.port + '/eventbus');
eb.reconnectEnabled = true;
eb.onopen = function()
{
    var width     = window.innerWidth;
    var height    = window.innerHeight - 20;
    var min       = Math.min(width, height)
    var size      = 10;
    var margin    = 10;
    var tileSize  = (min - (size+1) * margin ) / (size+1);

    var data      = [];
    var player    = {};
    var eyes;
    var overlay;

    var main = d3.select("#main").append("svg")
        .attr("width", width)
        .attr("height", height);

    var board = main.append("g");

    board.append("text")
        .attr("x", 310)
        .attr("y",305)
        .attr("fill", "#000")
        .text(tileSize);

    eb.registerHandler("players.ready", function(error, message) {
        d3.select("#start").remove();
        d3.select("#start-text").remove();

        overlay = d3.select("#list").append("rect")
            .attr("id", "overlay")
            .attr("width", 200)
            .attr("x", -40)
            .attr("height", 30)
            .attr("opacity", 0)
            .attr("fill", "#ccc");

        board.transition()
            .duration(1000)
            .delay(1000)
            .attr("transform", "translate(" + ((width / 2) + margin) + ",30)scale(1.4, 0.65)rotate(45)");
    });

    eb.registerHandler("player.joined", function(error, message) {

        var circle = board.append("g")
            .attr("id", "player_" + message.body.name);

        circle.append("circle")
            .attr("r", tileSize / 4)
            .attr("stroke", "#000")
            .attr("fill", message.body.color);

        d3.select("#player_" + message.body.name)
            .attr("transform", d3.select("#tile_" + message.body.position)
            .attr("transform"));

        d3.select("#player_" + message.body.name).select("circle")
            .attr("transform", "translate(" + tileSize / 2 + "," + tileSize / 2 + ")");

        var playerEntry = d3.select("#list").append("g")
            .attr("class", "player")
            .attr("id", "list-entry-" + message.body.name)
            .attr("transform", "translate(40," + (d3.selectAll(".player")._groups[0].length) * 30 + ")");

        playerEntry.append("text")
            .text(message.body.name);

        playerEntry.append("rect")
            .attr("x", -25)
            .attr("y", -20)
            .attr("width", 20)
            .attr("height", 20)
            .attr("fill", message.body.color);
    });

    eb.registerHandler("rent.collected", function(error, message) {
        if (message.body.name === player.name) {
            var currentMoney = d3.select("#stats-money-counter").text();
            d3.select("#stats-money-counter").text(parseInt(currentMoney) + parseInt(message.body.rent))
        }
    });

    eb.registerHandler("player.left", function(error, message) {
        console.log("player left," + message.body);
    });

    eb.send("init.board", {}, function(error, message) {
        initBoard(message.body);
    });

    function initBoard(data) {
        var arr  = [];
        for (var i = 0; i < data.length; i += size) {
            var smallarray = data.slice(i, i + size);
            arr.push(smallarray);
        }

        var biggestX = min + margin;
        var biggestY = min + margin;

        function calculateTilePosition(d, i) {
            var x;
            var y;
            var a;
            if (arr[0].indexOf(d) !== -1) {
                biggestX = biggestX - tileSize - margin;
                x = biggestX;
                y = min - tileSize;
                a = 0;
            } else if(arr[1].indexOf(d) !== -1) {
                biggestY = biggestY - tileSize - margin;
                x = tileSize + margin;
                y = biggestY;
                a = 90;
            } else if(arr[2].indexOf(d) !== -1) {
                biggestX = biggestX + tileSize + margin;
                x = biggestX - tileSize - (margin*2);
                y = tileSize + margin;
                a = 180;
            } else if(arr[3].indexOf(d) !== -1) {
                biggestY = biggestY + tileSize + margin;
                x = biggestX - tileSize - margin;
                y = (10 + tileSize) * (i - 29);
                a = 270;
            }
            return "translate(" + x + ", " + y + "), rotate(" + a + ")";
        }

        var tile = board.selectAll("g")
            .data(data)
            .enter().append("g")
            .attr("id", function(d, i) { return "tile_" + i })
            .attr("transform", function(d, i) { return calculateTilePosition(d, i) })

        tile.append("rect")
            .attr("width", tileSize)
            .attr("height", tileSize)
            .attr("fill", "#fff");

        tile.append("rect")
            .attr("width", tileSize)
            .attr("height", tileSize / 6)
            .attr("fill", function(d) { return d.color });

        tile.append("text")
            .attr("font-size", tileSize / 6)
            .attr("fill", "#000")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central')
            .attr("x", tileSize / 2)
            .attr("y", tileSize / 2.5)
            .text(function(d) { return d.label; });

        tile.append("text")
            .attr("font-size", tileSize / 4)
            .attr("fill", "#000")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central')
            .attr("x", tileSize / 2)
            .attr("y", tileSize / 1.5)
            .text(function(d) { return d.price; });

        var cubes = board.append("g")
            .attr("id", "cubes")
            .attr("transform", "translate(420,420)");

        var cube1 = cubes.append("rect")
            .attr("width", tileSize)
            .attr("height", tileSize)
            .attr("stroke", "#000")
            .attr("fill", "#fff")
            .attr("rx", "10")
            .attr("ry", "10");

        var cubeLabel1 = cubes.append("text")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central')
            .attr("fill", "#000")
            .attr("x", tileSize / 2)
            .attr("y", tileSize / 2)

        var cube2 = cubes.append("rect")
            .attr("width", tileSize)
            .attr("height", tileSize)
            .attr("stroke", "#000")
            .attr("fill", "#fff")
            .attr("x", tileSize + 10)
            .attr("rx", "10")
            .attr("ry", "10");

        var cubeLabel2 = cubes.append("text")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central')
            .attr("fill", "#000")
            .attr("x", tileSize / 2 + 10 + tileSize)
            .attr("y", tileSize / 2);

        var throwButton = cubes.append("rect")
            .attr("id", "throw")
            .attr("width", tileSize * 2 + 10)
            .attr("height", tileSize)
            .attr("stroke", "#000")
            .attr("fill", "#ccc")
            .attr("y",  tileSize + 10)
            .attr("rx", "10")
            .attr("ry", "10");

        var ThrowButtonLabel = cubes.append("text")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central')
            .attr("fill", "#000")
            .attr("x", tileSize)
            .attr("y", (tileSize * 1.5) + 10)
            .text("wuerfeln");

        var playerStats = main.append("g")
            .attr("id", "stats")
            .attr("transform", "translate(" + (width - 200 - margin) + ", " + 30 + ")");

        playerStats.append("rect")
            .attr("width",  200)
            .attr("height", 200)
            .attr("fill",   "#fff")
            .style("stroke", "#333");

        var playerName = playerStats.append("text")
            .attr("id", "stats-player-name")
            .attr("fill", "#000")
            .attr("x", 30)
            .attr("y", 30)
            .text(player.name);

        var playerMoney = playerStats.append("text")
            .attr("id", "stats-money-counter")
            .attr("fill", "#000")
            .attr("x", 30)
            .attr("y", 70)

        var playerList = main.append("g")
            .attr("id", "list")
            .attr("transform", "translate(" + (width - 200 - margin) + ", " + (height - 200) + ")");

        playerList.append("rect")
            .attr("width",  200)
            .attr("height", 200)
            .attr("fill",   "#fff")
            .style("stroke", "#333");

        throwButton.on("click", function() {
            eb.send("play", player, function(error, reply) {
                if (error) {
                    console.log(error.message)
                } else {
                    if (reply.body.name === player.name) {
                        playerMoney.text(reply.body.money);
                    } else {
                        alert("du bist NICHT dran, ok!?");
                    }
                }
            });
        });

        var buyContainer = main.append("g")
            .attr("id", "buy-container")
            .attr("transform", "translate(" + margin + "," + (height + margin) + ")");

        var buyContainerBg = buyContainer.append("rect")
            .attr("width", 400)
            .attr("height", 300)
            .attr("fill", "#fff")
            .attr("stroke", "#000")

        var buyNameLabel = buyContainer.append("text")
            .attr("x", 95)
            .attr("y", 30)
            .attr("fill", "#000")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central');

        var buyPriceLabel = buyContainer.append("text")
            .attr("x", 350)
            .attr("y", 30)
            .attr("fill", "#000")
            .attr("text-anchor", "end")
            .attr('alignment-baseline', 'central');

        var buy = buyContainer.append("rect")
            .attr("width", 150)
            .attr("height", 50)
            .attr("x", 20)
            .attr("y", 240)
            .attr("fill", "#fff")
            .attr("stroke", "#000")
            .on("click", function() {
                player.wants = 1;
                eb.send("buy", player, function(error, reply) {
                    if (!error) {
                        buyContainer.transition()
                            .duration(1000)
                            .attr("transform", "translate(" + margin + "," + (height + margin) + ")");
                        playerMoney.text(reply.body.money);

                    } else {
                        console.log("error" + error)
                    }
                })
            });

        buyContainer.append("text")
            .attr("x", 95)
            .attr("y",265)
            .attr("fill", "#000")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central')
            .text("Ja");

        var buynot = buyContainer.append("rect")
            .attr("width", 150)
            .attr("height", 50)
            .attr("x", 230)
            .attr("y", 240)
            .attr("fill", "#fff")
            .attr("stroke", "#000")
            .on("click", function() {
                player.wants = 0;
                eb.send("buy", player, function(error, reply) {
                    if (!error) {
                        buyContainer.transition()
                            .duration(1000)
                            .attr("transform", "translate(" + margin + "," + (height + margin) + ")");
                    } else {
                        console.log("error" + error)
                    }
                })
            });

        buyContainer.append("text")
            .attr("x", 310)
            .attr("y", 265)
            .attr("fill", "#000")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central')
            .text("Nein");

        eb.registerHandler("played", function(error, document) {
            var cubeAmount1 = document.body.cube1;
            var cubeAmount2 = document.body.cube2;
            var position    = document.body.position;
            var buyable     = document.body.buyable;
            var playerName  = document.body.name;

            overlay.style("opacity", 0.8);

            if (playerName === player.name) {
                if (buyable === true) {
                    buyNameLabel.text(data[position].label);
                    buyPriceLabel.text(data[position].price);
                    buyContainer.transition()
                        .duration(1000)
                        .attr("transform", "translate(" + margin + "," + (height - 300 - margin) + ")");
                } else {
                    buyContainer.transition()
                        .duration(1000)
                        .attr("transform", "translate(" + margin + "," + (height + margin) + ")");
                }

            } else {
                if (buyable === true) {
/*
                    overlay.style("opacity", 0.8);
*/
                } else {
/*
                    overlay.style("opacity", 0);
*/
                }
            }

            cubeLabel1.text(cubeAmount1);
            cubeLabel2.text(cubeAmount2);

            d3.select("#player_" + playerName)
                .transition()
                .duration(1000)
                .attr("transform", d3.select("#tile_" + position).attr("transform"));

            d3.select("#player_" + playerName).select("circle")
                .transition()
                .duration(1000)
                .attr("transform", "translate(" + tileSize / 2 + "," + tileSize / 2 + ")");


            overlay.attr("transform", d3.select("#list-entry-" + playerName).attr("transform"));

        });

        eb.registerHandler("bought", function(error, document) {
            if (document.body.wants != "-1") {
                d3.select("#tile_" + document.body.wants).append("circle")
                    .attr("r",5)
                    .attr("fill", document.body.color)
                    .attr("cx",65)
                    .attr("cy",6);
            }
        });
    }

    function generatePlayerList(players) {
        d3.select("#players-list").remove();
        main.selectAll(".players")
            .data(players)
            .enter()
            .append("text")
            .attr("class", "players")
            .attr("id", function(d, i) { return "player_" + d.textHandlerID })
            .attr("transform", function(d, i) { console.log(d); return "translate(1000," + (30 * (i + 1)) + ")" })
            .attr("fill", "#000")
            .attr("font-size",  tileSize / 6)
            .text(function(d, i) { return d.textHandlerID });
    }

    player = {
        "collection": "players",
        "session_id": getCookie("vertx-web.session"),
    };
    eb.send("find", player, function(error, message) {
        if (message.body.length > 0) {
/*
            // TODO relogin
            d3.select("#login").remove();
*/
        }
    });

    $("#color").colorPicker();

    d3.select("#submit").on("click", function() {
        var name = d3.select('#player-name').node().value;

        if (name === "") {
            d3.select('#player-name').attr("class", "invalid-input");
            return;
        }
        player = {
            "collection": "players",
            "session_id": getCookie("vertx-web.session"),
            "name":       name,
            "color":      rgb2hex($("#color").css("background-color"))
        };
        eb.send("player.add", player, function(error, reply) {

            var start = main.append("rect")
                .attr("id", "start")
                .attr("width", window.innerWidth)
                .attr("height", height)
                .attr("fill", "#cce3c7");

            var startText = main.append("text")
                .attr("x", window.innerWidth / 2)
                .attr("y", height / 2)
                .attr("fill", "#000")
                .attr("text-anchor", "middle")
                .attr('alignment-baseline', 'central')
                .attr("id", "start-text")
                .text("Warte auf andere Player!");

            d3.select("#stats-player-name").text(reply.body.name);
            d3.select("#stats-money-counter").text(reply.body.money);
            d3.select("#login").remove();
        });
    });
};

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function rgb2hex(rgb) {
    rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
    function hex(x) {
        return ("0" + parseInt(x).toString(16)).slice(-2);
    }
    return "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
}