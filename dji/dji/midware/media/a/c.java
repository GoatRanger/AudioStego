package dji.midware.media.a;

import dji.logic.album.a.b.f;
import java.util.Date;
import java.util.Locale;

public class c {
    public f a;
    public String b;
    public a c;
    public int d;
    public int e;
    public long f;
    public int g;
    public int h;
    private Date i = null;

    public enum a {
        SUECCESS(0),
        UNDO(1),
        INVALID_PARAM(2),
        ERR_INCOMPLETE(3),
        ERR_UNSPECIFIED(4),
        UNKNOWN(-1);
        
        private int g;

        private a(int i) {
            this.g = i;
        }

        public int a() {
            return this.g;
        }

        public boolean a(int i) {
            return this.g == i;
        }

        public static a find(int i) {
            a aVar = UNKNOWN;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return aVar;
        }
    }

    private String c() {
        if (this.a != null) {
            return "." + this.a.toString().toLowerCase(Locale.ENGLISH);
        }
        return "";
    }

    public String a() {
        return "clip_" + com.dji.frame.c.a.b(this.f + "_" + this.g + "_" + this.h + "_" + b()) + c();
    }

    public String toString() {
        return String.format("Clip No.%d's state is %s", new Object[]{Integer.valueOf(this.e), this.c.toString()});
    }

    public Date b() {
        if (this.i == null) {
            this.i = new Date();
        }
        return this.i;
    }
}
