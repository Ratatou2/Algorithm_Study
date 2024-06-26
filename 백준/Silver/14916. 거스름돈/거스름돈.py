"""
[백준]
14916, 거스름돈 (그리디)

[문제파악]
- 2원짜리와 5원짜리로만 거스름돈을 달라고 한다
- 5원부터 시작해서 거스름돈이 최소가 되면 된다
- 주의할 사항은 5와 2로 나눠지지 않는 경우가 문제

[입력]
- 숫자가 주어진다

[출력]
- 최소 동전의 갯수

[구현방법]
- 그리디니까 일단 다해보면 되긴하는데.. 이걸 어케했더라
- 일단 5로 최대한 많이 나누는게, 동전 갯수가 적으니 정답임
- 근데 안될 경우 2로 하나씩 뺴줘야지
- 근데 그러고도 마지막 잔액이 2로 안나눠지면 return -1 해야하는 것을 잊으면 안된다

[보완점]
"""


def cal_coin_count(N):
    count = 0

    while True:
        if N % 5 == 0:
            return count + (N // 5)  # 정수 나누기(//)를 써줘야 결과값이 int로 떨어진다
        else:
            if N < 2:  # 나머지가 2보다 작으면, 더이상 잔돈 처리 불가 return -1 처리하기 
                return -1
            else:
                count += 1
                N -= 2


if __name__ == '__main__':
    N = int(input())

    coin_count = cal_coin_count(N)

    print(coin_count)