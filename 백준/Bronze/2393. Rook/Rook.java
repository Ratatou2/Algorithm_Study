/*
[백준]


[문제파악]
You have just learned how to output text to the screen and your teacher has challenged you to create an ASCII art of a chess piece.
You have decided to make your favorite piece, the rook.

[입력]

[출력]
The rook art, exactly as shown below, with no extra blank spaces.
In particular, a line must not end with a blank space.

[구현방법]
- 그냥 체스의 룩을 출력하면 된다
- 들여쓰기 주의

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        sb.append("  ___  ___  ___\n");
        sb.append("  | |__| |__| |\n");
        sb.append("  |           |\n");
        sb.append("   \\_________/\n");
        sb.append("    \\_______/\n");
        sb.append("     |     |\n");
        sb.append("     |     |\n");
        sb.append("     |     |\n");
        sb.append("     |     |\n");
        sb.append("     |_____|\n");
        sb.append("  __/       \\__\n");
        sb.append(" /             \\\n");
        sb.append("/_______________\\\n");

        System.out.println(sb);
    }
}