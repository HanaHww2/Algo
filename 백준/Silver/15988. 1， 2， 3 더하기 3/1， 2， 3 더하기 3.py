# https://www.acmicpc.net/problem/15988

import sys

input = sys.stdin.readline

T = int(input())
MOD = 1_000_000_009

dp = [0, 1, 2, 4] + [0] * 1_000_000

def make_dp(until):

  for num in range(4, until + 1):
    dp[num] = (((dp[num-1] + dp[num-2]) % MOD) + dp[num-3]) % MOD

arr = list()
MAX = 0

for _ in range(T):
  temp = int(input())
  MAX = max(MAX, temp)
  arr.append(temp)

make_dp(MAX)

for i in arr:
  print(dp[i])