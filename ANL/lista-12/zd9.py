from scipy.integrate import newton_cotes
import numpy as np
import matplotlib.pyplot as plt
from scipy.interpolate import lagrange

def f(x):
    return 1/(1 + 25 * pow(x, 2))
a = -1
b = 1
exact = 0.54936

def make_plot(N):
    lagrange_xs = np.linspace(a, b, N + 1)
    xs = np.linspace(a, b, 100)
    ys = [f(x) for x in xs]
    lagrange_poli = lagrange(lagrange_xs, [f(x) for x in lagrange_xs])
    lagrange_value = [lagrange_poli(x) for x in xs]
    plt.plot(xs, ys)
    plt.plot(xs, lagrange_value)
    plt.show()

for N in [2, 4, 6, 8, 10, 12]:
    x = np.linspace(a, b, N + 1)
    an, B = newton_cotes(N, 1)
    dx = (b - a) / N
    quad = dx * np.sum(an * f(x))
    error = abs(quad - exact)
    print('{:2d}  {:10.9f} {:10.9f}'.format(N, quad, error))
    # print('{:2d}  {:10.9f}'.format(N, quad))
    make_plot(N)
