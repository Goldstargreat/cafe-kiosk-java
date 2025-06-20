package goldstargreat.kiosk.model;

public class Order {
    private static final int MAX_ITEMS = 10; // 장바구니 최대 개수 (10개로 제한)
    private final MenuItem[] items;           // 메뉴 아이템을 담을 '배열'
    private int itemCount;                    // 현재 장바구니에 담긴 개수

    public Order() {
        this.items = new MenuItem[MAX_ITEMS]; // 10칸짜리 배열 생성
        this.itemCount = 0;                   // 처음엔 0개
    }

    // 장바구니에 아이템 추가
    public void addItem(MenuItem item) {
        if (itemCount < MAX_ITEMS) { // 장바구니가 꽉 차지 않았을 때만 추가
            items[itemCount] = item;
            itemCount++;
        } else {
            System.out.println("❗ 장바구니가 가득 찼습니다.");
        }
    }

    // 현재 장바구니에 담긴 개수를 반환
    public int getItemCount() {
        return itemCount;
    }

    // 전체 가격 계산
    public int getTotalPrice() {
        int sum = 0;
        // 배열에 담긴 개수(itemCount)까지만 반복문 실행
        for (int i = 0; i < itemCount; i++) {
            sum += items[i].getPrice();
        }
        return sum;
    }

    // 장바구니 내역을 문자열로 만들기
    @Override
    public String toString() {
        if (itemCount == 0) {
            return "장바구니가 비어있습니다.";
        }
        String result = "--- 장바구니 내역 ---\n";
        for (int i = 0; i < itemCount; i++) {
            result += "- " + items[i].getName() + ": " + items[i].getPrice() + "원\n";
        }
        result += "--------------------\n";
        result += "총 합계: " + getTotalPrice() + "원";
        return result;
    }
}