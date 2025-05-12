def solution(elements):
    
    res = set()
    length = len(elements)
    
    for i in range(length):
        summ = elements[i]
        res.add(summ)
        
        for j in range(i+1, length+i):
            summ += elements[j % length]
            res.add(summ)
        
    return len(res)