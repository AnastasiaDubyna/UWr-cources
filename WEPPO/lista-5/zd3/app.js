const http = require("http");

(async function () {
    const server = http.createServer(
        (req, res) => {
            res.setHeader('Content-type', 'text/html; charset=utf-8');  
            res.setHeader("Content-Disposition", "attachment; filename=zd2.html")      
            res.end("Server with saving")
        });
        server.listen(3000);
})()