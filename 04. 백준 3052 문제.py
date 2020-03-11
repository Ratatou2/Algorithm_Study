rest_list = []

#10번 반복할 것임(숫자 입력이 10개)
#input을 user_input으로 받아서 42로 나눈 나머지를 i에 저장
#그리고 rest_list에 i를 추가하고 sort해줌(사실 기능상 필요는 없음)
for _ in range(10):
    user_input = int(input())
    i = user_input%42
    rest_list.append(i)
    rest_list.sort()
    # print(rest_list)
    
#중복을 허용하지 않는 set을 사용하여 같은 값들을 지우고,
#길이(총 갯수)를 재서 리스트 안의 값 갯수를 카운트함
print(len(set(rest_list)))



#Q. 두개의 차이는 무엇인가?
# print(set(rest_list))
# print(list(set(rest_list)))
