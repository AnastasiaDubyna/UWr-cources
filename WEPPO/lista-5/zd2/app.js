const https = require("https");
const fs = require("fs");

(async function () {
    const pfx = await fs.promises.readFile('key.pfx');
    const server = https.createServer(
        {
            pfx: pfx,
            passphrase: "0809"
        },
        (req, res) => {
            res.setHeader('Content-type', 'text/html; charset=utf-8');        
            res.end("Server with https protocol")
        });
        server.listen(3000);
})()