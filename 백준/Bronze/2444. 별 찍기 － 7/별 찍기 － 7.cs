/*
[백준]
2444, 별 찍기 - 7

[문제파악]
- 예제를 보고 규칙을 유추한 뒤에 별을 찍어 보세요.

[입력]
- 첫째 줄에 N(1 ≤ N ≤ 100)이 주어진다.

[출력]
- 첫째 줄부터 2×N-1번째 줄까지 차례대로 별을 출력한다.

[구현방법]
- C#을 다시금 떠올리기 위해 별찍기부터 도전!!!
- 와 콘솔도 생각 안나고 인풋도 생각 안나도 string 반복문도 생각 안남 ㅋㅋ 미친게야!!!

[보완점]
*/


class Program
{
    static void Main() {
        int input = int.Parse(Console.ReadLine());

        // 위쪽 삼각형
        for (int i = 1; i <= input; i++) {
            Console.Write(new string(' ', input - i));
            Console.WriteLine(new string('*', 2 * i - 1));
        }

        for (int i = input - 1; 0 < i; i--) {
            Console.Write(new string(' ', input - i));
            Console.WriteLine(new string('*', 2 * i - 1));
        }
    }
}