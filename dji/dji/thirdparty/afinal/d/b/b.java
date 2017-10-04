package dji.thirdparty.afinal.d.b;

import java.util.Date;

public class b {
    private String a;
    private Object b;

    public b(String str, Object obj) {
        this.a = str;
        this.b = obj;
    }

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public Object b() {
        if ((this.b instanceof Date) || (this.b instanceof java.sql.Date)) {
            return dji.thirdparty.afinal.g.b.a.format(this.b);
        }
        return this.b;
    }

    public void a(Object obj) {
        this.b = obj;
    }
}
