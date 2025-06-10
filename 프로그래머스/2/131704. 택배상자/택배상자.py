def solution(order):
    
    helper_c = []
    box = 1
    i = 0
    print(i)
    while i < len(order):
        
        if order[i] == box:
            box += 1
            i += 1
            continue
            
        if helper_c and helper_c[-1] == order[i]:
            helper_c.pop()
            i += 1
            continue
        
        if helper_c and helper_c[-1] > order[i]:
            break
        
        helper_c.append(box)
        box += 1
    
    return i