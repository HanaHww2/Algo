# 다리 만들기
# https://www.acmicpc.net/problem/2146

n = 10
# arr = [
#     "1 1 1 0 0 0 0 0 0 0",
#     "1 0 1 0 0 0 0 0 0 0",
#     "0 0 1 0 0 0 0 0 0 0",
#     "0 0 1 0 0 0 0 0 0 0",
#     "0 0 1 0 0 0 0 0 0 0",
#     "0 0 1 0 0 0 0 1 0 0",
#     "0 0 0 0 0 0 1 1 0 0",
#     "0 0 0 0 1 0 1 0 0 0",
#     "0 0 0 0 1 1 1 0 0 0",
#     "0 0 0 0 0 0 0 0 0 0",
# ]
arr = [
    "1 1 1 0 0 0 0 1 1 1",
    "1 1 1 1 0 0 0 0 1 1",
    "1 0 1 1 0 0 0 0 1 1",
    "0 0 1 1 1 0 0 0 0 1",
    "0 0 0 1 0 0 0 0 0 1",
    "0 0 0 0 0 0 0 0 0 1",
    "0 0 0 0 0 0 0 0 0 0",
    "0 0 0 0 1 1 0 0 0 0",
    "0 0 0 0 1 1 1 0 0 0",
    "0 0 0 0 0 0 0 0 0 0",
]

# n = 5
# arr = ["0 0 0 0 0", "0 1 0 0 0", "0 0 0 0 0", "0 0 1 0 1", "0 0 0 0 0"]
# # arr = ["0 0 0 0 0", "0 0 1 0 0", "0 1 0 0 0", "0 0 0 0 0", "0 0 0 0 0"]
# arr = ["1 0 0 0 1", "0 0 0 0 0", "0 0 0 0 0", "0 0 0 0 0", "0 1 0 0 1"]
# n = 100
# arr = list([0] * 100 for _ in range(100))
# arr[0][0] = arr[99][99] = 1
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


# 주변에 하나라도 바다가 있다면,
def near_sea(y, x):
    for d in range(4):
        ny = y + dy[d]
        nx = x + dx[d]
        if in_range(ny, nx) and arr[ny][nx] == 0:
            return True
    return False


# bfs 로직! (dfs로도 처리 가능)
# 처음엔 플래그를 두고 바다를 건넌 건지 확인해보려 했으나 실패
# 다른 사람들의 풀이를 확인 -> 섬들을 먼저 숫자 등으로 구분 짓고, 바다를 건너는 거리를 계산하는 로직으로 구현
def divide(y, x, name):
    outside = collections.deque()
    queue = collections.deque()

    queue.append((y, x))
    arr[y][x] = name

    while queue:
        y, x = queue.popleft()

        # 사방 체크
        for d in range(4):
            ny = y + dy[d]
            nx = x + dx[d]

            if not in_range(ny, nx):
                continue

            # 1이면 큐에 담는다. 연결된 대륙 찾기
            if arr[ny][nx] == 1:
                queue.append((ny, nx))
                arr[ny][nx] = name
            elif arr[ny][nx] == 0:
                outside.append((y, x, name))  # 바다와 인접

    return outside


def check_bridge(out, answer):
    island_num = 0  # 섬 외곽 정보
    dist = -1
    visited = set()

    while out:
        y, x, dist = out.popleft()

        if dist >= answer:
            return dist

        if dist < 0:
            island_num, dist = dist, 0

        # 사방 체크
        for d in range(4):
            ny = y + dy[d]
            nx = x + dx[d]

            if not in_range(ny, nx) or (ny, nx) in visited:
                continue

            # 바다면
            if arr[ny][nx] == 0:
                visited.add((ny, nx))
                out.append((ny, nx, dist + 1))

            # 바다에서 다른 대륙에 도착
            elif arr[ny][nx] != island_num:
                return dist
    return dist


def search(y, x):
    global answer
    name = 0
    outside_l = collections.deque()

    # 1 찾기
    for i in range(n):
        for j in range(n):
            if arr[i][j] == 1:
                name -= 1
                outside_l.append(divide(i, j, name))

    # 바다와 인접한 대륙 탐험
    while outside_l:
        out = outside_l.popleft()
        if not outside_l:
            break
        temp = check_bridge(out, answer)
        answer = min(temp, answer)


answer = 200
search(0, 0)

print(answer)
