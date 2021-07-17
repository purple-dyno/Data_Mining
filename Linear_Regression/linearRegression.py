# Linear Regression

import numpy as np
import matplotlib.pyplot as plt


def calc_coef(x, y):
    n = np.size(x)
    x_bar = np.mean(x)
    y_bar = np.mean(y)
    sum_XY = np.sum(x*y)
    sum_XX = np.sum(x*x)

    beta = (sum_XY - n*y_bar*x_bar) / (sum_XX - n*x_bar*x_bar)
    alpha = y_bar - beta*x_bar

    return beta, alpha


def plot_graph(x, y, beta, alpha, x_pred):
    plt.scatter(x, y, color="red", marker="+")

    line = beta*x + alpha
    plt.plot(x, line, color="blue")

    plt.plot(x_pred, y_pred, marker='o', markersize=8, color="red")

    plt.xlabel('x')
    plt.ylabel('y')

    plt.show()


if __name__ == "__main__":
    print("Enter X coordinates:")
    x = np.array(input().split(), dtype='int')

    print("\nEnter Y coordinates:")
    y = np.array(input().split(), dtype='int')

    x_pred = int(input("\nEnter X coordinate to predict Y: "))

    beta, alpha = calc_coef(x, y)
    print("\nbeta = {:.3f}, alpha = {:.3f}".format(beta, alpha))

    y_pred = beta*x_pred + alpha
    print("Estimated Y value = {:.3f}".format(y_pred))

    plot_graph(x, y, beta, alpha, x_pred)
