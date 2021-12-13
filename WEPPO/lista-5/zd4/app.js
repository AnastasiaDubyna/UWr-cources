const http = require("http");
const express = require("express");

const app = express();

app.set("view engine", "ejs");
app.set("views", "./views");
app.use(express.static("./static"));
app.use(express.urlencoded({ extended: false }));

app.get("/", (req, resp) => {
    resp.render("default",  {name: '' , surname: '', subject: '', grades: [], errorMessage: ''});
});

app.get('/print', (req, res) => {
    res.redirect('/');
})

app.post('/print', (req, res) => {
    const name = req.body.name;
    const surname = req.body.surname;
    const subject = req.body.subject;
    const grades = [];

    for(let i = 1; i<11; i++){
        let gradeVal = req.body["exercise"+i];
        if(gradeVal === ''){
            gradeVal = '0';
        }
        grades.push(gradeVal);
    }

    if(name === '' || surname === ''){
        res.render('default', {name: name, surname: surname, subject: subject, grades: grades, errorMessage: 'Write your name and surname'});
    }
    else{
        res.render('print', {name: name, surname: surname, subject: subject, grades: grades, errorMessage: ''});
    }
});

http.createServer(app).listen(3000);