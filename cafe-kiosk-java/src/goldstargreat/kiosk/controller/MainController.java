package goldstargreat.kiosk.controller;

import goldstargreat.kiosk.view.MenuView;

public class MainController {
    private final MenuView menuView = new MenuView();
    private final OrderController orderController = new OrderController();

    public void start() {
        while (true) {
            menuView.showMenu();
            int choice = menuView.getUserChoice();

            if (choice == 0) {
                menuView.showGoodbyeMessage();
                break; // 0 입력 시 루프 종료
            }
            if (choice != -1) { // -1은 잘못된 입력이므로 처리하지 않음
                orderController.processOrder(choice);
            }
        }
        // 프로그램이 끝나기 직전 Scanner 리소스를 닫는다.
        menuView.closeScanner();
    }
}