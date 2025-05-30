# https://www.acmicpc.net/problem/21277

import sys

input = sys.stdin.readline

N1, M1 = map(int, input().split())
arr = [['0'] * 150 for _ in range(150)]

extent = float('inf')

for r in range(50, 50 + N1):
  str_list = input().strip()
  for c in range(50, 50 + M1):
    arr[r][c] = str_list[c-50]

N2, M2 = map(int, input().split())

arr2 = []
for r in range(N2):
  arr2.append(input().strip())
  
def check():
  global extent

  for r in range(50 - N2, 50 + N1 + 1):
    for c in range(50 - M2, 50 + M1 + 1):

      is_possible = True
      
      for nxt in range(N2):        
        for mxt in range(M2):
          val = arr[r+nxt][c+mxt]
          if val == '1' and arr2[nxt][mxt] != '0':
            is_possible = False
            break
        
        if not is_possible:
          break
      
      if is_possible:
        height = max(50 + N1, r + N2) - min(r, 50)
        width = max(50 + M1, c + M2) - min(c, 50) 
        extent = min(extent, width * height)

# 4가지 케이스 (그대로, 90, 180, 270 회전)
for i in range(4):
  if i == 0:
    check()
    continue
  
  temp = []
  for row in zip(*arr2):
    temp.append(row[::-1])
  arr2 = temp
  N2 = len(arr2)
  M2 = len(arr2[0])
  
  check()

print(extent)