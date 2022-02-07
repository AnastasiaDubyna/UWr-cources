function setupGame(app) {
    app.set("game", new Map([["availableRooms", []]]));
}

function addPlayerRole(app, roomNumber, socketId, role) {
    app.get("game").get(roomNumber).get("playersRoles").set(socketId, role);
}


function addPlayerUsername(app, roomNumber, socketId, username) {
    app.get("game").get(roomNumber).get("playersUsernames")[socketId] = username;
}


function getOpponentUsername(app, roomNumber, socketId) {
    const playersUsername = app.get("game").get(roomNumber).get("playersUsernames");
    for (const id in playersUsername) {
        if (id !== socketId) {
            return playersUsername[id];
        }
    }
}


// 00  01  02
// 10  11  12
// 20  21  22

module.exports = {
    addPlayerRole,
    addPlayerUsername,
    getOpponentUsername,
    setupGame
}

//app
//   game[Map]
//            roomNumber[Map]
//                            playersRoles[Map]
//                            board[Obj]
//                            turn[Obj]
//                                     playerOneId: Bool
//                                     playerTwoId: Bool
//                            playersUsernames[Obj]