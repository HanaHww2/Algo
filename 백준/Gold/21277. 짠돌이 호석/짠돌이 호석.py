# https://www.acmicpc.net/problem/21277

import sys

input = sys.stdin.readline

N1, M1 = map(int, input().split())
arr = [['0'] * 151 for _ in range(151)]
min_r = min_c = float('inf')
max_r = max_c = float('-inf')
extent = float('inf')

for r in range(50, 50 + N1):
  str_list = input().strip()
  for c in range(50, 50 + M1):
    arr[r][c] = str_list[c-50]

    if arr[r][c] == '0':
      max_c = max(max_c, c)
      max_r = max(max_r, r)

N2, M2 = map(int, input().split())

arr2 = []
for r in range(N2):
  arr2.append(input().strip())
  
def check():
  global extent

  for r in range(50 - N2, max_r):
    for c in range(50 - M2, max_c):

      is_possible = True
      
      for nxt in range(len(arr2)):        
        for mxt in range(len(arr2[0])):
          val = arr[r+nxt][c+mxt]
          if val == '1' and arr2[nxt][mxt] != '0':
            is_possible = False
        
        if not is_possible:
          break
      
      if is_possible:
        width = max(50 + N1, r + N2)- min(r, 50)
        height = max(50 + M1, c + M2) - min(c, 50) 
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
  
  check()

print(extent)