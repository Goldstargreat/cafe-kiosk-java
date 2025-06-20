package goldstargreat.kiosk.controller;

import goldstargreat.kiosk.model.MenuItem;
import goldstargreat.kiosk.model.Order;
import goldstargreat.kiosk.model.User;
import goldstargreat.kiosk.view.KioskView; // KioskView를 사용합니다.

public class MainController {
    private final KioskView kioskView = new KioskView();

    public void start() {
        kioskView.showWelcomeMessage();

        // 1. 회원 여부 확인
        System.out.println("회원 여부를 선택해주세요.");
        System.out.println("1. 회원  |  2. 비회원");
        User user;
        while (true) {
            int userChoice = kioskView.getUserInput(); // 모든 입력은 getUserInput()으로 처리
            if (userChoice == 1) {
                user = new User(true);
                break;
            } else if (userChoice == 2) {
                user = new User(false);
                break;
            } else {
                System.out.println("⚠ 1 또는 2를 입력해주세요.");
            }
        }

        // 2. 장바구니에 메뉴 담기
        Order cart = new Order();
        while (true) {
            kioskView.showMenu();
            int choice = kioskView.getUserInput();

            if (choice == 1) {
                cart.addItem(new MenuItem("아메리카노", 3000));
                System.out.println("✅ 아메리카노를 장바구니에 담았습니다.");
            } else if (choice == 2) {
                cart.addItem(new MenuItem("카페라떼", 3500));
                System.out.println("✅ 카페라떼를 장바구니에 담았습니다.");
            } else if (choice == 0) {
                break;
            } else {
                System.out.println("⚠ 메뉴에 있는 번호를 입력해주세요.");
            }
        }

        // 3. 결제 진행
        if (cart.getItemCount() == 0) {
            System.out.println("\n장바구니가 비어있어 주문을 종료합니다.");
        } else {
            kioskView.showOrderDetails(cart, user);
            kioskView.showPaymentChoice();

            while (true) {
                int paymentChoice = kioskView.getUserInput();
                if (paymentChoice == 1 || paymentChoice == 2) {
                    kioskView.showPaymentResult(paymentChoice);
                    break;
                } else {
                    System.out.println("⚠ 1 또는 2를 입력해주세요.");
                }
            }
        }

        // 4. 마무리
        kioskView.showGoodbyeMessage();
        kioskView.closeScanner(); // Scanner도 한 번만 닫아주면 됩니다.
    }
}