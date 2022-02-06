const cookieService = require("../services/cookieService")
const User = require("../models/User");


async function renderHomePage(req, res) {
    const areCookiesValid = await cookieService.areCookiesValid(req);
    if (areCookiesValid) {
        const username = req.cookies.username;
        const status = req.cookies.status;
        if (status === "authorized") {
            const user = await User.findOne({username})
            res.render("home.ejs", {username: username, statistics: user.statistics});
            return;
        }
        res.render("home.ejs", {username: username, statistics: null});
        return;
    }
    res.redirect("/auth");
}


async function postStatistics(req, res) {
    if (req.cookies.status === "authorized") {
        const {incrementedStat} = req.body;
        const user = await User.findOne(req.cookies.username)
        user.statistics[incrementedStat] += 1;
        user.save();
    }
}



module.exports = {
    renderHomePage,
    postStatistics
}