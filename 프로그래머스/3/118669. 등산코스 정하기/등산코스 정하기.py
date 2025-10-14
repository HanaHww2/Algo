INF = 10 ** 10+1
import heapq
def solution(n, paths, gates, summits):
    def findpath(q) :
        while q :
            cost,node = heapq.heappop(q)
            if dp[node] < cost :
                continue
            for nextcost,nextnode in graph[node]:
                if dp[nextnode] > max(nextcost,cost):
                    dp[nextnode] = max(nextcost,cost)
                    if nextnode in summits_set :
                        continue
                    heapq.heappush(q,[max(nextcost,cost),nextnode])

    graph = [[] for _ in range(n+1)]
    dp = [INF]*(n+1)
    summits_set = set(summits)
    for a,b,c in paths :
        graph[a].append([c,b])
        graph[b].append([c,a])

    q = []
    for gate in gates :
        dp[gate] = 0
        heapq.heappush(q,[0,gate])

    findpath(q)

    min_cost,target = INF,0

    summits.sort()
    for summit in summits :
        if dp[summit] < min_cost :
            min_cost = dp[summit]
            target= summit

    return [target,min_cost]