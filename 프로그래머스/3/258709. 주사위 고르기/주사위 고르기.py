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

        t_win = 0
        t_lose = 0
        t_tie = 0
        for k in ac.keys():
            win = 0
            lose = 0
            tie = 0
            for x in bc.keys():
                if x < k:
                    win += bc[x]
                elif x == k:
                    tie += bc[x]
                else:
                    lose += bc[x]
            t_win += win * ac[k]
            t_lose += lose * ac[k]
            t_tie += tie * ac[k]
        
        return (t_win / (t_win + t_lose + t_tie)) * 100
    
    max_r = 0
    for comb in combinations(candi, l//2):
        
        r = get_result(comb)
        if max_r < r:
            max_r = r
            answer = [c+1 for c in comb]
            
    return answer