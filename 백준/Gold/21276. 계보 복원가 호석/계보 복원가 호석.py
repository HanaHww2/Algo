# 

import sys, collections

input = sys.stdin.readline

N = int(input().strip())
arr = sorted(input().split())

numbering = {}
for i, a in enumerate(arr):
  numbering[a] = i

M = int(input().strip())

graph = [[] for _ in range(N)]
indegree = [0] * (N)

for _ in range(M):
  # y에는 부모, 부모의 조상이 포함됨
  x, y = input().split()
  graph[numbering[y]].append(numbering[x])
  indegree[numbering[x]] += 1 # 자식 노드 depth

def topology_sort(start):
  q = collections.deque([start])
  _indegree = indegree[:]
  while q:
    idx = q.popleft()

    for c in graph[idx]:
      _indegree[c] -= 1

      if _indegree[c] == 0:
        q.append(c)
        children[idx].append(c)

children = [[] for _ in range(N)]
cnt = 0
root = []

for i in range(N):
  if indegree[i] == 0:
    topology_sort(i)
    cnt += 1
    root.append(i)

print(cnt)
print(" ".join([arr[r] for r in root]))

for i in range(N):
  if len(children[i]) > 0:
    result = [arr[i], str(len(children[i]))]
    result.extend([arr[i] for i in sorted(children[i])])
    print(" ".join(result))
  else:
    print(" ".join([arr[i], "0"]))