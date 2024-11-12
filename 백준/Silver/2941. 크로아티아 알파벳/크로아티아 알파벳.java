/*
[백준]
2941, 크로아티아 알파벳

[문제파악]
예전에는 운영체제에서 크로아티아 알파벳을 입력할 수가 없었다.
따라서, 다음과 같이 크로아티아 알파벳을 변경해서 입력했다.

    크로아티아 알파벳
        č	c=
        ć	c-
        dž	dz=
        đ	d-
        lj	lj
        nj	nj
        š	s=
        ž	z=

예를 들어, ljes=njak은 크로아티아 알파벳 6개(lj, e, š, nj, a, k)로 이루어져 있다.
단어가 주어졌을 때, 몇 개의 크로아티아 알파벳으로 이루어져 있는지 출력한다.
dž는 무조건 하나의 알파벳으로 쓰이고, d와 ž가 분리된 것으로 보지 않는다.
lj와 nj도 마찬가지이다. 위 목록에 없는 알파벳은 한 글자씩 센다.

[입력]
첫째 줄에 최대 100글자의 단어가 주어진다.
알파벳 소문자와 '-', '='로만 이루어져 있다.
단어는 크로아티아 알파벳으로 이루어져 있다.
문제 설명의 표에 나와있는 알파벳은 변경된 형태로 입력된다.

[출력]
입력으로 주어진 단어가 몇 개의 크로아티아 알파벳으로 이루어져 있는지 출력한다.

[구현방법]
- 크로아티아 알파벳 하나씩 밀어넣고 직접 replace 해가며 자리를 찾는 방법뿐이 안떠오름...
- 이거 말고 더 효율적인, 직접 replace를 구현하는듯한 방법이 있을텐데 흠...

[보완점]
- 특수문자로 바꾸고 결과 문자열 길이를 세면 편하다
- 특히 dz= 와 z=는 문자 하나 차이로 동일하기 때문에 순서 신경써주기 위해서 TreeSet을 썼음
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Set<String> croatia = new TreeSet<>();
        croatia.add("dz=");
        croatia.add("lj");
        croatia.add("nj");
        croatia.add("c=");
        croatia.add("c-");
        croatia.add("d-");
        croatia.add("s=");
        croatia.add("z=");

        String input = br.readLine();

        for (String temp : croatia) {
            if (!input.contains(temp)) continue;  // 포함 안되면 패스
            while (input.contains(temp)) {
                input = input.replaceAll(temp, "*");
            }
        }

        System.out.println(input.length());
    }
}