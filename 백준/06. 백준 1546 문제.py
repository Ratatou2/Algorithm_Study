#문제 설명하기 어려우니 본문 참조

#일단 빈 리스트 하나 만들어줌 여기에다가 점수 원본들을 다 저장할 것임
score_list = []

#몇 개의 점수를 입력받을건지 알아야 함 
#그래서 일단 count_input에 과목 갯수를 받아둠(평균 구할 때 필요)
#그리고 점수를 입력받음, 단 받을 때 한번에 받으니 split()으로 쪼개주고,
#그 다음엔 int로 map이후에 list로 저장함
count_input = int(input())
score_list = list(map(int, input().split()))

#입력받은 값들 중 최대값을 구함(=high_score)
#그리고 이 값은 다른 값들과 중첩되면 계산을 또 해줘야하니 그냥 리스트에서 제거함
high_score = max(score_list)
score_list.remove(high_score)

#수정된 점수값을 저장할 리스트와 리스트 위치를 지정해줄 변수 n 생성
reset_list = []
n = 0

#지금 최댓값을 리스트에서 지웠으니 반복 횟수는 아까 받은 count_input에서 -1을 함
#수정된 점수를 계산해주고, 새로운 리스트 reset_list에 저장해줌
#리스트 자리를 옮기기 위한 n += 1
for _ in range(count_input-1):
    reset = score_list[n]/high_score * 100
    reset_list.append(reset)
    n += 1

#sum을 이용하면 list내의 값의 총합을 구할 수 있음 
tot_sum = sum(reset_list)

#최대값을 100점으로 설정하니 high_score로 복잡하게 또 계산해줄 필요가 없음
#그래서 그냥 위에서 구한 리스트 내의 총합(=sum)과 100점을 더한 후 count_input으로 나눠 평균을 구함
print((100 + tot_sum)/count_input)


#list(map(int, input().split())) 설명 및 이해
#일단 input().split은 입력값을 쪼개주는 것이다.
# 그 다음엔 map을 통해 int로 다 덮어씌워주고
# list형식으로 받기 위해 맨 앞에 list를 적어주는 것이다
# (여기에는 형식을 지정하는 것이라 tuple, set 등등으로 바꿔도 무방하다)
