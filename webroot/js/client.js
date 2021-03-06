var eb = new EventBus(window.location.protocol + '//' + window.location.hostname + ':' + window.location.port + '/eventbus');
eb.reconnectEnabled = true;

var width     = window.innerWidth;
var height    = window.innerHeight - 20;
var min       = Math.min(width, height)
var size      = 10;
var margin    = 10;
var tileSize  = (min - (size+1) * margin ) / (size+1);
var iconSize  = tileSize;

var format    = d3.format("d");
var data      = [];
var player    = {};
var eyes;
/*var overlay;*/

eb.onopen = function()
{
    var main = d3.select("#main").append("svg")
        .attr("width", width)
        .attr("height", height);

    var board = main.append("g").attr("id", "board").attr("transform","translate(" + 450 + ",0)");









    /*    board.append("text")
            .attr("x", 100)
            .attr("y",150)
            .attr("fill", "#000")
            .text("- Karten");

        board.append("text")
            .attr("x", 100)
            .attr("y",170)
            .attr("fill", "#000")
            .text("- Pasch");*/








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
            .attr("class", "streets")
            .attr("id", function(d, i) { return "tile_" + i })
            .attr("opacity", 0)
            .attr("transform", function(d, i) { return calculateTilePosition(d, i) })

        tile.append("rect")
            .attr("width", tileSize)
            .attr("height", tileSize)
            .attr("fill", "#fff");

        var tileHead = tile.append("rect")
            .attr("width", tileSize)
            .attr("height", tileSize / 6)
            .attr("fill", function(d) { return d.color });

        var houses = [0,1,2,3];
        var bla = tile.selectAll(".houses")
            .data(houses)
            .enter().append("svg")
            .attr("class", function(d, i) {  return "houses house-" + i })
            .attr("x", function(d, i) { return i * (tileSize + margin) / 5 })
            .attr("y", -4 )
            .attr("opacity", 0 )
            /*            .attr("width", (tileSize - margin) / 6)
                        .attr("height", (tileSize - margin) / 6)
                        .attr("fill", "#0f0");

                    bla.append("svg").*/.append("image")
            .attr("xlink:href","/assets/house_bottom2.svg")
            .attr("width", (tileSize - margin) / 3.5)
            .attr("height", (tileSize - margin) / 3.5)

        tile.append("rect")
            .attr("x", margin + (tileSize / 6.5))
            .attr("y", 1 )
            .attr("class", "hotel")
            .attr("opacity", 0 )
            .attr("width", (tileSize - margin) / 2)
            .attr("height", (tileSize - margin) / 6)
            .attr("fill", "#f00");

        var streetName = tile.append("text")
            .attr("font-size", tileSize / 6)
            .attr("id", function(d) { return d.code })
            .attr("class", "street-name")
            .attr("fill", "#000")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central');

        streetName.append("tspan")
            .attr("x", tileSize / 2)
            .attr("y", tileSize / 3)
            .text(function(d) { return d.label1; });

        streetName.append("tspan")
            .attr("x", tileSize / 2)
            .attr("y", tileSize / 2)
            .text(function(d) { return d.label2; });

        tile.append("text")
            .attr("font-size", tileSize / 4)
            .attr("fill", "#000")
            .attr("class", "street-price")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central')
            .attr("x", tileSize / 2)
            .attr("y", tileSize - (tileSize / 5))
            .text(function(d) { return d.price ? d.price : ""; });

        var cubes = board.append("g")
            .attr("id", "cubes")
            .attr("opacity", 0)
            .attr("transform", "translate(" + (((biggestX - tileSize*2) + margin) / 2) + "," +  (biggestY + tileSize + margin) + ")");

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

        var tools = board.append("g")
            .attr("id", "tools")
            .attr("opacity", 0)
            .attr("transform", "rotate(-90)translate(" + -((tileSize * size) + margin) + "," + biggestY + ")");

        var playerMoney = board.append("text")
            .attr("id", "money-counter")
            .attr("font-size", tileSize)
            .attr("transform", "rotate(-90)translate(" + -((tileSize * size) - (tileSize * 4 ) + (margin * 3)) + "," + (biggestY + (tileSize / 2)  + tileSize) + ")")
            .text(0);

        tools.append("svg").attr("id", "buy-houses").append("image")
            .attr("xlink:href","/assets/house2.svg")
            .attr("width", iconSize)
            .attr("height", iconSize)
            .on("click", function() {
                eb.send("get.streets", player, function(error, reply) {
                    if (!error) {
                        d3.select("#sell-streets").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#chance-cards").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#community-cards").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#logo").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#cubes").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#community").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#chance").transition().duration(1000).attr("opacity", 0.25);
                        d3.selectAll(".streets")
                            .on("click", function() {
                                return false;
                            })
                            .transition()
                            .duration(1000)
                            .attr("opacity", 0.25)
                        reply.body.forEach(function (street) {
                            d3.select("#tile_" + street.id)
                                .on("click", function(d) {
                                    player.wants = d.id;
                                    eb.send("buy.house", player, function(error, reply) {
                                        if (!error) {
                                            if (reply.body === "ok") {
                                                var currentMoney = d3.select("#money-counter").text();
                                                tweenText('#money-counter', parseInt(currentMoney) - parseInt(d.house), format);
                                            } else {
                                                alert(reply.body);
                                            }
                                            d3.selectAll(".streets")
                                                .on("click", function() {
                                                    return false;
                                                })
                                                .transition()
                                                .duration(1000)
                                                .attr("opacity", 1);
                                            d3.select("#buy-houses").transition().duration(1000).attr("opacity", 1);
                                            d3.select("#sell-streets").transition().duration(1000).attr("opacity", 1);
                                            d3.select("#chance-cards").transition().duration(1000).attr("opacity", 1);
                                            d3.select("#community-cards").transition().duration(1000).attr("opacity", 1);
                                            d3.select("#logo").transition().duration(1000).attr("opacity", 1);
                                            d3.select("#cubes").transition().duration(1000).attr("opacity", 1);
                                            d3.select("#community").transition().duration(1000).attr("opacity", 1);
                                            d3.select("#chance").transition().duration(1000).attr("opacity", 1);
                                        } else {
                                            console.log("error" +  error);
                                        }
                                    });
                                })
                                .transition()
                                .duration(1000)
                                .attr("opacity", 1)
                            d3.select("#logo").transition().duration(1000).attr("opacity", 1);
                        });
                    } else {
                        console.log("error" + error)
                    }
                })
            });

        tools.append("svg").attr("id", "sell-streets").append("image")
            .attr("xlink:href","/assets/trade2.svg")
            .attr("width", iconSize)
            .attr("height", iconSize)
            .attr("transform", "translate(" + (iconSize + margin) + ",0)")
            .on("click", function() {
                eb.send("get.streets", player, function(error, reply) {
                    if (!error) {
                        d3.select("#buy-houses").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#chance-cards").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#community-cards").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#logo").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#cubes").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#community").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#chance").transition().duration(1000).attr("opacity", 0.25);
                        d3.selectAll(".streets")
                            .on("click", function() {
                                return false;
                            })
                            .transition()
                            .duration(1000)
                            .attr("opacity", 0.25)
                        reply.body.forEach(function (street) {
                            d3.select("#tile_" + street.id)
                                .on("click", function(d) {
                                    player.offers = d.id;
                                    eb.send("offer.street", player, function(error, reply) {

                                    });
                                })
                                .transition()
                                .duration(1000)
                                .attr("opacity", 1)
                            d3.select("#logo").transition().duration(1000).attr("opacity", 1);
                        });
                    } else {
                        console.log("error" + error)
                    }
                })
            });

        tools.append("svg").append("image")
            .attr("xlink:href","/assets/jail2.svg")
            .attr("id", "prison")
            .attr("opacity", 0)
            .attr("width", iconSize)
            .attr("height", iconSize)
            .attr("transform", "translate(" + ((iconSize + margin) * 2) + ",0)");

        throwButton.on("click", function() {
            eb.send("play", player, function(error, reply) {
                if (error) {
                    console.log(error.message)
                } else {
                    if (reply.body.name === player.name) {
                        tweenText('#money-counter', reply.body.money, format);
                    } else {
                        alert("du bist NICHT dran, ok!?");
                    }
                }
            });
        });

        var buyContainer = main.append("g")
            .attr("id", "buy-container")
            .attr("transform", "translate(" + margin + "," + -1000 + ")");

        var buyContainerBg = buyContainer.append("rect")
            .attr("width", width / 4)
            .attr("height", height / 4)
            .attr("fill", "#fff")
            .attr("stroke", "#000")

        var buyNameLabel = buyContainer.append("text")
            .attr("x", 20)
            .attr("y", 30)
            .attr("font-size", 16)
            .attr("fill", "#000")
            .attr("text-anchor", "start")
            .attr('alignment-baseline', 'central');

        var buyPriceLabel = buyContainer.append("text")
            .attr("x", (width / 4) - 20)
            .attr("y", 30)
            .attr("font-size", 16)
            .attr("fill", "#000")
            .attr("text-anchor", "end")
            .attr('alignment-baseline', 'central');

        var buy = buyContainer.append("rect")
            .attr("width", 100)
            .attr("height", 30)
            .attr("x", 20)
            .attr("y", 80)
            .attr("fill", "#fff")
            .attr("stroke", "#000")
            .on("click", function() {
                player.wants = 1;
                eb.send("buy", player, function(error, reply) {
                    if (!error) {
                        buyContainer.transition()
                            .duration(1000)
                            .attr("transform", "translate(" + margin + "," + -1000 + ")");
                        tweenText('#money-counter', reply.body.money, format);

                    } else {
                        console.log("error" + error)
                    }
                })
            });

        buyContainer.append("text")
            .attr("x", 70)
            .attr("y", 95)
            .attr("font-size","26")
            .attr("fill", "#000")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central')
            .text("Ja");

        var buynot = buyContainer.append("rect")
            .attr("width", 100)
            .attr("height", 30)
            .attr("x", (width / 4) - 120)
            .attr("y", 80)
            .attr("fill", "#fff")
            .attr("stroke", "#000")
            .on("click", function() {
                player.wants = 0;
                eb.send("buy", player, function(error, reply) {
                    if (!error) {
                        buyContainer.transition()
                            .duration(1000)
                            .attr("transform", "translate(" + margin + "," + -1000 + ")");
                    } else {
                        console.log("error" + error)
                    }
                })
            });

        buyContainer.append("text")
            .attr("x", (width / 4) - 65)
            .attr("y", 95)
            .attr("fill", "#000")
            .attr("font-size", "26")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central')
            .text("Nein");

        eb.registerHandler("players.ready", function(error, message) {

            var content = d3.select("#players").node().innerHTML;
            var list = board.append("g").attr("id", "clone_players").html(content);
            list.attr("transform", "rotate(-90)translate(0,150)");

            d3.select("#start-text").remove();
            d3.select("#players").remove();
            d3.select("#login").remove();
            d3.select("#start").remove();
            d3.select("#starter").remove();

            d3.selectAll(".streets").transition().duration(200).delay(function(d, i) { return 200 + (i * 10) }).attr("opacity", 1);
            d3.select("#cubes").transition().delay(800).duration(500).attr("opacity", 1);
            d3.select("#tools").transition().delay(800).duration(500).attr("opacity", 1);

            board.transition()
                .duration(1000)
                .delay(1000)
                .attr("transform", "translate(" + ((width / 2) + margin) + ",30)scale(1.4, 0.65)rotate(45)");
        });

        var layout = board.append("g")
            .attr("id", "layout");

        layout.append("image")
            .attr("id", "logo")
            .attr("xlink:href","/assets/logo.png")
            .attr("width", tileSize * size)
            .attr("height", (tileSize * size / 2.91))
            .attr("transform", "rotate(-45),scale(0.9,1),translate(-" + ((tileSize * size) / 2) + "," + height / 1.7 + ")")

        var pathDataCommunity = [
            [(tileSize * 2) + margin, (tileSize * 2) + margin],
            [(tileSize * 4) + margin, (tileSize * 2) + margin],
            [(tileSize * 4) + margin, (tileSize * 5) + margin],
            [(tileSize * 2) + margin, (tileSize * 5) + margin],
            [(tileSize * 2) + margin, (tileSize * 2) + margin]
        ];

        layout.append('path')
            .attr('id', "community")
            .attr("transform","translate(" + (((biggestX / 2) + (tileSize * 3) + margin) / 2) + ",0)rotate(45)")
            .attr('d', getPathData(pathDataCommunity))
            .attr("stroke", "black")
            .attr("stroke-width", 1)
            .style("fill", "none")
            .transition()
            .duration(1000)
            .ease(d3.easeSin)
            .attrTween("stroke-dasharray", function() {
                var len = this.getTotalLength();
                return function(t) { return (d3.interpolateString("0," + len, len + ",0"))(t) };
            });

        var pathDataChance = [
            [(tileSize * 2) + margin, (tileSize * 2) + margin],
            [(tileSize * 2) + margin, (tileSize * 5) + margin],
            [(tileSize * 4) + margin, (tileSize * 5) + margin],
            [(tileSize * 4) + margin, (tileSize * 2) + margin],
            [(tileSize * 2) + margin, (tileSize * 2) + margin]
        ];

        layout.append('path')
            .attr('id', "chance")
            .attr("transform","translate(" + ((biggestX / 2) + (tileSize * 2) + margin) + "," + ((biggestY / 2) - (tileSize * 3) + margin) + ")rotate(45)")
            .attr('d', getPathData(pathDataChance))
            .attr("stroke", "black")
            .attr("stroke-width", 1)
            .style("fill", "none")
            .transition()
            .duration(1000)
            .ease(d3.easeSin)
            .attrTween("stroke-dasharray", function() {
                var len = this.getTotalLength();
                return function(t) { return (d3.interpolateString("0," + len, len + ",0"))(t) };
            });

        var cardContainer = main.append("g")
            .attr("id", "card-container")
            .attr("transform", "translate(" + (width / 2 - ((width / 4) / 2 )) + ",-1000)");

        var cardContainerBg = cardContainer.append("rect")
            .attr("width", width / 4)
            .attr("height", height / 4)
            .attr("fill", "#fff")
            .attr("stroke", "#000");

        var cardNameLabel = cardContainer.append("text")
            .attr("x", 20)
            .attr("y", 30)
            .attr("font-size", 14)
            .attr("fill", "#000")
            .attr("text-anchor", "start")
            .attr('alignment-baseline', 'central');


        eb.send("get.cards", {"type": "community"}, function(error, reply) {
            var communityCards = reply.body;

            var colorBorder = d3.scaleLinear().domain([1,communityCards.length])
                .interpolate(d3.interpolateHcl)
                .range([d3.rgb("#e8e8e8"), d3.rgb('#fff')]);

            var color = d3.scaleLinear().domain([1,communityCards.length])
                .interpolate(d3.interpolateHcl)
                .range([d3.rgb("#45938a"), d3.rgb('#91fff2')]);

            var communityCardGroup = layout.append("g")
                .attr("id", "community-cards")
                .attr("transform","translate(" + (((biggestX / 2) + (tileSize * 3) + margin) / 2) + "," + (tileSize * 3) + ")rotate(45)");

            var groups = communityCardGroup.selectAll("g")
                .data(communityCards)
                .enter().append("g")
                .attr("class", "card")
                .attr("id", function(d) { return "card_" + d.type + d.id })
                .attr("opacity", 0)

            groups.append("rect")
                .attr("fill", "#fff")
                .attr("fill", function(d, i) { return colorBorder(i) })
                .attr("width", (tileSize * 2) + margin)
                .attr("height", (tileSize * 3) + margin)
                .attr("rx", 10)
                .attr("ry", 10)

            groups.append("rect")
                .attr("x", 10)
                .attr("y", 10)
                .attr("fill", function(d, i) { return color(i) })
                .attr("width", (tileSize * 2) - margin)
                .attr("height", (tileSize * 3) - margin)
                .attr("rx", 10)
                .attr("ry", 10)

            groups.transition()
                .duration(10)
                .delay(function(d, i) { return 1500 + 100 * i })
                .attr("opacity", 1);
        });

        eb.send("get.cards", {"type": "chance"}, function(error, reply) {
            var chanceCards = reply.body;

            var colorBorder2 = d3.scaleLinear().domain([1,chanceCards.length])
                .interpolate(d3.interpolateHcl)
                .range([d3.rgb("#e8e8e8"), d3.rgb('#fff')]);

            var color2 = d3.scaleLinear().domain([1,chanceCards.length])
                .interpolate(d3.interpolateHcl)
                .range([d3.rgb("#f442d4"), d3.rgb('#ffaaef')]);

            var chanceCardGroup = layout.append("g")
                .attr("id", "chance-cards")
                .attr("transform","translate(" + ((biggestX / 2) + (tileSize * 2) + margin) + "," + ((biggestY / 2) + margin) + ")rotate(45)")

            var groups2 = chanceCardGroup.selectAll("g")
                .data(chanceCards)
                .enter().append("g")
                .attr("class", "card")
                .attr("id", function(d) { return "card_" + d.id })
                .attr("opacity", 0)

            groups2.append("rect")
                .attr("fill", "#fff")
                .attr("fill", function(d, i) { return colorBorder2(i) })
                .attr("width", (tileSize * 2) + margin)
                .attr("height", (tileSize * 3) + margin)
                .attr("rx", 10)
                .attr("ry", 10)

            groups2.append("rect")
                .attr("x", 10)
                .attr("y", 10)
                .attr("fill", function(d, i) { return color2(i) })
                .attr("width", (tileSize * 2) - margin)
                .attr("height", (tileSize * 3) - margin)
                .attr("rx", 10)
                .attr("ry", 10)

            groups2.transition()
                .duration(10)
                .delay(function(d, i) { return 1500 + 100 * i })
                .attr("opacity", 1);

        });



        eb.registerHandler("player.joined", function(error, message) {

            d3.select("#money-counter").text(message.body.money);
            // TODO
            eb.send("findWithOptions", {collection: "players"}, function(error, document) {

                d3.select("#players").remove();
                var players = main.append("g")
                    .attr("transform","translate(" + (width - 384) + ",0)")
                    .attr("id", "players");

                var playerEntry = players.selectAll('g')
                    .data(document.body)
                    .enter()
                    .append('g')
                    .attr("class", "player")
                    .attr("id", function(d) { return "entry_" + d.name });

                playerEntry.append("rect")
                    .attr("width", tileSize / 2)
                    .attr("height", tileSize / 2)
                    .attr("fill", function(d) { return d.color})
                    .attr("x", 30)
                    .attr("y", function(d, i) { return 25 + (i * 30); })

                playerEntry.append("text")
                    .text(function(d) { return d.name})
                    .attr("text-anchor", "start")
                    .attr("x", 80)
                    .attr("y", function(d, i) { return 40 + (i * 30); })
                    .attr("font-size", tileSize / 2);

                if (document.body.length > 1) {
                    d3.select("#starter").attr("opacity", 1).on("click", function() {
                        eb.send("start.game");
                    });
                }
            });

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
        });

        eb.registerHandler("rent.collected", function(error, message) {
            if (message.body.name === player.name) {
                var currentMoney = d3.select("#money-counter").text();
                tweenText('#money-counter', parseInt(currentMoney) + parseInt(message.body.rent), format);
            }
        });

        eb.registerHandler("player.left", function(error, message) {
            console.log("player left," + message.body);
        });

        eb.registerHandler("bought.house", function(error, document) {
            var id    = document.body.id;
            var house = document.body.house;
            if (house < 4) {
                d3.select("#tile_" + id).select(".house-" + house).attr("opacity", 1)
            } else {
                d3.select("#tile_" + id).selectAll(".houses").attr("opacity", 0)
                d3.select("#tile_" + id).select(".hotel").attr("opacity", 1)
            }
        });

        eb.registerHandler("offer.rejected", function(error, document) {

            d3.selectAll(".streets")
                .on("click", function() {
                    return false;
                })
                .transition()
                .duration(1000)
                .attr("opacity", 1);
            d3.select("#buy-houses").transition().duration(1000).attr("opacity", 1);
            d3.select("#sell-streets").transition().duration(1000).attr("opacity", 1);
            d3.select("#chance-cards").transition().duration(1000).attr("opacity", 1);
            d3.select("#community-cards").transition().duration(1000).attr("opacity", 1);
            d3.select("#logo").transition().duration(1000).attr("opacity", 1);
            d3.select("#cubes").transition().duration(1000).attr("opacity", 1);
            d3.select("#community").transition().duration(1000).attr("opacity", 1);
            d3.select("#chance").transition().duration(1000).attr("opacity", 1);
            d3.selectAll(".clone").transition().duration(1000).attr("opacity", 0).remove();
            d3.select("#tile_" + document.body.id).select(".street-owner").attr("y", (-tileSize / 10 - 2));
        });





        eb.registerHandler("reoffered.street", function(error, document) {

            var id = document.body.id;
            var streetOwner = document.body.streetOwner;

            cloneTile(d3.select("#tile_" + id));
            d3.select("#clone_" + "tile_" + id).attr("class", "clone " + "reoffer");
            d3.select("#clone_" + "tile_" + id).transition().duration(1).attr("transform", "rotate(0)translate(" + (tileSize * 3) + "," + (tileSize * 7) + "),scale(" + ((((biggestX - (tileSize * 2)) - (margin * 2)) / tileSize) / 4) + ")");

            if (player.name === streetOwner) {

                d3.select("#clone_tile_" + id)
                    .on("click", function (d) {
                        var ids = [];


                        var offer = d3.select(".offer");
                        var offerId   = offer.node().id.split("_")[2];
                        var offerOwner = offer.select(".street-owner").node().id.split("-")[0];



                        var reoffer = d3.select(".reoffer");
                        var reofferId = reoffer.node().id.split("_")[2];
                        var reofferOwner = reoffer.select(".street-owner").node().id.split("-")[0];

                        ids.push(parseInt(offerId));
                        ids.push(parseInt(reofferId));

                        eb.send("accept.offer", {"ids": ids}, function (error, reply) {

                        });
                    })
                    .transition()
                    .duration(1000)
                    .attr("opacity", 1)
            }
        });



        eb.registerHandler("offer.accepted", function(error, document) {
            console.log("Angebot angenommen:");
            console.log(document.body);
            d3.selectAll(".streets")
                .on("click", function() {
                    return false;
                })
                .transition()
                .duration(1000)
                .attr("opacity", 1);
            d3.select("#buy-houses").transition().duration(1000).attr("opacity", 1);
            d3.select("#sell-streets").transition().duration(1000).attr("opacity", 1);
            d3.select("#chance-cards").transition().duration(1000).attr("opacity", 1);
            d3.select("#community-cards").transition().duration(1000).attr("opacity", 1);
            d3.select("#logo").transition().duration(1000).attr("opacity", 1);
            d3.select("#cubes").transition().duration(1000).attr("opacity", 1);
            d3.select("#community").transition().duration(1000).attr("opacity", 1);
            d3.select("#chance").transition().duration(1000).attr("opacity", 1);
            d3.selectAll(".clone").transition().duration(1000).attr("opacity", 0).remove();
            var offererColor   = d3.select("#clone_players").select("#entry_" + document.body.id[0].owner).select("rect").attr("fill");
            var reoffererColor = d3.select("#clone_players").select("#entry_" + document.body.id[1].owner).select("rect").attr("fill");
            d3.select("#tile_" + document.body.id[0].id).select(".street-owner").attr("y", (-tileSize / 10 - 2)).attr("fill", offererColor);
            d3.select("#tile_" + document.body.id[1].id).select(".street-owner").attr("y", (-tileSize / 10 - 2)).attr("fill", reoffererColor);
        });

        eb.registerHandler("offered.street", function(error, document) {
            var id = document.body.id;
            cloneTile(d3.select("#tile_" + id));


            var streetOwner = d3.select("#clone_" + "tile_" + id).select(".street-owner").attr("id").split("-")[0];

            d3.select("#clone_" + "tile_" + id).transition().duration(1000).attr("transform", "rotate(0)translate(" + (margin + tileSize) + "," + (margin + tileSize) + "),scale(" + (((biggestX - (tileSize * 2)) - (margin * 2)) / tileSize) + ")");

            if (player.name !== streetOwner) {
                d3.select("#clone_" + "tile_" + id).append("rect")
                    .attr("width", tileSize / 2)
                    .attr("height", tileSize / 4)
                    .attr("x", (tileSize / 2) - (tileSize / 4))
                    .attr("y", tileSize / 2.5)
                    .attr("fill", "#fff")
                    .attr("stroke", "#000")
                    .on("click", function () {
                        eb.send("reject.offer", {id: id}, function (error, reply) {
                            if (reply.body === "ok") {
                                d3.select("#clone_" + "tile_" + id).transition().duration(1000).attr("opacity", 0).remove();
                                d3.select("#tile_" + id).select(".street-owner").attr("y", (-tileSize / 10 - 2));
                            }
                        });
                    });

                d3.select("#clone_" + "tile_" + id).append("text")
                    .attr("x", tileSize / 2)
                    .attr("y", tileSize / 2)
                    .attr("font-size", tileSize / 8)
                    .attr("fill", "#000")
                    .attr("text-anchor", "middle")
                    .attr('alignment-baseline', 'central')
                    .text("Nein!");


                eb.send("get.streets", player, function (error, reply) {
                    if (!error) {
                        d3.select("#sell-streets").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#chance-cards").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#community-cards").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#logo").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#cubes").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#community").transition().duration(1000).attr("opacity", 0.25);
                        d3.select("#chance").transition().duration(1000).attr("opacity", 0.25);
                        d3.selectAll(".streets")
                            .on("click", function () {
                                return false;
                            })
                            .transition()
                            .duration(1000)
                            .attr("opacity", 0.25)

                        reply.body.forEach(function (street) {
                            d3.select("#tile_" + street.id)
                                .on("click", function (d) {
                                    player.streetOwner = streetOwner;
                                    player.offers      = d.id;
                                    eb.send("reoffer.street", player, function (error, reply) {

                                    });
                                })
                                .transition()
                                .duration(1000)
                                .attr("opacity", 1)
                            d3.select("#logo").transition().duration(1000).attr("opacity", 1);
                        });
                    }
                });
            }
        });

        eb.registerHandler("card.drawn", function(error, document) {
            var id      = document.body.id;
            var type    = document.body.type;
            var content = document.body.content;

            d3.select("#card_" + type + id).transition().duration(1000).attr("transform", "translate(-1000,0)");

            cardNameLabel.text(function(d) { return content });

            cardContainer.transition()
                .duration(1000)
                .attr("transform", "translate(" + (width / 2 - ((width / 4) / 2 )) + "," + (height / 2 - ((height / 4) / 2 )) + ")")
                .transition()
                .duration(1000)
                .delay(4000)
                .attr("transform", "translate(" + (width / 2 - ((width / 4) / 2 )) + ",-1000)");
        });

        eb.registerHandler("played", function(error, document) {
            var cubeAmount1 = document.body.cube1;
            var cubeAmount2 = document.body.cube2;
            var position    = document.body.position;
            var buyable     = document.body.buyable;
            var playerName  = document.body.name;
            var prison      = document.body.prison;

            if (playerName === player.name) {
                if (buyable === true) {
                    buyNameLabel.text(data[position].label3);
                    buyPriceLabel.text(data[position].price);
                    buyContainer.transition()
                        .duration(1000)
                        .attr("transform", "translate(" + margin + "," + margin + ")");
                } else {
                    buyContainer.transition()
                        .duration(1000)
                        .attr("transform", "translate(" + margin + "," + -1000 + ")");
                }
                if (prison) {
                    d3.select("#prison").transition().duration(1000).attr("opacity", 1)
                } else {
                    d3.select("#prison").transition().duration(1000).attr("opacity", 0)
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
        });

        eb.registerHandler("bought", function(error, document) {
            if (document.body.wants != "-1") {
                d3.select("#tile_" + document.body.wants).append("rect")
                    .attr("fill", document.body.color)
                    .attr("width", tileSize)
                    .attr("class", "street-owner")
                    .attr("id", document.body.name + "-" + document.body.street)
                    .attr("height", tileSize / 10)
                    .attr("y", -tileSize / 10 - 2);
            }
        });
    }






    // TODO
    eb.send("findWithOptions", {collection: "players"}, function(error, reply) {
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

        main.append("rect")
            .attr("id", "starter")
            .attr("text-anchor", "middle")
            .attr('alignment-baseline', 'central')
            .attr("x", window.innerWidth / 2)
            .attr("y", height / 2 + 100)
            .attr("width", tileSize * 2)
            .attr("height", tileSize)
            .attr("stroke", "#000")
            .attr("opacity", 0)
            .attr("fill", "#fff")
            .attr("rx", "10")
            .attr("ry", "10");

        main.append("text")
            .text("Start?");

        var players = main.append("g")
            .attr("transform","translate(" + (width - 384) + ",0)")
            .attr("id", "players");

        players.append("rect")
            .attr("width", 384)  // TODO
            .attr("height", height)
            .attr("fill", "#f0ffff")

        var playerEntry = players.selectAll('g')
            .data(reply.body)
            .enter()
            .append('g')
            .attr("class", "players");

        playerEntry.append("rect")
            .attr("width", tileSize / 2)
            .attr("height", tileSize / 2)
            .attr("fill", function(d) { return d.color})
            .attr("x", 30)
            .attr("y", function(d, i) { return 25 + (i * 30); })

        playerEntry.append("text")
            .text(function(d) { return d.name})
            .attr("text-anchor", "start")
            .attr("x", 80)
            .attr("y", function(d, i) { return 40 + (i * 30); })
            .attr("font-size", tileSize / 2);
    });

    player = {
        "collection": "players",
        "session_id": getCookie("vertx-web.session"),
    };

    $("#player-name").val("player_" + Math.floor((Math.random() * 100) + 1));
    $("#color").colorPicker();

    d3.select("#submit").on("click", function() {
        var sessionId = getCookie("vertx-web.session");
        if (sessionId === "") {
            alert("Sorry, nochmal ...");
            return;
        }
        var name = d3.select('#player-name').node().value;
        if (name === "") {
            d3.select('#player-name').attr("class", "invalid-input");
            return;
        }
        player = {
            "collection": "players",
            "session_id": sessionId,
            "name":       name,
            "color":      rgb2hex($("#color").css("background-color"))
        };
        eb.send("player.add", player, function(error, reply) {

        });
    });
};

var lineGenerator = d3.line()/*.curve(d3.curveNatural)*/;

function getPathData(data) {
    return lineGenerator(data);
}

function cloneTile(tile) {
    var content = d3.select(tile).node();
    content.selectAll(".houses").remove()
    content.select(".street-owner").attr("y", tileSize - (tileSize / 10))
    var target  = d3.select("#board");
    target.append("g").attr("class", "clone offer").attr("id", "clone_" + content.node().id).html(content.node().innerHTML);
}

var tweenText = function(id, value, format) {
    d3.select(id).transition()
        .duration(1500)
        .on("start", function repeat() {
            d3.active(this)
                .tween("text", function () {
                    var that = d3.select(this),
                        i = d3.interpolateNumber(that.text().replace(/,/g, ""), value);
                    return function (t) {
                        that.text(format(i(t)));
                    };
                })
                .transition()
        })
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