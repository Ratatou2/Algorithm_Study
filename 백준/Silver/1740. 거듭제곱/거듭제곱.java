/*
[백준]
1740, 거듭제곱

[문제파악]
- 3의 제곱수를 생각하자. 3의 0제곱, 3의 1제곱, 3의 2제곱, ... 은 순서대로 1, 3, 9, 27, ... 이 됨
- 여기서 한 개 이상의 서로 다른 3의 제곱수의 합으로 표현되는 수를 생각할 수 있다.
	- 예를 들어 30은 27과 3의 합이므로, 이러한 수에 포함된다.

- 한 개 이상의 서로 다른 3의 제곱수의 합으로 표현되는 N번째로 작은 수를 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 N이 주어진다. (N은 500,000,000,000 이하의 자연수이다.)

[출력]
- 첫째줄에 한 개 이상의 서로 다른 3의 제곱수의 합으로 표현되는 N번째로 작은 수를 출력한다.

[구현방법]
- 선린의 터를 이라는 문제와 비슷하면서도 굉장히 명확히 적혀있다! 이것 좀 보라!
- 구현하라고 한다면 차라리 N보다 큰 3^k 근 솟값을 구하고 이전 값들과 더해서 찾아보겠는데 비트마스킹으로 어떻게 풀지..?
- 1은 사용, 3은 미사용, 9는 사용 이런식으로 조합하면 되지 않나...? (근데 이게 비트마스킹을 사용하는 것이긴 한가?, 2진수 사용하면 죄다 비트마스킹?!)
- 이거 들어오는 N은 2진수로 변경하고 각 위치에 해당하는 숫자를 index에 따라 3^index해서 더하면 되겠네 해봅시다
- 내 식대로 구현할거면 index 신경써줘야 한다
- 최소 단위부터 해줘야 원하는대로 구현 가능

[보완점]
- 그리고 제곱이랑 그런 것들있으니까 형(long)도 신경써줘야 함
- for 범위 체크해봐야지 이거 대충 짜니까 이렇게 틀리는거 아냐
- Math.pow를 쓰면... 제대로 된 결과가 안나온다고... 하아 ㅋㅋ
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());
        String[] nToBinary = Long.toBinaryString(N).split("");
        long result = 0;
        long threePower = 1;

        for (int i = 0; i < nToBinary.length; i++) {
//            System.out.println("현재 binary : " + nToBinary[i] + " | 현재 index : " + i);
            if (nToBinary[nToBinary.length-1-i].equals("1")) result += threePower;
            threePower *= 3;
        }

        System.out.println(result);
    }
}