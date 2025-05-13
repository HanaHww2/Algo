import sys, collections

input = sys.stdin.readline

r, c = map(int, input().split(" "))
arr = []

for i in range(r):
  arr.append(input().strip())

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]
visited = [[0] * c for _ in range(r)]

total_sheep = 0
total_wolf = 0

def search(row, col):

  if visited[row][col] == 1:
    return
  
  global total_sheep
  global total_wolf
  sheep = 0
  wolf = 0

  q = collections.deque()
  q.append((row, col))

  while q:
    row, col = q.popleft()
    
    if visited[row][col]:
      continue
    
    visited[row][col] = 1

    if arr[row][col] == "v":
      wolf += 1
    if arr[row][col] == "o":
      sheep += 1

    for d in range(4):
      ny = row + dy[d]
      nx = col + dx[d]

      if nx < 0 or nx >= c or ny < 0 or ny >= r:
        continue

      if arr[ny][nx] == "#":
        continue

      if visited[ny][nx] != 1:
        q.append((ny, nx))

  if sheep > wolf:
    total_sheep += sheep
  else:
    total_wolf += wolf

for i in range(r):
  for j in range(c):
    if arr[i][j] in '.vo':
      search(i, j)

print(total_sheep, total_wolf)