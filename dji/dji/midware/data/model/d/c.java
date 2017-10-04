package dji.midware.data.model.d;

import dji.midware.data.a.b.b;
import dji.midware.data.manager.P3.n;
import java.io.File;
import java.io.FileOutputStream;

public class c extends n {
    private static c a = null;
    private File b = new File("/sdcard/testwrite.bin");
    private FileOutputStream c;
    private int d = 0;
    private boolean e = false;
    private b f;

    public static synchronized c getInstance() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c();
            }
            cVar = a;
        }
        return cVar;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    protected void setPushRecData(byte[] bArr) {
        super.setPushRecData(bArr);
    }

    protected void doPack() {
    }

    public b a() {
        return this.f;
    }

    public void a(b bVar) {
        this.f = bVar;
        setPushRecData(bVar.i);
        if (this.e) {
            try {
                if (this.c == null) {
                    this.c = new FileOutputStream(this.b);
                }
                if (this.d % 20 == 0) {
                    this.c.write(bVar.i);
                }
                this.d++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int b() {
        return ((Integer) get(0, 4, Integer.class)).intValue();
    }

    public int c() {
        return ((Integer) get(4, 4, Integer.class)).intValue();
    }

    public int d() {
        return ((Integer) get(8, 4, Integer.class)).intValue();
    }

    public int e() {
        return ((Integer) get(12, 1, Integer.class)).intValue();
    }

    public String f() {
        return get(13, e());
    }

    public int g() {
        return e() + 13;
    }
}
