package goldstargreat.kiosk.view;

import java.util.Scanner;

public class MenuView {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("\n===== 메뉴를 선택해주세요 =====");
        System.out.println("1. 아메리카노 : 3000원");
        System.out.println("2. 카페라떼 : 3500원");
        System.out.println("-------------------------");
        System.out.println("0. 결제하기");
        System.out.println("===========================");
    }

    public int getUserChoice() {
        System.out.print("입력: ");
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("⚠ 숫자만 입력하세요!");
            return -1;
        }
    }

    // ★★★ 인삿말 메서드 ★★★
    public void showWelcomeMessage() {
        System.out.println("⭐ GoldStar 카페에 오신 것을 환영합니다! ⭐\n");
    }

    public void showGoodbyeMessage() {
        System.out.println("\n주문해 주셔서 감사합니다. 맛있게 드세요!");
    }

    public void closeScanner() {
        scanner.close();
    }
}
