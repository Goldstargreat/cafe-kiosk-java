package goldstargreat.kiosk.controller;

import goldstargreat.kiosk.model.MenuItem;
import goldstargreat.kiosk.model.Order;
import goldstargreat.kiosk.model.User;
import goldstargreat.kiosk.view.KioskView;

public class MainController {
    private final KioskView kioskView = new KioskView();

    public void start() {
        kioskView.showWelcomeMessage();

        // 1. 회원 여부 확인 (이전과 동일)
        System.out.println("회원 여부를 선택해주세요. (1. 회원 | 2. 비회원)");
        User user;
        while (true) {
            int userChoice = kioskView.getUserInput();
            if (userChoice == 1 || userChoice == 2) {
                user = new User(userChoice == 1);
                break;
            } else {
                System.out.println("⚠ 1 또는 2를 입력해주세요.");
            }
        }

        Order cart = new Order();

        // 2. 메인 루프: 메뉴 담기와 결제 처리
        while (true) {
            kioskView.showMenu();
            int choice = kioskView.getUserInput();

            // --- ★★★ 길었던 if-else if 문이 이렇게 짧아졌습니다 ★★★ ---
            if (choice > 0 && choice <= kioskView.getMenuSize()) {
                // 1. KioskView에서 선택한 번호에 맞는 메뉴 아이템을 가져옵니다.
                MenuItem selectedItem = kioskView.getMenuItem(choice);
                // 2. 장바구니에 추가합니다.
                cart.addItem(selectedItem);
                System.out.println("✅ " + selectedItem.getName() + "을(를) 장바구니에 담았습니다.");

            } else if (choice == 0) { // 결제하기 (이전과 동일)
                if (cart.getItemCount() == 0) {
                    System.out.println("❗ 장바구니가 비어있습니다. 메뉴를 먼저 담아주세요.");
                    continue;
                }

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
                break;
            } else {
                System.out.println("⚠ 메뉴에 있는 번호를 입력해주세요.");
            }
        }

        // 4. 마무리 인사 (이전과 동일)
        kioskView.showGoodbyeMessage();
        kioskView.closeScanner();
    }
}