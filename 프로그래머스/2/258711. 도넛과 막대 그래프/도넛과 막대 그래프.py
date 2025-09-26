import sys
from collections import defaultdict, Counter

sys.setrecursionlimit(10**6)

def solution(edges):
    answer = []
    
    graph = defaultdict(list)
    candidate = set()
    has_in = set()
    
    for e in edges:
        graph[e[0]].append(e[1])
        
        has_in.add(e[1])
        
        if e[0] not in has_in:
            candidate.add(e[0])
        if e[1] in candidate:
            candidate.remove(e[1])
        
    if not candidate:
        return [0, 0, 0, 0]  
    
    addition = max(candidate, key=lambda i: len(graph[i]))
    answer.append(addition)
        
    def search(node:int, start:int):
        kind = 0
        for nxt in graph[node]:
            if len(graph[node]) > 1:
                return 2 # 8자
            if nxt == start:
                return 1 # 도넛
            kind = max(kind, search(nxt, start))
        return kind
    
        
    couter = Counter()
    for nxt in graph[addition]:
        couter[search(nxt, nxt)] += 1
        
    for i in [1, 0, 2]:
        answer.append(couter[i])
    
    return answer