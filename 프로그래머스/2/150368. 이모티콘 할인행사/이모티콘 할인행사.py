from itertools import product # 중복순열

def solution(users, emoticons):
    
    answer = [0, 0]
    L = len(emoticons)
    
    def check_users(discount):
        plus = 0
        total = 0
        for (dm, pm) in users:
            tp = 0
            flag = False
            for i, d in enumerate(discount):
                if d >= dm:
                    tp += emoticons[i] * (100 - d) / 100
                if tp >= pm:
                    flag = True
                    break
            if flag: plus += 1
            else: total += tp
        return (plus, total)
    
    
    for d in product(range(10, 41, 10), repeat = L):
        result = check_users(d)
        if result[0] > answer[0] or(result[0] == answer[0] and result[1] > answer[1]):
            answer = result
    return answer