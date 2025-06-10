def solution(order):
    
    helper_c = []
    box = 1
    i = 0
    while box < len(order) + 1:
        helper_c.append(box)
        box += 1
          
        while helper_c[-1] == order[i]:
            helper_c.pop()
            i += 1
            if not helper_c: break
    
    return i