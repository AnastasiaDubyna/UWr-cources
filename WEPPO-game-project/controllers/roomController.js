const cookiesService = require("../services/cookieService")

const existingRooms = [];

function generateRoomNumber() {
    let newRoomNumber = Date.now();
    while (newRoomNumber in existingRooms) {
        newRoomNumber = Date.now();
    }
    existingRooms.push(newRoomNumber);

    return newRoomNumber;
}

async function roomController(req, res) {
    const areCookiesValid = await cookiesService.areCookiesValid(req);
    if (areCookiesValid) {
        const roomNumber = req.params.roomNumber;
        const username = req.cookies.username;
        res.render("room.ejs", {roomNumber, username});
        return;
    }
    res.redirect("/auth")
}

function doesRoomExist(req, res) {
    const roomNumber = req.params.roomNumber;
    console.log("doesExist", existingRooms);
    if (existingRooms.includes(parseInt(roomNumber))) {
        res.send({"exist": true});
    } else {
        res.send({"exist": false});
    }
}

function createNewRoom(req, res) {
    const roomNumber = generateRoomNumber();
    console.log(existingRooms);
    req.app.get("game").set("players", {
        "X": req.cookies.username
    })
    res.send({"roomNumber": roomNumber, "sign": "X"});
}

module.exports = {
    roomController,
    doesRoomExist,
    createNewRoom
};