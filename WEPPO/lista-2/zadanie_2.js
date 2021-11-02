const example = {
    first: 5,
    second: 10, 
    1: 15
}

const exampleTwo = {
    third: 20
}

let exampleArray = ["first", "second", "third"];

//1. 

// console.log(example.first); //5
// console.log(example[first]); //ReferenceError: first is not defined
// console.log(example["first"]); //5

//2

console.log(example[1]); //undefined o ile nie ma takiego klucza
console.log(example[exampleTwo]); //undefined
console.log(exampleTwo); //{ third: 20 }

//3
exampleArray["1"] = 1;
console.log(exampleArray["1"]); //undefined
console.log(exampleArray[exampleTwo]); //undefined

exampleArray.length = 5;
console.log(exampleArray);

exampleArray.length = 2;
console.log(exampleArray);