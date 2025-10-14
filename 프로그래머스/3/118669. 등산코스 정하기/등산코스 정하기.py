from collections import defaultdict, deque
import heapq

def solution(n, paths, gates, summits):
    s_summits = set(summits)
    s_gates = set(gates)
    
    INF = 10**8
    graph = defaultdict(list)
    
    for (i, j, w) in paths:
        graph[i].append((j, w))
        graph[j].append((i, w))
        
    answer = [INF, INF]
    
    def bfs(gate):
        nonlocal answer
        
        pq = [(0, gate)]
        visited = set()

        while pq:
            (w, fr) = heapq.heappop(pq)
            visited.add(fr)

            if answer[1] < w:
                 return
                    
            if fr in s_summits:
                if w < answer[1]:
                    answer = (fr, w)
                elif w == answer[1] and fr < answer[0]:
                    answer = (fr, w)
                return
            
            for (nxt, nw) in graph[fr]:
                if nxt in s_gates or nxt in visited:
                     continue
                        
                mnw = max(nw, w)
                if answer[1] < mnw:
                    continue
                    
                heapq.heappush(pq, (mnw, nxt))
             

    for g in gates:
        bfs(g)

        
    return answer