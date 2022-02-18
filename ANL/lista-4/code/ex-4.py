from numpy import sign, log

def ex_4_function(x):
    return pow(x, 4) - log(x + 4)


def bisection(a_0, b_0, error, fun_zero):
    value_at_a_0 = ex_4_function(a_0)
    value_at_b_0 = ex_4_function(b_0)
    length = b_0 - a_0
    center = 0

    if sign(value_at_b_0) == sign(value_at_a_0) :
        return
    
    for i in range(1, 1000000):
        length = length / 2
        center = a_0 + length
        value_at_center = ex_4_function(center)

        if abs(fun_zero - center) < error:
            print("Liczba kroków:", i)
            print("Błąd:", abs(fun_zero - center))
            return center

        if sign(value_at_center) != sign(value_at_a_0):
            b_0 = center
            value_at_b_0 = value_at_center
        else:
            a_0 = center
            value_at_a_0 = value_at_center
    return center

#Błąd = 10^-8
# Pierwsze zero funkcji na przedziale [-1.5, -0.5]
# x = -1.0220662404863400034
print(bisection(-1.5, -0.5, 1/pow(10, 8), -1.0220662404863400034))
# Drugie zero funkcji na przedziale [0.5, 1.5]
# x = 1.1308296656007111722 
print(bisection(0.5, 1.5, 1/pow(10, 8), 1.1308296656007111722))
