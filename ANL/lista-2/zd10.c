#include <stdio.h> 
#include <math.h>

double rekurencjaPi(double k){
    if(k == 1){
        return 2.0;
    }
    double xkminus1 = rekurencjaPi(k-1);
    return sqrt(2 * pow(xkminus1, 2) / (1 + sqrt(1 - pow(xkminus1/pow(2, k-1), 2))));
}

// double rekurencjaPi(double k){
//     if(k == 1){
//         return 2.0;
//     }
//     return pow(2.0, k-1) * sqrt(2.0 * (1.0 - sqrt(1.0 - pow(rekurencjaPi(k-1.0)/pow(2.0, k-1), 2.0))));
// }

int main(){
    printf("Dla k = 1: %lf\n", rekurencjaPi(1.0));
    printf("Dla k = 2: %lf\n", rekurencjaPi(2.0));
    printf("Dla k = 3: %lf\n", rekurencjaPi(3.0));
    printf("Dla k = 4: %lf\n", rekurencjaPi(4.0));
    printf("Dla k = 5: %lf\n", rekurencjaPi(5.0));
    printf("Dla k = 10: %lf\n", rekurencjaPi(10.0));
    printf("Dla k = 20: %lf\n", rekurencjaPi(20.0));
    printf("Dla k = 21: %lf\n", rekurencjaPi(21.0));
    printf("Dla k = 22: %lf\n", rekurencjaPi(22.0));
    printf("Dla k = 23: %lf\n", rekurencjaPi(23.0));
    printf("Dla k = 24: %lf\n", rekurencjaPi(24.0));
    printf("Dla k = 25: %lf\n", rekurencjaPi(25.0));
    printf("Dla k = 26: %lf\n", rekurencjaPi(26.0));
    printf("Dla k = 27: %lf\n", rekurencjaPi(27.0));
    printf("Dla k = 28: %lf\n", rekurencjaPi(28.0));
    printf("Dla k = 29: %lf\n", rekurencjaPi(29.0));
    printf("Dla k = 30: %lf\n", rekurencjaPi(30.0));
    printf("Dla k = 40: %lf\n", rekurencjaPi(40.0));
    printf("Dla k = 50: %lf\n", rekurencjaPi(50.0));
    printf("Dla k = 100: %lf\n", rekurencjaPi(100.0));
}