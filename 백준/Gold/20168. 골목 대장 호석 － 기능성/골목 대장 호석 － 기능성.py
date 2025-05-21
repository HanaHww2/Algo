# https://www.acmicpc.net/problem/20168

import sys, collections, heapq

input = sys.stdin.readline

N, M, A, B, C = map(int, input().split(" "))

table = collections.defaultdict(list)
for _ in range(M):
  s, e, cost = map(int, input().split(" "))
  table[s].append((e, cost))
  table[e].append((s, cost))

def bfs(A:int, B:int, C:int):

  global result
  visited = [[False] * N for _ in range(N)]
  q = list()
  heapq.heappush(q, (0, 0, A))

  while q:
    max_c, total, s = heapq.heappop(q)

    for (next, n_cost) in table[s]:

      n_total = total + n_cost
      if n_total > C or visited[s-1][next-1]:
        continue
      
      visited[s-1][next-1] = True
      if next == B:
        result = min(result, max(max_c, n_cost))
        continue
      heapq.heappush(q, (max(max_c, n_cost), total+n_cost, next))
    
  return -1

result = float("inf")
bfs(A, B, C)
print(result if result < float("inf") else -1)