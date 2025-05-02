/*
[백준]
2920, 음계

[문제파악]
- 다장조는 c d e f g a b C, 총 8개 음으로 이루어져있다. 
- 이 문제에서 8개 음은 다음과 같이 숫자로 바꾸어 표현한다. c는 1로, d는 2로, ..., C를 8로 바꾼다.
- 1부터 8까지 차례대로 연주한다면 ascending, 8부터 1까지 차례대로 연주한다면 descending, 둘 다 아니라면 mixed 이다.
- 연주한 순서가 주어졌을 때, 이것이 ascending인지, descending인지, 아니면 mixed인지 판별하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 8개 숫자가 주어진다. 
- 이 숫자는 문제 설명에서 설명한 음이며, 1부터 8까지 숫자가 한 번씩 등장한다.

[출력]
- 첫째 줄에 ascending, descending, mixed 중 하나를 출력한다.

[구현방법]
- 이미 다 치환되어 있으니 영어 알파벳은 신경 쓸 것 없다
- 들어오는 숫자들이 순차적인지만 확인하면 됨
- 이때 이전값과 빼서 1이상이면 mixed이고 그게 아니면 오름차순인지, 내림차순인지만 체크하면 된다다

[보완점]
*/

using System.IO;

class Program 
{
    static void Main() {
        int[] input = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);
        String result = "";

        int prev = input[0];
        for(int i = 1; i < input.Length; i++) {
            int curr = input[i];

            // 이전값과 차이가 1이상이면 순차적인 순서가 아니라는 것이다
            if (1 < Math.Abs(prev - curr)) {
                result = "mixed";
                break;
            } 

            prev = curr;
        }

        // 이미 혼합으로 체크됐는지 확인인
        if (!result.Equals("mixed")) {
            if (input[0] == 1) result = "ascending";
            else result = "descending";
        }

        Console.WriteLine($"{result}");
    }
}