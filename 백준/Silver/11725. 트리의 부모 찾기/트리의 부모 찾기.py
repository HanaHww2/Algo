# https://www.acmicpc.net/problem/11725

import sys

sys.setrecursionlimit(100_000_000)
input = sys.stdin.readline

N = int(input().strip())
graph = [[] for _ in range(N+1)]

for _ in range(N-1):
  a, b = map(int, input().split())
  graph[a].append(b)
  graph[b].append(a)


parent = [0] * (N + 1)
def dfs(root):

  for child in graph[root]:
    if parent[child] > 0: continue

    parent[child] = root
    dfs(child)

dfs(1)

for p in parent[2:]:
  print(p)
