let fs = require("fs");

fs.readFile("./helloworld.txt", "utf-8", (err, data) => {
    if (err) {
        console.log("Something went wrong");
    } else {
        console.log(data);
    }
})