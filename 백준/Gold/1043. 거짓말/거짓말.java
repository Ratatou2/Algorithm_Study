

/*
[백준]
1043, 거짓말

[문제파악]
지민이는 파티에 가서 이야기 하는 것을 좋아한다.
파티에 갈 때마다, 지민이는 지민이가 가장 좋아하는 이야기를 한다.
지민이는 그 이야기를 말할 때, 있는 그대로 진실로 말하거나 엄청나게 과장해서 말한다.
당연히 과장해서 이야기하는 것이 훨씬 더 재미있기 때문에, 되도록이면 과장해서 이야기하려고 한다.
하지만, 지민이는 거짓말쟁이로 알려지기는 싫어한다.
문제는 몇몇 사람들은 그 이야기의 진실을 안다는 것이다.
따라서 이런 사람들이 파티에 왔을 때는, 지민이는 진실을 이야기할 수 밖에 없다.
당연히, 어떤 사람이 어떤 파티에서는 진실을 듣고, 또다른 파티에서는 과장된 이야기를 들었을 때도 지민이는 거짓말쟁이로 알려지게 된다.
지민이는 이런 일을 모두 피해야 한다.
사람의 수 N이 주어진다. 그리고 그 이야기의 진실을 아는 사람이 주어진다.
그리고 각 파티에 오는 사람들의 번호가 주어진다. 지민이는 모든 파티에 참가해야 한다.
이때, 지민이가 거짓말쟁이로 알려지지 않으면서, 과장된 이야기를 할 수 있는 파티 개수의 최댓값을 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 사람의 수 N과 파티의 수 M이 주어진다.
둘째 줄에는 이야기의 진실을 아는 사람의 수와 번호가 주어진다.
진실을 아는 사람의 수가 먼저 주어지고 그 개수만큼 사람들의 번호가 주어진다.
사람들의 번호는 1부터 N까지의 수로 주어진다.
셋째 줄부터 M개의 줄에는 각 파티마다 오는 사람의 수와 번호가 같은 방식으로 주어진다.
N, M은 50 이하의 자연수이고, 진실을 아는 사람의 수는 0 이상 50 이하의 정수, 각 파티마다 오는 사람의 수는 1 이상 50 이하의 정수이다.

[출력]
첫째 줄에 문제의 정답을 출력한다.

[구현방법]
- 핵심은 파티에서 두가지 선택지가 있다는 것
    1) 진실만을 말한다
    2) 과장해서 말한다
- 그렇게해서 들키지 않아야하기 떄문에 진실을 말해버린 경우 해당 파티에 있는 모든 사람들이 참여하는 다른 파티에서는 거짓말을 할 수가 없다
- 이 문제는 LinkedList를 이용할수도, 인접행렬을 이용할수도 있다

[보완점]
- 이 문제는 Union-Find 알고리즘을 쓰는게 더 깔끔하고 효율적이다
    1) 불필요한 메모리 낭비없이, 사랑 & 그룹 관계 표현 가능
        - 인접행렬만 봐도 N^2 메모리가 필요함 (근데 사실 50명 제한에 2초라서 하려면 할 순 있음 - 물론 다른 방식에 비해 압도적으로 느리겠지)
        - 반면에 Union-Find는 부모 배열만 필요해서 O(N)이면 된다
    2) 동전 연결 처리가 훨씬 빠름 (여기서는 파티별 사람 연결을 의미)
        - Union(a,b) : 두 사람을 같은 그룹으로 합침
        - Find(a) == Find(b) : 두 사람이 같은 그룹인지 비교
        - 확실히 인접행렬이랑 List로 해결하려면 연결된 관계를 일일이 따라가야하는 번거로움이 있긴함
*/

import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    static void union (int x, int y) {
        int root_X = find(x);
        int root_Y = find(y);

        // 둘의 부모가 같지 않으면 root_X를 root_Y의 부모로 세팅
        if (root_X != root_Y) {
            parent[root_Y] = root_X;
        }
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 사람의 수
        int M = Integer.parseInt(st.nextToken());   // 파티의 수
        int count = 0;      // 정답 카운트

        List<List<Integer>> parties = new ArrayList<>();  // 파티 저장할 이중 리스트

        // 부모 노드 기록 초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int truthPeopleCount = Integer.parseInt(st.nextToken());    // 비밀을 아는 사람들 수
        Set<Integer> whoKnowTruth = new HashSet<>();                // 비밀을 아는 사람들
        Set<Integer> whoKnowTruthRoot = new HashSet<>();            // 비밀을 아는 사람들의 루트

        // 비밀을 아는 사람이 없을 경우(= 0)에는 파티 수(M)가 답이다 (즉시 종료)
        if (0 == truthPeopleCount) System.out.println(M);
        else {
            // 비밀을 아는 사람 저장
            while (st.hasMoreTokens()) {
                whoKnowTruth.add(Integer.parseInt(st.nextToken()));
            }

            // 각 파티에 온 사람들을 Union함
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int partyPoepleCount = Integer.parseInt(st.nextToken());

                // 현 파티 참여 인원들 추가
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j < partyPoepleCount; j++) {
                    temp.add(Integer.parseInt(st.nextToken()));
                }

                // 파티 리스트에 현 파티 추가
                parties.add(temp);

                // 기준점이 되는 사람은 아무나 잡아도 된다 (어차피 진실만을 말해야하는 모든 사람을 하나의 group으로 만들거라서 그렇다)
                // 여기서는 첫번째 사람을 기준으로 잡았음
                for (int j = 1; j < partyPoepleCount; j++) {
                    union(temp.get(0), temp.get(j));
                }
            }

            // 비밀을 아는 사람들의 root를 구한다
            for (int temp : whoKnowTruth) {
                whoKnowTruthRoot.add(find(temp));
            }

            // 파티에 온 사람들 체크하며 진실만을 말해야하는 그룹인지 체크
            for (List<Integer> party : parties) {
                boolean onlyTruth = false;

                for (int temp : party) {
                    if (whoKnowTruthRoot.contains(find(temp))) {
                        onlyTruth = true;
                        break;
                    }
                }

                // 거짓을 말해도 된다면 count + 1
                if (!onlyTruth) count++;
            }

            System.out.println(count);
        }
    }
}
