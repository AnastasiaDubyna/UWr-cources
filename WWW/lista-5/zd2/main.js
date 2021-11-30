const rssUrl = "https://tvn24.pl/najnowsze.xml";
const rssUrl2 = "https://cors-anywhere.herokuapp.com/https://tvn24.pl/najnowsze.xml";

const xmlFile = $.get(rssUrl2)
    .then(data => console.log(data))
    .catch(errorData => console.error(errorData));
console.log(xmlFile)