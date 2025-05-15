def solution(n, left, right):

    if n == 0 : return []
    
    # n * n
    # i 행 i 열까지
    # i // n , i % n
    # max(r, c) 
    arr = []
    
    for i in range(left, right+1):
        
        quotient = i // n + 1
        remainder = i % n + 1
        arr.append(max(quotient, remainder))
    
    return arr