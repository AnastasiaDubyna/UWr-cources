let exampleObject = {
    field1: 1,
    exampleMethod: function() {
        console.log("Example method works");
    },
    get field1() {
        return this.field1;
    },

    set field1(val) {
        this.field1 = val;
    }
}

Object.defineProperty(exampleObject, "field2", {value: 2});
console.log(exampleObject.field2);

o.field3 = 3;
// is equivalent to:
Object.defineProperty(exampleObject, "field3", {
  value: 3,
  writable: true, //true if the value associated with the property may be changed with an assignment operator.
  configurable: true, //true if the type of this property descriptor may be changed and if the property may be deleted from the corresponding object.
  enumerable: true //true if and only if this property shows up during enumeration of the properties on the corresponding object.
});

// On the other hand,
Object.defineProperty(o, "field4", { value: 4 });
// is equivalent to:
Object.defineProperty(o, "field4", {
  value: 4,
  writable: false,
  configurable: false,
  enumerable: false
});
// By default, values added using Object.defineProperty() are immutable and not enumerable.
