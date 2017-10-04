package dji.thirdparty.afinal;

import dji.thirdparty.afinal.c.d;

class a$c extends d<Object, Void, Void> {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    final /* synthetic */ a f;

    private a$c(a aVar) {
        this.f = aVar;
    }

    protected /* synthetic */ Object b(Object[] objArr) {
        return a(objArr);
    }

    protected Void a(Object... objArr) {
        switch (((Integer) objArr[0]).intValue()) {
            case 1:
                a.a(this.f);
                break;
            case 2:
                a.b(this.f);
                break;
            case 3:
                a.c(this.f);
                break;
            case 4:
                a.a(this.f, String.valueOf(objArr[1]));
                break;
            case 5:
                a.b(this.f, String.valueOf(objArr[1]));
                break;
        }
        return null;
    }
}
