# https://www.acmicpc.net/problem/20165

import sys

input = sys.stdin.readline

n, m, r = map(int, input().split(" "))

arr = []
results = []
for _ in range(n):
  arr.append(list(map(int, input().split(" "))))
  results.append(["S"]*m)

dir = {"E": (0, 1), "W": (0, -1), "S": (1, 0), "N": (-1, 0)}
cnt = 0

def attack(x, y, d):
  global cnt

  length =  arr[x][y]

  for i in range(length):
    if i != 0:
      x, y = x + dir[d][0], y + dir[d][1]

    if x < 0 or x > n - 1 or y < 0 or y > m - 1:
      return 
      
    if results[x][y] == "S":
      results[x][y] = "F"
      cnt += 1

      new_l = arr[x][y]
      if new_l > length - i:
        attack(x, y, d)
  return

for i in range(2 * r):
  if i % 2 == 0:
    x, y, d =  map(str, input().replace("\n", "").split(" "))
    x, y = int(x), int(y)
    attack(x-1, y-1, d)
  else:
    x, y = map(int, input().replace("\n", "").split(" "))
    results[x-1][y-1] = 'S'  

print(cnt)
for r in results:
  print(" ".join(r))