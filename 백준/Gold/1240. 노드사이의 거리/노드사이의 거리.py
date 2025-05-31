# https://www.acmicpc.net/problem/1240

import sys
from collections import deque

input = sys.stdin.readline
N, M = map(int, input().split())

graph = [[] for _ in range(N+1)]

for _ in range(N-1):
  a, b, c = map(int, input().split())  
  graph[a].append((b, c))
  graph[b].append((a, c))

def get_dist(a, b):
  visited = [False] * (N+1)
  q = deque([(0, a)])

  while q:
    cost, start = q.popleft()
    visited[start] = True

    for nxt, nc in graph[start]:
      if visited[nxt]: continue
    
      if nxt == b:
        return cost + nc
      q.append((cost+nc, nxt))


for _ in range(M):
  a, b = map(int, input().split())
  print(get_dist(a, b))