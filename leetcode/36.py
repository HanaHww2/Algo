import collections


class Solution:
    def isValidSudoku(self, board) -> bool:
        def get_sub(i, j):
            r = i // 3
            c = j // 3
            return r, c

        group = defaultdict(set)
        ROW_STR = "r"
        COL_STR = "c"

        for i in range(9):
            for j in range(9):
                k = board[i][j]

                if k == ".":
                    continue

                k = int(k)

                if k in group[(i, ROW_STR)]:
                    return False
                group[(i, ROW_STR)].add(k)

                if k in group[(j, COL_STR)]:
                    return False
                group[(j, COL_STR)].add(k)

                r, c = get_sub(i, j)

                if k in group[(r, c)]:
                    return False
                group[(r, c)].add(k)

        return True


board = [
    ["8", "3", ".", ".", "7", ".", ".", ".", "."],
    ["6", ".", ".", "1", "9", "5", ".", ".", "."],
    [".", "9", "8", ".", ".", ".", ".", "6", "."],
    ["8", ".", ".", ".", "6", ".", ".", ".", "3"],
    ["4", ".", ".", "8", ".", "3", ".", ".", "1"],
    ["7", ".", ".", ".", "2", ".", ".", ".", "6"],
    [".", "6", ".", ".", ".", ".", "2", "8", "."],
    [".", ".", ".", "4", "1", "9", ".", ".", "5"],
    [".", ".", ".", ".", "8", ".", ".", "7", "9"],
]

sol = Solution()
answer = sol.isValidSudoku(board)
print(answer)
