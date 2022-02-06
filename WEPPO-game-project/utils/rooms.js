function setupRoom(app, roomNumber) {
    if (!app.get("game").get(roomNumber)) {
        app.get("game").set(roomNumber, new Map([["playersRoles", new Map()], ["board", {}], ["turn", {}], ["playersUsernames", {}]]));
    }
}

function addToAvailableRooms(app, roomNumber) {
    app.get("game").get("availableRooms").push(roomNumber);
}


function removeFromAvailableRooms(app, roomNumber) {
    const av
}


module.exports = {
    setupRoom,
    addToAvailableRooms,
    removeFromAvailableRooms
}