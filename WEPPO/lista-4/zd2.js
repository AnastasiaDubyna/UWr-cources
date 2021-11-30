let Person = (function() {
    let generateNumSymbol = Symbol("generateNum");

    function Person(name, surname) {
        this.name = name;
        this.surname = surname;
        this[generateNumSymbol] = function() {
            return Math.floor(Math.random() * 10000000);
        }
    }

    Person.prototype.generateId = function() {
        return this.surname + this[generateNumSymbol]();
    }

    return Person;

})();



let generateNumSymbol = Symbol("generateNum");
let person1 = new Person("Jan", "Kowalski");
// console.log(person1.generateNum());
console.log(person1.generateNumSymbol);