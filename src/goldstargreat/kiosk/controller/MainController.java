package goldstargreat.kiosk.controller;

import goldstargreat.kiosk.model.Menu;
import goldstargreat.kiosk.model.MenuItem;
import goldstargreat.kiosk.model.Order;
import goldstargreat.kiosk.model.User;
import goldstargreat.kiosk.view.KioskView;

// 프로그램의 흐름(순서)만 담당하는 클래스
// 출력은 View에게, 데이터와 계산은 Model에게 맡깁니다.
public class MainController
{
    private final KioskView kioskView = new KioskView();
    private final Menu menu = new Menu();

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
            }

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