package Algorithm_0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

class TowerInfo {
    int index;
    int height;

    TowerInfo(int index, int height) {
        this.index = index;
        this.height = height;
    }
}

public class 백준_2493_Tower {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<TowerInfo> towerStack = new Stack<TowerInfo>();

        int towerCount = Integer.parseInt(br.readLine());
        int[] towerList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] lazerHit = new int[towerCount];  // 레이저 맞추는 탑 체크 리스트
        towerStack.push(new TowerInfo(0, towerList[0]));  // 초기값 세팅

        for (int i = 0; i < towerList.length; i++) {
            // 스택이 비어 있지 않은 경우
            if (!(towerStack.isEmpty())) {
                while (true) {
                    // 스택 비어 있으면 break;
                    if (towerStack.isEmpty()) break;

                    TowerInfo prevTower = towerStack.peek();

                    // 이전 타워 값이 현재 타워보다 높으면 이전 타워가 현재 타워의 레이저가 닿는 곳이란 뜻
                    // 현재 타워 레이저 닿는 곳 이전 타워 값으로 업데이트 후 stack에 push
                    if (towerList[i] < prevTower.height) {
                        lazerHit[i] = prevTower.index + 1;
                        towerStack.push(new TowerInfo(i, towerList[i]));
                        break;
                    } else if (prevTower.height <= towerList[i]) {  // 현재 타워가 더 클 경우
                        towerStack.pop();  // 이전 타워값 치워버리고 반복
                    }
                }
            } else towerStack.push(new TowerInfo(i, towerList[i]));  // 스택이 비어있으면 그냥 현재 타워 index, height 스택에 넣고 끝
        }

        for (int temp : lazerHit) System.out.print(temp + " ");
    }
}

// 주의할 점
// 높이가 같으면 레이저 안 닿는다고 봄 -> 이거 주의해서 코드 짤것
// stack으로 구현해야 편함

// 실수했던 지점
// 1. 일단 레이저가 아무데도 닿지 않아도 그 타워의 값을 towerStack에 넣어줘야 함
// 2. 높이가 같을 땐 레이저가 안 닿는다
// => while문 안에서 스택이 비어있을 때의 처릭가 누락되어 있었음