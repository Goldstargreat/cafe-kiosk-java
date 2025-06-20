package goldstargreat.kiosk.controller;

import goldstargreat.kiosk.view.MenuView;

public class MainController {
    private final MenuView menuView = new MenuView();

    public void start() {
        menuView.showWelcomeMessage();

        // --- 1. 회원 여부 확인 ---
        System.out.println("회원 여부를 선택해주세요.");
        System.out.println("1. 회원  |  2. 비회원");
        User user;
        while (true) {
            int userChoice = menuView.getUserChoice();
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

        // --- 2. 장바구니에 메뉴 담기 ---
        Order cart = new Order();
        while (true) {
            menuView.showMenu();
            int choice = menuView.getUserChoice();

            if (choice == 1) {
                MenuItem americano = new MenuItem("아메리카노", 3000);
                cart.addItem(americano);
                System.out.println("✅ 아메리카노를 장바구니에 담았습니다.");
            } else if (choice == 2) {
                MenuItem latte = new MenuItem("카페라떼", 3500);
                cart.addItem(latte);
                System.out.println("✅ 카페라떼를 장바구니에 담았습니다.");
            } else if (choice == 0) {
                // 0번 '결제하기'를 누르면 반복문 탈출
                break;
            } else {
                System.out.println("⚠ 메뉴에 있는 번호를 입력해주세요.");
            }
        }

        // --- 3. 결제 진행 ---
        if (cart.getItemCount() == 0) {
            System.out.println("\n장바구니가 비어있어 주문을 종료합니다.");
        } else {
            System.out.println("\n--- 최종 주문 내역 ---");
            System.out.println(cart.toString()); // 장바구니 내용 모두 출력

            int finalPrice = cart.getTotalPrice();
            if (user.isMember()) {
                System.out.println("\n✨ 회원 혜택! 10% 할인이 적용됩니다.");
                finalPrice = (int) (finalPrice * 0.9); // 10% 할인
            }

            System.out.println("\n💳 최종 결제 금액: " + finalPrice + "원");

            System.out.println("결제 수단을 선택해주세요 (1. 카드 / 2. 현금)");
            while (true) {
                int paymentChoice = menuView.getUserChoice();
                if (paymentChoice == 1) {
                    System.out.println("\n🎉 카드로 결제가 완료되었습니다.");
                    break;
                } else if (paymentChoice == 2) {
                    System.out.println("\n🎉 현금으로 결제가 완료되었습니다.");
                    break;
                } else {
                    System.out.println("⚠ 1 또는 2를 입력해주세요.");
                }
            }
        }

        // --- 4. 마무리 ---
        menuView.showGoodbyeMessage();
        menuView.closeScanner();
    }
}