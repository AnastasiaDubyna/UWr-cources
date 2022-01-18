document.getElementsByTagName("form")[0].addEventListener("submit", event => {
    event.preventDefault();
    const formData = {
        login: document.getElementById("login").value,
        password: document.getElementById("password").value,
        passwordConfirmation: document.getElementById("passwordConfirmation").value,
        birthDate: document.getElementById("birthDate").value
    };

    fetch("process.php", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
            login: document.getElementById("login").value,
            password: document.getElementById("password").value,
            passwordConfirmation: document.getElementById("passwordConfirmation").value,
            birthDate: document.getElementById("birthDate").value
        })
    })
        .then(response => response.json())
        .then(response => {
            console.log(formData)
            if (response["success"] === false) {
                for (const key in response["errors"]) {
                    const errorText = document.createElement("span").appendChild(document.createTextNode(response["errors"][key]));
                    document.getElementById(`${key}-error`).appendChild(errorText);
                }
            }
        })
        .catch(error => {
            console.log('Unhappy little error :(', error);
        });

})
