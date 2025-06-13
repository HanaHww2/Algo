# 

import sys
from collections import deque

input = sys.stdin.readline
N = int(input())
K = int(input())

board = [[0] * N for _ in range(N)]

for _ in range(K):
  y, x = map(int, input().split())
  board[y-1][x-1] = 1

L = int(input())
DIR = [0]*(10_000+1) 

def turn_L(y, x):
  return -x, y

def turn_D(y, x):
  return x, -y

for _ in range(L):
  x, c = input().split()
  DIR[int(x)] = c # 시간, 방향

q= deque([(0, 0)])
snail = [(0, 0)]
board[0][0] = 2
time = 0
d = (0, 1)

while True:
  y, x = q[-1]

  time += 1
  dy, dx = d
  ny, nx = y + dy, x + dx

  if ny < 0 or ny >= N or nx < 0 or nx >= N or board[ny][nx] == 2:
    print(time)
    sys.exit()

  if board[ny][nx] != 1:
    ty, tx = q.popleft()
    board[ty][tx] = 0
  board[ny][nx] = 2

  q.append((ny, nx))

  if DIR[time]: 
    D = DIR[time]
    if D == 'D': d = turn_D(*d)
    if D == 'L': d = turn_L(*d)