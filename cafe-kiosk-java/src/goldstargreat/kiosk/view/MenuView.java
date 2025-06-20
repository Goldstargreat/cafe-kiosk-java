package goldstargreat.kiosk.view;

import java.util.Scanner;

public class MenuView {
    // Scanner 객체를 한 번만 생성하여 클래스 전체에서 공유
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("\n===== 메뉴 =====");
        System.out.println("1. 아메리카노 : 3000원");
        System.out.println("2. 카페라떼 : 3500원");
        System.out.println("0. 주문 종료");
        System.out.println("================");
    }

    public int getUserChoice() {
        System.out.print("메뉴를 선택하십시오: ");
        String input = scanner.nextLine(); // 문자열로 입력 받기
        try {
            return Integer.parseInt(input); // 숫자로 변환 시도
        } catch (NumberFormatException e) {
            System.out.println("⚠ 숫자만 입력하세요! 예: 1 또는 2");
            return -1; // 잘못된 입력(문자 등)을 나타내는 값
        }
    }

    public void showGoodbyeMessage() {
        System.out.println("\n주문해 주셔서 감사합니다. 맛있게 드세요!");
    }

    // 프로그램이 끝날 때 Scanner를 닫아주는 메소드
    public void closeScanner() {
        scanner.close();
    }
}
