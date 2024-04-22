"""
[백준] 2138, 전구와 스위치
(https://www.acmicpc.net/problem/2138)

[문제파악]
1. N개 스위치, N개 전구
2. 전구 상태
    - 켜지 있는 상태
    - 꺼져 있는 상태
3. ㅑ번 스위치 클릭시
    - i-1, i, i+1의 세개의 전구 상태가 변경됨
    - 켜져 있는 전구 -> 꺼짐
    - 꺼져 있는 친구 -> 켜짐
4. N개의 전구 현재 상태 + 우리가 만들고자하는 상태일 때
    - 그 상태로 만들기 위한 최소 스위치 클릭 수는?


[Input]
1. 첫줄에 N2. 다음 줄에는 전구들의 현재 상태를 나타내는 숫자 N개 (공백없이 제공)
3. 만들고자 하는 전구들의 상태를 나타내는 숫자 N개 (공백없이 제공)

- 첫줄에 답 입력
- 불가능할 시 -1 출력


[풀이방식]
1) i 클릭해서  i-1, i, i+1가 바뀌면 i, i+1, i+2라고 생각해버리면 쉬움
2) 대신 유의할 점 하나 있음
    - 인덱스 0번을 i로 클릭하는 경우는 i, i+1, i+2로는 구현할 수 없다
    - 그러니 이 부분은 예외처리를 해두고 진행해야 함


[보완점]
- switch라는 함수를 따로 만들지 않아도 된다
- 그냥 '현재 전구 상태' + 1 하고 % 2 해버리면 2로 나눈 나머지가 남음

"""


def switch(bulb_state):
    if bulb_state == 0:
        return 1
    elif bulb_state == 1:
        return 0


if __name__ == '__main__':
    N = int(input())
    current_bulbs = list(map(int, input()))
    original_bulbs = current_bulbs[:]
    target_bulbs = list(map(int, input()))

    # print(N, current_bulbs, target_bulbs)

    count = 0

    # 0번 인덱스를 고르면 -1, 0, 1 인덱스의 전구들이 바뀐다
    # for문은 1번 인덱스부터 시작할 예정 (0번부터 하면 -1 인덱스는 없어서 터지거나, 파이썬에선 마지막 값을 가져오니까)
    # 그렇기 때문에 0, 1 인덱스의 전구를 그대로 냅두거나, 상태를 바꾸거나 두가지 경우의 케이스는 따로 지정해주고 시작해야한다
    # 그러기 위한 첫번째 for문
    # 0일땐 더해도 본인 값이니 변동 X
    # 1일땐 더하면 변동 O
    for case in range(2):
        if case == 1:
            current_bulbs[0] = switch(current_bulbs[0])
            current_bulbs[1] = switch(current_bulbs[1])

        # 전구를 순차적으로 탐색할 때
        for i in range(1, N):
            # 전구가 목표 전구들과 다르면, 껐다 켜야함
            if current_bulbs[i-1] != target_bulbs[i-1]:
                current_bulbs[i-1] = switch(current_bulbs[i-1])
                current_bulbs[i] = switch(current_bulbs[i])

                # 인덱스 넘어가면 안되니까 i+1은 인덱스 체크하고 진행
                if i != N - 1:
                    current_bulbs[i+1] = switch(current_bulbs[i+1])

                count += 1  # 스위치 눌렀으니까 count +1

        # 오리지널과 값이 일치하는지 확인
        for i in range(N):
            if current_bulbs[i] != target_bulbs[i]:
                current_bulbs = original_bulbs[:]  # 다르면 더 해볼 것 없음 그냥 원본값으로 롤백
                count = 1
                break
        else:  # 파이썬 문법의 장점, for문이 정상적으로 완료되면 일치한단 소리니까 동작함
            print(count)
            exit(0)

    print(-1)  # 2가지 경우 전부 일치 하지 않을 때