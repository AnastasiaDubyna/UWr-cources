#include <stdio.h> 
#include <math.h>

double exerciseOne(double n) {
    return 4042 / (sqrt(pow(n, 13.0) + 1) + 1);
}

int main() {
    printf("%lf\n", exerciseOne(0.000001));
    printf("%lf\n", exerciseOne(0.00001));
    printf("%lf\n", exerciseOne(0.0001));
    printf("%lf\n", exerciseOne(0.001));
    printf("%lf\n", exerciseOne(0.01));
    printf("%lf\n", exerciseOne(0.1));
}