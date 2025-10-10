import sys
sys.setrecursionlimit(10**6)

def solution(n, m, x, y, r, c, k):
    
    def is_possible(x, y, k):
        nonlocal r, c
        
        min_d = abs(r-x) + abs(c-y)
        if (k - min_d) % 2 != 0 or k < min_d: 
            return False
        return True
    
    if not is_possible(x, y, k): return "impossible"

    nxt = {"d":(1, 0), "l":(0, -1), "r":(0, 1), "u":(-1, 0)}
    
    def dfs(steps, x, y):
        
        nonlocal k, r, c
        
        if len(steps) == k:
            if x == r and y == c:
                return steps
            return
        
        if not is_possible(x, y, k - len(steps)): return
        
        for (key, val) in nxt.items():
            
            nx = x + val[0]
            ny = y + val[1]
            if nx > 0 and nx <= n and ny > 0 and ny <= m: 
                result = dfs(steps + key, nx, ny)
                if result:
                    return result
    
    answer = dfs("", x, y)
    return answer if answer != None else "impossible"