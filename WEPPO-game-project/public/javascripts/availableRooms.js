const availableRoomsContainer = $("#available-rooms-container");
const socket = io();


socket.emit("getAvailableRooms");

socket.on("availableRooms", rooms => {
    rooms.forEach((roomNumber) => {
       addNewRoom(roomNumber);
    })
})


socket.on("availableRoom", roomNumber => {
    addNewRoom(roomNumber);
})


socket.on("fullRoom", roomNumber => {
    // availableRoomsContainer.remove(`#${roomNumber}`);
    $(`#${roomNumber}`).remove();
})


$("#join-button").click(event => {
    const roomNumber = $("#existing-room-number").val();

    fetch(`/room/exist/${roomNumber}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    })
        .then(response => response.json())
        .then(response => {
            console.log("Exist", response.exist);
            if (response.exist) {
                window.location.replace(`/room/${roomNumber}`);
            } else {
                $("#error-message-container").append("<p>Room doesn't exist</p>");
            }
        })
})


$(".go-back-button").click(() => {
    window.location.replace("/home");
})

function addNewRoom(roomNumber) {
    availableRoomsContainer.append(`<button id="${roomNumber}" class="room-button">${roomNumber}</button>`);
    $(`#${roomNumber}`).click(() => {
        window.location.replace(`/room/${roomNumber}`);
    })
}