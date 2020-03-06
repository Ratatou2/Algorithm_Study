#8958번
#일단은 입력 받을 총 횟수를 count에 저장함
count = int(input())

#빈 리스트와 입력받은 ox를 분해해서 리스트로 만들어줄 for문 / 빈 리스트 안에 분해한 리스트를 집어넣음
line = []
for _ in range(count):
    anal_quiz = list(map(str, input()))
    line.append(anal_quiz)

#중간과정 - line의 print값
#[['O', 'O', 'X', 'X', 'O', 'X', 'X', 'O', 'O', 'O'], 
# ['O', 'O', 'X', 'X', 'O', 'O', 'X', 'X', 'O', 'O'], 
# ['O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X'], 
# ['O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'], 
# ['O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'X']]


#이제 리스트안에서 ox 분해한걸 하나씩 꺼내서 계산하는 for문
#for문이 입력받은 문제 갯수만큼 반복되고 반복될때마다 점수 증가폭과 총 점수는 자연스레 reset됨
m = 0
for _ in range(count):
    cal = line[m]
    m += 1

    score = 1
    tot_score = 0
    n = 0
    for _ in range(len(cal)):
        if cal[n] == "O":
            tot_score = tot_score + score
            score += 1

        else:
            score = 1

        n += 1
    print(tot_score)
