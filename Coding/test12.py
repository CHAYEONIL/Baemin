def solution(n, k, h):
    # 각 탑의 높이 중, 현재 탑부터 가장 높은 탑의 높이를 저장하는 배열
    max_heights = [0] * n
    # 첫번째 탑은 h[0]으로 초기화
    max_heights[0] = h[0]

    # 현재 탑부터 가장 높은 탑의 높이를 계산해 max_heights 배열에 저장
    for i in range(1, n):
        max_heights[i] = max(max_heights[i-1], h[i])

    # 필요한 타워의 수를 저장할 변수
    towers = 0
    # 현재 위치에서의 가장 높은 탑의 높이
    cur_tower = h[0]
    # 현재 위치
    cur_index = 0

    # 마지막 탑까지 도달할 때까지 반복
    while cur_index < n-1:
        # 현재 위치에서 k칸만큼 이동했을 때 도착할 인덱스
        next_index = min(cur_index+k, n-1)

        # 다음 위치에서의 가장 높은 탑의 높이가 현재 위치보다 높을 경우
        if max_heights[next_index] > cur_tower:
            # 타워가 필요하므로 towers 값을 1 증가시키고
            towers += 1
            # 현재 위치의 탑 높이를 다음 위치에서의 가장 높은 탑 높이로 변경
            cur_tower = max_heights[next_index]
            # 현재 위치를 다음 위치로 변경
            cur_index = next_index
        # 다음 위치에서의 가장 높은 탑의 높이가 현재 위치보다 낮을 경우
        else:
            # 현재 위치에서 다음 위치 사이에 있는 탑 중 가장 높은 탑의 높이를 구하고
            next_tower = max(h[cur_index+1:next_index+1])
            # 현재 위치를 가장 높은 탑의 위치로 변경
            cur_tower = next_tower
            cur_index = h.index(cur_tower)

    # 필요한 타워의 수를 반환
    return towers
# 예제 1번 출력: 2
print(solution(6, 2, [1, 3, 5, 4, 2, 6]))

# 예제 2번 출력: 3
print(solution(9, 2, [9, 5, 3, 8, 7, 6, 4, 2, 1]))
