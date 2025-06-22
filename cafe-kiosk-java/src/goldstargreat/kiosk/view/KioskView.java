package goldstargreat.kiosk.view;

import goldstargreat.kiosk.model.MenuItem;
import goldstargreat.kiosk.model.Order;
import goldstargreat.kiosk.model.User;
import java.util.Scanner;

public class KioskView {
    // 메뉴 데이터를 이 곳에 배열로 모아둡니다.
    private final MenuItem[] menuItems = {
            new MenuItem("아메리카노", 3000),
            new MenuItem("카페라떼", 3500),
            new MenuItem("카푸치노", 3500),
            new MenuItem("바닐라라떼", 4000),
            new MenuItem("돌체라떼", 4000)
    };

    private final Scanner scanner = new Scanner(System.in);

    // 환영 인사
    public void showWelcomeMessage() {
        System.out.println("⭐ GoldStar 카페에 오신 것을 환영합니다! ⭐\n");
    }

    // 메뉴판 출력
    public void showMenu() {
        System.out.println("\n===== 메뉴를 선택해주세요. =====");
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ". " + menuItems[i].getName() + " : " + menuItems[i].getPrice() + "원");
        }
        System.out.println("-------------------------");
        System.out.println("0. 결제하기");
        System.out.println("===========================");
    }

    // 최종 주문 내역 출력
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

    // 결제 수단 선택 문구 출력
    public void showPaymentChoice() {
        System.out.println("\n결제 수단을 선택해주세요 (1. 카드 / 2. 현금)");
    }

    // 결제 완료 결과 출력
    public void showPaymentResult(int paymentChoice) {
        if (paymentChoice == 1) {
            System.out.println("\n🎉 카드로 결제가 완료되었습니다.");
        } else {
            System.out.println("\n🎉 현금으로 결제가 완료되었습니다.");
        }
    }

    // 작별 인사
    public void showGoodbyeMessage() {
        System.out.println("\n주문해 주셔서 감사합니다. 맛있게 드세요!");
    }

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

    // 선택한 번호에 맞는 메뉴 아이템을 찾아주는 기능
    public MenuItem getMenuItem(int choice) {
        return menuItems[choice - 1];
    }

    // 메뉴의 총 개수를 알려주는 기능
    public int getMenuSize() {
        return menuItems.length;
    }

    // 스캐너 리소스 닫기
    public void closeScanner() {
        scanner.close();
    }
}