def solution(citations):
    citations.sort(reverse=True)
    total = len(citations)
    
    print(citations)
    
    for i in range(total):
        
        if i > citations[i]:
            
            return min(i, citations[i-1])
        
    return total