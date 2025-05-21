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
  visited = [float("-inf")] * N
  q = list()
  heapq.heappush(q, (0, 0, A))

  while q:
    max_c, total, s = heapq.heappop(q)

    if visited[s] < total:
      continue

    for (next, n_cost) in table[s]:

      n_total = total + n_cost
      if n_total > C:
        continue
      
      if next == B:
        return max(max_c, n_cost)
      
      heapq.heappush(q, (max(max_c, n_cost), total+n_cost, next))
    
  return -1

print(bfs(A, B, C))