# https://www.acmicpc.net/problem/3687
# 성냥개비
#
# 최댓값의 경우
# 1(2), 7(3) 을 활용한다.
# 짝수인 경우 111... 나열
# 홀수인 경우 7111...나열
#
# 최솟값의 경우
# 8(7), 10(2 + 6), 18(2+7), 22(5+5), 20(5+6), 28(5+7), 68(6+7), 
# 88(7+7), 108(2 + 6 + 7), 188(2+7+7), 200(5+6+6), 208(5+6+7), 288(5+7+7), 688(6+7+7) 을 활용
#
import sys

table = [6, 2, 5, 5, 4, 5, 6, 3, 7, 6]
min_table = [0, 0, 1, 7, 4, 2, 6, 8, 
             10, 18, 22, 20, 28, 68]

input = sys.stdin.readline
n = int(input())

def get_max(cnt):
    val, remainder = divmod(cnt, 2)
    if not remainder:
        return int('1'*val)
    return int('7' + '1'*(val-1))
    
def get_min(cnt):
    val, remainder = divmod(cnt, 7)
    if not val:
        return min_table[cnt]
    if remainder == 3:
        if val < 2:
            return min_table[cnt]
        if val >= 2:
            return int('200' + '8'*(val-2))
    if not remainder:
        return int('8'*val)
    return int(str(min_table[7 + remainder])+'8'*(val-1))


answer = []
for i in range(n):
    k = int(input())
    min = get_min(k)
    max = get_max(k)
    answer.append((min, max))

for min, max in answer:
    print(min, max)