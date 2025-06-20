package goldstargreat.kiosk.controller;

import goldstargreat.kiosk.view.MenuView;

public class MainController {
    private final MenuView menuView = new MenuView();
    private final OrderController orderController = new OrderController();

    public void start() {
        while (true) {
            menuView.showMenu();
            int choice = menuView.getUserChoice();
            if (choice == 0) break;
            orderController.processOrder(choice);
        }
    }
}
