let color1 = new String("green"); //ciąg utworzony przy pomocy konstruktora
color1 instanceof String; // true
typeof color1; //object

let color2 = "coral"; //literał łańcuchowy
color2 instanceof String; // false
typeof color2; //string



typeof true; //boolean
typeof new Boolean(true); //object
 
typeof "abc"; //string
typeof new String("abc"); //object
 
typeof 123; //number
typeof new Number(123); //object

var myString = new String();
myString instanceof String; // true
myString instanceof Object; // true