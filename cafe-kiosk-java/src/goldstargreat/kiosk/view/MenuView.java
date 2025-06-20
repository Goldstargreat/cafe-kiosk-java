package goldstargreat.kiosk.view;

import java.util.Scanner;

public class MenuView {
    private final Scanner scanner = new Scanner(System.in); // ✅ 클래스 필드로 선언
    public void showMenu() {
        System.out.println("1. 아메리카노 : 3000원");
        System.out.println("2. 카페라떼 : 3500원");
        System.out.println("0번을 누르면 종료됩니다.");
    }

    public int getUserChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴를 선택하십시오: ");
        String input = scanner.nextLine(); // 문자열로 입력받기
        try {
            return Integer.parseInt(input); // 숫자로 변환
        } catch (NumberFormatException e) {
            System.out.println("⚠ 숫자만 입력하세요! 예: 1 또는 2");
            return -1; // 잘못된 입력 처리
        }
    }
}
