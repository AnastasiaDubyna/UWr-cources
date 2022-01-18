$(document).ready(function () {
    $("form").submit(event => {
        event.preventDefault();
        const formData = {
            login: $("#login").val(),
            password: $("#password").val(),
            passwordConfirmation: $("#passwordConfirmation").val(),
            birthDate: $("#birthDate").val()
        };
        console.log(formData["password"] === formData["passwordConfirmation"]);

        $.ajax({
            type: "POST",
            url: "process.php",
            data: formData,
            dataType: "json",
            encode: true
        }).done(data => {
            if (data["success"] === false) {
                console.log(data["errors"]);
                $.each(data["errors"], (key, value) => {
                    $(`#${key}-error`).append(`<span>${value}</span>`);
                })
            }
        });

    });
});