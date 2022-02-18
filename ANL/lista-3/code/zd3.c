#include <stdio.h> 
#include <math.h>

double before(double q, double r) {
    double inner_sqrt = sqrt(pow(q, 3) + pow(r, 2));
    printf("sqrt: %lf\n", cbrt(r - inner_sqrt));
    return (cbrt(r + inner_sqrt) + cbrt(r - inner_sqrt));
}

double after(double q, double r) {
    double inner_sqrt = sqrt(pow(q, 3) + pow(r, 2));
    double cubic_root = cbrt(pow(r + inner_sqrt, 2));
    return (2*r / (cubic_root + (1 / cubic_root) * pow(q, 2) + q));
}

int main() {
    printf("Dla bardzo dużych r i małych q\n");
    printf("r = 10^20 i q = 1:\n");
    printf("before: %lf\n", before(1, pow(10, 88))); //tracimy pierwistek dla r = 10^8
    printf("after: %lf\n", after(1, pow(10, 88)));
}