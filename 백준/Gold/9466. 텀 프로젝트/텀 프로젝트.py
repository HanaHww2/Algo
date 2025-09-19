import sys

sys.setrecursionlimit(10**6)
input = sys.stdin.readline
case = int(input())


class Linked:

    def __init__(self, curr_idx, before):
        self.curr_idx = curr_idx
        self.before = before
        self.cnt = before.cnt + 1 if before else 1

    def change_before(self, before):
        self.before = before


def search(idx, before):
    global table
    global visited
    global total

    if visited[idx]:
        if before:
            total += before.cnt
        table = dict()
        before = None
        return

    visited[idx] = True
    new_idx = arr[idx] - 1
    before = Linked(idx, before)
    table[idx] = before

    if new_idx in table:
        removed = table[new_idx].before
        if removed:
            total += removed.cnt
        table = dict()
        before = None
        return

    search(new_idx, before)


while case > 0:
    case -= 1
    count = int(input())
    arr = list(map(int, input().split()))

    # 싸이클이 형성되지 않는 경우 팀이 되지 못함
    # 싸이클 탐지 필요 - 링크드 리스트?

    cnt = 0
    total = 0
    before = None
    table = dict()
    visited = [False] * count

    for idx in range(count):
        if visited[idx]: continue
        search(idx, None)

    print(total)
