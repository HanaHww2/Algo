# https://www.acmicpc.net/problem/20166

import sys, collections

input = sys.stdin.readline

N, M, K = map(int, input().split(" "))


arr = list(input() for _ in range(N))
strings = list(input().replace("\n", "") for _ in range(K))

set_strings = set(strings)


dy = [0, 0, 1, -1, 1, -1, 1, -1]
dx = [1, -1, 0, 0, -1, 1, 1, -1]

table = collections.defaultdict(int)

def dfs(i, j, prev):

  if len(prev) >= 5: return

  prev += arr[i][j]

  if prev in set_strings:
    table[prev] += 1

  for d in range(8):
    ny, nx = (i + dy[d]) % N, (j + dx[d]) % M
    dfs(ny, nx, prev)

for i in range(N):
  for j in range(M):
    dfs(i, j, '')

for s in strings:
  print(table[s])