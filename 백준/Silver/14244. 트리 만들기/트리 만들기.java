import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int leafCount = 0; //리프 개수
        if(M==2) leafCount = 1; //M이 2라면,리프가 1개 뿐인 경우다.

        int lastLeafIdx = 0; 
        for(int i =1;i<N;i++){  // 여기서 i는 노드의 idx임.
            if(M>leafCount){ //리프 개수가 M보다 작으면
                leafCount+=1; // 현재의 노드 i를 새 리프로 추가
                System.out.println(0+" "+i);
            }
            else{ //리프 개수가 M보다 크거나 같으면
                // 마지막에 추가한 리프에 현재의 노드 idx를 연결
                System.out.println(lastLeafIdx+" "+i);
            }
            lastLeafIdx = i; //마지막 추가 리프 업데이트
        }
    }
}