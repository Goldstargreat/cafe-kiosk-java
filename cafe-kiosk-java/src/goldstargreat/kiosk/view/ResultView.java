package goldstargreat.kiosk.view;

import goldstargreat.kiosk.model.Order;

public class ResultView {
    public void showOrderResult(Order order) {
        System.out.println("\n==== 주문 결과 ====");
        System.out.println(order);
        System.out.println("==================\n");
    }
}