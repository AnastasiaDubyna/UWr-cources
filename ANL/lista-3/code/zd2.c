#include <stdio.h> 
#include <math.h>

void do_delta(double a, double b, double c) {
    double delta = pow(b, 2) - 4 * a * c;
    double x_1 = (-b + sqrt(delta)) / (2 * a);
    double x_2 = (-b - sqrt(delta)) / (2 * a);
    printf("x1: %lf\n", x_1); 
    printf("x2: %lf\n", x_2); 
}

void do_delta_vieta(double a, double b, double c) {
    double x_1;
    double x_2;
    double delta = pow(b, 2) - 4*a*c;
    if (b > 0) {
        x_1 = (-b - sqrt(delta)) / (2 * a);
        x_2 = c / (a * x_1);
    }
    else {
        x_1 = (-b + sqrt(delta)) / (2 * a);
        x_2 = c / (a * x_1);
    }
    printf("x1: %lf\n", x_1); 
    printf("x2: %lf\n", x_2); 
}




int main() {
    printf("Only delta: \n");
    do_delta(1, pow(10, 20), 1);
    printf("Delta + vieta: \n");
    do_delta_vieta(1, pow(10, 20), 1);

}