"""
[백준] 16197, 두 동전 (https://www.acmicpc.net/problem/16197)


[문제파악]
1. N x M 크기의 보드 + 4개의 버튼으로 이뤄진 게임
2. 각각의 칸은 비어있거나 벽
3. 두개의 빈칸에는 동전이 하나씩 놓여 있고, 두 동전의 위치는 다름
4. 버튼은 상하좌우 4가지
5. 버튼을 누르면 두 동전이 버튼에 쓰여있는 방향으로 동시에 이동

- 조건
1) 동전이 이동하려는 칸이 벽이면, 동전 이동 X
2) 동전이 이동하려는 방향에 칸이 없으면 동전은 보드 밖으로 떨어짐
3) 그 외 경우엔, 이동하려는 방향으로 한칸 이동함
    - 칸에 동전이 있는 경우에도 한칸 이동

4) 두 동전 중 하나만 보드에서 떨구기 위해 버튼을 최소 몇번을 눌러야 하는가?
    - 동전은 항상 2개
[입력]
1. 첫째줄에 세로(N) X 가로(M)가 주어짐 (1 <= N,M <= 20)
2. 둘째줄부터 N개의 줄에는 보드의 상태가 주어짐
3. o : 동전 / . : 빈칸 / # : 벽

[출력]
1. 두 동전을 떨굴 수 없거나, 버튼을 10번보다 많이 눌러야하면 -1 출력

[구현방법]
1. 동전 기준으로 최단 거리로 벽으로 나가 떨어지는 거리를 구하면 될 것 같다
2. DFS 하면 될듯 싶은데 (이전보다 버튼 많이 눌러야하면 바로 return 하는 식)
3. 다만 움직이는 과정에 두가지 동전이 모두 떨어져야 하니까 흠... 동전 하나만 기준잡으면 되려나

[보완점]
0. BFS가 더 간결하다.. DFS는 10번 카운트까지 생각해서 예외처리 해줘야함
1. 이거 그냥 구현할 때, 코인을 동시에 움직이면 됨..
2. 초기에 생각했던건 하나 큐에서 꺼내고 움직이고, 그 다음 동전 움직이고 였는데 그냥 한 세트로 밀어넣으면 되지 뭐


"""
from collections import deque


def in_range(x, y):
    global N, M
    return 0 <= x < N and 0 <= y < M


def bfs():
    global q, board_map

    while q:
        x1, y1, x2, y2, count = q.popleft()

        # 10번 이상 클릭해야하면 -1 출력
        if 10 <= count:
            return -1

        # 사방탐색
        for i in range(4):
            next_x1 = x1 + move_x[i]
            next_y1 = y1 + move_y[i]
            next_x2 = x2 + move_x[i]
            next_y2 = y2 + move_y[i]

            # 동전이 범위 밖으로 안 나갔다면
            if in_range(next_x1, next_y1) and in_range(next_x2, next_y2):
                # 벽을 만난 동전은 이전 자리 유지
                if board_map[next_x1][next_y1] == '#': next_x1, next_y1 = x1, y1
                if board_map[next_x2][next_y2] == '#': next_x2, next_y2 = x2, y2
                q.append((next_x1, next_y1, next_x2, next_y2, count + 1))  # 새로운 위치 append

            # 둘중 하나 나갔으면, 다른 하나가 나갔는지 체크, 안 나갔으면 count + 1 해서 출력하면 됨
            elif in_range(next_x1, next_y1):
                return count + 1
            elif in_range(next_x2, next_y2):
                return count + 1

    return -1  # 만족하는게 없으면 -1


if __name__ == '__main__':

    # 상, 우, 하, 좌 (시계방향)
    move_x = [-1, 0, 1, 0]
    move_y = [0, 1, 0, -1]

    N, M = map(int, input().split(' '))
    board_map = [list(input()) for _ in range(N)]
    coin_location = []
    q = deque()

    # 코인 좌표 추가
    for x in range(N):
        for y in range(M):
            if board_map[x][y] == 'o':
                coin_location.append([x, y])

    # 두 코인의 좌표 + count를 한세트로 append
    q.append((coin_location[0][0], coin_location[0][1], coin_location[1][0], coin_location[1][1], 0))

    print(bfs())