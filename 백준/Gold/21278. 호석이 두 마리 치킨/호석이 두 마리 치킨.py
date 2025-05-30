# https://www.acmicpc.net/problem/21278

import sys
input = sys.stdin.readline
N, M = map(int, input().split())
graph = [[float('inf')] * N for _ in range(N)]
for i in range(N):
  graph[i][i] = 0

for _ in range(M):
  a, b = map(int, input().split())
  graph[a-1][b-1] = 1
  graph[b-1][a-1] = 1

for k in range(N):
  for i in range(N):
    for j in range(N): # 양방향이라서 i+1 부터 시작해서 최적화 가능한 것 같음 혹은 bfs 풀이도 가능한 듯
      graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])

chi1 = None
chi2 = None
total = float('inf')
for a in range(N-1):
  for b in range(a+1, N):
    temp = 0
    for i in range(N):
      temp += min(graph[i][a], graph[i][b]) * 2

    if total > temp:
      total = temp
      chi1 = a + 1
      chi2 = b + 1

print(chi1, chi2, total)