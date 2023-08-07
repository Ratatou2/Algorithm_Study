package Algorithm_0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class 백준_11660_PrefixSum5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        int towerCount = Integer.parseInt(br.readLine());
        int[] towerList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] maxTower = {0, towerList[0]};  // 가장 왼쪽의 타워로 최고층 초기화 (인덱스, 타워 높이)
        int[] lazerHit = new int[towerCount];  // 레이저 맞추는 탑 체크 리스트

        lazerHit[0] = 0;  // 가장 왼쪽의 타워는 레이저 맞출 곳이 없으니 초기화
        for (int i = 1; i < towerCount; i++) {

            // 이전 타워의 높이(i-1)가 나(i)보다 높으면 현재 탑의 레이저는 이전 타워가 맞음
            if (towerList[i] <= towerList[i - 1]) {
                lazerHit[i] = i; // 타워는 인덱스와 달리 1번부터 시작이니까 1을 빼지 않는다
                continue;
            }

            // 이전 타워가 나보다 작으면, maxHeight의 탑에 맞는지 확인
            // 내가 더 큰 경우는 maxTower를 나로 초기화
            if (maxTower[1] < towerList[i]) {
                maxTower[0] = i;
                maxTower[1] = towerList[i];
                continue;
            }

            // 내가 maxTower보다 작으면 내 레이저는 maxTower에 맞음
            // 배열이니까 타워 순번은 + 1
            lazerHit[i] = maxTower[0] + 1;

        }
        System.out.println(Arrays.toString(lazerHit));
    }
}