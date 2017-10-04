package dji.thirdparty.afinal.e;

public class d extends a {
    private static final long a = 1;
    private String b = null;

    public d(String str) {
        this.b = str;
    }

    public void printStackTrace() {
        if (this.b != null) {
            System.err.println(this.b);
        }
        super.printStackTrace();
    }
}
