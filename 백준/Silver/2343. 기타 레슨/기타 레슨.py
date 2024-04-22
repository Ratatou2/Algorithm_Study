"""
[백준] 2343, 기타 레슨
(https://www.acmicpc.net/problem/2343)


[문제파악]
1. 블루레이에 N개의 강의가 들어감
2. 순서 바뀌면 안됨
3. 블루레이 갯수 줄이고 싶음
4. M개의 블루레이에 모든 기타 강의 녹화
5. 이때, 블루레이의 크기 (녹화 가능한 길이)를 최소
6. 단, M개의 블루레이는 모두 같은 크기
7. 각 강의의 길이가 분 단위(자연수)로 주어짐
8. 이때, 가능한 블루레이의 크기 중 최소를 구할 것


[입력]
1. 첫줄에 강의의 수 N과 M
2. 기타 강의의 길이가 분 단위로 주어짐

- 각 강의의 길이는 10,000분을 넘지 않음


[구현방법]
1. 가장 크게 묶이는 케이스를 구하고 그 케이스의 크기 안에 다 들어가는지 확인 (그래서 어떻게 구현하는데..?)

- 30분 초과
1. 이분탐색하란다...
2. 이론을 설명해보면 아래와 같다
    1) start와 end 값을 설정해주면 됨
        - start는 정답이 될 수 있는 케이스 중, 가장 작은 값
        - end는 정답이 될 수 있는 케이스 중, 가장 큰 값
        - 1~9의 경우엔 한개씩만 잘라넣는다고 하면 9가 start값
        - end는 1~9를 한군데에 몰아넣는 45가 되겠다
    2) 그러고 나면 start=9, end=45로 시작한다 (mid=(9+45)/2=27)
    3) 이제 mid를 기준으로 블루레이 갯수에 맞을 때까지 mid를 start와 end로 바꿔가며 진행한다

- 구현 실패
1. 일단 lecture를 돌면서 더하긴 해야함... (왜냐면 정렬 못하고 순차적으로 밀어넣어야 하니까)
2. 모든 경우의 수 따지듯, 사이즈를 정하면 그 사이즈 맞게 밀어 넣는 느낌
"""

if __name__ == '__main__':
    N, M = map(int, input().split(' '))
    lectures = list(map(int, input().split(' ')))

    start = max(lectures)
    end = sum(lectures)
    disk_size = 0

    # 시작이 끝과 같아지거나 작아지면 더할 필요 없음
    while start <= end:
        mid = (start + end) // 2  # //로 해야 소숫점 버림
        total_time = 0
        count = 1  # 초기엔 한덩어리로 1개니까 Default는 1

        for lec in lectures:
            # 여지껏 더한 lecture의 총 시간은 mid를 넘을 수 없음
            # 근데 넘어버렸어?
            # 그럼 count 하나 추가하고 total은 0으로 리셋 (= disk + 1이란 의미)
            if mid < total_time + lec:
                count += 1
                total_time = 0
            total_time += lec

        # 조건을 만족하는 케이스 (count <= M)
        # 근데 이보다 더 작게 만들수도 있으니 disk_size를 갱신만하고 더 진행
        # end는 하나 더 줄여서 진행하는데 disk_size를 줄이려면, start가 아닌 end가 작아져야 하기 때문이다
        if count <= M:
            disk_size = mid
            end = mid - 1
        else:  # 조건 불만족 (disk 갯수가 너무 많은 케이스, start를 늘려야 함)
            start = mid + 1

    print(disk_size)