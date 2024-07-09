/*
[백준]
13305, 주유소

[문제파악]
- N개의 도시 (일직선 위에 존재)
- 제일 왼쪽의 도시에서 제이 오른쪽의 도시로 자동차를 이용하여 이동
- 인접한 두 도시들은 서로 길이가 다를 수 있음 (단위는 1km)
- 처음 출발시 주유 필
- 기름통의 크기는 무제한
- 1km 이동시 1L 소모
- 각 도시에는 하나의 주유소가 있고 주유소의 리터당 가격은 다름 (단위는 ₩)
- 각 도시에 있는 주유소의 기름 가격과, 각 도시를 연결하는 도로의 길이를 입력으로 받아, 제일 왼쪽의 도시에서 제일 오른쪽 도시로 이동하는 최소의 비용을 계산하는 프로그램을 구현할 것

[입력]
- 첫번째 줄에 도시의 개수를 나타내는 정수 N(2 ≤ N ≤ 100,000)
- 두번째 줄에는 인접한 두 도시를 연결하는 도로의 길이가 제일 왼쪽 도로부터 N-1개의 자연수로 주어짐.
- 세번째 줄에는 주유소의 리터당 가격이 제일 왼쪽 도시부터 순서대로 N개의 자연수로 주어짐.
- 제일 왼쪽 도시부터 제일 오른쪽 도시까지의 거리는 1이상 1,000,000,000 이하의 자연수.
- 리터당 가격은 1 이상 1,000,000,000 이하의 자연수.

[출력]
- 최소 비용 출력

[구현방법]
- 아니 기회비용으로 따졌을 때, 결국 최소비용을 구해야하는데 이게 어떻게 그리디..지..? (아직 이해 다 못함...)
- 그 일단 이전보다 지금이 싸면 다음 이동해야하는만큼 구매하면 되는 것이다.
- 이전이 더 싸면 이전에서 필요한만큼 구매해두면 되는거고 (연료탱크 사이즈 무제한)

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 입력값을 배열로 만들어주는 기능
    public static int[] readIntArray(BufferedReader br, int size) throws IOException {
        int[] array = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < size; i++) {
            if (st.hasMoreTokens()) {
                array[i] = Integer.parseInt(st.nextToken());
            }
        }

        return array;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cityCount = Integer.parseInt(br.readLine());
        int[] roadLen;
        int[] fuelPrice;
        
        roadLen = readIntArray(br, cityCount - 1);  // 도로 길이 입력 받기
        fuelPrice = readIntArray(br, cityCount);  // 연료 가격 입력 받기

//        System.out.println(Arrays.toString(roadLen));
//        System.out.println(Arrays.toString(fuelPrice));

        int totalCost = 0;
        int minPrice = fuelPrice[0];  // 맨 처음 지역에서 무조건 기름 넣어야 하니 그 값을 minPrice로 갱신

        for (int i = 0; i < cityCount - 1; i++) {
            if (fuelPrice[i] < minPrice) minPrice = fuelPrice[i];  // 현재 지역의 기름값이 이전의 minPrice보다 작으면 갱신
            totalCost += minPrice * roadLen[i];  // 현재까지의 이동거리는 그간의 minPrice로 지불
        }

        System.out.println(totalCost);
    }
}