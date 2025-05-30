# https://www.acmicpc.net/problem/11728

import sys

input = sys.stdin.readline

N, M = input().split()

arr = list(map(int, input().split()))
arr.extend(list(map(int, input().split())))

print(" ".join(list(map(str, sorted(arr)))))