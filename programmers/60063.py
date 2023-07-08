import collections


def solution(board):
    answer = 0
    n = len(board)
    MOVE = [
        ((0, -1), (0, -1)),
        ((0, 1), (0, 1)),
        ((-1, 0), (-1, 0)),
        ((1, 0), (1, 0)),
    ]  # 좌,우,위,아래
    H_MOVE = [
        # 1 이면, 주어진 드론의 좌표값의 순서 변경 필요, 정렬
        ((0, 0), (1, -1), 0),
        ((1, 1), (0, 0), 1),
        ((0, 0), (-1, -1), 1),
        ((-1, 1), (0, 0), 0),
    ]
    V_MOVE = [
        ((0, 0), (-1, 1), 0),
        ((1, 1), (0, 0), 1),
        ((0, 0), (-1, -1), 1),
        ((1, -1), (0, 0), 0),
    ]

    visited = set()  # collections.defaultdict(int)

    def possible_move(a, b):
        if 1 <= a[0] <= n and 1 <= a[1] <= n and 1 <= b[0] <= n and 1 <= b[1] <= n:
            if board[a[0] - 1][a[1] - 1] == 0 and board[b[0] - 1][b[1] - 1] == 0:
                return True
        return False

    q = collections.deque([(0, "h", [(1, 1), (1, 2)])])

    while q:
        cnt, state, (a, b) = q.popleft()

        if (a, b) in visited:
            continue
        else:
            visited.add((a, b))

        if b == (n, n):
            return cnt

        if state == "h":
            for da, db in MOVE:

                na = tuple(map(lambda x: x[0] + x[1], zip(a, da)))
                nb = tuple(map(lambda x: x[0] + x[1], zip(b, db)))

                if (na, nb) not in visited and possible_move(na, nb):
                    q.append((cnt + 1, "h", (na, nb)))

            for i, (da, db, s) in enumerate(H_MOVE):

                temp = ((-1, 0), (-1, 0))
                if i < 2:
                    temp = ((1, 0), (1, 0))

                aa = tuple(map(lambda x: x[0] + x[1], zip(a, temp[0])))
                bb = tuple(map(lambda x: x[0] + x[1], zip(b, temp[1])))
                na = tuple(map(lambda x: x[0] + x[1], zip(a, da)))
                nb = tuple(map(lambda x: x[0] + x[1], zip(b, db)))

                if (na, nb) not in visited and possible_move(aa, bb):
                    q.append((cnt + 1, "v", (na, nb) if s == 0 else (nb, na)))
        else:
            for da, db in MOVE:

                na = tuple(map(lambda x: x[0] + x[1], zip(a, da)))
                nb = tuple(map(lambda x: x[0] + x[1], zip(b, db)))

                if (na, nb) not in visited and possible_move(na, nb):
                    q.append((cnt + 1, "v", (na, nb)))

            for i, (da, db, s) in enumerate(V_MOVE):

                temp = ((0, -1), (0, -1))
                if i < 2:
                    temp = ((0, 1), (0, 1))

                aa = tuple(map(lambda x: x[0] + x[1], zip(a, temp[0])))
                bb = tuple(map(lambda x: x[0] + x[1], zip(b, temp[1])))
                na = tuple(map(lambda x: x[0] + x[1], zip(a, da)))
                nb = tuple(map(lambda x: x[0] + x[1], zip(b, db)))

                if (na, nb) not in visited and possible_move(aa, bb):
                    q.append((cnt + 1, "h", (na, nb) if s == 0 else (nb, na)))

    return answer


answer = solution(
    # [
    #     [0, 0, 0, 1, 1],
    #     [0, 0, 0, 1, 0],
    #     [0, 1, 0, 1, 1],
    #     [1, 1, 0, 0, 1],
    #     [0, 0, 0, 0, 0],
    # ]
    [
        [0, 0, 1, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 1, 0, 0, 0, 0],
        [1, 0, 0, 0, 1, 0, 0, 0, 0, 0],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 1, 1, 1, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 1, 0, 0, 0, 0, 0],
        [0, 1, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 1, 1, 0, 1],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    ]
)

print(answer)
