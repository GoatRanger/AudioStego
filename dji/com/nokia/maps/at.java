package com.nokia.maps;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.os.Build.VERSION;
import com.autonavi.amap.mapcore.MapCore;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

class at implements EGLConfigChooser {
    private static boolean g = false;
    private static boolean h = true;
    private static int k = Integer.MAX_VALUE;
    private static boolean l = false;
    private static volatile EGLConfig m = null;
    private static volatile EGLConfig n = null;
    private static volatile EGLConfig o = null;
    private static volatile EGLConfig p = null;
    private static int[] q = new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 16, 12326, 0, 12352, 4, 12344};
    private static int[] r = new int[]{12324, 5, 12323, 6, 12322, 5, 12321, 8, 12325, 16, 12326, 0, 12338, 0, 12337, 0, 12352, 4, 12344};
    private static int[] s = new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 16, 12512, 0, 12338, 0, 12352, 4, 12344};
    private static int u = 0;
    private static final String[] v = new String[]{"Adreno.* 20\\d?", "Adreno.* 4\\d?0"};
    private static final String[] w = new String[]{"Adreno.* 3\\d{2}"};
    private static final String[] x = new String[]{"(Mali-).*"};
    protected int a;
    protected int b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    private boolean i = false;
    private boolean j = false;
    private int[] t = new int[1];

    public at(Context context) {
        if (e()) {
            h = false;
        }
        if (e() && !h) {
            f();
        }
    }

    private int[] d() {
        q[q.length - 2] = 4;
        return q;
    }

    public EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, boolean z, boolean z2, boolean z3) {
        EGLConfig a = a(egl10, eGLDisplay, s, z, z2, z3);
        if (a == null) {
            a = a(egl10, eGLDisplay, d(), z, z2, z3);
        }
        if (a == null) {
            q[q.length - 2] = 4;
            a = a(egl10, eGLDisplay, q, z, z2, z3);
        }
        if (a == null) {
            int length = r.length - 2;
            if (length >= 0 && length < q.length) {
                q[length] = 4;
            }
            a = a(egl10, eGLDisplay, r, z, z2, z3);
        }
        if (a != null) {
            return a;
        }
        throw new IllegalArgumentException("No configs match configSpec");
    }

    public EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, int[] iArr, boolean z, boolean z2, boolean z3) {
        for (int i = 0; i < iArr.length - 1; i += 2) {
            switch (iArr[i]) {
                case 12321:
                    this.d = iArr[i + 1];
                    break;
                case 12322:
                    this.c = iArr[i + 1];
                    break;
                case 12323:
                    this.b = iArr[i + 1];
                    break;
                case 12324:
                    this.a = iArr[i + 1];
                    break;
                case 12325:
                    this.e = iArr[i + 1];
                    break;
                case 12326:
                    this.f = iArr[i + 1];
                    break;
                default:
                    break;
            }
        }
        int[] iArr2 = new int[1];
        egl10.eglChooseConfig(eGLDisplay, iArr, null, 0, iArr2);
        int i2 = iArr2[0];
        if (i2 <= 0) {
            return null;
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[i2];
        egl10.eglChooseConfig(eGLDisplay, iArr, eGLConfigArr, i2, iArr2);
        return a(egl10, eGLDisplay, eGLConfigArr, z, z2, z3);
    }

    public EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr, boolean z, boolean z2, boolean z3) {
        EGLConfig eGLConfig = null;
        EGLConfig eGLConfig2 = null;
        int i = -1;
        int i2 = -1;
        int i3 = MapCore.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER;
        int i4 = 0;
        int length = eGLConfigArr.length;
        int i5 = 0;
        while (i5 < length) {
            int i6;
            int i7;
            int i8;
            EGLConfig eGLConfig3;
            EGLConfig eGLConfig4;
            EGLConfig eGLConfig5 = eGLConfigArr[i5];
            int a = a(egl10, eGLDisplay, eGLConfig5, 12325, 0);
            int a2 = a(egl10, eGLDisplay, eGLConfig5, 12326, 0);
            this.i = a(egl10, eGLDisplay, eGLConfig5, 12512, 0) > 0;
            int i9 = z2 ? 1 : a(egl10, eGLDisplay, eGLConfig5, 12354, 0) > 0 ? 1 : 0;
            if (i9 == 1 || this.i) {
                if (a >= this.e) {
                    if (a2 < this.f) {
                        i9 = i4;
                        i6 = i3;
                        i7 = i2;
                        i8 = i;
                        eGLConfig3 = eGLConfig2;
                        eGLConfig4 = eGLConfig;
                    } else if (a != 0) {
                        if (a2 == 0) {
                            i9 = i4;
                            i6 = i3;
                            i7 = i2;
                            i8 = i;
                            eGLConfig3 = eGLConfig2;
                            eGLConfig4 = eGLConfig;
                        } else {
                            i9 = a(egl10, eGLDisplay, eGLConfig5, 12339, 0);
                            if (z3 && (i9 & 1) != 1) {
                                i9 = i4;
                                i6 = i3;
                                i7 = i2;
                                i8 = i;
                                eGLConfig3 = eGLConfig2;
                                eGLConfig4 = eGLConfig;
                            } else if ((a(egl10, eGLDisplay, eGLConfig5, 12352, -1) & 4) == 0) {
                                i9 = i4;
                                i6 = i3;
                                i7 = i2;
                                i8 = i;
                                eGLConfig3 = eGLConfig2;
                                eGLConfig4 = eGLConfig;
                            } else {
                                int a3 = a(egl10, eGLDisplay, eGLConfig5, 12324, 0);
                                int a4 = a(egl10, eGLDisplay, eGLConfig5, 12323, 0);
                                int a5 = a(egl10, eGLDisplay, eGLConfig5, 12322, 0);
                                int a6 = a(egl10, eGLDisplay, eGLConfig5, 12321, 0);
                                int a7;
                                if (this.i) {
                                    a7 = a(egl10, eGLDisplay, eGLConfig5, 12512, 0);
                                    i9 = a(egl10, eGLDisplay, eGLConfig5, 12513, 0);
                                    i6 = a7;
                                } else {
                                    a7 = a(egl10, eGLDisplay, eGLConfig5, 12338, 0);
                                    i9 = a(egl10, eGLDisplay, eGLConfig5, 12337, 0);
                                    i6 = a7;
                                }
                                bj.e("GLConfigHelper", "s=", new Object[]{Integer.valueOf(i6), "ss=", Integer.valueOf(i9)});
                                if (!z && i6 > 0) {
                                    i9 = i4;
                                    i6 = i3;
                                    i7 = i2;
                                    i8 = i;
                                    eGLConfig3 = eGLConfig2;
                                    eGLConfig4 = eGLConfig;
                                } else if (this.j && a6 != this.d) {
                                    i9 = i4;
                                    i6 = i3;
                                    i7 = i2;
                                    i8 = i;
                                    eGLConfig3 = eGLConfig2;
                                    eGLConfig4 = eGLConfig;
                                } else if (a3 == this.a && a4 == this.b && a5 == this.c && a6 >= this.d && i3 >= a2 && a >= i4) {
                                    if (i6 < i || i9 <= i2 || i9 > k) {
                                        i9 = a;
                                        i6 = a2;
                                        i7 = i2;
                                        eGLConfig3 = eGLConfig2;
                                        eGLConfig4 = eGLConfig5;
                                        i8 = i;
                                    } else {
                                        i7 = i9;
                                        eGLConfig3 = eGLConfig5;
                                        eGLConfig4 = eGLConfig5;
                                        i8 = i6;
                                        i9 = a;
                                        i6 = a2;
                                    }
                                }
                            }
                        }
                    }
                }
                i9 = i4;
                i6 = i3;
                i7 = i2;
                i8 = i;
                eGLConfig3 = eGLConfig2;
                eGLConfig4 = eGLConfig;
            } else {
                i9 = i4;
                i6 = i3;
                i7 = i2;
                i8 = i;
                eGLConfig3 = eGLConfig2;
                eGLConfig4 = eGLConfig;
            }
            i5++;
            i3 = i6;
            i2 = i7;
            i = i8;
            eGLConfig2 = eGLConfig3;
            eGLConfig = eGLConfig4;
            i4 = i9;
        }
        return eGLConfig2 == null ? eGLConfig : eGLConfig2;
    }

    private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
        if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.t)) {
            return this.t[0];
        }
        return i2;
    }

    public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
        EGLConfig eGLConfig;
        a();
        if (n == null || l) {
            n = a(egl10, eGLDisplay, true, false, false);
        }
        if (m == null) {
            m = a(egl10, eGLDisplay, false, false, false);
        }
        if (p == null) {
            p = a(egl10, eGLDisplay, true, true, false);
        }
        if (o == null) {
            o = a(egl10, eGLDisplay, false, true, false);
        }
        if (h) {
            if (g) {
                eGLConfig = p;
            } else {
                eGLConfig = n;
            }
        } else if (g) {
            eGLConfig = o;
        } else {
            eGLConfig = m;
        }
        a(a(egl10, eGLDisplay, eGLConfig, this.i ? 12513 : 12337, 0));
        l = false;
        return eGLConfig;
    }

    static boolean a() {
        int i = 0;
        if (h != MapSettings.o()) {
            h = MapSettings.o();
            i = 1;
        }
        return i | l;
    }

    static void a(int i) {
        u = i;
    }

    static boolean b() {
        return u > 0;
    }

    static synchronized void c() {
        boolean z = true;
        synchronized (at.class) {
            if (!MapSettings.b) {
                MapSettings.b = true;
                h();
                if (a(v)) {
                    MapSettings.n();
                } else {
                    if (!MapSettings.m()) {
                        MapSettings.a(true);
                    }
                    if (e()) {
                        g();
                    }
                }
                if (!((VERSION.RELEASE.startsWith("4.") || VERSION.RELEASE.startsWith("L") || VERSION.RELEASE.startsWith("5.")) && a(w))) {
                    z = false;
                }
                g = z;
            }
        }
    }

    private static boolean a(String[] strArr) {
        boolean z = false;
        CharSequence glGetString = GLES20.glGetString(7937);
        for (String compile : strArr) {
            z = Pattern.compile(compile).matcher(glGetString).find();
            if (z) {
                break;
            }
        }
        return z;
    }

    private static boolean e() {
        return VERSION.SDK_INT < 14;
    }

    private static synchronized void f() {
        InputStream fileInputStream;
        Exception e;
        Throwable th;
        synchronized (at.class) {
            if (e() && !MapSettings.b) {
                File file = new File(MapSettings.a(), "anti-aliasing.ini");
                if (file.exists()) {
                    try {
                        fileInputStream = new FileInputStream(file);
                        try {
                            if (fileInputStream.read() == 1) {
                                if (!MapSettings.m()) {
                                    MapSettings.a(true);
                                }
                                MapSettings.b = true;
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        } catch (Exception e3) {
                            e = e3;
                            try {
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e22) {
                                        e22.printStackTrace();
                                    }
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        }
                    } catch (Exception e5) {
                        e = e5;
                        fileInputStream = null;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                }
            }
        }
    }

    private static void g() {
        Exception e;
        Throwable th;
        if (e()) {
            File file = new File(MapSettings.a(), "anti-aliasing.ini");
            OutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(1);
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                            if (!file.setReadOnly()) {
                                throw new IOException("Failed to set AA settings file read only");
                            }
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                                if (!file.setReadOnly()) {
                                    throw new IOException("Failed to set AA settings file read only");
                                }
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                                if (!file.setReadOnly()) {
                                    throw new IOException("Failed to set AA settings file read only");
                                }
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                fileOutputStream = null;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                    if (!file.setReadOnly()) {
                        throw new IOException("Failed to set AA settings file read only");
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                    if (file.setReadOnly()) {
                        throw new IOException("Failed to set AA settings file read only");
                    }
                }
                throw th;
            }
        }
    }

    private static void h() {
        int i = k;
        if (a(x)) {
            i = 4;
        }
        if (k != i) {
            k = i;
            l = true;
        }
    }
}
