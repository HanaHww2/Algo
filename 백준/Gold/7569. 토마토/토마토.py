# https://www.acmicpc.net/problem/7569

import sys, collections

input = sys.stdin.readline

# 가로(열), 세로(행), 높이
N, M, H = map(int, input().split(" "))

arr = []
subArr = []

for i in range(M * H):
  if i % M == 0 and subArr:
    arr.append(subArr[:])
    subArr = []

  subArr.append(list(map(int, input().split(" "))))

arr.append(subArr[:])

dh = [0, 0, 0, 0, 1, -1]
dr = [1, -1, 0, 0, 0, 0]
dc = [0, 0, 1, -1, 0, 0]

def check_days():

  while q:
    (h, r, c) = q.popleft()
    days = arr[h][r][c]

    for i in range(6):
      nh, nr, nc = h + dh[i], r + dr[i], c + dc[i]
      
      if nh < 0 or nh > H -1 or nr < 0 or nr > M -1 or nc < 0 or nc > N -1:
        continue 

      if arr[nh][nr][nc] == 0 or arr[nh][nr][nc] > days + 1:
        arr[nh][nr][nc] = days + 1

        q.append((nh, nr, nc))

q = collections.deque()

for i in range(H):
  for j in range(M):
    for k in range(N):

      if arr[i][j][k] == 1:
        q.append((i, j, k))

check_days()

result = 0
flag = False

for i in range(H):
  for j in range(M):
    for k in range(N):
      if arr[i][j][k] == 0:
        print(-1)
        sys.exit()
      result = max(result, arr[i][j][k])
    
print(result - 1 if not flag else -1)