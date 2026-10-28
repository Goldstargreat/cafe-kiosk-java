package goldstargreat.kiosk.model;

// 장바구니(주문)를 관리하는 클래스
// 화면 출력은 하지 않고, 데이터 저장과 계산만 담당합니다.
public class Order
{
    private static final int MAX_ITEMS = 10;
    private static final int MEMBER_DISCOUNT = 10;
    // 상수: 절대 바뀌지 않는 값입니다. 이름은 대문자와 _로 쓰는 게 관례예요.
    // static: 객체마다 따로 만들지 않고 클래스에 하나만 존재합니다.
    // 숫자 10을 코드 곳곳에 직접 쓰는 대신 이름을 붙여 두면, 나중에 할인율을 바꿀 때 여기 한 곳만 고치면 됩니다.

    private final MenuItem[] items;
    private int itemCount;
    // items: 담은 메뉴들을 저장할 배열입니다.
    // itemCount: 지금까지 몇 개 담았는지 셉니다. 배열은 10칸이지만 실제로 채워진 칸은 이 숫자만큼이에요.
    // itemCount는 계속 바뀌므로 final이 아닙니다.

    public Order()
    {
        this.items = new MenuItem[MAX_ITEMS];
        this.itemCount = 0;
    }
    // 장바구니를 만들 때 10칸짜리 빈 배열을 준비하고, 개수를 0으로 시작합니다.
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
    // 장바구니가 가득 찼으면(10개 이상) 담지 않고 false를 반환합니다.
    // 아니면 items[itemCount] 칸에 넣습니다. 처음엔 itemCount가 0이니 0번 칸, 다음엔 1번 칸…
    // itemCount++로 개수를 1 늘리고 true를 반환합니다.
    // 여기서 메시지를 출력하지 않는 이유: Model은 데이터만 다루고, "가득 찼습니다" 같은 출력은 View의 일이기 때문입니다.
    // 결과(true/false)만 알려 주면 Controller가 판단합니다.

    public boolean isEmpty()
    {
        return itemCount == 0;
    } // itemCount == 0의 결과(true/false)를 그대로 반환합니다. 비어 있으면 결제를 막을 때 씁니다.

    public int getItemCount()
    {
        return itemCount;
    }

    public int getMaxItems()
    {
        return MAX_ITEMS;
    }

    public MenuItem getItem(int index)
    {
        return items[index];
    } // 현재 개수, 최대 개수, index번째 메뉴를 돌려주는 getter들입니다. View가 장바구니 내역을 출력할 때 사용해요.


    public int getTotalPrice()
    {
        int sum = 0;
        for (int i = 0; i < itemCount; i++)
        {
            sum = sum + items[i].getPrice();
        }
        return sum;
    }
    // 할인 전 총 금액
    // 할인 전 총 금액입니다.
    //sum을 0에서 시작해서, 담긴 메뉴 수(itemCount)만큼 반복하며 가격을 더합니다.
    //i < MAX_ITEMS가 아니라 i < itemCount인 이유: 비어 있는 칸은 null이라 getPrice()를 부르면 NullPointerException이 납니다.

    public int getDiscountPrice(User user)
    {
        if (user.isMember())
        {
            return (getTotalPrice() * MEMBER_DISCOUNT) / 100;
        }
        return 0;
    }
    // 회원이면 총 금액의 10%를, 비회원이면 0원을 반환합니다.
    // * 10 / 100으로 정수끼리만 계산합니다. * 0.9 같은 실수 계산은 소수점 오차가 생길 수 있어서 피했어요.
    // 곱하기를 먼저 해야 합니다. 10 / 100을 먼저 하면 정수 나눗셈이라 0이 돼 버려요.

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