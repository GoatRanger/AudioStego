package dji.midware.data.config.P3;

public class q {
    public static final boolean a = true;
    public static final int b = 13;
    public static final int c = 4;

    public enum a {
        REQUEST(0),
        ACK(1);
        
        private int c;

        private a(int i) {
            this.c = i;
        }

        public int a() {
            return this.c;
        }

        public boolean a(int i) {
            return this.c == i;
        }
    }

    public enum b {
        NO(0),
        DIC(1),
        OTHER(2),
        SIMPLE(3);
        
        private int e;

        private b(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public boolean a(int i) {
            return this.e == i;
        }
    }

    public enum c {
        YES(2),
        NO(0),
        YES_BY_PUSH(1);
        
        private int d;

        private c(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }
    }
}
