function fibIter(n) {
    let a = 0;
    let b = 1;
    let c;

    for (let i = 1; i < n; i++) {
        c = a + b;
        a = b;
        b = c;
    }

    return c;
}

function fibRec(n) {
    if (n == 0) {
        return 0;
    }
    if (n == 1) {
        return 1;
    }
    return (fibRec(n-1) + fibRec(n-2));
}

function main() {
    for (let i = 10; i <= 40; i++) {
        console.time("fibIter for n=" + i);
        fibIter(i);
        console.timeEnd("fibIter for n=" + i);
        console.time("fibRec for n=" + i);
        fibRec(i);
        console.timeEnd("fibRec for n=" + i);
    }
}

main()
//Czas trwania obliczeń za pomocą fibRec z każdym n zwiększa się w 1.5 razy. 
// Dla n=45 ten czas wynosi 13+ sekund. Dla n=47 ponad 30. n=48: 73s.

//Kod odpalony w przeglądarce działa znacznie wolniej. Dla fibRec(38) = 23s.

// console.log(fibIter(19))
// console.log(fibRec(19))