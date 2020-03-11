#10809번

#일단 알파벳과 -1로 가득 채운 리스트 2개를 준비
#나는 -1이 이미 가득차있는 리스트에서 하나씩 지우는 방식으로 할 것임
check_list = ["a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" , "i" , "j" , "k" , "l" , "m", "n" , "o" , "p" , "q" , "r" , "s" , "t" , "u", "v" , "w" , "x" , "y" , "z"]
empty = ["-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1", "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1", "-1" , "-1" , "-1" , "-1" , "-1"]

user_input = list(input())


#for문 밖에 것은 입력할 단어 / 안의 것은 알파벳 26개를 한번씩 다 화긴해줄 코드
#단 empty리스트 중에 바꿔야 할 값이 "-1"이 아닌 경우엔(= 중복되는 문자) 또 바꾸지 말고 그냥 카운트만 하고 넘어감
#그리고 알바펫 순서상 해당 자리에 맞는 값이 들어오면 그 자리에 있던 -1을 지우고 카운트하던 m값을 넣음
n = 0
m = 0
for _ in range(len(user_input)):
    for _ in range(len(check_list)):
        if user_input[n] == check_list[m]:
            if empty[m] != "-1":
                pass
            else:
                del empty[m]
                empty.insert(m, n)

        m += 1

    n += 1
    m = 0

empty = list(map(str, empty))

#리스트 안의 값들은 []와 ""가 있으므로 그냥 일반 문자열에 추가하기 위한 for문
#좀더 간결하게, 위에서 리스트로 하지 않고 해결할수 있다면 없어도 되는 코드(추가 공부 필요)
for_print = ""
num = 0
for _ in range(26):
    for_print += empty[num]
    for_print += " "
    
    num += 1

print(for_print)

# [+a]
# ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
# find 메소드는 찾는 문자나 문자열이 존재하지 않으면 -1을 반환함
# = empty 리스트를 미리 만들어둘 필요가 없었다는 소리
# ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
# 리스트를 print할때 '*'을 써주면 리스트 내용을 한칸 공백씩 넣어서 출력해줌


#ㅡㅡㅡㅡㅡㅡㅡㅡ[공부를 통한 수정 ver]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
check_list = ["a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" , "i" , "j" , "k" , "l" , "m", "n" , "o" , "p" , "q" , "r" , "s" , "t" , "u", "v" , "w" , "x" , "y" , "z"]
empty = ["-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1", "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1" , "-1", "-1" , "-1" , "-1" , "-1" , "-1"]

user_input = list(input())

n = 0
m = 0
for _ in range(len(user_input)):
    for _ in range(len(check_list)):
        if user_input[n] == check_list[m]:
            if empty[m] != "-1":
                pass
            else:
                del empty[m]
                empty.insert(m, n)

        m += 1

    n += 1
    m = 0

print(*empty)

