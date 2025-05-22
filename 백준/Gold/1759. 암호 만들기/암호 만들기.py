# https://www.acmicpc.net/problem/1759

import sys
input = sys.stdin.readline

L, C = map(int, input().split(" "))

arr = list(a.replace("\n", "") for a in input().split(" "))
arr.sort()

vowel = ['a', 'e', 'i', 'o', 'u']
answer = []
answer_set = set()

def re(prev, length, start, min_v, min_c):

  if length == 0:
    if min_v <= 0 and min_c <= 0:
      if prev not in answer_set:
        answer.append(prev)
        answer_set.add(prev)
    return
  
  for i, a in enumerate(arr[start:]):
    if a in vowel:
      re(prev+a, length - 1, start + i + 1, min_v - 1, min_c)
    else:
      re(prev+a, length - 1, start + i + 1, min_v, min_c - 1)

re("", L, 0, 1, 2)

for a in answer:
  print(a)