package dji.midware.data.forbid;

public class NfzAccountEvent {
    private String mAccount = "";
    private UnlimitAreasChanged mAreasChanged = UnlimitAreasChanged.TRUE;
    private String mFlycsn = "";

    public String getFlycsn() {
        return this.mFlycsn;
    }

    public void setFlycsn(String str) {
        this.mFlycsn = str;
    }

    public void setAccount(String str) {
        this.mAccount = str;
    }

    public String getAccount() {
        return this.mAccount;
    }

    public UnlimitAreasChanged getAreasChanged() {
        return this.mAreasChanged;
    }
}
