def solution(cap, n, deliveries, pickups):
    
    answer = 0
    dist = n
    pd = dist - 1
    dd = dist - 1    
    while dist > 0:
        answer += dist * 2
        
        pc = cap
        dc = cap
        flagP = True
        flagD = True
        while flagD or flagP:
            
            if flagP and pd > -1:
                if pickups[pd] > pc:
                    pickups[pd] -= pc
                    pc = 0
                    flagP = False
                else:
                    pc -= pickups[pd]
                    pickups[pd] = 0
                    pd -= 1
                    flagP = True
            else:
                flagP = False
            if flagD and dd > -1:
                if deliveries[dd] > dc:
                    deliveries[dd] -= dc
                    dc = 0
                    flagD = False
                else:
                    dc -= deliveries[dd]
                    deliveries[dd] = 0
                    dd -= 1
                    flagD = True
            else:
                flagD = False    
            
            if not flagP and not flagD:
                dist = max(pd, dd) + 1
                if answer == n*2 and dist == 0 and dc == cap and pc == cap:
                    return 0
        
    return answer