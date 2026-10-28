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

    // 메뉴의 총 개수
    public int getSize()
    {
        return menuItems.length;
    }

    // 번호(1부터 시작)에 맞는 메뉴 아이템 반환
    // 범위를 벗어난 번호면 null 반환
    public MenuItem getMenuItem(int number)
    {
        if (number < 1 || number > menuItems.length)
        {
            return null;
        }
        return menuItems[number - 1];
    }

    // 메뉴 전체 배열 반환 (View에서 메뉴판을 출력할 때 사용)
    public MenuItem[] getMenuItems() {
        return menuItems;
    }
}