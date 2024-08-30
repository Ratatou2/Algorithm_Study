/*
[백준]
12933, 오리

[문제파악]
- 오리의 울음 소리는 "quack"이다.
- 올바른 오리의 울음 소리는 울음 소리를 한 번 또는 그 이상 연속해서 내는 것이다.
- 예를 들어, "quack", "quackquackquackquack", "quackquack"는 올바른 오리의 울음 소리이다.

- 영선이의 방에는 오리가 있는데, 문제를 너무 열심히 풀다가 몇 마리의 오리가 있는지 까먹었다.
- 갑자기 영선이의 방에 있는 오리가 울기 시작했고, 이 울음소리는 섞이기 시작했다.
- 영선이는 일단 울음소리를 녹음했고, 나중에 들어보면서 총 몇 마리의 오리가 있는지 구해보려고 한다.
- 녹음한 소리는 문자열로 나타낼 수 있는데, 한 문자는 한 오리가 낸 소리이다.
- 오리의 울음 소리는 연속될 필요는 없지만, 순서는 "quack"이어야 한다.
- "quqacukqauackck"과 같은 경우는 두 오리가 울었다고 볼 수 있다.
- 영선이가 녹음한 소리가 주어졌을 때, 영선이 방에 있을 수 있는 오리의 최소 개수를 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 영선이가 녹음한 소리가 주어진다.
- 소리의 길이는 5보다 크거나 같고, 2500보다 작거나 같은 자연수이고, 'q','u','a','c','k'로만 이루어져 있다.

[출력]
- 영선이 방에 있을 수 있는 오리의 최소 수를 구하는 프로그램을 작성하시오.
- 녹음한 소리가 올바르지 않은 경우에는 -1을 출력한다.

[구현방법]
- 처음에 너무 어렵게 생각한게 사실 quack에 해당하는걸 제거하면 된다
- 갯수가 안남을 때까지
- 근데 이 방식을 for문을 여러번 돌아야해서 과연 이 방법이 효율적인가...는 살짝 의문
- 일단 이대로 구현은 해보고 더 효율적인 방법이 있나 고민해보도록 하자!

[보완점]
- 아 너무 헷갈리던데... 결국 참고했다 ㅠ
- 여러번 시도 했는데 핵심은 한마리가 여러번 우는 경우가 있을 수 있으니 울음 소리를 한바퀴 다 돌때까지 그냥 냅두는거임
- 한바퀴 다 돌았는데 전부다 사용처리가 되어있어? 그러면 한마리가 반복적으로 운 것이다
- 이런 디테일을 잘 생각해 봐야하는디...

- 배열로 만들어서 이전 문자 갯수와 비교해서 잘 세지고 있는지 체크해서 푸는 방법도 있다
- 진짜 푸는 방법 겁나 다양하네...

[입력값 : 출력값]
quqacukqauackck : 2
kcauq : -1
quackquackquackquackquackquackquackquackquackquack : 1
qqqqqqqqqquuuuuuuuuuaaaaaaaaaacccccccccckkkkkkkkkk : 10
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sound = br.readLine();
        char[] input = sound.toCharArray();
        char[] quack = {'q', 'u', 'a', 'c', 'k'};
        int soundCount = sound.length();
        int answer = 0;

        // 울음소리 자체가 5로 나눠떨어지지 않으면 종료한다 (quack이 5글자이기 때문에)
        if (sound.length() % 5 != 0) {
            System.out.println(-1);
            return;
        }

        int total = soundCount;
        while (true){
            int index = 0;
            int tempIndex = 0;
            int[] temp = new int[5]; //quack 인덱스 저장
            boolean flag = false;

            for (int i = 0; i < soundCount; i++) {
                // quack 처음부터 비교해서 같은 것을 찾으면, index를 +1해서 다음 것을 찾는다
                if (input[i] == quack[index]){
                    temp[tempIndex++] = i;
                    index++;
                }

                // 그렇게 순차적으로 돌기 전에 5개를 찾으면 index를 쵝화 해주며, index들을 사용처리(.)한다
                if (index == 5){
                    flag = true;
                    index = 0;
                    tempIndex = 0;
                    total -= 5;

                    //index '.'로 치환
                    for (int j = 0; j < 5; j++){
                        input[temp[j]] = '.';
                    }
                }
            }

            // 한 바퀴 다 돌았는데 조건이 충족되어 있으면 +1, 그게 아니라면 조건 불만족이므로 -1을 출력하고 종료한다
            // 내부에서 answer++ 해주는 구조가 아니기 때문에 한마리가 반복적으로 울면 여러번 울어도 한번만 처리된다
            if (flag) answer++;
            else {
                System.out.println(-1);
                return;
            }

            if (total == 0) break;  // 전체 갯수 카운트가 딱 떨어져서 남은게 없으면 중단하고 답 출력
        }

        System.out.println(answer);
    }

//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        // "quack"의 각 문자('q', 'u', 'a', 'c', 'k')가 몇 번 등장했는지 저장하기 위한 배열
//        // quack[0]은 'q', quack[1]은 'u', ..., quack[4]은 'k'의 개수를 저장
//        int[] quackCount = new int[5]; 
//        char[] sounds = br.readLine().toCharArray();
//        int result = 0;
//
//        for(char token : sounds) {
//            // 현재 문자가 "quack"에서 몇 번째 인덱스에 있는지 찾음 ('q'는 0, 'u'는 1, ..., 'k'는 4)
//            // 문자열에서 인덱스를 찾아버리는...
//            int target = "quack".indexOf(token);
//
//            // 현재 문자가 'q'일 경우 (= 시작일 경우)
//            if(token == 'q') {
//                quackCount[0]++;  // 'q'의 개수를 증가시키고
//                result = Math.max(result, quackCount[0]);  // 오리의 개수는 동시에 발생하는 'q'의 최대값으로 설정
//            } else { // 'q'가 아닌 다른 문자일 경우 (= 나머지 문자들인지
//                // 현재 문자가 나오기 전에, 이전 문자가 충분히 있지 않은 경우 오류 처리
//                if(quackCount[target - 1] <= quackCount[target]) {
//                    // 결과를 -1로 설정하고 루프 탈출
//                    result = -1;
//                    break;
//                }
//                
//                quackCount[target]++;  // 현재 문자의 개수를 증가시킴
//
//                // 현재 문자가 'k'일 경우, 하나의 "quack"이 완료된 것이므로
//                if (token == 'k') {
//                    // "quack"이 완료되었으므로 각 문자의 개수를 1씩 감소
//                    for (int idx = 0; idx < 5; idx++) quackCount[idx]--;
//                }
//            }
//        }
//
//        // 반복문 종료 후, "quack"이 완전히 종료되지 않은 상태가 남아있는지 확인
//        // quack 배열 중 어느 하나라도 0보다 크다면 -1을 출력
//        System.out.println((quackCount[0] > 0 || quackCount[1] > 0 || quackCount[2] > 0 || quackCount[3] > 0) ? -1 : result);
//    }
}