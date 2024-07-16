#11729번
count = int(input())
user_input = list(map(int, list(str(input()))))

n = 0
sum = 0
for _ in range(count):

    sum = sum + user_input[n]
    n += 1

print(sum)



#-------------another case----------------------------------------
# count = int(input())
# user_input = list(map(int, list(str(input()))))

#print(sum(user_input))
