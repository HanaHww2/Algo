def solution(queue1, queue2):

    length = len(queue1)*2
    half = (sum(queue1)+sum(queue2))/2

    linked = queue1 + queue2
    left, right = 0, length - 1
    mid = (left+right) // 2

    def search_l(left, right, mid, cnt):
        if mid < 0 or left > length-1:
            return -1

        ls = sum(linked[left:mid])
        rs = sum(linked[mid:]+linked[:right % length+1])

        if ls == half:
            return cnt
        if ls < rs:
            search_l(left, mid+1, right, cnt+1)
        else:
            search_l(left+1, mid, right+1, cnt+1)

    def search_r(left, right, mid, cnt):

        if mid < 0 or right < 0:
            return -1

        ls = sum(linked[left:mid])
        rs = sum(linked[mid:]+linked[:right % length+1])

        if rs == half:
            return cnt
        if ls < rs:
            search_r(left-1, mid, right-1, cnt+1)
        else:
            search_r(left, mid-1, right, cnt+1)

    result1 = search_l(left, right, mid, 0)
    result2 = search_r(left, right, mid, 0)

    answer = 10e9
    if result1 != -1:
        answer = min(answer, result1)
    if result2 == -1:
        answer = min(result2, answer)

    return -1 if answer == 10e9 else answer


solution([3, 2, 7, 2], [4, 6, 5, 1])
