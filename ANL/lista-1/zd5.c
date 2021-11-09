#include <stdio.h> 
#include <math.h>

double getDoublePrecision(){
    double sumPi = 0;
    double k = 0;
    while(fabs(M_PI - 4 * sumPi) >= pow(10, -6)){
        sumPi += pow(-1, k)/(2 * k + 1);
        ++k;
    }
    return k;
}

float getSinglePrecision(){
    float sumPi = 0;
    float k = 0;
    while(fabs(M_PI - 4 * sumPi) >= pow(10, -6)){
        sumPi += pow(-1, k)/(2 * k + 1);
        ++k;
    }
    return k;
}

int main(){

    printf("Double precision: %lf\n", getDoublePrecision());
    printf("Sinlge precision: %f\n", getSinglePrecision());
    
}