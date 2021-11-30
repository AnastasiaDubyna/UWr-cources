fs = require('fs');


let contentToFile = '';
for (let i = 0; i < 10000; i++) {
    contentToFile += Math.floor(Math.random() * 24) + ":" + Math.floor(Math.random() * 60) + ":" + Math.floor(Math.random() * 60) + " " +
        "192.168.1." + Math.floor(Math.random() * 255)
        + " GET /TheApplication/WebResource.axd 200\n";
}

fs.writeFile('log.txt', contentToFile, function (err) {
    if (err) {
        throw err;
    }
    console.log('Done!');
});