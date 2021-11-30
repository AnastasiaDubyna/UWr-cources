from math import sqrt

def ex_6_Newton(n, a):
    if n == 0:
        return -2 / sqrt(3 * a)

    x_n = ex_6_Newton(n - 1, a)
    return 0.5 * (3 * x_n - a * pow(x_n, 3))

#1/2 - 5 kroków 
print(ex_6_Newton(5, 4))

#1/sqrt(5) = 0.44721359549995793928
print("Dla a = 5")
for i in range(1, 10):
    error = abs(0.44721359549995793928 - ex_6_Newton(i, 5))
    print("Liczba kroków: ", i)
    print("Błąd:", error)
    if error < 1/pow(10, 8):
        print("Błąd bezwzględny < 10^-8")
        break

print("Dla a = 113")
for i in range(1, 100):
    error = abs(0.094072086838359729274 - ex_6_Newton(i, 113))
    print("Liczba kroków: ", i)
    print("Błąd:", error)
    if error < 1/pow(10, 8):
        print("Błąd bezwzględny < 10^-8")
        break



