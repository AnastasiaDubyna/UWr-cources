#!/usr/bin/env python

import numpy as np
from matplotlib import pyplot as plt
from scipy.special import comb


def bernstein(i, n, t):
    return comb(n, i) * (t ** (n - i)) * (1 - t) ** i


def bezier_curve(nodes, mesh_size = 100):
    node_x = np.array([n[0] for n in nodes])
    node_y = np.array([n[1] for n in nodes])
    t = np.linspace(0.0, 1.0, mesh_size)
    numerator = np.array(
        [bernstein(i, len(nodes) - 1, t) for i in
            range(0, len(nodes))])
    p_x = np.dot(node_x, numerator)
    p_y = np.dot(node_y, numerator)
    return p_x, p_y


def weighted_bezier_curve(nodes, weights, mesh_size = 100):
    node_x = np.array([n[0] for n in nodes])
    node_y = np.array([n[1] for n in nodes])
    weights = np.array(weights)

    t = np.linspace(0.0, 1.0, mesh_size)
    weighted_bernstein = np.array(
        [bernstein(i, len(nodes) - 1, t) * weights[i] for i in
         range(0, len(nodes))])

    sum_weighted_bernstein = np.sum(weighted_bernstein, axis = 0)

    p_x = np.divide(np.dot(node_x, weighted_bernstein), sum_weighted_bernstein)
    p_y = np.divide(np.dot(node_y, weighted_bernstein), sum_weighted_bernstein)
    return p_x, p_y


if __name__ == '__main__':
    # Define nodes & weights
    nodes = [
        [39.5, 10.5], [30, 20],    #0, 1
        [6, 6], [13, -12],         #2, 3
        [63, -12.5], [18.5, 17.5], #4, 5
        [48, 63], [7, 25.5],       #6, 7
        [48.5, 49.5], [9, 19.5],   #8, 9
        [48.5, 35.5], [59, 32.5],  #10, 12
        [56, 20.5]                 #12
        ]
    
    #Tak jak w poleceniu
    weights = [1, 2, 3, 2.5, 6, 1.5, 5, 1, 2, 1, 3, 5, 1]
    p_x, p_y = weighted_bezier_curve(nodes, weights)
    plt.plot(p_x, p_y, 'g-')



    # zmianiam wagę p.10: 3 -> 10
    # weights = [1, 2, 3, 2.5, 6, 1.5, 5, 1, 2, 1, 10, 5, 1]

    #p7: 1 -> 10
    weights = [1, 2, 3, 2.5, 6, 1.5, 5, 10, 2, 1, 3, 5, 1]

    p_x, p_y = weighted_bezier_curve(nodes, weights)
    plt.plot(p_x, p_y, 'r-')


    #Bez wag
    # p_x, p_y = bezier_curve(nodes)
    # plt.plot(p_x, p_y, 'b-')



    nodes_x = [n[0] for n in nodes]
    nodes_y = [n[1] for n in nodes]
    dot_size = [1 + w ** 2 for w in weights]
    plt.scatter(nodes_x, nodes_y, c = 'k', s = dot_size)

    for n in range(len(nodes)):
        plt.text(nodes_x[n], nodes_y[n], n)

    plt.show()