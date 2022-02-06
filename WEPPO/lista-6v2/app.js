const express = require('express');
const http = require("http");
const multer  = require('multer')
const app = express();

// const storage = multer.diskStorage({
//     destination: (req, file, cb) => {
//         cb(null, '/tmp/my-uploads')
//     },
//     filename: (req, file, cb) => {
//         cb(null, file.fieldname + '-' + Date.now())
//     }
// })
const upload = multer({ dest: 'uploads/' })

app.set("view engine", "ejs");
app.set("views", "./public");

app.get('/', (req, res) => {
    res.render('index', { title: 'Express' });
    res.end();
});

app.post('/upload', upload.single('file'), function (req, res) {

})

http.createServer(app).listen(3000);


