function addMove(app, roomNumber, socketId, squareId) {
    const board = getBoard(app, roomNumber);
    board[squareId] = app.get("game").get(roomNumber).get("playersRoles").get(socketId);
}

function getBoard(app, roomNumber) {
    return app.get("game").get(roomNumber).get("board");
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


module.exports = {
    addMove,
    getBoard,
    setTurn,
    changeTurn,
    getWhoseTurn,
    getGameStatus
}