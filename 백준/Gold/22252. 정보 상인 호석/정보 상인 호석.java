/*
[백준]
22252, 정보 상인 호석

[문제파악]
- 일단 정보를 긁어모으기 위해서 호석이는 여러 정보 고릴라들에게 정보를 구매하려고 한다.

- 암흑가의 연락망은 빼곡하기 때문에 누가 어떤 정보를 얻었는지에 대한 찌라시들이 수시로 퍼진다.
- 찌라시로 알 수 있는 것은, 어떤 이름을 가진 고릴라가 C_1 , C_2 , ..., C_k 만큼의 가치가 있는 정보 k 개를 얻었다는 점이다.
- 호석이는 이를 바탕으로 임의의 시점에 특정 고릴라에게 정보를 몇 개 살 것인지를 정할 수 있다.
- 이때 가치 순으로 가장 비싼 정보들을 구매한다.
- 예를 들어 고릴라가 가진 정보가 10개이고, 호석이가 사고 싶은 정보 개수가 4개라면, 고릴라는 10개 중에서 가치 순으로 가장 비싼 4개를 팔 것이다.
- 한 번 거래한 정보는 호석이에게 더 이상 가치가 없기 때문에 고릴라도 그 정보를 파기한다.
- 당신은 암흑가의 주먹이며 양대 산맥이 될 가능성이 있는 호석이를 주시하고 있다.
- 관찰하면서 얻은 정보는 총 Q 개이다.
- 각 정보는 다음의 2가지 중 하나이다.

    - 1 Name k C_1, C_2, ..., C_k : 이름이 [Name]인 고릴라가 k 개의 정보를 얻었으며, 각 가치는 C_1 부터 C_k 이다.
    - 2 Name b : 호석이가 이름이 [Name]인 고릴라에게 b 개의 정보를 구매한다.
        이때 고릴라가 가진 정보들 중 가장 비싼 b 개를 구매하며, 고릴라가 가진 정보가 b 개 이하이면 가진 모든 정보를 구매한다.
        견제를 위해서 호석이가 가진 정보들의 가치 총합, 즉 호석이가 정보들을 구매하는 데에 쓴 돈의 총합을 구하자.

[입력]
- 고릴라들이 정보를 얻는 사건과 호석이가 거래하는 정보가 시간순으로 주어진다.
- 첫 번째 줄에는 쿼리의 개수 Q 가 주어진다.
- 이어서 Q 개의 줄에 걸쳐서 각 줄에 쿼리가 주어진다.
- 쿼리는 1이나 2로 시작한다. 1로 시작하는 경우에는 정보를 얻은 정보 고릴라의 이름과 k 가 주어지며 이어서 k 개의 정보 가치 C_1, ..., C_k 가 자연수로 주어진다.
- 모든 C_i 는 1 이상 100,000 이하이다.
- 2로 시작하는 경우에는 호석이가 거래하려는 정보 고릴라의 이름과 구매하려는 정보의 개수 b 가 주어진다.

    [제한]
    1 ≤ Q ≤ 100,000, Q 는 자연수
    모든 Name은 알파벳 소문자 혹은 대문자로 이루어져 있고 공백이 없으며 길이는 1 이상 15 이하이다.
    1 ≤ k ≤ 100,000, k 는 자연수
    1 ≤ C ≤ 100,000, C 는 자연수
    1 ≤ b ≤ 100,000, b 는 자연수
    모든 쿼리에 대한 k 의 합은 1,000,000 을 넘지 않는다.

[출력]
- 모든 쿼리가 종료되었을 때에 호석이가 얻게 되는 정보 가치의 총합을 출력하라.

[구현방법]
- 일단, Map 구조랑 PQ 구조가 있어야한다
- 키워드에 따라 동작 방식이 나뉘기 때문에 키워드를 저장해줄 Map이 있어야하는 것이고 그 키워드 안에 정보가 들어있으니 PQ도 필요하다
- StringTokenizer로 첫번쨰가 1, 2인지 구분하고 그 뒤 분기처리하면 된다
- 아니 근데 답이 int 범위 초과하잖아 똑바로 안보냐?

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, PriorityQueue<Integer>> gorillas = new HashMap<>();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        
        long totalCost = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int num = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            
            if (num == 1) {  // 고릴라가 정보를 얻음
            int howMany = Integer.parseInt(st.nextToken());  // 몇 개 얻었는지
            
            // 등록된 적 없는 고릴라면 추가 (역방향 정렬 -> 가장 비싼 순서)
            gorillas.putIfAbsent(name, new PriorityQueue<>(Comparator.reverseOrder()));
            for (int j = 0; j < howMany; j++) {
                gorillas.get(name).add(Integer.parseInt(st.nextToken()));
            }
            } else {  // (num == 2) 고릴라에게 정보를 구매
                int count = Integer.parseInt(st.nextToken());
                for (int j = 0; j < count; j++) {
                    // 등록된적 없는 고릴라거나 PQ에 든게 없으면 break;
                    if (gorillas.get(name) == null || gorillas.get(name).isEmpty()) break;
                    
                    totalCost += gorillas.get(name).poll();  // 고릴라가 가진 가장 비싼 정보 구매
                }
            }
        }
        
        System.out.println(totalCost);
    }
}
