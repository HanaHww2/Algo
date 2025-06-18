# https://www.acmicpc.net/problem/12865

import sys
input = sys.stdin.readline

N, K = map(int, input().split())
W = [0]
V = [0]
DP = [[0] * (K+1) for _ in range(N+1)]

for _ in range(N):
  w, v = map(int, input().split())
  W.append(w)
  V.append(v)

for i in range(1, N+1):
  for k in range(1, K+1):
    if k >= W[i]: DP[i][k] = max(DP[i-1][k], DP[i-1][k-W[i]] + V[i])
    else: DP[i][k] = DP[i-1][k]
print(DP[-1][-1])