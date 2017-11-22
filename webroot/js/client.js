var eb = new EventBus(window.location.protocol + '//' + window.location.hostname + ':' + window.location.port + '/eventbus');
/*eb.enableReconnect(true);
eb.onreconnect = function() {
    console.log("sasasasasa!!!");
};

eb.onmessage = function(e) {
  console.log('message', e.data);
};*/

var data = [];

eb.onopen = function()
{
    var width    = window.outerWidth;
    var height   = window.outerHeight;
    var tileSize = window.outerHeight / 14;
    var size     = 10;
    var eyes;




    eb.send("init.board", {}, function(error, message) {
        initBoard(message.body);
    });



    function initBoard(data) {

        var arr  = [];
        for (var i = 0; i < data.length; i += size) {
            var smallarray = data.slice(i, i + size);
            arr.push(smallarray);
        }
        var biggestX = arr[0].length * tileSize + tileSize + 25;
        var biggestY = biggestX;

        function calculateTilePosition(d, i) {
            var x;
            var y;
            var a;
            if (arr[0].indexOf(d) !== -1) {
                biggestX = biggestX - tileSize - 10;
                x = tileSize + 10 + biggestX;
                y = 10 + biggestY;
                a = 0;
            } else if(arr[1].indexOf(d) !== -1) {
                biggestY = biggestY - tileSize - 10;
                x = biggestX + tileSize;
                y = tileSize + 10 + 10 + biggestY;
                a = 90;
            } else if(arr[2].indexOf(d) !== -1) {
                x = (tileSize + 10) * (i - 20);
                y = 10;
                a = 0;
                biggestX = x;
            } else if(arr[3].indexOf(d) !== -1) {
                x = biggestX + 10 + tileSize;
                y = (10 + tileSize) * (i - 29);
                a = -90;

            }
            return "translate(" + x + ", " + y + "), rotate(" + a + ")";
        }

        var board = d3.select("#main")
            .append("svg")
            .attr("width", width)
            .attr("height", height);

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
            .attr("fill", "#ccc")
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
            .attr("fill", "#ccc")
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
            .text("Los");

        var circle = board.append("g")
            .attr("id", "circle");

        circle.append("circle")
            .attr("r", tileSize / 4)
            .attr("stroke", "#000")
            .attr("fill", "#ff00ff");




        // Stats
        var playerStats = board.append("g")
            .attr("id", "stats")
            .attr("transform", "translate(" + (window.innerWidth - 300) + "," + (window.innerHeight - 150) + ")");

        var playerMoney = playerStats.append("text")
            .attr("fill", "#000")
            .attr("x", 100)
            .attr("y", 100)
            .text("2222");



        // Players
        eb.registerHandler("player.joined", function(error, message) {
            console.log(message.body);
        });

        eb.registerHandler("player.left", function(error, message) {
            d3.select("#player_" + message.body.textHandlerID).remove();
        });

/*        eb.registerHandler("init.players.done", function(error, message) {
//            initPlayers();
            generatePlayerList(message.body); */
            throwButton.on("click", function() {
                eb.send("play", {}, function(error, reply) {
                    if (error) {
                        console.log("error" + error)
                    }
                });
            });
            /*
        });*/





        var buyContainer = d3.select("svg").append("g")
            .attr("id", "buy-container")
            .style("opacity", 0);

        var buyContainerBg = buyContainer.append("rect")
            .attr("width", 500)
            .attr("height", 300)
            .attr("x", 100)
            .attr("y", 100)
            .attr("fill", "#fff")
            .attr("stroke", "#000")

        var buyNameLabel = buyContainer.append("text")
            .attr("x", 200)
            .attr("y", 140)
            .attr("fill", "#000")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central');

        var buyPriceLabel = buyContainer.append("text")
            .attr("x", 500)
            .attr("y", 140)
            .attr("fill", "#000")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central');

        buyContainer.append("rect")
            .attr("width", 150)
            .attr("height", 50)
            .attr("x", 400)
            .attr("y", 280)
            .attr("fill", "#fff")
            .attr("stroke", "#000")
            .on("click", function() {
                eb.send("buy", player, function(error, reply) {
                    if (!error) {
                        buyContainer.style("opacity", 0);
                    } else {
                        console.log("error" + error)
                    }
                })
            })

        buyContainer.append("text")
            .attr("x", 480)
            .attr("y",305)
            .attr("fill", "#000")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central')
            .text("Kaufen?");

        eb.registerHandler("played", function(error, document) {
            var cubeAmount1 = document.cube1;
            var cubeAmount2 = document.cube2;
            var position    = document.position;
            var buyable     = document.buyable;

            if (buyable === true) {
                buyNameLabel.text(data[position].label);
                buyPriceLabel.text(data[position].price);
                buyContainer.style("opacity", 1);
            } else {
                buyContainer.style("opacity", 0);
            }

            cubeLabel1.text(cubeAmount1);
            cubeLabel2.text(cubeAmount2);

            if (position > data.length) {
                position = position - data.length;
                addMoneyToPlayer(2000);
            }
            player.position = position;

            playerMoney.text(player.money);


            circle.attr("transform", d3.select("#tile_" + position).attr("transform"))
            circle.select("circle").attr("transform", "translate(" + tileSize / 2 + "," + tileSize / 2 + ")");

        });


        eb.registerHandler("bought", function(error, document) {
            console.log(document);
            d3.select("#tile_" + document.id); // optisches feedback, wem die straße ab jetzt gehört ...
        });



        function initPlayers() {
            eb.send("init.players", {}, function(error, reply) {
                /*if (reply !== "ok") {
                   console.log("error" + error)
                }*/
            })
        }

        initPlayers();
    }

    function generatePlayerList(players) {
        d3.select("#players-list").remove();
        d3.select("svg").selectAll(".players")
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

/*
    function addMoneyToPlayer(amount) {
        player.money = player.money + amount;
    }

    function withdrawMoneyFromPlayer(amount) {
        if (player.money >= amount) {
            player.money = player.money - amount;
        } else {
            alert("Pleite!");
        }
    }
*/


/*    d3.select("#submit").on("click", function() {
        var name = d3.select('#name').node().value;
        eb.send("login", {"name": name}, function(reply) {
            if (reply === "ok") {
                d3.select("#login").remove();
            }
        });
    })*/
};

