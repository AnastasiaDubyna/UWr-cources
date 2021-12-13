const rssUrl = "https://tvn24.pl/najnowsze.xml";
const rssUrl2 = "https://cors-anywhere.herokuapp.com/https://tvn24.pl/najnowsze.xml";

function extractData(data) {
    const items = $(data).find("item");
    return [items.children("title"), items.children("link"), items.children("description")];
}

function addToHTML(itemsList) {
    const tagName = $(itemsList[0]).prop("tagName");
    $.each(itemsList, (index, element) => {
        addToDiv(element, tagName + "s-list");
    });
}

function addToDiv(element, className) {
    const listContainer = $(`.${className}`);
    listContainer.append($(`<li>${$(element).text()}</li>`));
}

function putOnPage(data) {
    const extractedData = extractData(data);
    $.each(extractedData, (index, itemsList) => addToHTML(itemsList));
}

const xmlFile = $.get(rssUrl2)
    .then(data => putOnPage(data))
    .catch(errorData => console.error(errorData));

// console.log(xmlFile)