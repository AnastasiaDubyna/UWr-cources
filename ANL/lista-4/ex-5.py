from math import sqrt

ERROR = pow(10, -15)

def ex_5_Newton(n, R):
    if n == 0:
        return 1/(1.5*R)

    x_n = ex_5_Newton(n - 1, R)
    return x_n * (2 - x_n * R)


# 1/5
print("Dla R = 5")
for i in range(1, 10):
    e = abs(0.2 - ex_5_Newton(i, 5))
    print("Liczba kroków: ", i)
    print("Błąd:", e)
    if e < ERROR:
        print("Błąd bezwzględny < 10^-15")
        break
# 1/46
print("Dla R = 46")
for i in range(1, 10):
    e = abs(0.021739130434782608695652 - ex_5_Newton(i, 46))
    print("Liczba kroków: ", i)
    print("Błąd:", e)
    if e < ERROR:
        print("Błąd bezwzględny < 10^-15")
        break

#1/0.46 
print("Dla R = 0.46")
for i in range(1, 10):
    e = abs(2.1739130434782608695652173913043 - ex_5_Newton(i, 0.46))
    print("Liczba kroków: ", i)
    print("Błąd:", e)
    if e < ERROR:
        print("Błąd bezwzględny < 10^-15")
        break
