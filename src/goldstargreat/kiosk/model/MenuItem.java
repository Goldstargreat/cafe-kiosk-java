package goldstargreat.kiosk.model;

// 메뉴 하나(이름 + 가격)를 표현하는 클래스
public class MenuItem
{
    private final String name;
    private final int price;

    public MenuItem(String name, int price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public int getPrice()
    {
        return price;
    }
}