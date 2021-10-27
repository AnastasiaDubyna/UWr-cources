function is_divisible(n, digit) {
    return (digit !=0 && n % digit == 0);
}

function do_calculations(n) {
    let divided_num = n;
    let digits_sum = 0;
    let curr_digit;
    i = 0;

    while (divided_num) {
        curr_digit = divided_num % 10;
        if (is_divisible(n, curr_digit)) {
            divided_num = Math.floor(divided_num / 10);
            digits_sum += curr_digit;
        }
        else {
            return false;
        }
    }
    return (n % digits_sum == 0);
}

function main(range) {
    let result = []
    for (let i = 1; i <= range; i++) {
        if (do_calculations(i)) {
            result.push(i);
        }
    }
    return result;
}

console.log(main(100000));