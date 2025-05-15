def solution(n, left, right):

    if n == 0 : return []
    
    # n * n
    # i 행 i 열까지
    # i // n , i % n
    # max(r, c) 
    arr = []
    
    for i in range(left, right+1):
        
        remainder = (i + 1) % n
        arr.append(max(i // n + 1, n if remainder == 0 else remainder))
    
    return arr