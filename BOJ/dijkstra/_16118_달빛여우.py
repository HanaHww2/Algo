# https://www.acmicpc.net/problem/16118
# 달빛여우
# 아래 풀이는 시간초과 발생... 개선을 해보려했으나 안됨.
import sys, collections, heapq

input = sys.stdin.readline

n, m = map(int, input().split())

table = collections.defaultdict(list)
for _ in range(m):
    f, t, w = map(int, input().split())
    table[f].append((w * 2, t))
    table[t].append((w * 2, f))


def dijkstra_w(start):
    dist = [float("inf")] * (n + 1)

    dist[start] = 0
    q = [(0, start, 0)]

    while q:
        w, f, cnt = heapq.heappop(q)

        if w < dist[f]:  # 여기 수정 필요
            dist[f] = w

        for des in table[f]:
            nw, nd = des

            if dist[nd] != float("inf"):
                continue

            if cnt % 2 == 0:  # 2배 속도
                nw = nw * 1 / 2
            elif cnt % 2 != 0:  # 1/2배 속도
                nw = nw * 2
            if nw + w < dist[nd]:
                heapq.heappush(q, (nw + w, nd, cnt + 1))

    return dist


def dijkstra_f(start):
    global answer
    dist = [float("inf")] * (n + 1)

    dist[start] = 0
    q = [(0, start)]

    while q:
        w, f = heapq.heappop(q)

        if w > dist[f]:
            continue

        for des in table[f]:
            nw, nd = des

            if nw + w < dist[nd]:
                dist[nd] = nw + w
                if dist1[nd] > dist[nd]:
                    answer.add(nd)
                heapq.heappush(q, (nw + w, nd))

    return dist


answer = set()

dist1 = dijkstra_w(1)
dist2 = dijkstra_f(1)

# answer = 0
# for i in range(1, n + 1):
#     if dist1[i] > dist2[i]:
#         answer += 1
# print(answer)
print(len(answer))
