using System;
using System.Numerics;

class Program
{
    static void Main()
    {
        var input = Console.ReadLine().Split();
        BigInteger n = BigInteger.Parse(input[0]);
        BigInteger m = BigInteger.Parse(input[1]);

        Console.WriteLine(n / m); // 몫: 각 생명체가 받는 돈
        Console.WriteLine(n % m); // 나머지: 남는 돈
    }
}