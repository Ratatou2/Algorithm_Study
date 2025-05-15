/*
[백준]
25166, 배고픈 아리의 샌드위치 구매하기

[문제파악]
- "두리"라는 나라가 있다.
- 이 나라에서 사용되는 동전은 1원, 2원, 4원, 8원, 16원, 32원, 64원, 128원, 256원, 512원짜리 이렇게 총 10가지이다.
- 이 나라의 국민인 아리는 10가지의 동전을 각각 1개씩 총 10개를 가지고 있다.
- 아리는 샌드위치를 사러 빵집에 가기로 했다.
- 그런데 빵집에 잔돈이 없어서 샌드위치 가격 S 을 정확하게 지불하지 않으면 샌드위치를 살 수 없다고 한다.
- 아리가 가지고 있는 동전들을 가지고 계산을 하던 도중 아리의 친구인 쿠기가 마침 빵집에 들어왔다.
- 쿠기는 10종류의 동전 중에 모든 종류가 아니라 일부 종류의 동전을 각각 1개씩 가지고 있다.
- 쿠기가 가지고 있는 동전들의 총 금액은 M 원이다.
- 쿠기는 아리에게 자신이 가진 돈 중에 일부 또는 전체를 흔쾌히 빌려줄 수 있다고 한다.
- 단, 아리는 양심 상 자신의 돈을 남긴 상태로 쿠기에게 돈을 빌릴 수는 없어서 자신이 가진 돈을 모두 사용한 후에 부족한 금액에 대해서 쿠기에게 돈을 빌리려고 한다.
- 아리는 상황에 따라 쿠기에게 인사를 다르게 하려고 한다. 아리는 과연 쿠기에게 뭐라고 할지 구해보자!

[입력]
- 첫 번째 줄에 공백을 기준으로 샌드위치 가격 S(1 ≤ S ≤ 2048)와 쿠기가 가지고 있는 금액 M(1 ≤ M ≤ 1023)이 주어진다.

[출력]
- 아리의 돈으로만 살 수 있다면 "No thanks"를 출력하고, 쿠기의 도움을 받아야만 살 수 있다면 "Thanks" 그리고 쿠기가 돈을 빌려주더라도 샌드위치를 살 수 없다면 "Impossible"를 출력한다.

[구현방법]
- 이게 어떻게 비트마스킹...? bitset으로, 또는 비트 배열로 껐다 키면서 금액을 구해야하는듯?
- 감이 안잡혀서 찾아보니 뭔 DP...? 이딴게 브론즈1...?
- 역시 그게 아니었다 일부 하드코딩의 결과물로 브론즈 1답게 풀렸음
- 내가 헷갈렸던 부분은 if ((rest & coogie) == rest) sb.append("Thanks");인데 이 부분은 아래와 같이 설명할 수 있겠다
    - cost = 1040 → 아리가 1023원이 있으므로 부족한 금액: rest = 17
    - rest = 17 → 비트로: 0000010001 → 필요 동전: 16원 + 1원
    - coogie = 49 → 비트로: 000110001 → 32원 + 16원 + 1원 보유
- 결국, 잔금이랑 쿠기가 가진 돈이랑 &를 했다는건? 서로 가지고 있는 재화가 같은 경우만 1이 된다는 의미
- & 이후의 값이 여전히 rest라는 것은? 쿠기가 가진 돈의 조합이 rest를 충족할 수 있다는 의미!!!
- 그렇기 때문에 다음과 같은 코드가 나온 것이다

[보완점]
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
        
        int cost = Integer.parseInt(st.nextToken());
        int coogie = Integer.parseInt(st.nextToken());

        // 현재 아리가 보유하고 있는 모든 돈은 1024원이다
        // 즉, 비용이 아리가 보유한 돈보다 적다면 빌릴 필요가 없음
        if (cost < 1024) sb.append("No thanks");
        else {  // 남은 돈을 구해서 얼마나 빌려야하는지 확인
            int rest = cost - 1023;

            if ((rest & coogie) == rest) sb.append("Thanks");  // 남은 돈에서 쿠기가 가지고 있는 돈을 비트 연산자 &를 하면 남은 돈과 같다면 낼 수 있다.
            else sb.append("Impossible");  // 쿠기의 돈을 합쳐도 낼 수 없다.
        }

        System.out.println(sb);
    }
}