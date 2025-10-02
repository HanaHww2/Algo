import sys, math

sys.setrecursionlimit(10**6)

def solution(numbers):
    answer = []
    
    def dfs(n, start, end, depth):
        
        half = (end - start) // 2 
        mid = start + half

        if depth == 1 and not n & 1 << mid:
            return 0
        
        # 리프 노드인 경우
        if half <= 1:
            if (n & 1 << start or n & 1 << end) and not n & 1 << mid:
                return 0
            else:
                return 1
        else:
            if not dfs(n, start, mid - 1, depth + 1) or not dfs(n, mid + 1, end, depth + 1):
                return 0
            else:
                left = mid - (half // 2 + 1 if half > 1 else half)
                right = mid + (half // 2 + 1 if half > 1 else half)
        
                l = n & 1 << left
                r = n & 1 << right
                h = n & 1 << mid
                
                if (l or r) and not h:
                    return 0
                else:
                    return 1
    
    for n in numbers:
        if n == 0:
            answer.append(0)
            continue
            
            
        # 홀수 인덱스는 자신의 자식이 존재하는 경우 1이어야 함
        # start, left, mid, rigth, end
        # if 자식 모두 0 이면 자신도 0 가능
        # 루트는 존재해야 함.
        # 분할 정복
        # 포화 이진 트리의 크기는 2 ** n - 1
        
        max_i = int(math.log2(n))

        if not math.log2(max_i + 2).is_integer():
            max_i = 2 ** (int(math.log2(max_i + 2)) + 1) - 2
            
        flag = dfs(n, 0, max_i, 1)
        
        answer.append(flag)
    return answer