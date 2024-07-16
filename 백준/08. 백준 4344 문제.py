#4344번

#일단은 첫 횟수를 count변수에 저장함
#그리고 user가 넣어줄 빈 리스트 생성
count = int(input())
user_input = []

#첫번째 for문 입력받은 count만큼 들어올테니 값 받을 준비하는 단계
for _ in range(count):
    #이 한줄 짜는데 시간 좀 썼음... list로 빈 리스트인 user_input에다가 append하되,
    #값 하나하나 int로 매핑해줘야하고(그래야 나중에 sum써서 평균 구할수 있으니까)
    #동시에 입력받은 값들이 " "로 띄워져 있으니 " "단위마다 쪼개줘야하는 기능을 갖고 있어야 했음
    user_input.append(list(map(int, input().split(" "))))


#그 다음엔 이제 첫번째는 인원수니까 따로 꺼내서 저장한다음, del로 지워주고
#평균을 미리 구해서 그거보다 큰 친구들만 인원수 세줘야함(= count_p)
position = 0
for _ in range(count):
    save_score = user_input[position][0]
    del user_input[position][0]
    average = sum(user_input[position])/save_score
    divisiton = len(user_input[position])

    n = 0
    count_p = 0
    for _ in range(divisiton):
        if user_input[position][n] > average:
            count_p += 1
        n += 1


    #계산식 round를 써서 소숫점 4번째 자리에서 반올림하게 했음
    #그리고는 이제 뒤에 %를 붙여서 print하는 과정
    #print할 때 '+'를 쓰면 공백없이 바로 붙고,
    #','로 print하면 공백이 생긴뒤에 붙는다
    percentage = round(100 / divisiton * count_p, 3)
    print("%.3f" %percentage+"%")
    
    position += 1
    save_score = 0
    count = 0
