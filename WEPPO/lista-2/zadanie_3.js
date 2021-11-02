console.log( (![]+[])[+[]] + (![]+[])[+!+[]] + ([![]]+[][[]])[+!+[]+[+[]]] + (![]+[])[!+[]+!+[]] );

//(![]+[])[+[]] = false[0] = f

console.log([] == true); //false, bo [] = 0
console.log(!![]); //true
//[] zwraca true, bo array jest obiektem 

// (![]+[])[+!+[]] = false[+!0] = false[+true] = false[1] = a
// ([![]]+[][[]])[+!+[]+[+[]]] = falseundefined[+!+[]+[+[]]] = falseundefined[10] = i
// (![]+[])[!+[]+!+[]] = false[2] = l
