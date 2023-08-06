import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_1247_OptimalPath {
    static int clients;
    static int[] isVisited;
    static int minTotalDistance;
    static int[] location;
    static int[][] subLocation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < testCase + 1; tc++) {
            clients = Integer.parseInt(br.readLine());
            location = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            isVisited  = new int[clients];
            minTotalDistance = Integer.MAX_VALUE;
            subLocation = new int[clients][];

            int[] tempLocation = Arrays.copyOfRange(location, 4, location.length);

            for (int i = 1; i <= clients; i++) {
                subLocation[i - 1] = new int[] {tempLocation[(i * 2) - 2], tempLocation[(i * 2) - 1]};
            }

            visitClient(0, location[0], location[1], 0, "");
            System.out.println("#" + tc + " " + minTotalDistance);
        }
    }

    private static void visitClient(int moveCount, int x, int y, int totalDistance, String path){
        // totalDistance가 0이 아니고, 이전에 구한 최솟값보다 크면 더 진행할 필요없음
        if (totalDistance != 0 && minTotalDistance < totalDistance) return;

        if (moveCount == clients) {
            int homeX = location[2];
            int homeY = location[3];

            // 고객 다 돌았으니까 집까지의 거리 계산 필요
            minTotalDistance = Math.min(totalDistance + (Math.abs(x-homeX) + Math.abs(y-homeY)), minTotalDistance);
            return;
        }

        for (int i = 0; i < clients; i++) {
            // 방문했던 곳이면 안갈거임
            if (isVisited[i] == 1) continue;

            int nextX = subLocation[i][0];
            int nextY = subLocation[i][1];
            int tempDistance = Math.abs(x - nextX) + Math.abs(y - nextY);

            isVisited[i] = 1;
            visitClient(moveCount + 1, nextX, nextY, totalDistance + tempDistance, path + i + " ");
            isVisited[i] = 0;
        }
    }
}

// 실수한 지점
// nextX, nextY 같은 것들에 값을 줄 때 복붙해서 쓰는 경우가 있는데 값 제대로 수정했는지 index 확인 잘하자...

// 수정 및 개선 필요한 지점
// 메모리 겁나 많이씀 => 배열이 세개나 되어서 그럼
// 효율적으로 관리하는 방법 필요

// 개선해야할 지점
// 다른 코드 참조해보면 입력 받는 부분을 저렇게 어렵게 할 필요가 없음
// 좀 귀찮더라도 회사와 집을 하나씩 따로 받고 그 이후 값들만 따로 빼면 됨
// 예시는 아래와 같다
/*			
String[] line = br.readLine().split(" ");
int str_r = Integer.parseInt(line[0]); // 출발지 좌표
int str_c = Integer.parseInt(line[1]);
dst_r = Integer.parseInt(line[2]); // 목적지 좌표
dst_c = Integer.parseInt(line[3]);
for(int i=4, j=0; i<line.length; i+=2, j++) {
	dr[j] = Integer.parseInt(line[i]);
	dc[j] = Integer.parseInt(line[i+1]);
}
*/

// 그리고 좀 더 개선하고 싶으면 거리를 구하는 abs가 지금 두군데서 쓰이고 있으니 메서드로 따로 빼도 됨