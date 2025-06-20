package goldstargreat.kiosk.model;

public class Order {
    private MenuItem item;  // 주문한 단일 메뉴
    private int totalPrice; // 총 가격

    public Order(MenuItem item) {
        this.item = item;
        this.totalPrice = item.getPrice();
    }

    public MenuItem getItem() {
        return item;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    // 주문 내역을 보기 쉽게 문자열로 반환
    @Override
    public String toString() {
        return "주문 내역:\n" +
                "- " + item.getName() + ": " + item.getPrice() + "원\n" +
                "총 합계: " + totalPrice + "원";
    }
}