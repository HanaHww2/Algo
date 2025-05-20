# https://www.acmicpc.net/problem/9489

import sys, collections

sys.setrecursionlimit(1_000_000)
input = sys.stdin.readline
 
def get_cousins_num(start, roots):

  global K
  idx  = start
  siblings = 0
  target_cnt = 0
  cnt = 0
  flag = False

  while roots:
    parent_nums = roots.popleft()

    while parent_nums > 0:

      temp_siblings = 0

      while True:
        
        if idx >= len(arr) or (idx > start and arr[idx] - arr[idx-1] > 1):
          parent_nums -= 1
          start = idx
          roots.append(temp_siblings)
          break

        if arr[idx] == K:
          flag= True
        idx += 1
        cnt += 1
        temp_siblings += 1

      if siblings == 0 and flag:
        siblings = temp_siblings

      if parent_nums == 0:
        if target_cnt == 0 and flag:
          target_cnt = cnt
          break

        cnt = 0

  if flag:
    return target_cnt - siblings

  return 0

while True:
  N, K = map(int, input().split(" "))
  
  if N == 0:
    break

  arr = list(map(int, input().split(" ")))

  print(get_cousins_num(1, collections.deque([1])))