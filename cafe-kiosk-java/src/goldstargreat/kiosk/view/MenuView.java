package goldstargreat.kiosk.view;

import java.util.Scanner;

public class MenuView {
    public void showMenu() {
        System.out.println("1. 아메리카노 : 3000원");
        System.out.println("2. 카페라떼 : 3500원");
        System.out.println("0번을 누르면 종료됩니다.");
    }

    public int getUserChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴 선택: ");
        return sc.nextInt();
    }
}
