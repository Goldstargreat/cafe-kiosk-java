package goldstargreat.kiosk.view;

import goldstargreat.kiosk.model.Order;
import goldstargreat.kiosk.model.User;

import java.util.Scanner;

// 화면에 보여지는 모든 것을 책임지는 단 하나의 클래스
public class KioskView {
    // Scanner를 딱 한 번만 생성해서 계속 사용한다.
    private final Scanner scanner = new Scanner(System.in);

    // --- 환영 인사 작별인사 ---
    public void showWelcomeMessage() {
        System.out.println("⭐ GoldStar 카페에 오신 것을 환영합니다! ⭐\n");
    }

    public void showGoodbyeMessage() {
        System.out.println("\n주문해 주셔서 감사합니다. 맛있게 드세요!");
    }

    // --- 메뉴 선택 화면 ---
    public void showMenu() {
        System.out.println("\n===== 메뉴를 선택해주세요 =====");
        System.out.println("1. 아메리카노 : 3000원");
        System.out.println("3. 카페라떼 : 3500원");
        System.out.println("4. 카푸치노 : 3500원");
        System.out.println("5. 바닐라라떼 : 3500원");
        System.out.println("6. 돌체라떼 : 3500원");
        System.out.println("-------------------------");
        System.out.println("0. 결제하기");
        System.out.println("===========================");
    }

    // --- 주문 및 결제 화면 ---
    public void showOrderDetails(Order cart, User user) {
        System.out.println("\n--- 최종 주문 내역 ---");
        System.out.println(cart.toString());

        int finalPrice = cart.getTotalPrice();
        if (user.isMember()) {
            System.out.println("\n✨ 회원 혜택! 10% 할인이 적용됩니다.");
            finalPrice = (int) (finalPrice * 0.9);
        }
        System.out.println("\n💳 최종 결제 금액: " + finalPrice + "원");
    }

    public void showPaymentChoice() {
        System.out.println("\n결제 수단을 선택해주세요 (1. 카드 / 2. 현금)");
    }

    public void showPaymentResult(int paymentChoice) {
        if (paymentChoice == 1) {
            System.out.println("\n🎉 카드로 결제가 완료되었습니다.");
        } else {
            System.out.println("\n🎉 현금으로 결제가 완료되었습니다.");
        }
    }

    // --- 사용자의 입력을 처리 (프로젝트 모든 곳에서 이 메소드 하나만 사용) ---
    public int getUserInput() {
        System.out.print("입력: ");
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("⚠ 숫자만 입력하세요!");
            return -1;
        }
    }

    // --- 프로그램 종료 시 Scanner 정리 ---
    public void closeScanner() {
        scanner.close();
    }
}
