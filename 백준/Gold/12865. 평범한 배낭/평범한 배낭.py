import sys

n, k = map(int, sys.stdin.readline().split(" "))

arr = []
for i in range(n):
    arr.append(tuple(int(x) for x in sys.stdin.readline().split(" ")))

dp = [[0] * (k + 1) for _ in range(n + 1)]

# 정렬 없어도 됨.
arr.sort(key=lambda x: x[0])

def packing():
    for i, b in enumerate(arr):
        w, v = b

        if w > k:  # 정렬 수행한 경우
            return print(dp[i][-1])

        for j in range(1, k + 1):
            if j < w:
                dp[i + 1][j] = dp[i][j]
                continue
            
            dp[i + 1][j] = max(dp[i][j], v + dp[i][j - w])

    return print(dp[-1][-1])

packing()
