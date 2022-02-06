$(".authorization-button").click(event => {
    const buttonId = $(event.target).attr("id");
    const formId = buttonId.replace("button", "form");
    const goBackBtnId = buttonId.replace("button", "go-back-button");

    $(".authorization-form").css("display", "none");
    $(`#${formId}`).css("display", "flex");
    $(".buttons-container").css("display", "none");
    $(`#${goBackBtnId}`).css("display", "block");

})

$("#sign-up-go-back-button").click(event => {
    $(".buttons-container").css("display", "flex");
    $("#sign-up-form").css("display", "none");
    $("#sign-up-go-back-button").css("display", "none");
})

$("#log-in-go-back-button").click(event => {
    $(".buttons-container").css("display", "flex");
    $("#log-in-form").css("display", "none");
    $("#log-in-go-back-button").css("display", "none");
})

$("#anonymous-go-back-button").click(event => {
    $(".buttons-container").css("display", "flex");
    $("#anonymous-form").css("display", "none");
    $("#anonymous-go-back-button").css("display", "none");
})

$("#log-in-form").submit(event => {
    event.preventDefault();

    fetch("/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
            username: $("#login-input").val(),
            password: $("#login-password-input").val()
        })
    })
        .then(response => response.json())
        .then(response => {
            if (response.success === true) {
                window.location.replace("/home");
            } else {
                $("#log-in-form .error-container").append(`<p>${response.message}</p>`);
            }
        })
        .catch(error => {
            console.log('Unhappy little error :(', error);
        });
})

$("#sign-up-form").submit(event => {
    event.preventDefault();

    fetch("/auth/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
            username: $("#new-login-input").val(),
            password: $("#new-password-input").val(),
            passwordConfirmation: $("#password-confirmation-input").val()
        })
    })
        .then(response => response.json())
        .then(response => {
            if (response.success === true) {
                window.location.replace("/home");
            } else {
                $("#sign-up-form .error-container").append(`<p>${response.message}</p>`);
            }
        })
        .catch(error => {
            console.log('Unhappy little error :(', error);
        });
})

$("#anonymous-form").submit(event => {
    event.preventDefault();

    fetch("/auth/anon", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
            username: $("#anon-login-input").val(),
        })
    })
        .then(response => response.json())
        .then(response => {
            if (response.success === true) {
                window.location.replace("/home");
            } else {
                $("#anonymous-form .error-container").append(`<p>${response.message}</p>`);
            }
        })
        .catch(error => {
            console.log('Unhappy little error :(', error);
        });
})