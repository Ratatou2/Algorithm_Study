T = int(input())

for temp in range(T):
    n = int(input())
    day_price = list(map(int, input().split()))
    rev_price = reversed(day_price)

    max_price = 0
    result = 0

    for i in rev_price:
        if max_price < i:
            max_price = i

        else:
            result += max_price - i

    print(f'#{temp + 1} {result}')