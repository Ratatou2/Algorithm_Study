"""
[백준] 2176, 합리적인 이동경로 (https://www.acmicpc.net/problem/2176)


[문제파악]
1. 그래프 정점 S -> T로 이동
2. T와 가까지며 이동하는 경우 = 합리적인 이동경로
    - 물론 최단 경로 아닐 수 있음
3. 합리적인 이동경로의 개수를 구할 것
    - S : 1
    - T : 2

[입력]
1. 첫쨋줄에 정점의 갯수 N(1 < N <= 1000), 간선의 갯수 M(1 <= M <= 100,000)
2. 다음의 M 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C
    - A번 정점, B번 정점의 길이 C인 간선으로 연결되어있다는 의미

[출력]
걍 바로 답 출력

[구현방법]
- 아니 근데 이거 가까워지고 멀어지는걸 거리로 측정해야하나?
- 그럼 삼각함수 나와야해? 초기 값보다 멀어지면 합리적이지 못하네?

[보완점]
- 다익스트라로도 된다..
- 문제 더 많이 풀어봐야겠다 어렵 ㅠ
- 알고리즘을 아는 것이 선행되어야, 어떤 상황에서 어떤 알고리즘을 적용할 것인지가 정해질듯

- 다익스트라 알고리즘이란?
    - 최단거리 알고리즘
    - 하나의 노드 to 다른 노드 전부 다 구할 수  있음
    - A to Z로 갈 때, 기록된 값이 있어도 더 짧은 경로가 있다면 해당 값으로 갱신한다
"""
import heapq

N, M = map(int, input().split())
S, T = 1, 2

connection_list = [[] for _ in range(N+1)]  # 노드끼리 연결 정보
dist = [99999999 for _ in range(N + 1)]  # 각 노드까지의 최단 거리 (큰 값으로 리셋)
dp = [0 for _ in range(N + 1)]  # 최단 경로 갯수 저장 dp

# A, B, C 입력 받기
for _ in range(M):
    A, B, C = map(int, input().split(' '))

    # 각 노드에 연결된 노드를 [노드, 거리] 순으로 입력
    connection_list[A].append([B, C])
    connection_list[B].append([A, C])

heap = [[0, T]]
dist[T] = 0
dp[T] = 1

while heap:
    # 현재까지의 이동거리, 현재 정점
    current_dist, current_node = heapq.heappop(heap)

    # 현재 거리가, 저장된 거리보다 길면 더 볼 필요 없음
    if dist[current_node] < current_dist: continue

    # 현재 노드와 연결된 모든 노드 확인
    for next_node, next_dist in connection_list[current_node]:
        total_dist = current_dist + next_dist   # 다음 노드까지의 거리 = 현재까지의 거리 + 다음 노드 거리

        # [새로 구한 거리]가 [저장된 거리]보다 짧다면 갱신
        # => 이번에 구한 더 짧은 것으로 새로 계산해서 dp(= 합리적인 이동 경로)를 갱신하기 위함이다
        if total_dist < dist[next_node]:
            dist[next_node] = total_dist
            heapq.heappush(heap, [total_dist, next_node])

        # 다음 정점까지의 거리가 현재 이동거리보다 짧을 경우, 합리적인 이동 경로 갱신
        if dist[next_node] < current_dist:
            dp[current_node] += dp[next_node]

print(dp[S])