/*
[백준]
14244, 트리만들기

[문제파악]
- n과 m이 주어졌을 때, n개의 노드로 이루어져 있고, m개의 리프로 이루어져 있는 트리를 만들어라
- 항상 정답이 존재하는 경우만 입력으로 주어짐
- 트리는 사이클이 없는 연결 그래프이고, 리프는 차수가 1인 노드를 의미한다.

[입력]
- 첫째 줄에 n과 m이 주어진다. (3 ≤ n ≤ 50, 2 ≤ m ≤ n-1)


[출력]
- 첫째 줄부터 n-1개의 줄에 트리의 간선 정보를 출력
	- 트리의 정점은 0번부터 n-1번까지이다

[구현방법]
- 트리 공부하자!!!!

[보완점]
- 아 이게 정답이 정해진게 아니고, 조건만 만족하면 되는걸 깜빡했다
- 4 3 집어 넣었는데 왜 예시랑 똑같이 안나오나 계속 생각하고 있었네;;;
- 문제인 즉, 위 문제에서 리프는 트리의 끝단을 의미한다
- 그렇기 때문에 갯수가 모자라면, 중심이 되는 노드에 추가를 하면 되고 (리프 증가 O)
- 갯수가 차면, 앞서 이어지던 노드에 계속 이어가면 된다 (그럼 리프가 증가 X)
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());  // 노드 갯수
        int M = Integer.parseInt(st.nextToken());  // 리프 갯수 (차수가 1인 노드)

        int leafCount = 0; // 리프 개수
        if (M == 2) leafCount = 1; // M이 2라면, 리프가 1개 뿐인 경우

        int lastLeafIdx = 0; // 마지막 리프 노드의 인덱스

        // 트리를 구성하는 반복문
        for (int i = 1; i < N; i++) { // 여기서 i는 노드의 idx임.
            // 리프 개수가 M보다 작으면, 현재의 노드 i를 새 리프로 추가
            if (leafCount < M) {
                leafCount++;
                sb.append(0).append(" ").append(i).append("\n");

            } else { // 리프 개수가 M보다 크거나 같으면, 마지막에 추가한 리프에 현재의 노드 idx를 연결
                sb.append(lastLeafIdx).append(" ").append(i).append("\n");
            }
            
            lastLeafIdx = i; // 마지막 추가 리프 업데이트
        }

        System.out.print(sb);
    }
}