package goldstargreat.kiosk;

import goldstargreat.kiosk.controller.MainController;

public class KioskApp
{
    public static void main(String[] args)
    {
        MainController mainController = new MainController();
        mainController.start();
    }
}
// Java 프로그램은 항상 main 메서드에서 시작합니다.
//public static void main(String[] args)는 정해진 형식이라 그대로 써야 해요.
//Controller를 만들고 start()를 호출하면, 6단계의 흐름이 처음부터 실행됩니다.
//main에는 로직을 넣지 않고 "시작 버튼" 역할만 하게 둔 것입니다.
