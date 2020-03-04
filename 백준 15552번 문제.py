#[백준 15552번]

#밑의 readline()에서 sys를 쓰기위한 import
import sys

#첫 입력의 숫자에 따라 입력 받을 횟수를 지정
count = int(input())


#for문을 돌리는데 범위는 초기에 입력받은 count만큼 반복한다
#그 이후에 input_A와 input_B로 나누어 받으며(split()), int로 maping한다
#그 다음에 두 숫자를 더한 값을 출력(print())
for _ in range(count):
    input_A, input_B = map(int, sys.stdin.readline().split())
    print(input_A + input_B)
