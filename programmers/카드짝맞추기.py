import collections
import math
import copy
import heapq


def in_range(r, c):
    if -1 < r < 4 and -1 < c < 4:
        return True
    return False


def solution(board, r, c):
    move = [[1, -1, 0, 0], [0, 0, 1, -1]]
    answer = 10e5
    table = collections.defaultdict(list)
    for y, b in enumerate(board):
        for x, n in enumerate(b):
            if n != 0:
                table[n].append((y, x))

    tot = len(table.keys()) * 2

    def check_pair(cur, pair, target, temp_board):
        # for (y, x) in table[target]:
        #     if (y, x) in visited:
        #         continue
        result = 10e5
        new_src = ()
        for i in range(2):
            temp = count_bfs(cur[0], cur[1], pair[i][0],
                             pair[i][1], temp_board)
            temp_board[pair[i][0]][pair[i][1]] = 0
            temp += count_bfs(pair[i][0], pair[i][1],
                              pair[i-1][0], pair[i-1][1], temp_board)
            temp_board[pair[i][0]][pair[i][1]] = target  # 보드 임시 복구

            if temp < result:
                new_src = pair[i]
                result = temp

        temp_board[pair[i][0]][pair[i][1]] = 0
        temp_board[pair[i-1][0]][pair[i-1][1]] = 0
        #print(f'{cur}, {y,x} => {cnt}, {result}, {target}')
        return temp_board, result, new_src

    def count_bfs(r, c, y, x, new_board):
        min_h = [(0, r, c)]
        weight = [[100] * 4 for _ in range(4)]
        weight[r][c] = 0

        while min_h:
            w, r, c = heapq.heappop(min_h)
            if weight[r][c] < w:
                continue  # 갱신 불필요

            # 원스텝 이동 counting
            for i in range(4):
                temp = 0
                nr = r + move[0][i]
                nc = c + move[1][i]
                if not in_range(nr, nc):
                    continue
                temp += 1
                if (w + temp) < weight[nr][nc]:
                    weight[nr][nc] = w + temp
                    heapq.heappush(min_h, (w + temp, nr, nc))

            # ctrl 이동 counting
            for i in range(4):
                temp = 0
                nr, nc = r, c
                while True:
                    nr += move[0][i]
                    nc += move[1][i]
                    if not in_range(nr, nc):
                        break
                    if in_range(nr, nc) and (new_board[nr][nc] != 0 or ((nr == 0 or nr == 3) and (nc == 0 or nc == 3))):
                        temp += 1
                        if (w + temp) < weight[nr][nc]:
                            weight[nr][nc] = w + temp
                            heapq.heappush(min_h, (w + temp, nr, nc))

        return weight[y][x] + 1

    def dfs(cur, target, cards, visited, cnt, new_board):

        nonlocal answer
        if cnt > answer:
            return

        cards = list(cards)
        i = 0
        # 최초 시작 카드 정하기
        while (len(cards) == 0 and target != 0) or i < len(cards):
            print(cards)
            # 최초 시작이며, 카드 위에서 시작인 경우
            if target != 0 and cur == (r, c):
                for (y, x) in table[target]:
                    if (y, x) in visited:
                        continue
                    cnt += count_bfs(cur[0], cur[1], y, x, new_board)
                    visited = visited | {(y, x)}
                    new_board[y][x] = 0
                    cur = (y, x)

            if target == 0:
                # 목표 정하기, 6 개 중에 하나
                target = cards[i]
                i += 1

            temp_board, result, new_src = check_pair(
                cur, table[target], target, copy.deepcopy(new_board))

            dfs(new_src, 0, set(cards) - {target}, visited |
                set(table[target]), cnt + result, temp_board)
            print(
                f'{i}, {cards}, {cur}, {visited |set(table[target])}, {set(table[target])} => {cnt}, {result}, {target}')
            target = 0  # 타겟 초기화

        if len(visited) == tot:
            answer = min(answer, cnt)
            print(target, cnt, cur, visited)
    cnt = 0
    target = 0
    if board[r][c] == 0:
        visited = set()
    else:
        target, board[r][c] = board[r][c], 0
        visited = {(r, c)}
        cnt = 1

    dfs((r, c), target, set(table.keys()).copy() -
        {board[r][c]}, visited, cnt, board)
    print(answer)
    return answer


solution([[3, 0, 0, 2], [0, 0, 1, 0], [0, 1, 0, 0], [2, 0, 0, 3]], 0, 1)
