$("#create-room-button").click(event => {
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


$("#join-room-button").click(() => {
    window.location.replace("/room/available");
})
