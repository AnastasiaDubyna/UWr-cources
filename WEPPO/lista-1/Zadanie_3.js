function do_sieve_of_eratosthenes(n) {
    let sieve = [];
    for (let i = 1; i <= n; i++) {
        sieve[i] = true;
    }

    for (let i = 2; i <= Math.floor(Math.sqrt(n)); i++) {
        if (sieve[i]) {
            for (let j = i * i; j <= n; j += i) {
                sieve[j] = false;
            }
        }
    }
    for (let i = 1; i <= n; i++) {
        if (sieve[i]) {
            console.log(i);
        }
    }
}

do_sieve_of_eratosthenes(100000);