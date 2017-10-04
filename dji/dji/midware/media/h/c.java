package dji.midware.media.h;

import android.opengl.GLES20;
import android.os.Build.VERSION;
import android.util.Log;
import dji.midware.media.e;

public abstract class c {
    public final boolean b = false;
    protected final int c = 12440;
    protected final int d = 4;
    protected boolean e = false;

    public abstract void a(long j);

    public abstract void a(Object obj);

    public abstract void b();

    public abstract void e();

    public abstract void f();

    public abstract void g();

    public abstract void h();

    public abstract void i();

    public abstract void j();

    public abstract boolean k();

    public abstract int l();

    public abstract int m();

    public abstract String n();

    public static c o() {
        if (VERSION.SDK_INT >= 18) {
            return new b();
        }
        return new a();
    }

    protected void a(String str) {
        while (true) {
            int glGetError = GLES20.glGetError();
            if (glGetError != 0) {
                Log.e(n(), str + ": glError " + glGetError);
                e.b(n(), str + ": glError " + glGetError);
            } else {
                return;
            }
        }
    }
}
