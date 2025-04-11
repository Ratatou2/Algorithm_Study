/*
[백준]
12738, 가장 긴 증가하는 부분 수열 3

[문제파악]
- 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
- 예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.

[입력]
- 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.
- 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (-1,000,000,000 ≤ Ai ≤ 1,000,000,000)

[출력]
- 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

[구현방법]
- 이분탐색? 될 때까지 해야겠지?
- 어제 풀었던 문제에서 조건이 달라져서, 골2가 된 문제다!
- 재도전!
- 이 문제의 요점은 이분탐색을 통해 교체할 수 있는 지점을 찾는 것임.
- LIS를 구하는 것이 아니니까 길이만 구하면 된다

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer> LIS;

    static int findnLowerBound(int num) {
        int result = -1;

        // 교체할 수 있는 지점을 찾아야한다
        // 즉, left == right가 되는 지점을 찾아야 함
        // 근데 여기서 right = size - 1 해버리면, 1개 밖에 없을 때 left = 0 = right가 되어버려서 터지니까 (left == right) 지점을 찾을 때 right는 size 그대로 준다
        int left = 0;
        int right = LIS.size();

        // (left == right) 같아지는 지점까지 탐색한다
        while (left < right) {
            int mid = (left + right) / 2;

            // LIS의 중간값 보다 현재 입력값이 더 작으면?
            // 1) right를 mid로 옮긴다 (#1)
            // 2) 답일지도 모르니까 reesult에 갱신한다
            if (num <= LIS.get(mid)) {  // (#3)
                right = mid;  // (#2)
                result = mid;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        LIS = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int curr = Integer.parseInt(st.nextToken());

            // LIS가 비어 있거나, 끝자락에 있는 숫자보다 더 트면? List 끝에 추가하면 된다
            if (LIS.isEmpty() || LIS.get(LIS.size() - 1) < curr) {
                LIS.add(curr);
            } else {
                int index = findnLowerBound(curr);
                if (index != -1) LIS.set(index, curr);
            }
        }

        System.out.println(LIS.size());
    }
}

/* (#1) 왜 right를 옮겨야할까?
 * 개인적으로는 이 부분이 헷갈리기 쉬운 부분이라고 생각한다
 * 일단 왜 right를 옮길까?
 * 그 이유는 교체할 친구는, 자기 자신보다 크면서, 그중에 가장 작은 숫자를 찾는 것이 목표이기 때문이다
 * 즉, 90, 100, 110이 있고 input이 92이면 100을 찾아야한단 소리다
 * 그러면 현재 중앙값이 100이니까 mid를 1로 기록해뒀다
 * 이제 다음 로직은 어떻게 될까? 92가 들어가려면 100 이상에는 못 들어간다 (LIS니까)
 * 그러면 mid를 right로 잡고, 혹시 100보다 더 작으면서 자신(92)보단 클 누군가를 찾아야한다
 * 다시 정리하면 90, 95, 100, 105, 110이 있다고 가정할 경우, LIS를 위한 최적의 위치는 90과 95 사이이다
 * 그러면? 중앙값이 100을 우선적으로 기록하고 나면? right를 110에서 100으로 옮겨와야 그 다음 탐색으로 95를 찾아볼 가능성이라도 생기는 것이다
 * 이것이 계속해서 이분탐색을 해야하는 이유이고, Math.min으로 탐색하지 않아도 되는 이유다
 * mid를 기준으로해서 왼쪽은 자기 자신보다 작은 숫자들일테고, 오른쪽은 자기 자신보다 큰 숫자일 것이 확실하기 때문이다 (이분탐색 + 정렬 로직이라서)
 * 즉 num < LIS.get(mid)로 자기 자신보다 큰 숫자 지점을 찾았다면 right를 줄여야하는 것이다
 * */

/* (#2) 왜 right = mid - 1을 하면 안될까?
 * 이게 LowerBound의 핵심이기 때문이다
 * 이렇게 mid - 1을 해버리면 mid가 답일 가능성을 배제하게 된다
 * 그 값이 왼쪽 or 오른쪽에 있느냐 없느냐만 따지고 싶은 탐색의 범주 내에 있는 것이라면 그냥 mid가 찾는 답인 순간 return 해버리면 된다
 * 반대로 mid가 값의 후보긴하나 더 작은, 큰 값이 있는지 탐색하고 싶다면 그 mid값 조차도 계속 남겨둬야 정확한 값을 찾을 수 있는 것이다
 * 온전하게 이해는 안됐지만... 그냥 공식 같은거라고 생각하면 납득 못할 정도는 아님
 * */

/*
 * (#3) 왜 같은 값까지 덮어줘야할까?
 * LIS라서 생기는 문제이다
 * 나는 어차피 같은 값이면 굳이 덮어줄 필요가 있느냐 였는데 이 문제는 실제 LIS를 구하는 것이 아니라 LIS '같은' 길이를 구하는 것이기 때문에 덮어씌워야 한다고...
 * 그러니까 요지는 '항상' 이후 들어올 값들을 위해 항상 가장 작은 값 상태를 유지해야 하니까, 같은 값이어도 set해서 덮어 씌워버리는게 안정성이 더 높다는 의미다
 * */