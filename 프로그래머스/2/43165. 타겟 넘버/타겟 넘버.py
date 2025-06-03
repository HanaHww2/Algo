def solution(numbers, target):
    answer = 0
    def dfs(curr, idx):
        if idx == len(numbers):
            if target == curr:
                return 1
            return 0
        
        return dfs(curr+numbers[idx], idx+1) + dfs(curr-numbers[idx], idx+1)
    
    return dfs(0, 0)