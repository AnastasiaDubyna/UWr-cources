const socket = io();
const roomNumber = window.location.pathname.split("/")[2];
let yourTurn = false;

const images = {
    X: "../images/ex.png",
    O: "../images/zero.png"
}

// $(window).on("beforeunload", () => {
//     socket.emit("leave room", roomNumber);
// })

socket.emit("join room", roomNumber);

socket.on("playerRole", playerRole => {
    console.log(playerRole);
    addPlayerRole(playerRole);
})

socket.on("board", board => {
    updateBoard(board);
})

socket.on("game", status => {
    switch (status) {
        case "start":
            allowMoves();
            console.log("Moves allowed")
            break;
        case "yourTurn":
            console.log("your turn")
            yourTurn = true;
            toggleTurnInfo();
            break;
        case "draw":
            yourTurn = false;
            addResultInfo("It's a draw!");
            addToStatistics("gamesDraw");
            break;
        case "victory":
            yourTurn = false;
            addResultInfo("You won!");
            addToStatistics("gamesWon");
            break;
        case "defeat":
            yourTurn = false;
            addResultInfo("You lost!")
            addToStatistics("gamesLost");
    }
})

socket.on("error", type => {
    switch (type) {
        case "full":
            redirectToHome();
            break;
    }
})


function addToStatistics(statName) {
    fetch("/home/statistics", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
            incrementedStat: statName
        })
    })
        .then(response => response.json())
        .then(response => {
            console.log(response);
        })
        .catch(error => {
            console.log('Unhappy little error :(', error);
        });
}


function allowMoves() {
    $(".square").click(event => {
        if (yourTurn) {
            if ($(event.currentTarget).children().length === 0) {
                const squareId = $(event.target).attr("id");
                socket.emit("move", {
                    roomNumber: roomNumber,
                    squareId: squareId
                });
                yourTurn = false;
                toggleTurnInfo();
            }
        }
    })
}


function toggleTurnInfo() {
    const turnContainer = $("#turn-container");
    if (turnContainer.children().length === 0) {
        turnContainer.append("<p>Your turn</p>");
    } else {
        turnContainer.empty();
    }
}


function addResultInfo(result) {
    $("#game-result-container").append(`<p>${result}</p>`);
}


function updateBoard(board) {
    $(".square").empty();
    console.log("Board on frontend", board);
    $.each(board, (id, sign) => {
        $(`#${id}`).append(`<img src=${images[sign]} alt=${sign} width="150px">`);
    });
}


function addPlayerRole(playerRole) {
    $("#player-role").append(`<p>You are playing as ${playerRole}</p>`);
}


function redirectToHome() {
    window.location.replace("/home");
}


// 00  01  02
// 10  11  12
// 20  21  22

