# https://www.acmicpc.net/problem/6593

import sys
from collections import deque

input = sys.stdin.readline

dir = [(1, 0, 0), (-1, 0, 0), (0, 1, 0), (0, -1, 0), (0, 0, 1), (0, 0, -1)]

def search(l, r, c, t):

  q = deque([(l, r, c, t)])
  while q:

    (l, r, c, t) = q.popleft()

    for (dz, dy, dx) in dir:
      nl = l + dz
      nr = r + dy
      nc = c + dx
    
      if 0 <= nl < L and 0 <= nr < R and 0 <= nc < C and arr[nl][nr][nc] != "#":
        if arr[nl][nr][nc] == 'E':
          return f"Escaped in {t+1} minute(s)."
        arr[nl][nr][nc] = '#'
        q.append((nl, nr, nc, t+1))

  return "Trapped!"
      

while True:
  
  L, R, C = map(int, input().split())

  if L == R == C == 0: break

  arr = list()
  for _ in range(L):
    temp = list()
    for _ in range(R):
      word = list()
      for w in input().strip():
          word.append(w)
      temp.append(word)
    arr.append(temp)
    input()

  for i in range(L):
    for j in range(R):
      for k in range(C):
        if arr[i][j][k] == 'S':
          arr[i][j][k] = "#"
          print(search(i, j, k, 0))