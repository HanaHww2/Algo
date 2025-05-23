# https://www.acmicpc.net/problem/21275

import sys

input = sys.stdin.readline
strs = input().split()
nums = []
max_nums = [0, 0]

for i in range(2):
  temps = []
  for j, x in enumerate(strs[i]):
    temp = int(strs[i][j]) if strs[i][j].isdigit() else ord(strs[i][j]) - 87
    temps.append(temp)
    max_nums[i] = max(temp, max_nums[i])

  nums.append(temps) 

a_len = len(nums[0])
b_len = len(nums[1])
a_dict = dict()
b_dict = dict()

cnt = 0
result = set()

MAX = 36

def check_base():

  global cnt

  def dfs(power):

    global cnt

    if power > 36:
      return

    a_num = -1
    b_num = -1
    idx = 0 
    
    while idx < max(a_len, b_len):

      if max_nums[0] < power and idx < a_len:
        if a_num == -1 : a_num += 1
        a_num += nums[0][a_len - idx - 1] * (power ** idx)
       
      if max_nums[1] < power and idx < b_len:
        if b_num == -1 : b_num += 1
        b_num += nums[1][b_len - idx - 1] * (power ** idx)

      idx += 1

    if a_num >= 0:
      a_dict[a_num] = power
    if b_num >= 0:
      b_dict[b_num] = power

    if a_num in b_dict:
      result.add(a_num)
      cnt += 1   
    if b_num in a_dict:
      result.add(b_num)  
      cnt += 1

    dfs(power + 1)

  dfs(2)

  if cnt > 1:
    return "Multiple"
  
  if not result:
    return 'Impossible'

  answer = result.pop()

  if a_dict[answer] == b_dict[answer]:
    return 'Impossible'

  return " ".join([str(answer), str(a_dict[answer]), str(b_dict[answer])]) 

print(check_base())