# https://www.acmicpc.net/problem/15900

import sys
from collections import deque 

input = sys.stdin.readline

N = int(input().strip())
tree = [[] for _ in range(N+1)]

for _ in range(N-1):
  a, b = map(int, input().split())
  
  tree[a].append(b)
  tree[b].append(a)

depth = 0

def make_depth(start, d):
  global depth
  visited = [0] * (N + 1)
  q = deque([(start, d)])

  while q:
    s, d = q.popleft()
    visited[s] = 1
          
    if s != 1 and len(tree[s]) == 1:
      depth += d
      continue

    for c in tree[s]:

      if visited[c] != 1:
        q.append((c, d + 1))  

make_depth(1, 0)

if depth % 2 == 0:
  print('No')
else: print('Yes')