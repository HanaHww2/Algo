def solution(want, number, discount):
    
    w_dict = {}
    
    for i, w in enumerate(want):
        w_dict[w] = number[i]    

    
    idx = 0
    cnt = 0
    
    while idx <= len(discount):
        idx += 1
        
        if idx > 10:
            
            if sum([0 if w < 0 else w for w in w_dict.values()]) == 0:
                cnt += 1 
            
            if idx > len(discount): break
            
            removed = discount[idx - 11]
            if removed in w_dict:
                w_dict[removed] += 1
            
        prod = discount[idx-1]
        
        if prod in w_dict:
            w_dict[prod] -= 1
    
    return cnt