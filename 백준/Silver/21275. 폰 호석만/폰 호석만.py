# https://www.acmicpc.net/problem/21275

import sys, collections

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
a_dict = collections.defaultdict(set)
b_dict = collections.defaultdict(set)

result = set()

MAX = 36

def check_base():

  def dfs(power):

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
      a_dict[a_num].add(power)
    if b_num >= 0:
      b_dict[b_num].add(power)

    if len(b_dict[a_num]) > 0:
      result.add(a_num) 
    if len(a_dict[b_num]) > 0:
      result.add(b_num)

    dfs(power + 1)

  if nums[0] == nums[1]:

    if len(nums[0]) == 1 and nums[0][0] < 35: return "Multiple"
    return 'Impossible'

  dfs(2)
  
  if not result:
    return 'Impossible'

  if len(result) > 1:
    return "Multiple"

  answer = result.pop()

  if len(a_dict[answer]) > 1 or len(b_dict[answer]) > 1:
    return 'Multiple'

  if a_dict[answer] == b_dict[answer]:
    return 'Impossible'

  return " ".join([str(answer), str(a_dict[answer].pop()), str(b_dict[answer].pop())]) 

print(check_base())