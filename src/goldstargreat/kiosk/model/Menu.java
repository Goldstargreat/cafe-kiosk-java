package goldstargreat.kiosk.model;

// 카페에서 판매하는 메뉴 목록(데이터)을 관리하는 클래스
// 메뉴 데이터는 '데이터'이므로 View가 아닌 Model에서 관리합니다.
public class Menu
{
    private final MenuItem[] menuItems =
            {
            new MenuItem("아메리카노", 3000),
            new MenuItem("카페라떼", 3500),
            new MenuItem("카푸치노", 3500),
            new MenuItem("바닐라라떼", 4000),
            new MenuItem("돌체라떼", 4000)
    };

    // MenuItem[]: MenuItem을 여러 개 담는 배열입니다.
    // { ... } 안에 new MenuItem(...)을 5개 써서 크기 5짜리 배열을 바로 만들고 채웁니다.
    // 배열 번호(인덱스)는 0부터 시작해요. 아메리카노가 menuItems[0], 돌체라떼가 menuItems[4]입니다.

    // 메뉴의 총 개수
    public int getSize()
    {
        return menuItems.length;
    }

    public MenuItem getMenuItem(int number)
    {
        if (number < 1 || number > menuItems.length)
        {
            return null;
        }
        return menuItems[number - 1];
    }
    // 사용자가 입력한 번호에 맞는 메뉴를 돌려줍니다.
    // 사용자는 1~5를 입력하지만 배열은 0~4라서 number - 1을 합니다. (1번 → menuItems[0])
    // 범위를 벗어난 번호(예: 7, -3)면 배열에 접근하기 전에 null(아무것도 없음)을 반환합니다.
    // 이를 안 하면 ArrayIndexOutOfBoundsException 오류로 프로그램이 멈춰요.
    // ||는 "또는"입니다. 1보다 작거나, 5보다 크면 잘못된 번호예요.

    public MenuItem[] getMenuItems()
    {
        return menuItems;
    } // 메뉴판 전체를 출력할 때 쓰려고 배열 통째로 돌려줍니다.
}