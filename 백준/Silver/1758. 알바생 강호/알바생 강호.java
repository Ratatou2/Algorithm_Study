/*
[백준]
1758, 알바생 강호

[문제파악]
- 8시 될 때까지 문앞에서 줄, 들어갈 때 강호에게 팁을 줌
- 자기 커피가 몇번째인지에 따라 팁을 다르게 줌
- 각 손님은 [원래 주려고 생각했던 돈 - (받은 등수 - 1)]만큼의 팁을 줌
- 위의 계산식으로 음수가 나오면 팁을 받을 수 없음
- 손님의 순서를 바꿔 팁의 최댓값을 구할 것

[입력]
- 첫쨋줄에 사람수 N (N <= 100,000)
- 둘쨋줄에 손님이 주는 팁 순서대로 (tip < 100,000)

[출력]
- 최대 tip

[구현방법]
- 사실상 많이 받을 수 있는 사람부터 정렬하면 되는 것
- 다만 자바는 편리하게 배열을 역순으로 돌려주는 기능이 없음 ㅠ (파이썬... 그립다...)
- 아래와 같이 collection 쓰는게 편하긴한데 매번 변환해주는거 말고 for문을 역순으로 써보기로 결정
1) 원본 배열을 List로 변환
List<Integer> list = Arrays.asList(arr);

2) Collections.reverse() 메소드를 사용하여 list를 거꾸로 변환
Collections.reverse(list);

3) 리스트를 배열로 변환
Integer[] reverseArr = list.toArray(arr);

[보완점]
- 계산식을 거쳤는데 음수이면 더하지 않는다는 점
- 역순으로 for문 돌릴 땐, 으레 그렇듯 index 조심할 것
- int형이라 오버플로가 일어날 수 있다는 것... 이걸 체크 못했네... 파이썬이 그립다...
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] tips = new int[N];
        long totalTip = 0;

        for (int i = 0; i < N; i++) {
            tips[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(tips);  // tip의 오름차순 정렬
//        System.out.println(Arrays.toString(tips));

        // 역순(팁이 큰 순서)부터 시작
        for (int i = 1; i < N + 1; i++) {
            int currentTip = tips[N-i] - (i - 1);
//            System.out.println(tips[N-i] + " : 현재 돈 / " + currentTip + " : 계산된 돈");
            totalTip = 0 < currentTip ? totalTip + currentTip : totalTip;
        }

        System.out.println(totalTip);
    }
}