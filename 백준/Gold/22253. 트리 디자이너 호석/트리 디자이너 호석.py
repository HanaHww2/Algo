# https://www.acmicpc.net/problem/22253

import sys

sys.setrecursionlimit(1_000_000_000)
input = sys.stdin.readline

MOD = 1_000_000_007
N = int(input())
val = input().split()
cnt = [[0] * 10 for _ in range(N+1)]

for i in range(1, N+1):
  val[i-1] = int(val[i-1])
  cnt[i][val[i-1]] = 1

graph = [[] for _ in range(N+1)]
for _ in range(N-1):
  a, b = map(int, input().split())
  graph[a].append(b)
  graph[b].append(a)

def dfs(curr, par):

  for nxt in graph[curr]:
    if nxt == par: continue
    dfs(nxt, curr)

    for i in range(10):
      cnt[curr][i] += cnt[nxt][i]
      cnt[curr][i] %= MOD

    # 현재 노드에 불을 키는 경우
    for i in range(val[curr-1], 10):
      cnt[curr][val[curr-1]] += cnt[nxt][i] 
      cnt[curr][val[curr-1]] %= MOD

dfs(1, 0)
print(sum(cnt[1]) % MOD)