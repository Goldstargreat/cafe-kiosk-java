package goldstargreat.kiosk.controller;

import goldstargreat.kiosk.model.Menu;
import goldstargreat.kiosk.model.MenuItem;
import goldstargreat.kiosk.model.Order;
import goldstargreat.kiosk.model.User;
import goldstargreat.kiosk.view.KioskView;
// Controller는 Model과 View를 모두 사용하므로 둘 다 import합니다.

public class MainController
{
    private final KioskView kioskView = new KioskView();
    private final Menu menu = new Menu();
    // 화면 담당(View)과 메뉴판(Model)을 하나씩 만들어 둡니다.

    public void start()
    {
        kioskView.showWelcomeMessage();

        // 1. 회원 여부 확인
        User user = selectUser();

        // 2. 메뉴 담기
        Order cart = new Order();
        selectMenus(cart);

        // 3. 주문 내역 확인 및 결제
        kioskView.showOrderDetails(cart, user);
        int paymentChoice = selectPayment();
        kioskView.showPaymentResult(paymentChoice);

        // 4. 마무리
        kioskView.showGoodbyeMessage();
        kioskView.closeScanner();
    }
    // 프로그램 전체 순서입니다. 환영 → 회원 선택 → 메뉴 담기 → 주문 내역 → 결제 → 작별.
    //복잡한 부분은 selectUser, selectMenus, selectPayment로 나눠서 start()는 목차처럼 읽힙니다.
    // new Order()로 빈 장바구니를 만들어 selectMenus에 넘기면, 그 안에서 메뉴가 채워집니다.
    // (객체는 참조로 전달되므로, 메서드 안에서 담은 내용이 밖의 cart에도 그대로 남아요.)

    // 회원 여부를 입력받아 User 객체를 만들어 반환
    private User selectUser()
    {
        kioskView.showMemberChoice();
        while (true)
        {
            int userChoice = kioskView.getUserInput();
            if (userChoice == 1)
            {
                return new User(true);
            } else if (userChoice == 2)
            {
                return new User(false);
            }
            kioskView.showInvalidChoice();
        }
    }

    // 0(결제하기)을 누를 때까지 메뉴를 장바구니에 담기
    private void selectMenus(Order cart)
    {
        while (true)
        {
            kioskView.showMenu(menu.getMenuItems());
            int choice = kioskView.getUserInput();
            // 매번 메뉴판을 보여 주고 번호를 입력받습니다.
            // Menu에서 메뉴 배열을 꺼내 View에게 전달하는 것이 Controller의 역할입니다.

            if (choice == 0)
            {
                if (cart.isEmpty())
                {
                    kioskView.showCartEmpty();
                    continue; // 다시 메뉴판으로
                }
                return; // 결제 단계로 이동
            }

            MenuItem selectedItem = menu.getMenuItem(choice);
            if (selectedItem == null)
            {
                kioskView.showInvalidMenu();
                continue;
            } // 입력한 번호에 맞는 메뉴를 Menu에게 요청합니다.
            // null이면 없는 번호(또는 숫자가 아닌 입력으로 받은 -1)이므로 경고 후 처음으로 돌아갑니다.

            boolean added = cart.addItem(selectedItem);
            if (added)
            {
                kioskView.showItemAdded(selectedItem);
            } else
            {
                kioskView.showCartFull(cart.getMaxItems());
            }
        }
    }
    // 장바구니에 담고, 결과(true/false)를 added에 받습니다.
    // 성공이면 "담았습니다", 실패면 "가득 찼습니다"를 View에게 출력시킵니다.
    // 이렇게 Model이 결과를 알려 주고 → Controller가 판단하고 → View가 출력하는 흐름이 MVC의 핵심입니다.

    // 결제 수단(1. 카드 / 2. 현금)을 입력받아 반환
    private int selectPayment()
    {
        kioskView.showPaymentChoice();
        while (true)
        {
            int paymentChoice = kioskView.getUserInput();
            if (paymentChoice == 1 || paymentChoice == 2)
            {
                return paymentChoice;
            }
            kioskView.showInvalidChoice();
        }
    }
}

// Model과 View가 다 준비됐으니, 이제 어떤 순서로 실행할지를 정합니다.