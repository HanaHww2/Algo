import sys

input = sys.stdin.readline
N, K = map(int, input().split(" "))

leafs = list(map(int, input().split(" ")))

dp = [0] * N 
temp  = 0

left, right = 0, 0

while right < N:

  dp[right] = dp[right-1] if right else 0
  temp += leafs[right]
  
  while temp >= K:
    before = dp[left - 1] if left else 0
    dp[right] = max(dp[right], before + temp - K)
    temp -= leafs[left]
    left += 1
  right += 1

print(dp[N-1])