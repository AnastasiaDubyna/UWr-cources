const path = require('path');
const http = require('http');
const express = require('express');
const socketio = require('socket.io');
const authorizationRouter = require("./routes/authorization");
const homeRouter = require("./routes/home");
const roomRouter = require("./routes/room");
const mongoose = require("mongoose");
const cookieParser = require('cookie-parser');
const logger = require('morgan');
const gameSetupUtils = require("./utils/gameSetup");
const cookie = require("cookie");


const app = express();
const server = http.createServer(app);
const io = socketio(server);

// app.use(logger('dev'));
app.use(express.static(path.join(__dirname, 'public')));
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
app.set("game", new Map());

app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());


app.use("/auth", authorizationRouter);
app.use("/home", homeRouter);
app.use("/room", roomRouter);

app.get("/", (req, res) => {
    res.redirect("/auth");
})

io.on("connection", (socket) => {
    socket.on("join room", roomNumber => {
        socket.join(roomNumber);
        gameSetupUtils.setupRoom(app, roomNumber);
        const roomSize = io.sockets.adapter.rooms.get(roomNumber).size;
        const socketId = socket.id;
        const username = cookie.parse(socket.handshake.headers.cookie).username;

        switch (roomSize) {
            case 1:
                gameSetupUtils.addPlayerRole(app, roomNumber, socketId, "X");
                gameSetupUtils.addPlayerUsername(app, roomNumber, socketId, username);
                gameSetupUtils.setTurn(app, roomNumber, socketId, true);
                socket.emit("playerRole", "X");
                break;
            case 2:
                gameSetupUtils.addPlayerRole(app, roomNumber, socketId, "O");
                gameSetupUtils.setTurn(app, roomNumber, socketId, false);
                socket.emit("playerRole", "O");
                socket.emit("opponent", gameSetupUtils.getOpponentUsername(app, roomNumber, socketId));
                socket.to(roomNumber).emit("opponent", username);
                io.to(roomNumber).emit("game", "start");
                // console.log(gameSetupUtils.getWhoseTurn(app, roomNumber));
                io.to(gameSetupUtils.getWhoseTurn(app, roomNumber)).emit("game", "yourTurn");
                break;
            default:
                socket.emit("error", "full");
        }
    });

    // socket.on("leave room", roomNumber => {
    //     socket.leave(roomNumber);
    //     console.log("USER LEFT ROOM");
    // })

    socket.on("move", ({roomNumber, squareId}) => {
        // io.sockets.clients(roomNumber).forEach(function(s){
        //     s.leave(roomNumber);
        // });
        gameSetupUtils.addMove(app, roomNumber, socket.id, squareId);
        io.to(roomNumber).emit("board", gameSetupUtils.getBoard(app, roomNumber));
        const gameStatus = gameSetupUtils.getGameStatus(app, roomNumber);
        console.log(gameStatus);
        if (gameStatus.continue === true) {
            gameSetupUtils.changeTurn(app, roomNumber);
            io.to(gameSetupUtils.getWhoseTurn(app, roomNumber)).emit("game", "yourTurn");
        } else if (gameStatus.draw === true) {
            io.to(roomNumber).emit("game", "draw");
        } else {
            socket.emit("game", "victory");
            socket.to(roomNumber).emit("game", "defeat");
        }
    })
});


mongoose.connect("mongodb+srv://AnastasiiaDubyna:Nn08091998@cluster0.r6len.mongodb.net/weppo_game_project?retryWrites=true&w=majority")
    .then(result => server.listen(3000, () => console.log("Server running")))



