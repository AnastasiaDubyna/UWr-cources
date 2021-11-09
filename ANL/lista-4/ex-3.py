from numpy import sign

def ex_3_function(x):
    return x - 0.49


def bisection(a_0, b_0):
    u = ex_3_function(a_0)
    v = ex_3_function(b_0)
    e = b_0 - a_0
    c = 0

    if sign(u) == sign(v) :
        return
    
    for i in range(5):
        e = e / 2
        c = a_0 + e
        w = ex_3_function(c)

        if sign(w) != sign(u):
            b_0 = c
            v = w
        else:
            a_0 = c
            u = w
        print("step", i, ":", c)
        print("błąd:", abs(c - 0.49))
        print("błąd oszacowany:", 1/pow(2, i + 1))
    return c

print(bisection(0, 1))


    
