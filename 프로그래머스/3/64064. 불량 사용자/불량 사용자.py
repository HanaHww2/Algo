from collections import defaultdict 

def solution(user_id, banned_id):
    answer = 0
    L = len(banned_id)
    
    table = defaultdict(list)
    for k, b in enumerate(banned_id):
        for i, u in enumerate(user_id):
            len_b = len(b)
            if len_b != len(u): continue
            flag = True
            for j in range(len_b):
                if b[j] != '*' and b[j] != u[j]:
                    flag = False
                    break
            if flag: table[k].append(i)

    result_set = set()
    def dfs(st, b_prev):
        nonlocal answer
        
        if st == L:
            if b_prev in result_set: return
            result_set.add(b_prev)
            answer += 1

        for t in table[st]:
            if b_prev & 1 << t: continue
            
            b_prev |= 1 << t
            dfs(st + 1, b_prev)
            b_prev ^= 1 << t
        
    dfs(0, 0)

    return answer if table else 0