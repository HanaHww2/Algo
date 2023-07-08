import collections
import heapq
import bisect

# 참고한 풀이 https://school.programmers.co.kr/questions/25116
# set을 이용해 풀이한 경우도 있다. (https://school.programmers.co.kr/questions/16101)


def b_search(left, right, arr, target):

    while left <= right:
        mid = (left + right)//2
        if arr[mid] >= target:
            right = mid - 1
        else:
            left = mid + 1
    return len(arr) - right - 1


def solution(info, query):

    answer = []
    table = collections.defaultdict(list)
    query_arr = [q.replace(' and', '').split(" ") for q in query]

    def dfs(depth, key):
        if depth == 4:
            # 점수 정보 bisect 이용해 정렬된 입력⭐⭐⭐
            val = int(info_row[depth])
            bisect.insort(table[key], val)

            # bisect 로 삽입할 위치를 찾아서 해보려고 했지만 실패
            # idx = bisect.bisect_left(table[key], val)
            # table[key] = table[key][:idx]+[val]+table[key][idx:] # 이부분이 비효율적인 거 같다.

            # 힙을 쓰려면, 힙에서 정렬된 배열을 또 새로 생성해야 되며 이 시간복잡도도 고려해야 한다.
            # heapq.heappush(table[key], int(info_row[depth]))

            # 그냥 삽입 후 아래에서 정렬
            # table[key].append(int(info_row[depth]))
        else:
            dfs(depth+1, key + info_row[depth])
            dfs(depth+1, key+'-')

    for i in info:
        info_row = i.split(" ")

        # 한명의 정보 당 2**4 개의 적합 조건 보유
        dfs(0, '')

    # 반복해서 정렬을 수행하지 않도록 미리 수행
    # for key in table:
    #     table[key].sort()

    for q in query_arr:
        key = ''.join(q[:-1])
        # table[key].sort()
        temp = []
        answer.append(b_search(0, len(table[key])-1, table[key], int(q[-1])))

    return answer
