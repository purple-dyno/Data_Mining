"""
Authors
https://github.com/Manu1ND
https://github.com/Ichigo27
"""
# HITS
import numpy as np
import math


def read_input():
    n = int(input("Enter number of nodes: "))
    print("Enter adjacency matrix: ")
    matrix = [list(map(int, input().split())) for i in range(n)]
    k = int(input("Enter value of k: "))
    return n, matrix, k


def normalize(m):
    max = math.sqrt(sum(m**2))
    m = m / max
    return m


n, matrix, k = read_input()
A = np.array(matrix)
AT = A.transpose()
u = np.ones(n)
for _ in range(k):
    v = np.matmul(AT, u)
    u = np.matmul(A, v)
    u = normalize(u)
    v = normalize(v)
print(f"\nHub: {u}")
print(f"Authority: {v}")
