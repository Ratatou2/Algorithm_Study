def solution(m, n, puddles):
    # 지도 초기화
    map = [[0] * (m + 1) for _ in range(n + 1)]

    # 지도 물웅덩이 표기
    for puddle in puddles:
        y, x = puddle
        map[x][y] = -1

    # 집 좌표 처리
    map[1][1] = 1

    # 배열의 크기가 n+1, m+1이라는 것을 잊어서는 안됨
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            # 현 위치가 물웅덩이면 어차피 못 지나가니까 의미없다
            # 다만 다음 값들에 영향을 주지 않도록 0으로 리셋해두고 넘어가기!
            if map[i][j] == -1:
                map[i][j] = 0
                continue

            # 현 위치의 위쪽과 왼쪽에 올 수 있는 경우의 수를 다 더한다
            # 나 자신의 값을 더하는 것(+=)도 빼먹으면 안된다
            map[i][j] += (map[i - 1][j] + map[i][j - 1]) % 1000000007

    return map[n][m]