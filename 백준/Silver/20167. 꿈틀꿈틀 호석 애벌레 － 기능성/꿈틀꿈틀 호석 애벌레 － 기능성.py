# https://www.acmicpc.net/problem/20167

import sys

input = sys.stdin.readline
N, K = map(int, input().split(" "))

leafs = list(map(int, input().split(" ")))

def dfs(i, prev, energy):
  if i > N - 1:
    return energy
  
  temp = energy
  if prev == 0:
    temp = dfs(i+1, prev, energy)
  
  prev += leafs[i]
  if prev >= K:
    energy += prev - K
    prev = 0

  return max(dfs(i+1, prev, energy), temp)


print(dfs(0, 0, 0))