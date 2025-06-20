package goldstargreat.kiosk.model;
import java.util.List;

public class Order {
    private List<MenuItem> items;  // 주문한 메뉴 목록
    private int totalPrice;        // 총 가격

    public Order(List<MenuItem> items) {
        this.items = items;
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        int sum = 0;
        for (MenuItem item : items) {
            sum += item.getPrice();
        }
        this.totalPrice = sum;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("주문 내역:\n");
        for (MenuItem item : items) {
            sb.append("- ").append(item.getName()).append(": ").append(item.getPrice()).append("원\n");
        }
        sb.append("총 합계: ").append(totalPrice).append("원");
        return sb.toString();
    }
}
