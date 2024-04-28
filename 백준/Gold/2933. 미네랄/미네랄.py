"""
[백준] 2933, 미네랄 (https://www.acmicpc.net/problem/2933)

[문제파악]
1. 동굴에 미네랄있고, 던진 막대기가 미네랄 파괴 가능
2. R행 x C열, 비어있거나 미네랄 포함
3. 네 방향 중 하나로 인접한 미네랄이 포함된 두칸은 클러스터 (밀집)
4. 창영 = 동굴의 왼쪽 / 상근 = 동굴의 오른쪽
5. 막대 던지기 전에 높이를 정하고 번갈아가며 막대 던짐
6. 막대는 땅과 수평을 이루며 날아감
7. 막대가 날아가다가 미네랄을 만나면 미네랄은 파괴, 막대는 해당 위치에서 정지
8. 미네랄 파괴시, 클러스터 분리가능
9. 새롭게 생성된 클러스터가 떠 있는 경우, 중력에 의해 바닥에 떨어짐
10. 클러스터는 다른 클러스터 or 땅을 만나기전까지 떨어짐
11. 다른 클러스터 위에 떨어질 수 있고, 그럴 경우 합쳐짐
12. 동굴에 있는 미네랄 모양과, 두 사람이 던진 막대의 높이가 주어짐
13. 모든 막대를 던지고 난 이후의 미네랄 모양을 구할 것

[입력]
5 6
......
..xx..
..x...
..xx..
.xxxx.
1
3

1. 첫줄에 동굴 크기 (R, C) (1 <= R,C <= 100)
2. 둘째 줄에 R개의 줄에 C개의 문자
3. '.'는 빈 칸, 'x'는 미네랄
4. 셋째줄에 막대를 던진 높이가 주어짐 (공백으로 구분)
5. 모든 높이는 1과 R 사이 (1은 행렬의 가장 바닥, R은 가장 위를 의미)
6. 첫 막대는 왼쪽에서 오른쪽으로, 두번째 막대는 오른쪽에서 왼쪽으로


[풀이방식]
1. 기냥 대놓고 구현구현구현 빡구현! 이라고 쓰여있네여 ㅋㅅㅋ
2. 테트리스 느낌도 나고
3. 일단 막대 던져서 부시는건 쉬움
4. 막대를 던지고 났을 떄, BFS를 통해서 클러스터가 '떠있는지'를 확인해야 함
5. 떠 있으면 그 크기 그대로 이동하면 되는데 다른 부분이 땅이나, 클러스터에 닿으면 그만 움직여야 함
6. 분명 오른쪽이나 왼쪽이 튀어나온 막대에 걸리는 경우 밑에 공간이 떠있음에도 더이상 내려갈 필요가 없는 경우가 예외처리 필요할텐데

[보완점]
1. 클러스터를 덩어리쨰 옮기는 곳에서 아이디어 막힘 (+ 삽질!!!!!!!)
2. 그냥 미네랄 꺤 곳을 기억해뒀다가 사방 탐색하고,
    2-1. 주변에 클러스터가 있다면 패스
    2-2. 주변에 없으면 떠 있는 것으로 판단 -> 이동로직 진행
3. 이동할 떈, 방문한적 있는지 (미네랄(="x")이 있던 곳이었는지) 체크하면 됨
4. 그리고 몇칸 내려갈지 체크도 해야하고
5. 체크할 땐 클러스터를 이루고 있는 미네랄들을 하나씩 다 이동하면서, 최솟값을 찾아야한다는 것이다
6. 위에서 내가 염려했던 튀어나온 곳이 다른 곳에 걸리는 케이스 같은 경우, 다른 곳이 걸렸으면 더 내려갈 수 없음을 유의

- 어렵다 어려워;;; 추후에 다시 풀어보자
- 여담으로 출력형태도 신경쓰자 냅다 리스트 출력하지말고
"""
from collections import deque


# BFS
def find_cluster(cave_map):
    is_visited = [[False for _ in range(C)] for _ in range(R)]  # 방문체크 배열
    q = deque()

    for i in range(C):
        if cave_map[R - 1][i] == 'x':
            q.append((R - 1, i))

    while q:
        x, y = q.popleft()
        adjlist = [[x - 1, y], [x + 1, y], [x, y - 1], [x, y + 1]]  # 상하좌우 탐색한 배열을 만듦 (부서진 미네랄 기준으로만 찾게하면 시간단축 가능할지도?!)

        # 상하좌우 위치 탐색
        for nx, ny in adjlist:
            if 0 <= nx < R and 0 <= ny < C:  # cave_map 범주 내에 있으며
                if not is_visited[nx][ny] and cave_map[nx][ny] == 'x':  # 방문한적이 없고, 미네랄(="x")이 있으면
                    is_visited[nx][ny] = True  # 방문처리하고
                    q.append((nx, ny))  # BFS 탐색 대상에 넣는다

    # 클러스터 탐색하기
    cluster = []
    for row in range(R - 1, -1, -1):
        for column in range(C):
            # 미네랄이고, 방문한적 없으면
            if cave_map[row][column] == 'x' and not is_visited[row][column]:
                # 클러스터 발생
                cluster.append([row, column])

    if 0 < len(cluster):  # cluster 있음
        return cluster, 1, is_visited
    else:  # cluster 없음 (= 떠있단 소리)
        return cluster, 0, is_visited


# 클러스터 이동
def move_cluster(cave_map, cluster, is_visited):
    down_min = 1e9

    for x, y in cluster:
        down_count = 0

        for i in range(x + 1, R):
            if cave_map[i][y] == '.':
                down_count += 1
            if cave_map[i][y] == 'x' and is_visited[i][y]:  # 미네랄 만났거나, 방문한적 있으면(= 이미 방문한 x지점) 더 이동 안함
                break
        down_min = min(down_min, down_count)

    # 이동 count만큼 아래로 내린 위치에 미네랄(="x") 표기
    for x, y in cluster:
        cave_map[x][y] = '.'
        cave_map[x + down_min][y] = 'x'

    return cave_map


def print_cave_map():
    for row in cave_map:
        print(''.join(row))



# 막대 던져서 미네랄 부셔버리기
def throw_stick(height, is_reverse):
    current_list = cave_map[height]

    if is_reverse:  # 오른쪽에서 던지는 케이스는 리스트 역순
        for index in range(len(current_list) - 1, -1, -1):
            if current_list[index] == "x":
                cave_map[height][index] = "."
                break
    else:  # 왼쪽에서 던지는 케이스
        for index, cave in enumerate(current_list):
            if cave == "x":
                cave_map[height][index] = "."
                break


if __name__ == '__main__':
    R, C = map(int, input().split())  # 행, 열
    cave_map = [list(input()) for _ in range(R)]  # 동굴 지도
    N = int(input())  # 막대를 던진 횟수
    throw_height = list(map(int, input().split()))  # 막대 던진 높이

    # 막대 던지기 진행
    for i in range(N):
        current_height = R - throw_height[i]  # 마지막 행이 바닥이기 때문에 R-height

        # (1) 막대기 던지기
        if i % 2 == 0:  # 왼쪽에서 던짐
            throw_stick(current_height, False)
        else:  # 오른쪽에서 던짐
            throw_stick(current_height, True)

        # (2) 클러스터 조사
        cluster, is_levitation, is_visited = find_cluster(cave_map)

        # (3) 클러스터 이동
        if is_levitation == 1:
            cave_map = move_cluster(cave_map, cluster, is_visited)

    print_cave_map()