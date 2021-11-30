let accNumberInput = document.getElementById("account-number");

function addValidation(name, validationRegex) {
    let input = document.getElementById(name);

    input.addEventListener("focusout", (event) => {
        let inputValue = event.target.value;
        let inputName = event.target.getAttribute('id');
        let errorContainer = document.getElementById(inputName + "-error");
        
        if (!validationRegex.test(inputValue)) {
            errorContainer.style.display = "block";
        } else {
            errorContainer.style.display = "none";
        }
    });
}

addValidation("account-number", /^\d{16}$/);
addValidation("pesel", /^\d{11}$/);
addValidation("email", /@+.+/);