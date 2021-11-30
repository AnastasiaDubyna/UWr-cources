module.exports = firstFoo;
const second = require("./second");

function firstFoo(n) {
    if (n > 0) {
        console.log("first" + n);
        // second.secondFoo(n - 1);
        second(n - 1);
    }
}

// module.exports = {firstFoo};