from itertools import combinations

def solution(n, q, ans):
    answer = 0
    
    for c in combinations(range(1, n+1), 5):
        flag = True
        sc = set(c)
        
        for i, qi in enumerate(q):
            r = sc & set(qi)
            if ans[i] != len(r):
                flag = False
                break
        
        if flag:
            answer += 1
    
    return answer