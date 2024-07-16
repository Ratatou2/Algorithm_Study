package Algorithm_0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_16935_배열돌리기3 {
    static void printForAnswer (int[][] answer) {
        for (int row = 0; row < answer.length; row++){
            for (int column = 0; column < answer[0].length; column++) {
                System.out.print(answer[row][column] + " ");
            }
            System.out.println();
        }
    }

    static int[][] upSideDown (int[][] inputArray, int inputRow, int inputColumn) {
//        System.out.println("upside Down");
        int[][] upsideDown = new int[inputRow][inputColumn];

        for (int row = 0; row < inputRow; row++) upsideDown[inputRow - row - 1] = inputArray[row];

        return upsideDown;
    }

    static int[][] reverse (int[][] inputArray, int inputRow, int inputColumn) {
//        System.out.println("reverse");
        int[][] reverse = new int[inputRow][inputColumn];

        for (int row = 0; row < inputRow; row++) {
            int[] temp = new int[inputColumn];  // 거꾸로 담을 임시 배열

            // 좌우 바꿔 넣기
            for (int r = 0; r < inputColumn; r++) temp[r] = inputArray[row][inputColumn - r - 1];
            reverse[row] = temp;
        }

        return reverse;
    }

    static int[][] rotationRight90 (int[][] inputArray, int inputRow, int inputColumn) {
//        System.out.println("rotationRight90");
        int[][] rotationRight90 = new int[inputColumn][inputRow];

        for (int column = 0; column < inputRow; column++) {
            for (int row = 0; row < inputColumn; row++) {
                rotationRight90[row][inputRow - column - 1] = inputArray[column][row];
            }
        }

        return rotationRight90;
    }

    static int[][] rotationLeft90 (int[][] inputArray, int inputRow, int inputColumn) {
//        System.out.println("rotationLeft90");
        int[][] rotationLeft90 = new int[inputColumn][inputRow];

        for (int column = 0; column < inputColumn; column++) {
            for (int row = 0; row < inputRow; row++) {
                rotationLeft90[column][row] = inputArray[row][inputColumn - column - 1];
            }
        }

        return rotationLeft90;
    }

    static int[][] splitRotation (int[][] inputArray, int inputRow, int inputColumn, int caseNum) {
//        System.out.println("splitBy4PieceRotationRight");

        int halfN = inputRow / 2;
        int halfM = inputColumn / 2;

        int[][] part1 = new int[halfN][halfM];
        int[][] part2 = new int[halfN][halfM];
        int[][] part3 = new int[halfN][halfM];
        int[][] part4 = new int[halfN][halfM];

        int[][] splitRoatation = new int[inputRow][inputColumn];

        // 분할
        for (int row = 0; row < inputRow; row++) {
            for (int column = 0; column < inputColumn; column++) {
                if (row < halfN && column < halfM) part1[row][column] = inputArray[row][column];
                else if (row < halfN) part2[row][column - halfM] = inputArray[row][column];
                else if (column < halfM) part4[row - halfN][column] = inputArray[row][column];
                else part3[row - halfN][column - halfM] = inputArray[row][column];
            }
        }
        switch (caseNum) {
            case (5) :
                // 분할 후 오른쪽 회전
                for (int row = 0; row < inputRow; row++) {
                    for (int column = 0; column < inputColumn; column++) {
                        if (row < halfN && column < halfM) splitRoatation[row][column] = part4[row][column];
                        else if (row < halfN) splitRoatation[row][column] = part1[row][column - halfM];
                        else if (column < halfM) splitRoatation[row][column] = part3[row - halfN][column];
                        else splitRoatation[row][column] = part2[row - halfN][column - halfM];
                    }
                }
                break;
            case (6) :
                // 분할 후 왼쪽 회전
                for (int row = 0; row < inputRow; row++) {
                    for (int column = 0; column < inputColumn; column++) {
                        if (row < halfN && column < halfM) splitRoatation[row][column] = part2[row][column];
                        else if (row < halfN) splitRoatation[row][column] = part3[row][column - halfM];
                        else if (column < halfM) splitRoatation[row][column] = part1[row - halfN][column];
                        else splitRoatation[row][column] = part4[row - halfN][column - halfM];
                    }
                }
        }

        return splitRoatation;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tempInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N = tempInput[0];
        int M = tempInput[1];
        int R = tempInput[2];

        // 배열 입력받기
        int[][] inputArray = new int[N][M];
        for (int row = 0; row < N; row++) {
            inputArray[row] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//            System.out.println(Arrays.toString(inputArray[row]));
        }

        int[] calculateList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        for (int repeat = 0; repeat < R; repeat++) {
            int calculate = calculateList[repeat];
            N = inputArray.length;
            M = inputArray[0].length;

            switch (calculate) {
                case (1):  // 상하반전
                    inputArray = upSideDown(inputArray, N, M);
                    break;
                case (2):  // 좌우반전
                    inputArray = reverse(inputArray, N, M);
                    break;
                case (3):  // rotation 90 (right)
                    inputArray = rotationRight90(inputArray, N, M);
                    break;
                case (4):  // rotation 90 (left)
                    inputArray = rotationLeft90(inputArray, N, M);
                    break;
                case (5):  // rotation 90 (left)
                    inputArray = splitRotation(inputArray, N, M, 5);
                    break;
                case (6):  // rotation 90 (left)
                    inputArray = splitRotation(inputArray, N, M, 6);
                    break;
            }
        }
        printForAnswer(inputArray);
    }
}