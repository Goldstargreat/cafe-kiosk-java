package goldstargreat.kiosk.view;

import goldstargreat.kiosk.model.Order;

public class ResultView {
    public void showOrderResult(Order order) {
        System.out.println("\n==== 주문 결과 ====");
        System.out.println(order.toString()); // order 객체의 toString() 메소드 호출
        System.out.println("==================\n");
    }
}