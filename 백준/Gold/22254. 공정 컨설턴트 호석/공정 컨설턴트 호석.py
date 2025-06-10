# https://www.acmicpc.net/problem/22254
import sys, math, heapq

input = sys.stdin.readline

N, X = map(int, input().split())
arr = list(map(int, input().split()))

def process(mid):
  global X
  
  q = list()
  for i in range(mid):
    heapq.heappush(q, arr[i])
    
  i = mid
  while i < len(arr):
    c = heapq.heappop(q)
    nc = arr[i]

    if c + nc <= X:
      heapq.heappush(q, c+nc)
      i += 1
    else:
      return False
  
  return True

def bs():
  global N
  left = 1 #max(1, math.ceil(sum(arr) / X))
  right = N + 1 #max(left + 1, len(arr) + 1)

  while left < right:
    mid = left + (right-left) // 2

    if process(mid):
      right = mid
    else:
      left = mid + 1
  
  return left

print(bs())