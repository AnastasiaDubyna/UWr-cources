module.exports = secondFoo;
const first = require("./first");

function secondFoo(n) {
    if (n > 0) {
        console.log("second" + n);
        first(n - 1);
        // first.firstFoo(n - 1);
    }
}

// module.exports = {secondFoo};