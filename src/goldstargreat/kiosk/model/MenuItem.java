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
    // 생성자: new MenuItem("아메리카노", 3000)으로 객체를 만들 때 실행됩니다.
    // this.name은 클래스의 필드, 그냥 name은 매개변수입니다. 이름이 같아서 this로 구분해요.
    // final 필드는 생성자에서 딱 한 번 값을 넣을 수 있습니다.

    public String getName()
    {
        return name;
    }

    public int getPrice()
    {
        return price;
    }
}
// getter: 필드가 private이라 밖에서 직접 볼 수 없으니, 값을 읽을 수 있는 메서드를 열어 줍니다.
//값을 바꾸는 setter는 만들지 않았어요. 읽기만 가능하게 한 것입니다.