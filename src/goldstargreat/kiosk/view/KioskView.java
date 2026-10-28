package goldstargreat.kiosk.view;

import goldstargreat.kiosk.model.MenuItem;
import goldstargreat.kiosk.model.Order;
import goldstargreat.kiosk.model.User;
import java.util.Scanner;

// 화면 출력과 사용자 입력만 담당하는 클래스
// 계산이나 데이터 저장은 하지 않습니다.
public class KioskView
{
    private final Scanner scanner = new Scanner(System.in);

    // System.in(키보드)에서 입력을 읽는 Scanner를 하나 만들어 둡니다. 입력할 때마다 새로 만들지 않고 이것 하나를 계속 씁니다.

    public int getUserInput()
    {
        System.out.print("입력: ");
        String input = scanner.nextLine();
        // print는 줄바꿈 없이 출력합니다. 그래서 커서가 "입력: “ 바로 옆에 옵니다.
        // nextLine()은 Enter를 칠 때까지 한 줄을 문자열로 읽습니다.
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e)
        {
            System.out.println("⚠ 숫자만 입력하세요!");
            return -1;
        }
    } // trim(): 앞뒤 공백을 지웁니다. " 3 "을 "3"으로.
//       Integer.parseInt(): 문자열 "3"을 숫자 3으로 바꿉니다.
//        "abc"처럼 숫자가 아니면 NumberFormatException 예외가 발생합니다.
//        try-catch로 잡아서 프로그램이 멈추지 않게 하고, 경고 후 -1을 반환합니다.
//        -1은 메뉴 번호에도, 회원 선택(1/2)에도 없는 값이라 자연스럽게 "잘못된 입력"으로 처리됩니다.

    // 스캐너 리소스 닫기
    public void closeScanner() {
        scanner.close();
    }

    // ===== 안내 문구 =====

    // 환영 인사
    public void showWelcomeMessage() {
        System.out.println("⭐ GoldStar 카페에 오신 것을 환영합니다! ⭐\n");
    }

    // 회원 여부 질문
    public void showMemberChoice() {
        System.out.println("회원 여부를 선택해주세요. (1. 회원 | 2. 비회원)");
    }

    // 메뉴판 출력 (메뉴 데이터는 Controller에게 전달받음)
    public void showMenu(MenuItem[] menuItems)
    {
        System.out.println("\n===== 메뉴를 선택해주세요. =====");
        for (int i = 0; i < menuItems.length; i++)
        {
            System.out.println((i + 1) + ". " + menuItems[i].getName() + " : " + menuItems[i].getPrice() + "원");
        }
        System.out.println("-------------------------");
        System.out.println("0. 결제하기");
        System.out.println("===========================");
    }
    // 메뉴 배열을 매개변수로 받아서 출력합니다. View가 메뉴 데이터를 직접 갖고 있지 않아요.
    // 배열은 0번부터지만 사람에게는 1번부터 보여 주려고 i + 1을 출력합니다.
    // (i + 1)에 괄호가 꼭 필요합니다. 없으면 i + 1 + ". "가 아니라 문자열 이어 붙이기가 돼서 01. 처럼 나올 수 있어요.

    // 장바구니에 담았을 때
    public void showItemAdded(MenuItem item)
    {
        System.out.println("✅ " + item.getName() + "을(를) 장바구니에 담았습니다.");
    }

    // 장바구니가 가득 찼을 때
    public void showCartFull(int maxItems)
    {
        System.out.println("❗ 장바구니가 가득 찼습니다. (최대 " + maxItems + "개) 결제를 진행해주세요.");
    }

    // 장바구니가 비었는데 결제하려 할 때
    public void showCartEmpty()
    {
        System.out.println("❗ 장바구니가 비어있습니다. 메뉴를 먼저 담아주세요.");
    }

    // 잘못된 번호를 입력했을 때
    public void showInvalidMenu() {
        System.out.println("⚠ 메뉴에 있는 번호를 입력해주세요.");
    }

    // 1 또는 2만 입력해야 할 때
    public void showInvalidChoice() {
        System.out.println("⚠ 1 또는 2를 입력해주세요.");
    }

    // ===== 주문 / 결제 =====

    // 최종 주문 내역 출력 (금액은 Order가 계산한 값을 받아서 출력만 함)
    public void showOrderDetails(Order cart, User user)
    {
        System.out.println("\n--- 최종 주문 내역 ---");
        for (int i = 0; i < cart.getItemCount(); i++)
        {
            MenuItem item = cart.getItem(i);
            System.out.println("- " + item.getName() + ": " + item.getPrice() + "원");
        }
        // 장바구니에 담긴 개수만큼 반복하며 메뉴 이름과 가격을 한 줄씩 출력합니다.
        //cart.getItem(i)로 i번째 메뉴를 꺼내 item 변수에 잠깐 담아 두면 아래 줄이 짧아집니다.
        System.out.println("--------------------");
        System.out.println("총 합계: " + cart.getTotalPrice() + "원");

        if (user.isMember())
        {
            System.out.println("\n✨ 회원 혜택! " + cart.getMemberDiscount() + "% 할인이 적용됩니다. (-"
                    + cart.getDiscountPrice(user) + "원)");
        }
        System.out.println("\n💳 최종 결제 금액: " + cart.getFinalPrice(user) + "원");
    }
    // 금액은 직접 계산하지 않고 Order에게 물어봐서 출력만 합니다. (getTotalPrice, getDiscountPrice, getFinalPrice)
    // 회원일 때만 할인 문구를 보여 줍니다.

    // 결제 수단 선택 문구
    public void showPaymentChoice()
    {
        System.out.println("\n결제 수단을 선택해주세요 (1. 카드 / 2. 현금)");
    }

    // 결제 완료 결과
    public void showPaymentResult(int paymentChoice)
    {
        if (paymentChoice == 1)
        {
            System.out.println("\n🎉 카드로 결제가 완료되었습니다.");
        } else {
            System.out.println("\n🎉 현금으로 결제가 완료되었습니다.");
        }
    }

    // 작별 인사
    public void showGoodbyeMessage()
    {
        System.out.println("\n주문해 주셔서 감사합니다. 맛있게 드세요!");
    }
}