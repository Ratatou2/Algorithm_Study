#세개의 자연수 입력받고 빈 리스트에 저장하는 문제

my_list = []
ept_list = []

for _ in range(3):
    user_input = int(input())
    my_list.append(user_input)

multiple = my_list[0] * my_list[1] * my_list[2]
# print(multiple)
# print(type(multiple))
my_list.clear()
my_list.append(multiple)

#list(str(multiple))의 기능은 multiple에 들어있는 값을 str(문자열)로 바꾸어서 list에 하나씩 넣는 기능이다(잘게 쪼개준다는 의미)
#그리고 map을 써서 list에 있는 문자열을 int로 maping해줌으로써 문자열 → 숫자로 바꿈
#그렇게 만들어진 것은 변수 i에 저장하는데, 여기서 i는 리스트임_type(i)해보면 바로 알수 있음
i = list(map(int, list(str(multiple))))

#이제 숫자를 세줄 카운트 for문을 만듬_1-9니까 9번 반복해야됨
n = 0
for _ in range(10):
    p = i.count(n)
    print(p)
    n += 1



# <map의 사용방법>
# list(map(함수, 리스트)) 혹은
# tuple(map(함수, 튜플))이다
