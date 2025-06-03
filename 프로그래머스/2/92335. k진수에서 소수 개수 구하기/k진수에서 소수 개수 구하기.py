def solution(n, k):
    answer = 0
    
    def arithmetic_convert(n, k, prev):
        if n == 0: return "".join(prev[::-1])
    
        prev.append(str(n % k))
        return arithmetic_convert(n // k, k, prev)
        
    def if_prime(n):
        if n == 1: return False
        for i in range(2, int(n**(1/2)) + 1):
            if n % i == 0:
                return False
        return True
    
    str_n = arithmetic_convert(n, k, [])
    start = end = 0
    while end < len(str_n):
        if str_n[start] == '0':
            start += 1
            end += 1
            continue
        if end+1 == len(str_n) or str_n[end+1] == '0':
            if if_prime(int(str_n[start:end+1])):
                answer += 1
            start = end = end + 2
        else:
            end += 1

    return answer