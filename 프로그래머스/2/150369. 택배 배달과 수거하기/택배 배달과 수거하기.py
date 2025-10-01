def solution(cap, n, deliveries, pickups):
    pd = dd = n
    
    while deliveries and not deliveries[-1]:
        deliveries.pop()
        dd -= 1
    while pickups and not pickups[-1]:
        pickups.pop()
        pd -= 1
    answer = 0
    while deliveries or pickups:
        answer += max(dd, pd) * 2

        dc = pc = cap
        while deliveries:
            if deliveries[-1] <= dc:
                dc -= deliveries.pop()
                dd -= 1
            else:
                deliveries[-1] -= dc
                break
        while pickups:
            if pickups[-1] <= pc:
                pc -= pickups.pop()
                pd -= 1
            else:
                pickups[-1] -= pc
                break

    return answer