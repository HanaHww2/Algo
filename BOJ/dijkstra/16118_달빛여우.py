# https://www.acmicpc.net/problem/16118
# 달빛여우
#
# dp 테이블을 두고, 빠른 속도로 정점에 도달한 경우인지, 느린 속도로 도달한 경우인지 확인해줘야 함
import sys, collections, heapq

input = sys.stdin.readline

n, m = map(int, input().split())

table = collections.defaultdict(list)
for _ in range(m):
    f, t, w = map(int, input().split())
    table[f].append((w * 2, t))
    table[t].append((w * 2, f))


def dijkstra_w(start):
    dist = [[float("inf")] * (n + 1) for _ in range(2)]

    # 빠르게 온 경우
    # dist[0][start] = 0 ## 주의 첫시작은 느리게 온 케이스부터
    # 느리게 온 경우
    dist[1][start] = 0

    q = [(0, start, True)]

    while q:
        w, f, faster = heapq.heappop(q)

        # faster를 미래 시점의 속도로 설정
        # 미래 faster이면 현 시점은 느리게 접근한 경우가 된다.
        if (faster and w > dist[1][f]) or (not faster and w > dist[0][f]):
            continue

        for des in table[f]:
            nw, nd = des

            if faster:  # 2배 속도
                nw = nw * 1 / 2
                if nw + w < dist[0][nd]:
                    dist[0][nd] = nw + w
                    heapq.heappush(q, (nw + w, nd, not faster))
            else:  # 1/2배 속도
                nw = nw * 2
                if nw + w < dist[1][nd]:
                    dist[1][nd] = nw + w
                    heapq.heappush(q, (nw + w, nd, not faster))

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
                # if min(dist1[0][nd], dist1[1][nd]) > dist[nd]:  # 늑대의 이동 시간과 비교 수행
                #     answer.add(nd)
                heapq.heappush(q, (nw + w, nd))

    return dist


# set()을 활용해서 여우의 다익스트라 실행시
# 정답 카운팅을 시도해보려 했는데
# 오히려 시간 초과가 발생한다.
# 다익스트라 중 시행을 하면서, 불필요한 연산이 늘어나는 듯.
# answer = set()

dist1 = dijkstra_w(1)
dist2 = dijkstra_f(1)

# print(len(answer))

answer = 0
for i in range(1, n + 1):
    if min(dist1[1][i], dist1[0][i]) > dist2[i]:
        answer += 1
print(answer)
