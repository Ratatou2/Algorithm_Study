/*
[백준]
2014, 소수의 곱

[문제파악]
K개의 소수가 있다.
이때, 이 소수들 중에서 몇 개를 곱해서 얻게 되는 수들이 있을 것이다.
소수들을 선택할 때에는 같은 수를 선택해도 되며, 주어지는 소수 자체도 포함시키자.
예를 들어 세 소수가 2, 5, 7이었다면, 이러한 곱들을 오름차순으로 나타내 보면, 2, 4, 5, 7, 8, 10, 14, 16, 20, 25, 28, 32, 35, 등이 된다.
K개의 소수가 주어졌을 때, 이러한 소수의 곱들 중에서 N번째 수를 구해 보자.
단 정답은 2^31보다 작은 자연수이다.

[입력]
첫째 줄에 K(1 ≤ K ≤ 100), N(1 ≤ N ≤ 100,000)이 주어진다.
다음 줄에는 K개의 소수가 오름차순으로 주어진다.
같은 소수가 여러 번 주어지는 경우는 없으며, 주어지는 소수는 모두 541보다 작거나 같은 자연수이다.

[출력]
첫째 줄에 문제에서 설명한 대로 소수의 곱을 나열했을 때, N번째 오는 것을 출력한다.

[구현방법]
- C# 기본적인 것은 됐고, 이제 자료구조를 빡세게 써볼 시간..!
- N번쨰 숫자까지니까 K의 제곱수로 N을 초과하는데까지만 구하고 첫번째 숫자부터 제곱해서 Queue에 밀어넣는다
- 그러고 나면 나중에 Q 안에는 정렬도 되어있고, N을 초과하는 갯수도 구할 수 있음
- 근데 이 방식은 그리면서 해봤는데 터무니 없다 첫번째, 두번째까지면 어떻게 구하겠지만 3제곱만 해도, 5가 곱해야할 분량은 9개... K는 최대 100개까지 들어올 수 있는데, 값을 미리 구하고 있는다는 것은 말이 안될 것 같다

- 이건 푸는 방법이 있다
    1) 우선 소수 K를 입력받는다
    2) 우선순위 큐 pq에 다 밀어넣고, visited라는 HashSet에 넣은 값들을 기록한다 (중복 제거를 위함)
    3) 매 반복마다 가장 작은 수 x를 pq에서 꺼낸다 (제일 앞에 있는 숫자가 될테죠?)
    4) x에 모든 소수를 곱하고, 소수를 곱한 새로운 수(x * p)를 pq에 넣는다 (방문하지 않았던 값들만)
    5) 위 과정을 N번 반복하면, pq에 들어있을 가장 작은 수가 N번째로 작은 수가 됨

- 이렇게 구하면 시간복잡도를 따져도 N번만큼만 루프를 돌게된다
- 내가 초기에 짠 것처럼 N * N -1 => N^2이 되지 않음

[보완점]
1) 런타임 에러 (Segfault)뜸
    - 이거 StreamReader 쓸거면 그거만 쓰고, Console.ReadLine() 쓸거면 그거만 써야함.

2) 메모리 초과
    - 모든 걸 PQ에 밀어넣으니까 메모리 터짐...
    - 슬라이딩 윈도우 기법 쓰란다.. 그게 뭔데...
    - 그래도 터지는데...?
    - 설마 C#이라서...? JAVA로 풀면 되려나?
    - 자바로 해도 여전히 메모리 초과
    - 어려운데.. 전략이 잘못됐단 소린데.. Set이랑 PQ에 너무 많이 집어넣는게 문제인듯 함
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashSet<Long> isVisited = new HashSet<>();
        PriorityQueue<Long> pq = new PriorityQueue<>();

        int K = Integer.parseInt(st.nextToken());  // 정수 갯수
        int N = Integer.parseInt(st.nextToken());  // N번째 숫자

        int[] nums = new int[K];

        // 소수 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            isVisited.add((long) nums[i]);  // 방문처리
            pq.add((long) nums[i]);  // pq에 삽입
        }

        long curr = 0;

        // N번째 숫자 찾기
        for (int i = 1; i < N; i++) {
            curr = pq.poll();

            for (int j = 0; j < K; j++) {
                long next = curr * nums[j];

                if (next >= (1L << 31)) break;

                pq.add(next);

                // 중복 제거: 오름차순 생성만 허용
                if (curr % nums[j] == 0) break;
            }
        }

        System.out.println(pq.peek());
    }
}