package dji.midware.data.config.a;

public class a {
    public static final int a = 10;

    public enum a {
        a(0),
        File(1),
        Stream(2),
        Num(3),
        UNDEFINED(100);
        
        private int f;

        private a(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }

        public boolean a(int i) {
            return this.f == i;
        }

        public static a find(int i) {
            a aVar = UNDEFINED;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return aVar;
        }
    }

    public enum b {
        REQUEST(0),
        DATA(1),
        ACK(2),
        PUSH(3),
        ABORT(4),
        DEL(5),
        PAUSE(6),
        RESUME(7),
        UNDEFINED(100);
        
        private int j;

        private b(int i) {
            this.j = i;
        }

        public int a() {
            return this.j;
        }

        public boolean a(int i) {
            return this.j == i;
        }

        public static b find(int i) {
            b bVar = UNDEFINED;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return bVar;
        }
    }

    public enum c {
        ORG(0),
        THM(1),
        SCR(2),
        CLIP(3),
        Stream(4),
        Pano(5),
        Pano_SCR(6),
        Pano_THM(7),
        TIMELAPSE(8),
        MP4(9),
        UNDEFINED(100);
        
        private int l;

        private c(int i) {
            this.l = i;
        }

        public int a() {
            return this.l;
        }

        public boolean a(int i) {
            return this.l == i;
        }

        public static c find(int i) {
            c cVar = UNDEFINED;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return cVar;
        }
    }
}
