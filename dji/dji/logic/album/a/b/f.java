package dji.logic.album.a.b;

public enum f {
    JPG(0),
    DNG(1),
    MOV(2),
    MP4(3),
    PANO(4),
    TIF(5),
    UNDEFINED(100);
    
    private int h;

    private f(int i) {
        this.h = i;
    }

    public int a() {
        return this.h;
    }

    public boolean a(int i) {
        return this.h == i;
    }

    public boolean b() {
        return this == MOV || this == MP4;
    }

    public boolean c() {
        return (this == MOV || this == DNG) ? false : true;
    }

    public static f find(int i) {
        f fVar = UNDEFINED;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2].a(i)) {
                return values()[i2];
            }
        }
        return fVar;
    }

    public static f find(String str) {
        f fVar = UNDEFINED;
        for (int i = 0; i < values().length; i++) {
            if (values()[i].toString().equals(str)) {
                return values()[i];
            }
        }
        return fVar;
    }
}
