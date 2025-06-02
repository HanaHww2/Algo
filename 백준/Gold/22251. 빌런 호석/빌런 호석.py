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

def dfs(curr, idx, p):
  global answer

  if curr > N: return
  if idx == len(origin):
    if curr != 0 and curr != X: 
      answer += 1
    return

  for i in range(10):
    cnt = count_change(origin[idx], i)
    if p >= cnt:
      dfs(curr * 10 + i, idx + 1, p - cnt)

answer = 0
origin = [X // 10**i % 10 for i in range(len(str(N))-1, -1, -1)]

dfs(0, 0, P)

print(answer)