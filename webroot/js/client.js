var eb    = new vertx.EventBus(window.location.protocol + '//' + window.location.hostname + ':' + window.location.port + '/eventbus');
eb.onopen = function()
{
    eb.send("topics.list", {}, function(reply) {
        var message = JSON.parse(reply);
        $("#topics").show();
        $.each(message, function(key, value) {
            var li = $("<li>");
            var topicLabel = $('<div class="topic-label">').text(key);
            li.append(topicLabel);
            var ul = $("<ul>");
            li.append(ul);
            $.each(value, function(key1, value1) {
                var partitionLi = $("<li>");
                var partitionLabel = $('<div class="partition-label">').text(value1.partition);
                partitionLi.append(partitionLabel);
                partitionLi.appendTo(ul);
            });
            li.appendTo($('#topics-ul'));

        })

    });

    eb.registerHandler("kafka.producer.message", function(document) {
        console.log(document);
    });
};
