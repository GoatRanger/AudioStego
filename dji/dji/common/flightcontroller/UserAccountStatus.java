package dji.common.flightcontroller;

public enum UserAccountStatus {
    NotLoggedin(0),
    NotAuthorized(1),
    Authorized(2),
    TokenOutOfDate(3),
    InvalidToken(4),
    Unknown(255);
    
    private int data;

    private UserAccountStatus(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static UserAccountStatus find(int i) {
        UserAccountStatus userAccountStatus = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return userAccountStatus;
    }
}
