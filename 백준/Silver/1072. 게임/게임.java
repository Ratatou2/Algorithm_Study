/*
[백준]
1072, 게임

[문제파악]
- 게임 횟수 : X
- 이긴 게임 : Y (Z%)
- Z가 승률이고 소숫점은 버린다.
- X와 Y가 주어졌을 때 최소 게임을 몇번 더 해야 Z가 변하는지 구해라
- 참고로 앞으로의 모든 게임은 이긴다는 전제 조건이 있다.

[입력]
- 첫줄에 X와 Y

[출력]
- 첫줄에 최소 몇판을 더 해야하는지 출력
- Z가 절대 변하지 않는다면 -1 출력

[구현방법]
- 기본적으로 평균을 구할 때 수식을 주의해야한다 int로 주고 계산할거면 Y에 100을 곱하고 시작해야함
- 두번째는 승률이 100퍼인 경우, 계속 이기는 지금 상황에선 변동될 수 없으므로 -1을 빠르게 출력하는 것이 중요
- 세번째는 멈추는 조건이 하나 더 있다는 것 (이전 확률에 비해서 변하는 순간)
- 위 사항들을 유의하고 풀이에 임할 것

[보완점]
- 소숫점 버릴 때 (int)로만 하면 0.5부턴 반올림 시작한다
- Math.floor는 버림을 해주는 친구기 때문에 이걸 쓰는게 좋다
- 아오 왤케 조건 설정하는게 헷갈리냐
- 손코딩해보니 이론은 이해하고 있었음
- 고대로 갖다 옮기는 과정에서 헷갈려했음
- 틀렸다는데 설마 형변환 문제..?
- 30000000 29999999을 입력받으면 15000001을 토해내고 죽는 이슈...
    - 99% 이상이면 -1을 해야할듯
- 계속된 실패 double 문제인듯 하다
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Integer.parseInt(st.nextToken());
        long Y = Integer.parseInt(st.nextToken());
        long Z = (Y * 100) / X;  // 나눗셈 신경써줘야한다. 특히 소숫점 버리는거 (파이썬이 그립다..)

        long min = 0;
        long max = X;  // 최댓값은 전체 범위까지 포함할 수 있어야 한다

        if (99 <= Z) min = -1;
        else {
            while (min <= max) {
                long mid = (max + min) / 2;
                long currentX = X + mid;
                long currnetY = Y + mid;

                long currentPercent = (currnetY * 100) / currentX;  // 새로 계산된 확률
                if (0 < currentPercent - Z) {  // 움직였다면, max를 출여가며 다시 시도 (평균치의 감소가 필요)
                    max = mid - 1;
                } else {  // 움직이지 않았다면, min을 늘려가며 다시 시도 (평균치의 증가가 필요)
                    min = mid + 1;
                }
            }
        }

        System.out.println(min);
    }
}