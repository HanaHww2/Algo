import math
import os
import random
import re
import sys
import collections
import fractions

# 시간초과 남 ㅡ,ㅡ
# Complete the 'storyOfATree' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
#  1. INTEGER n
#  2. 2D_INTEGER_ARRAY edges
#  3. INTEGER k
#  4. 2D_INTEGER_ARRAY guesses
#


def storyOfATree(n, edges, k, guesses):
    # Write your code here
    # print(n, edges, k, guesses)

    def check_win(root, guesses):
        cnt = 0
        # guesses : ((parent, node), ...)
        for p, c in guesses:
            if tree[c] == p:
                cnt += 1
            if cnt == k:
                return True

    def reorganize_tree(parent):
        for node in graph[parent]:
            if tree[node] == 0:
                tree[node] = parent
                reorganize_tree(node)

    # need to fix root
    # except nearby root node, all node fix to their parents
    graph = collections.defaultdict(list)
    cnt = 0

    for a, b in edges:
        graph[a].append(b)
        graph[b].append(a)

    keys = graph.keys()
    total = len(keys)

    for root in keys:
        # reorganize graph by root
        tree = collections.defaultdict(int)
        tree[root] = -1
        reorganize_tree(root)

        if check_win(root, guesses):
            cnt += 1
    if cnt == 0:
        return "0/1"

    a = fractions.Fraction(cnt, total)
    answer = f"{a.numerator}/{a.denominator}"
    return answer


if __name__ == "__main__":
    fptr = open("input.txt", "w")

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
