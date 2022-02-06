$("#join-room-form").submit(event => {
    event.preventDefault();
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
                window.location.replace(`room/${roomNumber}`);
            } else {
                $("#error-message-container").append("<p>Room doesn't exist</p>");
            }
        })
})

$("#create-room-form").submit(event => {
    event.preventDefault();
    let roomNumber;

    fetch("/room/create", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    })
        .then(response => response.json())
        .then(result => {
            roomNumber = result.roomNumber;
            window.location.replace(`room/${roomNumber}`);
        })

})
