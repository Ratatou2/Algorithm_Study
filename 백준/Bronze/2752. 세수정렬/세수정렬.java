/*
[백준]
2752, 세수정렬

[문제파악]
- 동규는 세수를 하다가 정렬이 하고 싶어졌다.
- 정수 세 개를 생각한 뒤에, 이를 오름차순으로 정렬하고 싶어졌다.
- 정수 세 개가 주어졌을 때, 가장 작은 수, 그 다음 수, 가장 큰 수를 출력하는 프로그램을 작성하시오.

[입력]
- 정수 세 개가 주어진다.
- 이 수는 1보다 크거나 같고, 1,000,000보다 작거나 같다.
- 이 수는 모두 다르다.

[출력]
- 제일 작은 수, 그 다음 수, 제일 큰 수를 차례대로 출력한다.

[구현방법]
- 초심으로 돌아가서 다시
- 일단 세 가지 숫자는 다 다르다고 한다
- 그냥 배열로 입력을 집어넣고 정렬하는게 제일 빠를듯하다

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] nums = new int[3];

        // 숫자 입력 받기
        for (int i = 0; i < 3; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(nums);

        // 출력
        for (int temp : nums) {
            sb.append(temp).append(" ");
        }

        System.out.println(sb);
    }
}