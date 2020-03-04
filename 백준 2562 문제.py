#빈 리스트를 하나 만들어줌(여기에다가 저장할 것임)
my_list = []


#아홉개의 숫자를 받는다고 하였으므로 range에 9를 넣는다
#이는 따로 변수로 만들어줘도 깔끔함
#사용자의 input을 9번을 받고 그것을 매번 아까 만든 my_list에 넣어줌
for _ in range(9):
    user_input = int(input())
    my_list.append(user_input)


#max_v는 my_list내에서 최대값이고
#position은 my_list내에서 max_v의 위치값이다
#position을 따로 만들어준 이유는 인덱스 번호값은 0부터 시작하므로
#문제에서 원하는, 1부터 시작하는 위치값을 출력해주려면 나중에 + 1을 해줘야하기 때문이다.
max_v = max(my_list)
position = my_list.index(max_v)

print(max_v)
print(position+1)
