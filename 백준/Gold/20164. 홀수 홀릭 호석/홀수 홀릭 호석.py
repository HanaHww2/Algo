import sys

input = sys.stdin.readline()
input = str(input).replace("\n", "")

counts = []

def sum_three_partition(nums):

  sums = []
  l = len(nums)
  for i in range(1, l):
    for j in range(2, l):
      if i >= j:
        continue
      sums.append(int(nums[:i]) + int(nums[i:j]) + int(nums[j:]))

  return sums   

def dfs(nums, count):
  
  for n in nums:
    if int(n) % 2 == 1:
      count += 1
  
  if len(nums) == 1:
    counts.append(count)
    return

  if len(nums) == 2:
    sum = int(nums[0]) + int(nums[1])
    dfs(str(sum), count)

  if len(nums) > 2:
    
    cases = sum_three_partition(nums)
    for sum in cases:
      dfs(str(sum), count)
  
dfs(input, 0)

print(min(counts), max(counts))