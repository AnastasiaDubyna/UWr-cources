const socket = io();
const roomNumber = window.location.pathname.split("/")[2];
const messageContainer = $("#message-container");
let yourTurn = false;
let playerRole;

const images = {
    X: "../images/ex.png",
    O: "../images/zero.png"
}

showWaitingMessage();
// $(window).on("beforeunload", () => {
//     socket.emit("leave room", roomNumber);
// })

socket.emit("join room", roomNumber);

socket.on("playerRole", role => {
    playerRole = role;
    addPlayerRole();
})

socket.on("board", board => {
    updateBoard(board);
})

socket.on("game", status => {
    switch (status) {
        case "start":
            startGameHandler();
            break;
        case "yourTurn":
            console.log("your turn")
            yourTurn = true;
            toggleTurnInfo();
            break;
        case "draw":
            drawHandler();
            break;
        case "victory":
            victoryHandler();
            break;
        case "defeat":
            defeatHandler();
            break;
    }
})

socket.on("error", type => {
    switch (type) {
        case "full":
            redirectToHome();
            break;
    }
})


socket.on("opponent", username => {
    addOpponent(username);
})


socket.on("playerLeft", showPlayerLeftMessage);

function startGameHandler() {
    $(".go-home-button").click(redirectToHome);
    allowMoves();
    toggleLayout();
    changeBackground();
    console.log("Moves allowed")
}


function drawHandler() {
    yourTurn = false;
    removeBackground();
    addResultInfo("It's a draw!");
    addToStatistics("gamesDraw");
}


function victoryHandler() {
    yourTurn = false;
    removeBackground();
    addResultInfo("You won!");
    addToStatistics("gamesWon");
}


function defeatHandler() {
    yourTurn = false;
    removeBackground();
    addResultInfo("You lost!");
    addToStatistics("gamesLost");
}


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


function changeBackground() {
    if (yourTurn) {
        $("#you").css("background-color", "lightgreen");
        $("#opponent").css("background-color", "transparent");
    } else {
        $("#you").css("background-color", "transparent");
        $("#opponent").css("background-color", "lightgreen");
    }
}

function toggleTurnInfo() {
    const turnContainer = $("#turn-container");
    if (turnContainer.children().length === 0) {
        turnContainer.append("<p>Your turn</p>");
    } else {
        turnContainer.empty();
    }

    changeBackground();
}


function removeBackground() {
    $("#you").css("background-color", "transparent");
    $("#opponent").css("background-color", "transparent");
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


function addPlayerRole() {
    $("#players-container #you").append(`<p>${playerRole}</p>`);
}


function addOpponent(username) {
    const opponentRole = playerRole === "X" ? "O" : "X";
    $("#players-container #opponent").append(`<p>${username}</p> <p>${opponentRole}</p>`);
}


function redirectToHome() {
    socket.emit("leave", {roomNumber});
    window.location.replace("/home");
}


function showWaitingMessage() {
    messageContainer.append(`<h2>Your room number is ${roomNumber}</h2> <p>Waiting for another player</p>`);
    toggleLayout();
}


function showPlayerLeftMessage() {
    messageContainer.empty().append("<p>Your opponent left the room</p> " +
        "<button class='go-home-button' style='bottom: 355px'>Return to home page</button>");
    $(".go-home-button").click(redirectToHome);
    toggleLayout();
}

function toggleLayout() {
    $("#info-container").toggleClass("invisible");
    $(".game-field-container").toggleClass("invisible");
    messageContainer.toggleClass("invisible");
}


// 00  01  02
// 10  11  12
// 20  21  22

