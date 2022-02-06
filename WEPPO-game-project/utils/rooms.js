function setupRoom(app, roomNumber) {
    if (!app.get("game").get(roomNumber)) {
        app.get("game").set(roomNumber, new Map([["playersRoles", new Map()], ["board", {}], ["turn", {}], ["playersUsernames", {}]]));
    }
}

function addToAvailableRooms(app, roomNumber) {
    app.get("game").get("availableRooms").push(roomNumber);
    console.log("roomNumber" + roomNumber);
    console.log(app.get("game").get("availableRooms"))
}


function removeFromAvailableRooms(app, roomNumber) {
    const availableRooms = app.get("game").get("availableRooms");
    const index = availableRooms.indexOf(roomNumber);
    availableRooms.splice(index, 1);
}


module.exports = {
    setupRoom,
    addToAvailableRooms,
    removeFromAvailableRooms
};
