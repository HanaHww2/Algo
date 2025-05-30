# https://www.acmicpc.net/problem/11403

import sys

input = sys.stdin.readline
N = int(input().strip())
graph = [list(map(int, input().split())) for _ in range(N)]

for k in range(N):
  for i in range(N):
    for j in range(N):
      if graph[i][j] == 0:
        possible = True if graph[i][k] + graph[k][j] == 2 else False
        if possible: graph[i][j] = 1

for i in range(N):
  print(*graph[i])