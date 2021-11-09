#include <stdio.h> 
#include <math.h>

double recursion(int n){
    return powf((-1)/6, n);
}

int main(){
    for (int i = 0; i < 51; ++i) {
        printf("%d: %lf\n", i, recursion(i));
    }
}