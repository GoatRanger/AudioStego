package dji.thirdparty.g.a;

import java.util.ArrayList;

public class j implements i {
    protected static final String a = System.getProperty("line.separator");
    private final ArrayList b = new ArrayList();

    public static class a implements dji.thirdparty.g.a.i.a {
        private final String a;
        private final String b;

        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public String toString() {
            return a(null);
        }

        public String a(String str) {
            String str2 = this.a + ": " + this.b;
            if (str != null) {
                return str + str2;
            }
            return str2;
        }
    }

    public void a(String str, String str2) {
        a(new a(str, str2));
    }

    public void a(dji.thirdparty.g.a.i.a aVar) {
        this.b.add(aVar);
    }

    public ArrayList a() {
        return new ArrayList(this.b);
    }

    public String toString() {
        return a(null);
    }

    public String a(String str) {
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.b.size(); i++) {
            if (i > 0) {
                stringBuffer.append(a);
            }
            stringBuffer.append(((dji.thirdparty.g.a.i.a) this.b.get(i)).a(str + "\t"));
        }
        return stringBuffer.toString();
    }
}
