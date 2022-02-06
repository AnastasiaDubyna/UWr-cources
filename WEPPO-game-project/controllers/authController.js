const cookieService = require("../services/cookieService")
const userService = require("../services/userService")


async function authorization(req, res) {
    if (await cookieService.areCookiesValid(req)) {
        res.redirect("/home");
        return;
    }
    res.render("authorization.ejs");
}



async function registration(req, res) {
    const {username, password, passwordConfirmation} = req.body;
    const doesUserExist = await userService.doesUserExist(username);
    if (doesUserExist) {
        console.log("HERE")
        res.send({success: false, message: "Login already exists"});
        return;
    }
    if (password !== passwordConfirmation) {
        res.send({success: false, message: "Passwords don't match"})
        return;
    }
    await userService.createNewUser(username, password);
    await cookieService.setCookies(res, username, "authorized");

    res.send({success: true, message: "User created"});
}

async function login(req, res) {
    const {username, password} = req.body;
    const doesUserExist = await userService.doesUserExist(username);
    if (!doesUserExist) {
        res.send({success: false, message: "User not found"});
        return;
    }
    const isValidPassword = await userService.isPasswordValid(username, password);
    if (!isValidPassword) {
        res.send({success: false, message: "Incorrect password"});
        return;
    }
    await cookieService.setCookies(res, username, "authorized");
    res.send({success: true});
}

async function anonymous(req, res) {
    const {username} = req.body;
    if (await userService.doesUserExist()) {
        res.send({success: false, message: "Username already exists"});
        return;
    }
    await cookieService.setCookies(res, username, "anonymous");
    res.send({success: true});

}



module.exports = {
    authorization,
    login,
    registration,
    anonymous
}