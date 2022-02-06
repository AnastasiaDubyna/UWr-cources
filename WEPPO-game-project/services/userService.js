const User = require("../models/User");
const bcrypt = require("bcryptjs");

async function doesUserExist(username) {
    const user = await User.findOne({username});
    return !!user;
}

async function isPasswordValid(username, password) {
    const user = await User.findOne({username});
    return bcrypt.compareSync(password, user.password);
}

async function createNewUser(username, password) {
    const hashPassword = bcrypt.hashSync(password, 7);
    const user = new User({username: username, password: hashPassword});
    await user.save();
}

module.exports = {
    doesUserExist,
    isPasswordValid,
    createNewUser
}
