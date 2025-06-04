# https://www.acmicpc.net/problem/10816

import sys
from collections import defaultdict
input = sys.stdin.readline


N = int(input().strip())
arr = input().split()
table = dict()

for a in arr:
  if a not in table: table[a] = 1
  else: table[a] += 1

M = int(input())
Q = input().split()

answer = ''
for q in Q:
  if q in table: print(table[q], end=" ")
  else: print(0, end=" ")