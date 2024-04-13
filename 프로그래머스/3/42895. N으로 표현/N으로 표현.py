def solution(N, number):
    # 이전에 구했던 집합들을 저장하기 위한 set
    # 숫자를 이어붙이는 친구들로 기본 세팅해두기 위해서 문자열과 곱해서 N자릿수의 1을 만들고 숫자를 곱한다
    # e.g. 7이 3개 붙어있는 숫자를 만든다! ('1' * 3 => 111 * 7 => 777)
    num_set = [{N * int('1' * i)} for i in range(1, 9)]

    for i in range(8):  # N을 사용한 횟수
        for j in range(i):
            for num1 in num_set[j]:  # i 번 사용해서 나타낼 수 있는 수
                for num2 in num_set[i - j - 1]:  # N-i번 사용해서 나타낼 수 있는 수
                    # 파이썬의 set에서 update를 쓰면 한번에 추가 가능!
                    num_set[i].update({num1 + num2, num1 - num2, num1 * num2})  # 사칙연산 추가

                    # 0으로 나누면 터져버리니까 이건 예외처리
                    if num2 != 0: num_set[i].add(num1 // num2)

        # 이제 N을 i번 사용해서 만들 수 있는 모든 수가 num_set[i]에 들어있게 된다
        # num_set[i] 안에 'number'가 있으면, 최소한의 N을 사용하는 것을 충족한 것이니 바로 return하면 된다
        if number in num_set[i]:
            return i + 1  # 인덱스 값이니까 + 1해서 실제 사용 갯수로 출력

    return -1