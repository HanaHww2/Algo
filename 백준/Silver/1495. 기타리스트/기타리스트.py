# https://www.acmicpc.net/problem/1495
import sys

input = sys.stdin.readline

N, S, M = map(int, input().split())
V = list(map(int, input().split()))

dp = [[0] * (M + 1) for _ in range(N+1)]
dp[0][S] = 1

for i in range(1, N+1):
  for j in range(M+1):
    if dp[i-1][j] == 1:
      if 0 <= j + V[i-1] <= M:
        dp[i][j + V[i-1]] = 1
      if 0 <= j - V[i-1] <= M:
        dp[i][j - V[i-1]] = 1

ans = -1
for v in range(M, -1, -1):
  if dp[N][v] == 1:
    ans = v
    break
print(ans)