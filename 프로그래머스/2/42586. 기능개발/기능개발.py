import collections, math

def solution(progresses, speeds):
    answer = []
    days = collections.deque([])
    
    for p, s in zip(progresses, speeds):
        
        d = math.ceil((100 - p) / s)
        days.append(d)
        
    while days:
        d = days.popleft()
        cnt = 1
        while days and days[0] <= d:
            days.popleft()
            cnt += 1
            
        answer.append(cnt)
    
    return answer