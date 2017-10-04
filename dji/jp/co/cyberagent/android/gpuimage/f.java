package jp.co.cyberagent.android.gpuimage;

import android.annotation.SuppressLint;
import android.opengl.GLES20;
import dji.midware.media.h.d;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jp.co.cyberagent.android.gpuimage.a.a;

public class f extends e {
    protected List<e> a;
    protected List<e> j;
    private int[] k;
    private int[] l;
    private final FloatBuffer m;
    private final FloatBuffer n;
    private final FloatBuffer o;

    public f() {
        this(null);
    }

    public f(List<e> list) {
        this.a = list;
        if (this.a == null) {
            this.a = new ArrayList();
        } else {
            q();
        }
        this.m = ByteBuffer.allocateDirect(h.b.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.m.put(h.b).position(0);
        this.n = ByteBuffer.allocateDirect(a.a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.n.put(a.a).position(0);
        float[] a = a.a(z.NORMAL, false, true);
        this.o = ByteBuffer.allocateDirect(a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.o.put(a).position(0);
    }

    public void a(e eVar) {
        if (eVar != null) {
            this.a.add(eVar);
            q();
        }
    }

    public void a() {
        super.a();
        for (e d : this.a) {
            d.d();
        }
    }

    public void f() {
        r();
        for (e e : this.a) {
            e.e();
        }
        super.f();
    }

    private void r() {
        if (this.l != null) {
            GLES20.glDeleteTextures(this.l.length, this.l, 0);
            this.l = null;
        }
        if (this.k != null) {
            GLES20.glDeleteFramebuffers(this.k.length, this.k, 0);
            this.k = null;
        }
    }

    public void a(int i, int i2) {
        super.a(i, i2);
        if (this.k != null) {
            r();
        }
        int size = this.a.size();
        for (int i3 = 0; i3 < size; i3++) {
            ((e) this.a.get(i3)).a(i, i2);
        }
        if (this.j != null && this.j.size() > 0) {
            int size2 = this.j.size();
            this.k = new int[(size2 - 1)];
            this.l = new int[(size2 - 1)];
            for (int i4 = 0; i4 < size2 - 1; i4++) {
                GLES20.glGenFramebuffers(1, this.k, i4);
                GLES20.glGenTextures(1, this.l, i4);
                GLES20.glBindTexture(3553, this.l[i4]);
                GLES20.glTexImage2D(3553, 0, d.c, i, i2, 0, d.c, 5121, null);
                GLES20.glTexParameterf(3553, 10240, 9729.0f);
                GLES20.glTexParameterf(3553, 10241, 9729.0f);
                GLES20.glTexParameterf(3553, 10242, 33071.0f);
                GLES20.glTexParameterf(3553, 10243, 33071.0f);
                GLES20.glBindFramebuffer(36160, this.k[i4]);
                GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.l[i4], 0);
                GLES20.glBindTexture(3553, 0);
                GLES20.glBindFramebuffer(36160, 0);
            }
        }
    }

    @SuppressLint({"WrongCall"})
    public void a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        h();
        if (i() && this.k != null && this.l != null && this.j != null) {
            int size = this.j.size();
            int i2 = 0;
            int i3 = i;
            while (i2 < size) {
                int i4;
                int i5;
                e eVar = (e) this.j.get(i2);
                if (i2 < size - 1) {
                    i4 = 1;
                } else {
                    i4 = 0;
                }
                if (i4 != 0) {
                    GLES20.glBindFramebuffer(36160, this.k[i2]);
                    GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                    GLES20.glClear(16384);
                }
                if (i2 == 0) {
                    eVar.a(i3, floatBuffer, floatBuffer2);
                } else if (i2 == size - 1) {
                    eVar.a(i3, this.m, size % 2 == 0 ? this.o : this.n);
                } else {
                    eVar.a(i3, this.m, this.n);
                }
                if (i4 != 0) {
                    GLES20.glBindFramebuffer(36160, 0);
                    i5 = this.l[i2];
                } else {
                    i5 = i3;
                }
                i2++;
                i3 = i5;
            }
        }
    }

    public List<e> c() {
        return this.a;
    }

    public List<e> p() {
        return this.j;
    }

    public void q() {
        if (this.a != null) {
            if (this.j == null) {
                this.j = new ArrayList();
            } else {
                for (e eVar : this.j) {
                    if (eVar != null) {
                        eVar.e();
                    }
                }
                this.j.clear();
                System.gc();
            }
            for (e eVar2 : this.a) {
                if (eVar2 instanceof f) {
                    ((f) eVar2).q();
                    Collection p = ((f) eVar2).p();
                    if (!(p == null || p.isEmpty())) {
                        this.j.addAll(p);
                    }
                } else {
                    this.j.add(eVar2);
                }
            }
        }
    }
}
