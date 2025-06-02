# https://www.acmicpc.net/problem/22251

import sys

input = sys.stdin.readline
N, K, P, X = map(int, input().split()) # N 층, K 자리수, P 반전 개수, X 현재 층

nums = [0b1110111, 
        0b0010010, 
        0b1011101, 
        0b1011011, 
        0b0111010, 
        0b1101011, 
        0b1101111,
        0b1010010,
        0b1111111,
        0b1111011]

table = [[-1] * 10 for _ in range(10)]
for i in range(10):
  table[i][i] = 0

def count_change(fr, to):
  if table[fr][to] >= 0 :
    return table[fr][to]
   
  table[fr][to] = table[to][fr] = bin(nums[fr]^nums[to]).count('1')
  return table[fr][to]

answer = 0
origin = [X // 10**i % 10 for i in range(len(str(N))-1, -1, -1)]

for f in range(1, N+1):

  if f == X: continue

  cnt = 0
  idx = len(origin) - 1
  while idx > -1:
    temp = f % 10
    cnt += count_change(origin[idx], temp)
    if P < cnt: 
      break

    idx -= 1
    f //= 10

  if idx == -1:
    answer += 1

print(answer)