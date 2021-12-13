
function handleJSON(itemsList) {
    $.each(itemsList.splice(0, 5), (index, item) => {
        let html = ""
                + "<ul>"
                + "<li><h1>{{title}}</h1></li>"
                + "<li>{{link}}</li>"
                + "<li>{{description.__cdata}}</li>"
                + "</ul>";
                 
        $(".main-body-container").append(Mustache.render(html,item));
    })
}

$.getJSON("najnowsze.json", data => handleJSON(data.rss.channel.item));
