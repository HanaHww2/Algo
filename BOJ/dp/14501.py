# 백준 퇴사
# https://www.acmicpc.net/problem/14501
import sys
input = sys.stdin.readline

N = int(input()) # 주어지는 일정 갯수
schedule = [list(map(int, input().split())) for _ in range(N)]

dp = [0 for i in range(N+1)]

### sol1.
for i in range(N):
    # 상담이 끝나는 일자
    for j in range(i+schedule[i][0], N+1):
        if dp[j] < dp[i] + schedule[i][1]:
            dp[j] = dp[i] + schedule[i][1]

print(dp[-1])

### sol2.
for i in range(N-1, -1, -1):
    # 상담이 끝나는 일자
    if i + schedule[i][0] > N:
        dp[i] = dp[i+1]
    else:
        dp[i] = max(dp[i+1], dp[i+schedule[i][0]] + schedule[i][1])

print(dp[0])