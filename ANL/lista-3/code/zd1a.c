#include <stdio.h> 
#include <math.h>

double do_1a(double x) {
    printf("sqrt: %lf\n", sqrt(pow(x, 10) + 2021));
    printf("pow + sqrt: %lf\n", (pow(x, 5) + sqrt(pow(x, 10) + 2021)));
    return -2 * pow(x, 5) * (pow(x, 5) + sqrt(pow(x, 10) + 2021));
}

int main() {
    printf("do_1a: %lf\n", do_1a(-pow(10, 30)));
}