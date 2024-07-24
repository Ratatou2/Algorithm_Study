/*
[백준]
1439, 뒤집기

[문제파악]
- 0과 1로만 이뤄진 문자열 S를 가지고 있다
- 문자열의 모든 숫자를 전부 같게 만들려고 한다
- 연속된 하나 이상의 숫자를 뒤집는다
	- 0을 1로, 1을 0으로 바꾸는 것을 의미
- 문자열 S가 주어졌을 때, 전부 다 같은 숫자로 만드는 최소 횟수를 출력하라

[입력]
- 첫째줄에 문자열 S (100만보다 작다)

[출력]
- 첫째줄에 최소 횟수를 출력한다

[구현방법]
- 0과 1의 덩어리를 세야할듯 몇개씩 나눠지는지 각각을 카운트하고 더 적은걸 답으로 출력하면 됨
- 그 카운트를 세려면 이전 문자열과 현 문자열이 같은지를 파악해야지

[보완점]
- 이게 보면 이전과 같으면 count를 안하니까 max로 구해야함
- 뭔 말이냐면 11101101을 보자
    1. 1 다똑같으니까 4번째 0까진 둘다 카운트 0임
    2. 4번째에서 이전(1)과 현재(0)가 다르니까 one에 count+1
    3. 그 상태로 진행하면 4(0)와 5(1)가 다르니까 zero에 count+1
    4. 5와 6은 1로 같고
    5. 6(1)과 7(0)이 다르니까 one에 count+1
    6. 7(0)과 8(1)이 다르니까 zero에 count+1
    7. 원래는 마지막 값에서 다르면 그것도 한덩어리니까 추가해줘야하지만 그 코드가 없으므로 이렇게 진행해야 함 
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] S = br.readLine().split("");

        // 110011001010
        int zeroCount = 0;
        int oneCount = 0;

        String prevString = S[0];
        for (int i = 1; i < S.length; i++) {
//            System.out.println("현재 i : " + i + " / " + S[i]);
            if (!S[i].equals(prevString)) {
                if (S[i].equals("0")) oneCount++;
                else zeroCount++;
            }

            prevString = S[i];
        }

        System.out.println(Math.max(zeroCount, oneCount));
    }
}