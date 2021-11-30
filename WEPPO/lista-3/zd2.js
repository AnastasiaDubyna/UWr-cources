function fib(n) {
    if (n <= 1) {
        return 1;
    }
    return fib(n - 1) + fib(n - 2);
}

function fibMemo(n) {
    if (n <= 1) {
        return 1;
    }
    return fibMemo(n - 1) + fibMemo(n - 2);
}

function memo(f) {
    const cache = {}
    return function(n) {
        if (cache.n === undefined) {
            let val = f(n);
            cache.n = val;
        } 
        return cache.n;
    }
}
const N = 35; 
let memoFib = memo(fib);

fibMemo = memo(fibMemo);


//Option 1
console.log("Classic Fib");
let startTime = performance.now();
console.log(fib(N));
let endTime = performance.now();
console.log("Time = " + (endTime - startTime));

//Option 2
console.log("Fib with memoization. First try");
startTime = performance.now();
console.log(memoFib(N));
endTime = performance.now();
console.log("Time = " + (endTime - startTime));

console.log("Fib with memoization. Second try");
startTime = performance.now();
console.log(memoFib(N));
endTime = performance.now();
console.log("Time = " + (endTime - startTime));

//Option 3
console.log("Fib with better memoization ");
startTime = performance.now();
console.log(fibMemo(1000));
endTime = performance.now();
console.log("Time = " + (endTime - startTime));