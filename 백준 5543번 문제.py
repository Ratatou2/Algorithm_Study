#버거와 음료의 조합으로 최저값의 세트메뉴를 찾는 문제


n = 0
burger = []
drink = []
set_menu = []


#처음 세번의 입력은 버거값  / 뒤의 2번의 입력은 콜라와 사이다의 입력값임
#그래서 if문으로 나눠서 입력할 수 있게 해두었음
#그래서 리스트를 2개로 분리, 각각에 저장해줌
for _ in range(5):
    user_input = int(input())
    if n < 3:
        burger.append(user_input)
    
    else:
        drink.append(user_input)
    
    n += 1

#버거와 음료의 리스트를 이제 각각 다 더하고 50원을 뺀 값을 계산하는 for문
#cock와 soda에 한번씩 더하고 세트할인 50원을 뺀뒤
#세트메뉴 리스트에 값을 입력함
num = 0
for _ in range(3):
    cock = burger[num] + drink[0] - 50
    soda = burger[num] + drink[1] - 50

    set_menu.append(cock)
    set_menu.append(soda)

    num += 1
    

#그리고 나서는 세트메뉴의 최저값을 구함
print(min(set_menu))
