const {Schema, model} = require('mongoose')


const User = new Schema({
    username: {type: String, unique: true, required: true},
    password: {type: String, required: true},
    statistics: {
        gamesPlayed: {type: Number, default: 0},
        gamesWon: {type: Number, default: 0},
        gamesLost: {type: Number, default: 0},
        gamesDraw: {type: Number, default: 0}
    }
})

module.exports = model('User', User)