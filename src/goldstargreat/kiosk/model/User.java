package goldstargreat.kiosk.model;

public class User
{
    private final boolean isMember;
   // boolean은 true(참) / false(거짓) 두 값만 가집니다. 회원이면 true, 비회원이면 false.
    public User(boolean isMember)
    {
        this.isMember = isMember;
    }

    public boolean isMember()
    {
        return isMember;
    }
}
// 생성자로 회원 여부를 받고, isMember()로 확인합니다.
//boolean의 getter는 getMember()가 아니라 isMember()처럼 is로 시작하는 게 관례입니다.
