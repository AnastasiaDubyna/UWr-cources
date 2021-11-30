function sum(...addends) {
    let res = 0
    for (let i = 0; i < addends.length; i++) {
        res += addends[i];
    }
    return res;
}

console.log(sum(1, 2, 3, 4, 5));
console.log(sum(1, 2, 3, 4, 5, 10, 20));