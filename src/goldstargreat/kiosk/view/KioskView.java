package goldstargreat.kiosk.view;

import goldstargreat.kiosk.model.MenuItem;
import goldstargreat.kiosk.model.Order;
import goldstargreat.kiosk.model.User;
import java.util.Scanner;

// 화면 출력과 사용자 입력만 담당하는 클래스
// 계산이나 데이터 저장은 하지 않습니다.
public class KioskView {
    private final Scanner scanner = new Scanner(System.in);

    // ===== 입력 =====

    // 숫자 입력 받기 (숫자가 아니면 -1 반환)
    public int getUserInput() {
        System.out.print("입력: ");
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("⚠ 숫자만 입력하세요!");
            return -1;
        }
    }

    // 스캐너 리소스 닫기
    public void closeScanner() {
        scanner.close();
    }

    // ===== 안내 문구 =====

    // 환영 인사
    public void showWelcomeMessage() {
        System.out.println("⭐ GoldStar 카페에 오신 것을 환영합니다! ⭐\n");
    }

    // 회원 여부 질문
    public void showMemberChoice() {
        System.out.println("회원 여부를 선택해주세요. (1. 회원 | 2. 비회원)");
    }

    // 메뉴판 출력 (메뉴 데이터는 Controller에게 전달받음)
    public void showMenu(MenuItem[] menuItems) {
        System.out.println("\n===== 메뉴를 선택해주세요. =====");
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ". " + menuItems[i].getName() + " : " + menuItems[i].getPrice() + "원");
        }
        System.out.println("-------------------------");
        System.out.println("0. 결제하기");
        System.out.println("===========================");
    }

    // 장바구니에 담았을 때
    public void showItemAdded(MenuItem item) {
        System.out.println("✅ " + item.getName() + "을(를) 장바구니에 담았습니다.");
    }

    // 장바구니가 가득 찼을 때
    public void showCartFull(int maxItems) {
        System.out.println("❗ 장바구니가 가득 찼습니다. (최대 " + maxItems + "개) 결제를 진행해주세요.");
    }

    // 장바구니가 비었는데 결제하려 할 때
    public void showCartEmpty() {
        System.out.println("❗ 장바구니가 비어있습니다. 메뉴를 먼저 담아주세요.");
    }

    // 잘못된 번호를 입력했을 때
    public void showInvalidMenu() {
        System.out.println("⚠ 메뉴에 있는 번호를 입력해주세요.");
    }

    // 1 또는 2만 입력해야 할 때
    public void showInvalidChoice() {
        System.out.println("⚠ 1 또는 2를 입력해주세요.");
    }

    // ===== 주문 / 결제 =====

    // 최종 주문 내역 출력 (금액은 Order가 계산한 값을 받아서 출력만 함)
    public void showOrderDetails(Order cart, User user) {
        System.out.println("\n--- 최종 주문 내역 ---");
        for (int i = 0; i < cart.getItemCount(); i++) {
            MenuItem item = cart.getItem(i);
            System.out.println("- " + item.getName() + ": " + item.getPrice() + "원");
        }
        System.out.println("--------------------");
        System.out.println("총 합계: " + cart.getTotalPrice() + "원");

        if (user.isMember()) {
            System.out.println("\n✨ 회원 혜택! " + cart.getMemberDiscount() + "% 할인이 적용됩니다. (-"
                    + cart.getDiscountPrice(user) + "원)");
        }
        System.out.println("\n💳 최종 결제 금액: " + cart.getFinalPrice(user) + "원");
    }

    // 결제 수단 선택 문구
    public void showPaymentChoice() {
        System.out.println("\n결제 수단을 선택해주세요 (1. 카드 / 2. 현금)");
    }

    // 결제 완료 결과
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
}