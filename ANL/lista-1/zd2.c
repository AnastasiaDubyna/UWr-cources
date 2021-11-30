#include <stdio.h> 
#include <math.h>

float singlePrecision(float n){
    return 4042 * (1 - cos(n) / pow(n, 2.0));
}

double doublePrecision(double n){
    return 4042 * (1 - cos(n) / pow(n, 2.0));
}

int main(){
    for (int i = 11; i < 21; ++i)
    {
        printf("for %d: %f vs %lf\n", i, singlePrecision(pow(10.0, -i)), doublePrecision(pow(10.0, -i)));
    }
    for (int i = 11; i < 21; ++i)
    {
        printf("for %d: %lf vs %f\n", i, doublePrecision(pow(10.0, -i)), singlePrecision(pow(10.0, -i)));
    }
}