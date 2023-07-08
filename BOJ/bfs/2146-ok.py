# 다리 만들기
# https://www.acmicpc.net/problem/2146

n = 10
arr = [
    "1 1 1 0 0 0 0 1 1 1",
    "1 1 1 1 0 0 0 0 1 1",
    "1 0 0 1 0 0 0 0 1 1",
    "0 0 0 1 0 0 0 0 0 1",
    "0 0 0 1 0 0 0 0 0 1",
    "0 0 0 1 0 0 0 0 0 1",
    "0 0 0 1 0 0 0 0 0 0",
    "0 0 0 0 0 0 1 0 0 0",
    "0 0 0 0 0 0 1 1 1 1",
    "0 0 0 0 0 0 0 0 0 0",
]

arr = [list(map(int, x.split(" "))) for x in arr]
###################################################################

import collections, copy
import sys

# n = int(sys.stdin.readline())
# arr = []

# for i in range(n):
#     arr.append(list(map(int, sys.stdin.readline().split(" "))))

dy = [1, -1, 0, 0]
dx = [0, 0, 1, -1]


def in_range(y, x):
    if -1 < y < n and -1 < x < n:
        return True
    return False


def near_sea(y, x):
    for d in range(4):
        ny = y + dy[d]
        nx = x + dx[d]
        if in_range(ny, nx) and arr[ny][nx] == 0:
            return True
    return False


def divide(y, x, name):
    visited = [[0] * n for _ in range(n)]
    checked = set()
    queue = collections.deque([(y, x)])
    arr[y][x] = name

    while queue:
        y, x = queue.popleft()

        # 사방 체크
        for d in range(4):
            ny = y + dy[d]
            nx = x + dx[d]

            if not in_range(ny, nx) or visited[ny][nx] == 1:
                continue

            if arr[ny][nx] == 1:  # 1이면 큐에 담는다. 연결된 대륙 찾기
                queue.append((ny, nx))
                arr[ny][nx] = name
                visited[ny][nx] = 1

            elif arr[ny][nx] == 0 and (y, x) not in checked:
                checked.add((y, x))
                outside[name].append((y, x, 0))  # 바다와 인접


def check_bridge(i, answer):
    visited = set()
    out = outside[i]
    dist = 200

    while out:
        y, x, dist = out.popleft()
        if dist >= answer:
            return dist

        # 사방 체크
        for d in range(4):
            ny = y + dy[d]
            nx = x + dx[d]

            if not in_range(ny, nx) or (ny, nx) in visited or arr[ny][nx] == i:
                continue

            # 바다면
            if arr[ny][nx] == 0 and not (ny, nx) in visited:
                visited.add((ny, nx))
                out.append((ny, nx, dist + 1))

            # 바다에서 다른 대륙에 도착
            elif arr[ny][nx] != i:
                return dist


def search():
    global answer
    name = 0

    # 1 찾기
    for i in range(n):
        for j in range(n):
            if arr[i][j] == 1:
                name -= 1
                divide(i, j, name)

    # 바다와 인접한 대륙 탐험
    for i in range(-1, -len(outside), -1):
        answer = min(check_bridge(i, answer), answer)


outside = collections.defaultdict(collections.deque)

answer = 200
search()
print(answer)
