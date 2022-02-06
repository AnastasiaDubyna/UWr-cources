const User = require("../models/User");

async function areCookiesValid(req) {
    if (req.cookies.status === "anonymous") {
        return true;
    } else if (req.cookies.status === "authorized") {
        const usernameCookie = req.cookies.username;
        const passwordCookie = req.cookies.password;
        if (usernameCookie && passwordCookie) {
            const user = await User.findOne({username: usernameCookie});
            if (user && passwordCookie === user.password) {
                return true;
            }
        }
    }
    return false;
}

async function setCookies(res, username, status) {
    const user = await User.findOne({username});
    console.log(user);
    if (status === "authorized") {
        res.cookie("username", user.username, {maxAge: 48 * 60 * 60 * 1000, httpOnly: true});
        res.cookie("password", user.password, {maxAge: 48 * 60 * 60 * 1000, httpOnly: true});
        res.cookie("status", status, {maxAge: 48 * 60 * 60 * 1000, httpOnly: true});
    } else if (status === "anonymous") {
        res.cookie("username", username, {maxAge: 60 * 60 * 1000, httpOnly: true});
        res.cookie("status", status, {maxAge: 60 * 60 * 1000, httpOnly: true});
    }
}

module.exports = {
    areCookiesValid,
    setCookies
};