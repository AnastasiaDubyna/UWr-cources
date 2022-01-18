<?php

$errors = [];
$data = [];

if (empty($_POST["login"])) {
    $errors["login"] = "Login is required";
}

if (empty($_POST["password"])) {
    $errors["password"] = "Password is required";
}

if (empty($_POST["passwordConfirmation"])) {
    $errors["passwordConfirmation"] = "Confirm your password";
}

if (empty($_POST["birthDate"])) {
    $errors["birthDate"] = "Birth date is required";
}

if (!empty($_POST["password"]) and !empty($_POST["passwordConfirmation"]) and strcmp($_POST["password"], $_POST["passwordConfirmation"]) != 0) {
    $errors["passwordConfirmation"] = "Passwords don't match";
}

if (!empty($errors)) {
    $data["success"] = false;
    $data["errors"] = $errors;
} else {
    $data["success"] = true;
}

echo json_encode($data);
