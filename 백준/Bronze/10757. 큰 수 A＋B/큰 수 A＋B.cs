/*
[백준]
10757, 큰 수 A+B

[문제파악]
두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.

[입력]
첫째 줄에 A와 B가 주어진다. (0 < A,B < 1010000)

[출력]
첫째 줄에 A+B를 출력한다.

[구현방법]
- 이제 C#으로 이정도는..! (하하...)
- 응 안돼 아직 모자라 
- 큰 숫자 계산에는 decimal이 좋다!!
    - 정수 or 큰 숫자 계산에는 decimal! 
    - 최대 28~29 자리 십진수까지 정밀도 보장하기 때문임임
- 근데 decimal해도 터지네...? long으로 변경
- 안돼.. (문제 조건을 봐라 되겠니?!!)
- BigInteger를 쓰라네...? 파이썬아 보고 있니...? 그립다...

[보완점]
*/

using System.IO;
using System.Numerics;

class Program
{
    static void Main() {
        BigInteger[] input = Array.ConvertAll(Console.ReadLine().Split(), BigInteger.Parse);

        Console.WriteLine(input[0] + input[1]);
    }
}