package goldstargreat.kiosk.model;

// 사용자(회원/비회원) 정보를 표현하는 클래스
public class User
{
    private final boolean isMember;

    public User(boolean isMember)
    {
        this.isMember = isMember;
    }

    public boolean isMember()
    {
        return isMember;
    }
}
