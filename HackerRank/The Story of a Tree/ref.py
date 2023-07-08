import math
import os
import random
import re
import sys
import collections
import fractions


def dfs(v, adj_list, parent):
    for u in adj_list[v]:
        if u != parent[v]:
            parent[u] = v
            dfs(u, adj_list, parent)


def sum_dfs(v, p, adj_list, count, k):

    count[v] += int(p != 0) * count[p]
    out = count[v] >= k
    for u in adj_list[v]:
        if u != p:
            out += sum_dfs(u, v, adj_list, count, k)
    return out


def storyOfATree(n, edges, k, guesses):
    adj_list = {i: set() for i in range(n)}
    for u, v in edges:
        adj_list[u].add(v)
        adj_list[v].add(u)
    parent, count = [0] * (n + 1), [0] * (n + 1)

    dfs(0, adj_list, parent)

    # guesses : ((parent, node), ...) 해당 노드의 연결은 반드시 트리에 존재함
    for u, v in guesses:
        if parent[v] == u:  # 추측이 맞다면,
            count[1] += 1
            count[v] -= 1  # v가 루트인 경우 감소
        else:  # 추측이 틀리다면,
            count[u] += 1  # u가 루트인 경우 증가

    # k 보다 큰 노드가 배열에 몇 개 존재하는지 확인
    n_correct = # sum_dfs(1, 0, adj_list, count, k)

    out = fractions.Fraction(n_correct, n)

    return f"{out.numerator}/{out.denominator}"


if __name__ == "__main__":
    fptr = open("simple.txt", "w")

    q = int(input().strip())  # game num

    for q_itr in range(q):
        n = int(input().strip())  # node num

        edges = []

        for _ in range(n - 1):
            edges.append(list(map(int, input().rstrip().split())))

        first_multiple_input = input().rstrip().split()

        g = int(first_multiple_input[0])

        k = int(first_multiple_input[1])

        guesses = []

        for _ in range(g):
            guesses.append(list(map(int, input().rstrip().split())))

        result = storyOfATree(n, edges, k, guesses)

        fptr.write(result + "\n")

    fptr.close()
