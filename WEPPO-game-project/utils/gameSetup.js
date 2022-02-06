function setupRoom(app, roomNumber) {
    if (!app.get("game").get(roomNumber)) {
        app.get("game").set(roomNumber, new Map([["playersRoles", new Map()], ["board", {}], ["turn", {}], ["playersUsernames", {}]]));
    }
}

function addPlayerRole(app, roomNumber, socketId, role) {
    app.get("game").get(roomNumber).get("playersRoles").set(socketId, role);
}

function addMove(app, roomNumber, socketId, squareId) {
    const board = getBoard(app, roomNumber);
    board[squareId] = app.get("game").get(roomNumber).get("playersRoles").get(socketId);
}

function getBoard(app, roomNumber) {
    return app.get("game").get(roomNumber).get("board");
}


function getPlayerIdBySign(app, roomNumber, sign) {
    const playerId = app.get("game").get(roomNumber).get("playersRoles");
    for (let [key, value] of playerId.entries()) {
        if (value === sign)
            return key;
    }

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


function setTurn(app, roomNumber, socketId, val) {
    app.get("game").get(roomNumber).get("turn")[socketId] = val;
}


function changeTurn(app, roomNumber) {
    const turnObject = app.get("game").get(roomNumber).get("turn");
    for (const playerId in turnObject) {
        turnObject[playerId] = !turnObject[playerId];
    }
}


function getWhoseTurn(app, roomNumber) {
    const turnObject = app.get("game").get(roomNumber).get("turn");
    for (const playerId in turnObject) {
        if(turnObject[playerId]) {
            return playerId;
        }
    }
}

function getGameStatus(app, roomNumber) {
    const board = getBoard(app, roomNumber);
    if (isVictory(board)) {
        return {"continue": false, "victory": true, "draw": false};
    } else if (Object.keys(board).length === 9) {
        return {"continue": false, "draw": true};
    }
    return {"continue": true};

}

function isVictory(board) {
    return checkHorizontals(board) || checkVerticals(board) || checkDiagonals(board);
}


function checkHorizontals(board) {
    return (board["00"] && board["01"] && board["02"] && board["00"] === board["01"] && board["01"] === board["02"])
        || (board["10"] && board["11"] && board["12"] && board["10"] === board["11"] && board["11"] === board["12"])
        || (board["20"] && board["21"] && board["22"] && board["20"] === board["21"] && board["21"] === board["22"])
}


function checkVerticals(board) {
    return (board["00"] && board["10"] && board["20"] && board["00"] === board["10"] && board["10"] === board["20"])
        || (board["01"] && board["11"] && board["21"] && board["01"] === board["11"] && board["11"] === board["21"])
        || (board["02"] && board["12"] && board["22"] && board["02"] === board["12"] && board["12"] === board["22"])
}

function checkDiagonals(board) {
    return (board["00"] && board["11"] && board["22"] && board["00"] === board["11"] && board["11"] === board["22"])
        || (board["02"] && board["11"] && board["20"] && board["02"] === board["11"] && board["11"] === board["20"])
}

// 00  01  02
// 10  11  12
// 20  21  22

module.exports = {
    setupRoom,
    addPlayerRole,
    addMove,
    getBoard,
    setTurn,
    changeTurn,
    getWhoseTurn,
    getGameStatus,
    addPlayerUsername,
    getOpponentUsername
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