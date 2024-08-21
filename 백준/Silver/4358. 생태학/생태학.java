/*
[백준]


[문제파악]
- 생태학에서 나무의 분포도를 측정하는 것은 중요하다.
- 그러므로 당신은 미국 전역의 나무들이 주어졌을 때, 각 종이 전체에서 몇 %를 차지하는지 구하는 프로그램을 만들어야 한다.

[입력]
- 프로그램은 여러 줄로 이루어져 있으며, 한 줄에 하나의 나무 종 이름이 주어진다.
- 어떤 종 이름도 30글자를 넘지 않으며, 입력에는 최대 10,000개의 종이 주어지고 최대 1,000,000그루의 나무가 주어진다.

[출력]
- 주어진 각 종의 이름을 사전순으로 출력하고, 그 종이 차지하는 비율을 백분율로 소수점 4째자리까지 반올림해 함께 출력한다.

[구현방법]
- 일단 Map에 없으면 추가하고, 있으면 Count 추가하는 식으로 각 나무가 몇 개인지 파악
    - 갯수를 파악하며 전체 나무 갯수가 몇개인지도 카운트하면 될듯함
- 그리고 이제 Map에 들어가 있는 것을 정렬하면 돼 (정렬 기능을 적용하기 위해서 Map은 TreeMap 쓰면 Key나 Value 기준으로 정렬 할 수 있다)
- 그렇게 정렬하고 나면 순서대로 출력하되, value로 전체 나무 갯수중에서 비율 구해서 같이 출력하면 될듯하다
- Tree의 기본 기능인 getOrDefault 같은 것들을 잘 쓸 줄 알아야 한다.

[보완점]
- trees.put(input, trees.getOrDefault(input, 0) + 1); 보단,
- map.compute("key", (k, v) -> (v == null) ? 1 : v + 1);
- 입력받는 break point를 if (input.isEmpty() || input == null) break;로 수정하는게 좀 더 적합해보인다
- 궁금한게 getOrDefault를 쓰면 더 느릴까? (compute 102560kb, 804ms)

[입력값]
Red Alder
Ash
Aspen
Basswood
Ash
Beech
Yellow Birch
Ash
Cherry
Cottonwood
Ash
Cypress
Red Elm
Gum
Hackberry
White Oak
Hickory
Pecan
Hard Maple
White Oak
Soft Maple
Red Oak
Red Oak
White Oak
Poplan
Sassafras
Sycamore
Black Walnut
Willow


*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Map<String, Integer> trees = new TreeMap<>();
        String input;
        int treeCount = 0;
        while (true) {
            input = br.readLine();

            if (input == null || input.length() == 0 || input.isEmpty()) break;

            // 값이 있으면 가져와서 + 1을 하고 없으면 0을 가져와서 1을 넣어줄 수 있도록 셋팅
            // compute를 쓰는게 더 깔끔하다
            // trees.put(input, trees.getOrDefault(input, 0) + 1);
            // trees.compute(input, (k, v) -> (v == null) ? 1 : v + 1);
            trees.put(input, trees.getOrDefault(input, 0) + 1);
            treeCount++;
        }

        for (Map.Entry<String, Integer> tree : trees.entrySet()) {
            double ratio = (double) tree.getValue() / treeCount * 100;
            sb.append(tree.getKey()).append(" ").append(String.format("%.4f", ratio)).append("\n");
        }

        System.out.println(sb);
    }
}