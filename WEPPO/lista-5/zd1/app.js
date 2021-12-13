const http = require("http");
const fs = require("fs");

// const server = http.createServer(
//     (req, resp) => {
//         resp.setHeader("Content-type", "text/html; charset = utf-8");
//         resp.end("Response from the server");
//     }
// )
// server.listen(3000);

(async function () {
    const mainHtml = await fs.promises.readFile('main_page.html', 'utf-8');
    const subHtml = await fs.promises.readFile('subpage.html', 'utf-8');
    const server = http.createServer(
    (req, res) => {
        res.setHeader('Content-type', 'text/html; charset=utf-8');        
        if (req.url === "/subpage.html") {
            res.end(subHtml);
        } else {
            res.end(mainHtml);
        }
    });
    server.listen(3000);
})()
