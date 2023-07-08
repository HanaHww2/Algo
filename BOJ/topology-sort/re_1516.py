# 게임개발
# https://www.acmicpc.net/problem/1516

import sys, collections

N = int(input())

graph = collections.defaultdict(list)
# pre_table = collections.defaultdict(list)
indegree = [0] * (N + 1)
time = [0] * (N + 1)

for idx in range(1, N + 1):
    temp = list(map(int, input().split()))
    time[idx] = temp[0]

    i = 1
    while temp[i] != -1:
        graph[temp[i]].append(idx)
        indegree[idx] += 1
        i += 1

def topology_sort():
    result = [0] * (N + 1)

    q = collections.deque()

    for i in range(1, N + 1):
        if indegree[i] == 0:
            q.append(i)

    while q:
        curr = q.popleft()
        result[curr] += time[curr]
        for n in graph[curr]:
            # result[n] += result[curr]
            result[n] = max(result[n], result[curr]) # 여기서 살짝 디피... 틀렸음
            indegree[n] -= 1
            if indegree[n] == 0:
                q.append(n)
    return result

result = topology_sort()

for i in range(1, N+1):
    print(result[i])