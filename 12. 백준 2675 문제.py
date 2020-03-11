#2675번
count = int(input())

#처음 입력 받은 횟수만큼 추가 입력을 받고
#그 안에 간단한 반복문 실행(빈 문자열 blank에 숫자만큼 반복해서 삽입후 출력)
for _ in range(count):
    user_input = input()

    empty = list(map(str, list(user_input)))
    repeat = empty.pop(0)

    blank = ""
    n = 1

    for _ in range(len(empty)-1):
        for _ in range(int(repeat)):
            blank += empty[n]

        n += 1
        
    print(blank)