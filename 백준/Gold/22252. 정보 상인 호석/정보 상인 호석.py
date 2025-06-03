# https://www.acmicpc.net/problem/22252

import sys, heapq
from collections import defaultdict

input = sys.stdin.readline
Q = int(input())

table = defaultdict(list)
value = 0
for _ in range(Q):
  temp = input().split()
  if temp[0] == '1':
    table[temp[1]].extend(list(map(int, temp[3:])))
  else:
    if len(table[temp[1]]) < 1: continue 
    table[temp[1]].sort()

    for _ in range(int(temp[2])):
    
      if len(table[temp[1]]) < 1: break 
      value += table[temp[1]].pop()

print(value)