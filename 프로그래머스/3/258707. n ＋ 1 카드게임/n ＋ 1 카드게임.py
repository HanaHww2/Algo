def solution(coin, cards):
    answer = 0
    l = len(cards)
    
    table = [0] * (l+1)
    for i, c in enumerate(cards):
        table[c] = i
    
    start = int(l/3)
    pair = 0
    
    for i in range(0, start):
        idx = table[l+1-cards[i]]
        if idx < i:
            pair += 1
    
    temp = []
    for i in range(start, l):
        nxt = cards[i]
        idx = table[l+1 - nxt]    
        
        if idx < start and coin > 0:
            pair += 1
            coin -= 1        
        elif idx < i:
            temp.append((idx, i))
        
        if (i-start) % 2 == 1:
            print(pair)
            pair -= 1
            answer += 1
            
            if pair == -1:                
                if temp and coin >= 2:
                    print(temp, coin)
                    temp.pop()
                    pair += 1
                    coin -= 2
                else:
                    break

        if i == l-1:
            if pair >= 0:
                answer +=1
            
    return answer