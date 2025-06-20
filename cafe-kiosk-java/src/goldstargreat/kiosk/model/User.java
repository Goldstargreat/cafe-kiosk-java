package goldstargreat.kiosk.model;

public class User {
    private final boolean isMember;

    public User(boolean isMember) {
        this.isMember = isMember;
    }

    public boolean isMember() {
        return isMember;
    }
}
