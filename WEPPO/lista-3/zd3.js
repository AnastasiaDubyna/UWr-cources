function forEach(a, f) {
    for (let i = 0; i < a.length; i++) {
        a[i] = f(a[i]);
    }
}

function map(a, f) {
    const result = [];
    for (let i = 0; i < a.length; i++) {
        result.push(f(a[i]));
    }
    return result;
}

function filter(a, f) {
    const result = [];
    for (let i = 0; i < a.length; i++) {
        if (f(a[i])) {
            result.push(a[i]);
        }
    }
    return result;
}

const a = [1, 2, 3, 4];
const b = [6, 8, 3, 4];

function square(a) {
    return a * a;
}

function isEven(a) {
    return a % 2 == 0;
} 
console.log("forEach");
forEach(a, square);
console.log(a);
forEach(a, x => x * x);
console.log(a);

console.log("Map");
const c = map(b, square);
const d = map(b, x => x * x);
console.log(c);
console.log(d);

console.log("Filter");
const e = filter(b, isEven);
const f = filter(b, x => x % 2 == 0);
console.log(e);
console.log(f);