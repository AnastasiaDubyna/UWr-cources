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
const gameUtils = require("./utils/game");
const roomsUtils = require("./utils/rooms");
const cookie = require("cookie");


const app = express();
const server = http.createServer(app);
const io = socketio(server);

// app.use(logger('dev'));
app.use(express.static(path.join(__dirname, 'public')));
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
gameSetupUtils.setupGame(app);


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
        roomsUtils.setupRoom(app, roomNumber);
        const roomSize = io.sockets.adapter.rooms.get(roomNumber).size;
        const socketId = socket.id;
        const username = cookie.parse(socket.handshake.headers.cookie).username;

        switch (roomSize) {
            case 1:
                gameSetupUtils.addPlayerRole(app, roomNumber, socketId, "X");
                gameSetupUtils.addPlayerUsername(app, roomNumber, socketId, username);
                gameUtils.setTurn(app, roomNumber, socketId, true);
                roomsUtils.addToAvailableRooms(app, roomNumber);
                socket.emit("playerRole", "X");
                io.emit("availableRoom", roomNumber);
                break;
            case 2:
                gameSetupUtils.addPlayerRole(app, roomNumber, socketId, "O");
                gameUtils.setTurn(app, roomNumber, socketId, false);
                roomsUtils.removeFromAvailableRooms(app, roomNumber);
                io.emit("fullRoom", roomNumber);
                socket.emit("playerRole", "O");
                socket.emit("opponent", gameSetupUtils.getOpponentUsername(app, roomNumber, socketId));
                socket.to(roomNumber).emit("opponent", username);
                io.to(roomNumber).emit("game", "start");
                io.to(gameUtils.getWhoseTurn(app, roomNumber)).emit("game", "yourTurn");
                break;
            default:
                socket.emit("error", "full");
        }
    });

    socket.on("move", ({roomNumber, squareId}) => {
        gameUtils.addMove(app, roomNumber, socket.id, squareId);
        io.to(roomNumber).emit("board", gameUtils.getBoard(app, roomNumber));
        const gameStatus = gameUtils.getGameStatus(app, roomNumber);
        if (gameStatus.continue === true) {
            gameUtils.changeTurn(app, roomNumber);
            io.to(gameUtils.getWhoseTurn(app, roomNumber)).emit("game", "yourTurn");
        } else if (gameStatus.draw === true) {
            io.to(roomNumber).emit("game", "draw");
        } else {
            socket.emit("game", "victory");
            socket.to(roomNumber).emit("game", "defeat");
        }
    })


    socket.on("leave", ({roomNumber}) => {
        socket.to(roomNumber).emit("playerLeft");
    })


    socket.on("getAvailableRooms", () => {
        socket.emit("availableRooms", app.get("game").get("availableRooms"));
    })
});


mongoose.connect("mongodb+srv://AnastasiiaDubyna:Nn08091998@cluster0.r6len.mongodb.net/weppo_game_project?retryWrites=true&w=majority")
    .then(result => server.listen(3000, () => console.log("Server running")))



