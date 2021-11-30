const readline = require("readline");
const fs = require("fs")


let lineReader = readline.createInterface({
    input: fs.createReadStream('log.txt'),
});

let ipsObject = {};
let ipsArray = []

lineReader.on("line", function (line) {
    let timeAndIp = line.split("GET")[0]
    let ipNumber = timeAndIp.split(" ")[1];
    let ipObject = {"ip": ipNumber, "count": 1};
    if (ipsObject[ipNumber] !== undefined) {
        ipsObject[ipNumber].count++;
    }
    else {
        ipsObject[ipNumber] = ipObject;
        ipsArray.push(ipObject); 
    }

});

lineReader.on("close", function () {
    ipsArray.sort((first, second) => {
        {
            if ( first.count > second.count ){
                console.log(first.count);
                return -1;
            }
            else if ( first.count < second.count ){
                return 1;
            }
            return 0;
        }
    });
    // console.log(ipsArray);
    console.log(ipsArray.slice(0, 3));
})