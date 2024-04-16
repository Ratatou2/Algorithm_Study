"""
백준 1253 좋다 

[문제분석]
1. N개의 수 중
2. 어떤 수가 다른 두개의 합으로 나타낼 수 있으면 좋다(GOOD)고 함
3. N개의 수가 주어지면 그 중의 좋은 수의 갯수는?
4. 수의 위치가 다르면 값이 같아도 다른 수임

[input]
1. 첫 줄에 수의 갯수 N
2. i번째 수를 나타내는 A가 N개 주어짐

[구현방식]
- 조합 구해가지고, 숫자 하나당 그 숫자 제외 모든 조합을 구해?
- 근데 이게 무슨 노가다야 (비효율에다가 소요시간이 너무 오래 걸림)
- 분명 좀 더 깔쌈한 코드 존재할거임..
- 그러라고 투 포인터가 있대요 근데 기억안나죠? ㅋㅋㅋ
- 대상이 되는 숫자를 제외하고 포인터 두개 움직이면 됨 (정렬되어 있으니까, 정렬해서 준다는 말은 없으니 정렬하면 되겠죠?)
- 즉, 둘의 합산이 대상과 일치하는지 확인하면 됨
    1. 작으면 왼쪽 포인터 +1
    2. 크면 오른쪽 포인터 -1
- 이렇게 하면 매번 모든 케이스를 살필 필요가 없음
- 안 된다 싶은건 빠르게 버리고 시작할 수 있음 (효울적)
- 막상 구현은 어렵지 않은데 내가 맨날 놓치는 부분, 2가지
    - 포인터 두개를 비교할 땐 while문에 조건은 left가 right를 넘어가기 직전까지라는 것
    - 포인터를 이동하는건 좋은데, for문 안에서 해야한다는 것
        - 뭔말이냐면, numbers의 모든 친구들이 한번씩 대상이 되어야한다는 것이죠
        - 합산이 되는 모든 케이스!가 아니고, 되는 경우의 수를 찾는 것이기에 한번 카운트 됐으면 더 셀 필요가 없음
        - 그렇기 때문에 for문 안에 while문이 존재하는 것


[추가 지식]
- 맨날 헷갈리는데 sort()는 원본 변경
- sorted({list 이름})는 원본 변경 안하고 새로운 리스트 반환
"""

if __name__ == '__main__':
    N = int(input())
    numbers = list(map(int, input().split(' ')))
    numbers.sort()

    result = 0

    for i in range(N):
        temp_numbers = numbers[:i] + numbers[i + 1:]  # numbers에서 자기 자신을 제외하기 위함
        left_pointer = 0  # 왼쪽 끝 인덱스
        right_pointer = len(temp_numbers) - 1  # 오른쪽 끝 인덱스

        while left_pointer < right_pointer:
            sum = temp_numbers[left_pointer] + temp_numbers[right_pointer]  # 왼쪽 + 오른쪽 포인터들이 가르키는 값들의 합

            # 각 포인터들의 합이 현재 값과 같은지 확인
            if numbers[i] == sum:
                result += 1
                break  # 현재 케이스에 대해 된다는 것을 알았으면 더 할 필요 없다

            if sum < numbers[i]: left_pointer += 1  # 현재 값보다 합산이 작으면 left 인덱스 +1
            else: right_pointer -= 1  # 현재 값보다 합산이 크면 right 인덱스 -1

    print(result)