package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_12891_DNAPassword {
    static String[] stringList;
    static int[] tempCount = new int[4];
    static int[] DNAStringCount;
	
    static void addString (int index) {
        switch (stringList[index]) {
            case ("A") : tempCount[0]++; break;
            case ("C") : tempCount[1]++; break;
            case ("G") : tempCount[2]++; break;
            case ("T") : tempCount[3]++; break;
        }
    }

    static void minusString (int index) {
        switch (stringList[index]) {
            case ("A") : tempCount[0]--; break;
            case ("C") : tempCount[1]--; break;
            case ("G") : tempCount[2]--; break;
            case ("T") : tempCount[3]--; break;
        }
    }

    static boolean checkCondition () {
        for (int i  = 0; i < 4; i++) {
            if (tempCount[i] < DNAStringCount[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tempInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int S = tempInput[0];
        int P = tempInput[1];

        stringList = br.readLine().split("");
        DNAStringCount = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int totalCount = 0;

        // 초기값 세팅
        for (int i = 0; i < P; i++) {
            addString(i);
//            System.out.print(stringList[i] + " ");
        }
//        System.out.println(Arrays.toString(tempCount));
        if (checkCondition()) totalCount++;

        // 한칸씩 이동하며 계산
        for (int i = 1; i < S - P + 1; i++) {
            int removeIndex = i - 1;  // 한칸 이동했으니 맨 앞의 인덱스는 빼주고
            int addindex = i + P - 1;  // 맨 뒤에서 한칸 추가된 인덱스는 추가
//            System.out.println(stringList[removeIndex] + "  " + stringList[addindex]);
            addString(addindex);
            minusString(removeIndex);

            if (checkCondition()) totalCount++;  // 이동했는데 조건 만족하면 totalCount에 +1 해주고

        }
        System.out.println(totalCount);
    }
}

// 문자열 슬라이싱 하면 시간 초과나서 터짐
// P 만큼 문자열 슬라이싱
//            for (int start = i; start < i + P; start++) {
//                switch (stringList[start]) {
//                    case ("A") : {
//                        if (0 < tempCount[0]) tempCount[0]--;
//                        break;
//                    }
//                    case ("C") : {
//                        if (0 < tempCount[1]) tempCount[1]--;
//                        break;
//                    }
//                    case ("G") : {
//                        if (0 < tempCount[2]) tempCount[2]--;
//                        break;
//                    }
//                    case ("T") : {
//                        if (0 < tempCount[3]) tempCount[3]--;
//                        break;
//                    }
//                }
//            }

// 실수한 점
// 더하는 경우와 빼는 경우 둘다 있어야 함

// 조건에 해당하는 친구들을 어떻게 계산해야 좋을지(효율적일지 고민하다가...)
// 강사님 조언 => 조건문 걸어서 복잡하게 +-하는 것보단 그냥 전부 다 더하고 전부다 빼서 원본과 비교하는 방식이 깔끔 (추후에 다른분들 코드 참조하고 개선할 것)


//    static void addString (int index) {
//        switch (stringList[index]) {
//            case ("A") : if (0 < DNAStringCount[0]) tempCount[0]++; break;
//            case ("C") : if (0 < DNAStringCount[1]) tempCount[1]++; break;
//            case ("G") : if (0 < DNAStringCount[2]) tempCount[2]++; break;
//            case ("T") : if (0 < DNAStringCount[3]) tempCount[3]++; break;
//        }
////        System.out.println("addString " + stringList[index] + Arrays.toString(tempCount));
//    }
//
//    static void minusString (int index) {
//        switch (stringList[index]) {
//            case ("A") : if (0 < DNAStringCount[0]) tempCount[0]--; break;
//            case ("C") : if (0 < DNAStringCount[1]) tempCount[1]--; break;
//            case ("G") : if (0 < DNAStringCount[2]) tempCount[2]--; break;
//            case ("T") : if (0 < DNAStringCount[3]) tempCount[3]--; break;
//        }
//
////        System.out.println("minusString " + stringList[index] + Arrays.toString(tempCount));
//    }

// 지금처럼 입력값을 전부 저장하는 것이 아닌 받을때마다 처리하면 메모리 덜 쓸 수 있음