package goldstargreat.kiosk.model;

// 장바구니(주문)를 관리하는 클래스
// 화면 출력은 하지 않고, 데이터 저장과 계산만 담당합니다.
public class Order
{
    private static final int MAX_ITEMS = 10;        // 장바구니 최대 개수 (10개로 제한)
    private static final int MEMBER_DISCOUNT = 10;  // 회원 할인율 (10%)

    private final MenuItem[] items;  // 메뉴 아이템을 담을 배열
    private int itemCount;           // 현재 장바구니에 담긴 개수

    public Order()
    {
        this.items = new MenuItem[MAX_ITEMS]; // 10칸짜리 배열 생성
        this.itemCount = 0;                   // 처음엔 0개
    }

    // 장바구니에 아이템 추가
    // 성공하면 true, 장바구니가 가득 차면 false를 반환합니다.
    // (메시지 출력은 Controller가 결과를 보고 View에게 맡깁니다.)
    public boolean addItem(MenuItem item)
    {
        if (itemCount >= MAX_ITEMS)
        {
            return false;
        }
        items[itemCount] = item;
        itemCount++;
        return true;
    }

    // 장바구니가 비었는지 확인
    public boolean isEmpty()
    {
        return itemCount == 0;
    }

    // 현재 장바구니에 담긴 개수
    public int getItemCount()
    {
        return itemCount;
    }

    // 장바구니 최대 개수
    public int getMaxItems()
    {
        return MAX_ITEMS;
    }

    // i번째 아이템 반환 (View에서 장바구니 내역을 출력할 때 사용)
    public MenuItem getItem(int index)
    {
        return items[index];
    }

    // 할인 전 총 금액
    public int getTotalPrice()
    {
        int sum = 0;
        for (int i = 0; i < itemCount; i++)
        {
            sum = sum + items[i].getPrice();
        }
        return sum;
    }

    // 할인 금액 (회원이면 10%, 비회원이면 0원)
    public int getDiscountPrice(User user)
    {
        if (user.isMember())
        {
            return getTotalPrice() * MEMBER_DISCOUNT / 100;
        }
        return 0;
    }

    // 최종 결제 금액 = 총 금액 - 할인 금액
    public int getFinalPrice(User user)
    {
        return getTotalPrice() - getDiscountPrice(user);
    }

    // 할인율 반환 (View에서 "10% 할인" 문구를 출력할 때 사용)
    public int getMemberDiscount()
    {
        return MEMBER_DISCOUNT;
    }
}