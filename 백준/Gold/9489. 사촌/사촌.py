# https://www.acmicpc.net/problem/9489

import sys, collections

input = sys.stdin.readline

def make_parent():
  p = -1
  for i, a in enumerate(arr):
    if i > 0 and a != arr[i-1] + 1:
      p += 1
    parent[i] = p

def get_cousins_num():
  global idx
  cnt = 0
  for i in range(N):
    if parent[idx] != parent[i] and parent[parent[i]] == parent[parent[idx]]:
      cnt += 1
  return cnt

while True:
  N, K = map(int, input().split())
  
  if N == 0:
    break

  arr = list(map(int, input().split()))

  parent = [0] * N
  make_parent()
  idx = arr.index(K)

  print(get_cousins_num())