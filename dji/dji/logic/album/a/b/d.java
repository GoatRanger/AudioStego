package dji.logic.album.a.b;

public enum d {
    Size_640_480p(0),
    Size_1280_720p(4),
    Size_1920_1080p(10),
    Size_3840_2160p(16),
    Size_4096_2160p(22),
    UNDEFINED(100);
    
    private int g;
    private int h;

    private d(int i) {
        this.h = 0;
        this.g = i;
    }

    public int a() {
        return this.g;
    }

    public int b() {
        return this.h;
    }

    private void b(int i) {
        this.h = i;
    }

    public boolean a(int i) {
        return this.g == i;
    }

    public static d find(int i) {
        d dVar;
        d dVar2 = UNDEFINED;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2].a(i)) {
                dVar = values()[i2];
                break;
            }
        }
        dVar = dVar2;
        dVar.b(i);
        return dVar;
    }

    public static d find(String str) {
        d dVar = UNDEFINED;
        for (int i = 0; i < values().length; i++) {
            if (values()[i].toString().equals(str)) {
                return values()[i];
            }
        }
        return dVar;
    }

    public boolean c() {
        return this.g > 13;
    }
}
