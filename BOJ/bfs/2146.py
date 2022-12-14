# 다리 만들기
# https://www.acmicpc.net/problem/2146

n = 10
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
arr = [list(map(int, x.split(" "))) for x in arr]
###################################################################

import collections, copy
import sys

# n = int(sys.stdin.readline())
# arr = []

# for i in range(n):
#     arr.append(list(map(int, sys.stdin.readline().split(' '))))


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


# bfs 로직!
# 처음엔 플래그를 두고 바다를 건넌 건지 확인해보려 했으나 실패
# 다른 사람들의 풀이를 확인 -> 섬들을 먼저 숫자 등으로 구분 짓고, 바다를 건너는 거리를 계산하는 로직으로 구현
def divide(y, x, island):
    queue = collections.deque()
    outside = collections.deque()
    queue.append((y, x))
    # arr[y][x] = island[-1]
    if near_sea(y, x):
        outside.append((y, x, island[-1]))

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
                arr[ny][nx] = island[-1]
                # visited[ny][nx] = True
                queue.append((ny, nx))
                if near_sea(ny, nx):  # 바다 근처면 체크
                    outside.append((ny, nx, island[-1]))
    return outside


def measure_overseas(src, answer):
    dist = [[0] * n for _ in range(n)]
    queue = collections.deque()

    while src:
        y, x, start = src.popleft()
        if dist[y][x] > answer:
            continue

        # 사방 체크
        for d in range(4):
            ny = y + dy[d]
            nx = x + dx[d]

            if not in_range(ny, nx):
                continue

            # 바다에서 다른 대륙에 도착
            if arr[y][x] == 0 and arr[ny][nx] < 0 and arr[ny][nx] != start:
                answer = min(answer, dist[y][x])

            # 바다면
            if arr[ny][nx] == 0:
                if dist[ny][nx] == 0 or (
                    dist[ny][nx] != 0 and dist[ny][nx] > dist[y][x] + 1
                ):
                    dist[ny][nx] = dist[y][x] + 1

                if dist[ny][nx] < answer:
                    src.append((ny, nx, start))


def search(y, x):
    # print(y, x, cnt)
    global answer
    island = []  # 섬 이름
    name = 0
    outside_l = []

    # 1 찾기
    for i in range(n):
        for j in range(n):
            if arr[i][j] == 1:
                name -= 1
                island.append(name)

                outside_l.append(divide(i, j, island))

    # 바다와 인접한 대륙 탐험
    while outside_l:
        out = outside_l.pop()
        measure_overseas(out, answer)


answer = 200
search(0, 0)
print(answer)
