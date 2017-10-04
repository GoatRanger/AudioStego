package dji.thirdparty.afinal.d.a;

import java.util.LinkedList;

public class f {
    private String a;
    private LinkedList<Object> b;

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public LinkedList<Object> b() {
        return this.b;
    }

    public void a(LinkedList<Object> linkedList) {
        this.b = linkedList;
    }

    public Object[] c() {
        if (this.b != null) {
            return this.b.toArray();
        }
        return null;
    }

    public String[] d() {
        if (this.b == null) {
            return null;
        }
        String[] strArr = new String[this.b.size()];
        for (int i = 0; i < this.b.size(); i++) {
            strArr[i] = this.b.get(i).toString();
        }
        return strArr;
    }

    public void a(Object obj) {
        if (this.b == null) {
            this.b = new LinkedList();
        }
        this.b.add(obj);
    }
}
