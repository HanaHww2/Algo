from collections import Counter
from itertools import combinations


def solution(dice):
    
    answer = []
    l = len(dice)
    candi = range(l)
    
    def get_result(comb: tuple):
        
        def dfs(case, idx, summ, player):

            if len(case) <= idx:
                if player=='a':
                    ac[summ] += 1
                else:
                    bc[summ] += 1
                return

            for c in dice[case[idx]]:
                dfs(case, idx + 1, summ + c, player)

        remainder = set(candi) - set(comb)
    
        ac = Counter()
        bc = Counter()
        dfs(comb, 0, 0, 'a')
        dfs(list(remainder), 0, 0, 'b')

        b_max = max(bc.keys())
        for i in range(1, b_max + 1):
            bc[i] += bc[i-1]   
        
        t_win = 0
        a_total = 0
        for k in ac.keys():
            t_win += bc[min(k-1, b_max)]* ac[k]
            a_total += ac[k]
        
        return (t_win / (a_total * bc[b_max])) * 100
    
    max_r = 0
    for comb in combinations(candi, l//2):
        
        r = get_result(comb)
        if max_r < r:
            max_r = r
            answer = [c+1 for c in comb]
            
    return answer