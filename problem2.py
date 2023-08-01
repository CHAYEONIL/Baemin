# 0~99까지의 2자리 정수 n개로 구성된 리스트 선언
# 숫자의 개수 k 입력
# k=0, 1, k>n이면 에러메시지 출력 후 종료
# 인접한 k개의 숫자합 중 가장 큰 값 출력

nums = [3, 4, 1, 2, 5, 7]
n = len(nums)

while True:
    _max = -1
    k = int(input())

    if n < k or k < 2:
        print('INVALID INPUT')
        break

    for i in range(k - 1, n + 1):
        _max = max(_max, sum(nums[i - k:i]))

    print(_max)
