/*
[백준]
14569, 시간표 짜기

[문제파악]
- 비어있는 시간에 추가로 신청할 수 있는 과목의 후보 갯수를 구해보자.
- 후보 갯수를 세는 것이므로 현재 내 시간표에서 신청할 수 있는 과목끼리 사긴이 겹치더라도 모두 세어야 함.
- 즉, 월요일 1, 2, 3, 4, 5 교시 시간이 비어있고 한 과목의 시간이 월요일 1, 2, 3, 4교시이고 나머지 한 과목의 시간이 월요일 2, 3, 4, 5 이더라도 둘 모두 후보가 될 수 있다.

[입력]
- 총 과목의 수 N  (3 <= N <= 1000)
- N줄에 걸쳐 각 과목의 수업시간의 수 k (4 <= k <= 50)이 주어짐
	- 그 옆에 k개의 숫자 t(1 <= t < = 50)가 공백으로 구분되어 주어짐
	- t는 이 과목의 수업이 진행되는 교시를 의미, 1 ~ 50의 값을 가짐
	- ex. 월요일 1~10교시 -> 1~10
		- 화요일 1~10교시 -> 11~20
- 다음줄에 학생수 M (1 <= M <= 10000)
- M줄에 걸쳐 각 학생들의 비어있는 교시 갯수 p (0 <= p <= 50)가 주어짐
	- 그 옆에 p개의 숫자 q (1 <= q <= 50)가 공백으로 구분되어 주어짐
	- ex. A 수업이 화요일 2, 3교시, 수요일 4, 5교시라면 다음과 같이 입력이 주어진다
		- 4 12 13 24 25


[출력]
- M줄에 걸쳐서 각 학생들의 들을 수 있는 과목 갯수를 출력


[구현방법]
- 이거는 보아하니 1부터 50까지가 하나의 배열로써 동작하면 될 것 같다 (그 시간에 수업이 있나 없나)
- 그럼 그냥 수업 자체를 하나의 배열로 보면 안되냐! 할 수도 있겠지만, 이 문제는 시간이 겹쳐도 수강할 수 있는 가능성이 있다면 count 해야하기 때문에 수업별로 따로 세야한다 (수업마다 배열이 따로 있어야 함)
- 각 시간의 여부를 2진수로 만들고 나면 >> 연산해가지고 하나씩 비교하면 쉽겠죠?
- 그래서 이걸 어케 구현하는ㄷ...
- True/False 배열 만들어가지고 각 시간에 등록 가능한가를 비교해서 푸는 방법은 이해했는데 이렇게 하면 비트 마스킹이 아님. 풀리긴 함.

[보완점]
- makeByBinary 효율화 작업 완성 코드
// 가능한 시간들을 배열에 넣는 과정
for (int i = 1; i <= repeat; i++) {
    int currentNum = Integer.parseInt(input[i]);
    num |= 1L << (currentNum - 1);
}

// 이전엔 아래와 같이 작성하였지만, 사실 2제곱은 비트 연산으로 왼칸으로 하나씩 밀면 (<<) 되는 일이다
// 또한 True값을 따로 두고 이후에 연산할 필요없이 한번에 연산 가능 (처음부터 합쳤어도 될 일이었다
// 더군다나 수업 시간 자체가 2진수의 자릿수기 때문에 41에 수업이 있다면 1L << 41 해버리면 될 일이다!
// 추가로 |=을 해주는 이유는 한 과목이 21, 22, 23, 24라면 해당 값들을 전부 합해줘야하기 때문에 OR(|) 연산을 진행하는 것이다.

// 가능한 시간들을 배열에 넣는 과정
for (int i = 1; i <= repeat; i++) {
    int currentNum = Integer.parseInt(input[i]);
    binary[currentNum - 1] = true;
}

int twoPower = 1;

// 배열을 기반으로 2진수를 만들고 10진수로 계산한다
// 이렇게 일일이 곱할 필요 없이 2진수니까 왼쪽으로 한칸씩 밀면 2제곱이 된 셈이다 그러니 아래와 같이 변경 가능
for (int i = 0; i < binary.length; i++) {
    if (binary[i]) num += twoPower;
    twoPower *= 2;
}

- 어렵다 어려워.. 추후에 다시 풀어보자

[입력예시]
3
4 1 2 3 4
6 5 6 7 8 9 10
4 11 21 31 41
1
8 1 2 3 4 5 6 7 8


[출력예시]
1
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long makeByBinary(String[] input) {
        long num = 0;
        int repeat = Integer.parseInt(input[0]);

        for (int i = 1; i <= repeat; i++) {
            int currentNum = Integer.parseInt(input[i]);
            num |= 1L << (currentNum - 1);
        }

        return num;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 과목 입력받기
        int N = Integer.parseInt(br.readLine());
        long[] classSchedule = new long[N];
        for (int i = 0; i < N; i++) {
            classSchedule[i] = makeByBinary(br.readLine().split(" "));
        }

        // 학생 스케줄 입력 받기
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            long studentSchedule = makeByBinary(br.readLine().split(" "));  // 학생 스케줄 2진수로 만듦
            int count = 0;
            
            for (long classTime : classSchedule) {
                // 학생의 '쉬는 시간'을 알려줬기 때문에 & 연산을 해서 수업시간이 나오면 수업을 들을 수 있다는 의미!
                if ((classTime & studentSchedule) == classTime) count++;
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }
}