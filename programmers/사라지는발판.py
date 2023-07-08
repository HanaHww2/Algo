# 해설을 보고 풀었다. 완전 탐색을 수행한다.
# 플레이어의 승패를 어떻게 판단하는지가 중요한 로직인 것 같다.

def remove_floor(board, loc):
    if board[loc[0]][loc[1]] == 0:
        return False
    board[loc[0]][loc[1]] = 0
    return board


def move_in_four(loc):
    # 동서남북 이동
    return [[loc[0], loc[1]+1], [loc[0], loc[1]-1], [loc[0]-1, loc[1]], [loc[0]+1, loc[1]]]


def play(cur_type, board, loc, n_loc, cnt):

    if cur_type == "A":
        next_type = "B"
    else:
        next_type = "A"

    # 동서남북 이동 후 play 호출
    locs = move_in_four(loc)
    # 현재 플레이어가 위치한 블록 삭제
    board = remove_floor(board, loc)
    # 삭제할 블록이 없다면?
    if not board:
        return [1, cnt]  # 상대의 승 전달

    result = []
    for new in locs:
        # 이동 가능
        if (-1 < new[0] < len(board)) and (-1 < new[1] < len(board[0])) and board[new[0]][new[1]] != 0:
            # 이동 가능하며 상대가 현재 같은 블록 위에 있다면, 승리
            if loc == n_loc:
                result.append([1, cnt+1])
            # 추가 탐색
            else:
                result.append(play(next_type, board, n_loc, new, cnt+1))

        # 이동이 불가하다면 패배
        else:
            result.append([-1, cnt])

    # if cur_type=="A":
    #     # A가 이기는 경우
    print(result)
    if 1 > 0:
        return [-1, min(result)]  # 상대의 패배 전달
    else:
        return [1, max(result)]  # 상대의 승 전달

    # B의 경우 고려해줘야 함


def solution(board, aloc, bloc):
    answer = -1
    rows = len(board)
    cols = len(board[0])

    # 홀수번 이동이면, A의 승,,,,,,,
    result = play("A", board, aloc, bloc, 0)
    print(result)

    return result[1]


solution([[1, 1, 1], [1, 1, 1], [1, 1, 1]], [1, 0], [1, 2])
