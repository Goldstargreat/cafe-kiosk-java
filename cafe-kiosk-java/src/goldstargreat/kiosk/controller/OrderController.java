package goldstargreat.kiosk.controller;
import goldstargreat.kiosk.controller.OrderController;
import goldstargreat.kiosk.model.MenuItem;
import goldstargreat.kiosk.model.Order;
import goldstargreat.kiosk.view.ResultView;

import java.util.ArrayList;
import java.util.List;


public class OrderController {
    private final ResultView resultView = new ResultView();

    public void processOrder(int choice) {
        MenuItem selectedItem = null;

        // 간단한 메뉴 번호로 아이템 선택 예시
        switch (choice) {
            case 1:
                selectedItem = new MenuItem("아메리카노", 3000);
                break;
            case 2:
                selectedItem = new MenuItem("카페라떼", 3500);
                break;
            default:
                System.out.println("잘못된 선택입니다.");
                return;
        }

        List<MenuItem> items = new ArrayList<>();
        items.add(selectedItem);

        Order order = new Order(items);
        resultView.showOrderResult(order);
    }
}