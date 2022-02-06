import matplotlib.pyplot as plt
import numpy as np

def find_wezly_rownoodlegle(n):
    wezly = []
    x = -1
    for i in range(n + 1):
        x += 2/(n+2)
        wezly.append(x)
    return wezly

def wartosc_wielomianu(x, n):
    wezly = find_wezly_rownoodlegle(n)
    print(wezly)
    result = 1
    for i in range(n + 1):
        result *= x - wezly[i]
    return result

print(wartosc_wielomianu(1, 1))

# x = [num for num in np.arange(-1, 1, 0.1)]

# for i in range(4, 21):
#     y = [wartosc_wielomianu(num, i) for num in x]
#     plt.plot(x, y, label="n = ${i}")

# plt.show()


