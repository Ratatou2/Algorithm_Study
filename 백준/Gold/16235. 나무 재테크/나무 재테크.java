

/*
[백준]
16235, 나무 재테크

[문제파악]
부동산 투자로 억대의 돈을 번 상도는 최근 N×N 크기의 땅을 구매했다.
상도는 손쉬운 땅 관리를 위해 땅을 1×1 크기의 칸으로 나누어 놓았다.
각각의 칸은 (r, c)로 나타내며, r은 가장 위에서부터 떨어진 칸의 개수, c는 가장 왼쪽으로부터 떨어진 칸의 개수이다. r과 c는 1부터 시작한다.
상도는 전자통신공학과 출신답게 땅의 양분을 조사하는 로봇 S2D2를 만들었다.
S2D2는 1×1 크기의 칸에 들어있는 양분을 조사해 상도에게 전송하고, 모든 칸에 대해서 조사를 한다.
가장 처음에 양분은 모든 칸에 5만큼 들어있다.
매일 매일 넓은 땅을 보면서 뿌듯한 하루를 보내고 있던 어느 날 이런 생각이 들었다.
나무 재테크를 하자!
나무 재테크란 작은 묘목을 구매해 어느정도 키운 후 팔아서 수익을 얻는 재테크이다.
상도는 나무 재테크로 더 큰 돈을 벌기 위해 M개의 나무를 구매해 땅에 심었다.
같은 1×1 크기의 칸에 여러 개의 나무가 심어져 있을 수도 있다.
이 나무는 사계절을 보내며, 아래와 같은 과정을 반복한다.
봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.
가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다.
상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다.
각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
K년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 N, M, K가 주어진다.
둘째 줄부터 N개의 줄에 A배열의 값이 주어진다. r번째 줄의 c번째 값은 A[r][c]이다.
다음 M개의 줄에는 상도가 심은 나무의 정보를 나타내는 세 정수 x, y, z가 주어진다.
처음 두 개의 정수는 나무의 위치 (x, y)를 의미하고, 마지막 정수는 그 나무의 나이를 의미한다.

[출력]
첫째 줄에 K년이 지난 후 살아남은 나무의 수를 출력한다.

[구현방법]
- 맨처음 양분은 '모든 칸에 5'
- 4계절을 보냄
    1) 봄
        - 나무가 자신의 나이만큼 양분을 먹음
        - 나이가 1 증가
        - 하나의 칸에 여러 나무가 있다면, 나이가 어린 나무부터 양분을 먹음
        - 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없다면, 즉시 사망
    2) 여름
        - 사망한 나무가 양분으로 변함
        - 죽은 나무마다 나이를 /2한 값이 양분이 됨 (나무가 있던 칸에 / 소숫점은 버림)
    3) 가을
        - 나무가 번식함
        - 번식하는 나무는 나이가 5의 배수여야함
        - 인접한 8개 칸에 나이가 1인 나무가 생김
        - 땅을 벗어나는 칸에는 나무 안생김
    4) 겨울
        - 로봇이 땅에 양분을 추가함
        - 양분을 추가하는 땅이 있음

- K년 후의 상도의 땅에 살아있는 나무의 갯수 구하기

- 이거 그냥 시뮬을 돌리긴하는데 0.3초라서 효율적으로 해야할지도?
- 근데 사이즈가 작긴하다 N 최대가 10임
- 위 내용 그냥 구현하면 될듯?

[보완점]
- reverseOrder 당연히 아니겠죠.. 작은 것부터 처리라니까 ㅡㅡ
- LinkedList는 삭제할 때 index로 삭제해야한다 -> 이말인 즉, index로 지우면 다 밀리니 새로운 List를 만드는게 낫다는 것;;
*/

import java.io.*;
import java.util.*;

