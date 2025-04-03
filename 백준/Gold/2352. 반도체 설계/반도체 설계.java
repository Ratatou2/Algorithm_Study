/*
[백준]
2352, 반도체 설계

[문제파악]
- 반도체를 설계할 때 n개의 포트를 다른 n개의 포트와 연결해야 할 때가 있다.
- 예를 들어 왼쪽 그림이 n개의 포트와 다른 n개의 포트를 어떻게 연결해야 하는지를 나타낸다.
- 하지만 이와 같이 연결을 할 경우에는 연결선이 서로 꼬이기 때문에 이와 같이 연결할 수 없다.
- n개의 포트가 다른 n개의 포트와 어떻게 연결되어야 하는지가 주어졌을 때, 연결선이 서로 꼬이지(겹치지, 교차하지) 않도록 하면서 최대 몇 개까지 연결할 수 있는지를 알아내는 프로그램을 작성하시오

[입력]
- 첫째 줄에 정수 n(1 ≤ n ≤ 40,000)이 주어진다.
- 다음 줄에는 차례로 1번 포트와 연결되어야 하는 포트 번호, 2번 포트와 연결되어야 하는 포트 번호, …, n번 포트와 연결되어야 하는 포트 번호가 주어진다.
- 이 수들은 1 이상 n 이하이며 서로 같은 수는 없다고 가정하자.

[출력]
- 첫째 줄에 최대 연결 개수를 출력한다.

[구현방법]
- 하나를 연결하면 해당 포트 기준으로 좌우 이분 탐색을 진행해야할 것 같은데 맞는지는 모르곘다.
- 무슨 말이냐면 하나를 정하고 좌우에 연결할 수 있는 갯수를 세는 것이다. 연결이 되는 순간 해당 선을 넘나드는 연결은 불가능하니까
- 그거 아니었음.. 그냥 LCS처럼 LIS라고 공식이 있었다;;; 이걸 몰랐어!!!
- 아니 진짜 그냥 LIS, LCS는 그냥 모르는거 나온 김에 포스팅으로 정리하고 암기하고 해야할듯하다 ㅠ 이해를 하든가...
- 내일 정리해야겠다 진짜로..

[보완점]
LIS란..?
- LIS (Longest Increasing Subsequence, 최장 증가 부분 수열)
- LIS는 주어진 수열에서 원소들의 순서를 유지하면서 가능한 가장 긴 증가하는 부분 수열을 찾는 문제이다
    - 예를들면 입력된 수열이 오른쪽과 같을 때 (10 20 10 30 20 50)
        가능한 증가 부분 수열
        [10, 20]
        [10, 20, 30]
        [10, 20, 50]
        [10, 30, 50]
        [10, 20, 30, 50] (최장 길이: 4)

        따라서 이 경우, LIS의 길이는 4다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());  // 포트 개수
        int[] ports = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();  // 문자열로 입력받아서는 Int로 각각 매핑한 배열 만드는 것

        // LIS를 구하기 위한 리스트
        ArrayList<Integer> lis = new ArrayList<>();

        for (int port : ports) {
            int pos = Collections.binarySearch(lis, port);

            if (pos < 0) pos = -(pos + 1);  // Lower bound 위치 찾기

            if (pos == lis.size()) lis.add(port);  // 새로운 최대값이면 추가
            else lis.set(pos, port);  // 기존 값 대체 (길이는 유지)
        }

        System.out.println(lis.size());  // LIS 길이 출력
    }
}