public class Main {
    static int[] moveX = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] moveY = {-1, 0, 1, -1, 1, -1, 0, 1};

    static int N, M, K;
    static int[][] A, energy;  // 땅의 양분
    static LinkedList<Tree>[][] farm;

    static class Tree {
        int x, y, age;
        boolean isDead;

        Tree (int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
            this.isDead = false;
        }
    }

    static class Energy {
        int x, y, addEnergy;

        Energy (int x, int y, int addEnergy) {
            this.x = x;
            this.y = y;
            this.addEnergy = addEnergy;
        }
    }

    // 나무 심을 수 있는 범위 넘어갔는지 체크
    static boolean isOutOfBound (int x, int y) {
        return x < 1 || y < 1 || N < x || N < y;
    }

    // 4계절 시뮬레이션
    static void fourSeason() {
        Queue<Energy> deadTrees = new ArrayDeque<>();
        Queue<Tree> growTrees = new ArrayDeque<>();

        // 1) 봄 (나무가 자신의 나이만큼 양분을 먹음)
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (farm[row][col].isEmpty()) continue;  // 비어있으면 패스

                LinkedList<Tree> temp = farm[row][col];
                LinkedList<Tree> newTree = new LinkedList<>();

                // 나무가 자라거나 죽거나
                for (int i = 0; i < farm[row][col].size(); i++) {
                    Tree currTree = temp.get(i);

                    // 사망하지 않은 나무 중에, 현재 토양보다 많은 양의 양분이 필요한 나무는 사망처리 & 토양에 추가할 준비하기
                    if (!currTree.isDead && energy[row][col] < currTree.age) {
                        currTree.isDead = true;
                        deadTrees.add(new Energy(row, col, currTree.age / 2));  // 죽은 나무마다 나이를 /2한 값이 양분이 됨 (나무가 있던 칸에 / 소숫점은 버림)

                    } else {  // 자랄 수 있는 나무는
                        energy[row][col] -= currTree.age++;  // 양분을 나이만큼 빼앗고, 나이를 한 살 더 먹는다
                        if (currTree.age % 5 == 0) growTrees.add(currTree);
                        newTree.add(currTree);
                    }
                }

                // 살아있는 트리만 담아둔 LinkedList로 갱신
                farm[row][col] = newTree;
            }
        }

        // 2) 여름 (사망한 나무가 양분으로 변함)
        for (Energy temp : deadTrees) {
            energy[temp.x][temp.y] += temp.addEnergy;   // 사망한 나무 양분처리
        }

        // 3) 가을 (나무가 번식함)
        for (Tree temp : growTrees) {
            // 8방 이동
            for (int i = 0; i < moveX.length; i++) {
                int nextX = temp.x + moveX[i];
                int nextY = temp.y + moveY[i];

                // 밭 범위 바깥은 어차피 계산 못하니까 패스
                if (isOutOfBound(nextX, nextY)) continue;

                farm[nextX][nextY].addFirst(new Tree(nextX, nextY, 1));
            }
        }

        // 4) 겨울 (로봇이 땅에 양분을 추가함)
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                energy[row][col] += A[row][col];
            }
        }
    }

    static int countTree() {
        int treeCount = 0;

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (farm[row][col].isEmpty()) continue;  // 비어있으면 패스

                for (Tree temp : farm[row][col]) {
                    if (temp.isDead) continue;  // 죽었으면 패스

                    treeCount++;
                }
            }
        }

        return treeCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 땅 양분 초기화
        energy = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                energy[row][col] = 5;
            }
        }

        // 농장 필드 초기화
        farm = new LinkedList[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                farm[row][col] = new LinkedList<>();
            }
        }

        // 겨울철 로봇이 줄 양분 초기화
        A = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 1; col <= N; col++) {
                A[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 심은 나무 정보 입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            farm[x][y].add(new Tree(x, y, age));
        }

        // 4계절을 K만큼 반복
        for (int i = 0; i < K; i++) {
            fourSeason();
        }

        System.out.println(countTree());
    }
}
