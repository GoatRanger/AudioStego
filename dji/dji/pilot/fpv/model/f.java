package dji.pilot.fpv.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.support.v4.media.TransportMediator;
import android.util.Log;
import com.here.posclient.analytics.TrackerEvent;
import dji.log.DJILogHelper;
import dji.midware.util.c;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.usercenter.f.e;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.xeustechnologies.jtar.TarHeader;

public class f {
    public static final float a = 80.0f;
    private static final Charset ai = Charset.forName("UTF-8");
    private static final String aj = "Map Loading";
    private static final String ak = "_djipilot";
    private static final int al = 4;
    private static final int am = 20;
    private static final int an = 16;
    public static final float b = 60.0f;
    public static final float c = 8888.0f;
    public static final float d = -400.0f;
    protected static final int e = 500;
    protected static final int f = 400;
    protected static final int g = 9;
    protected static final int h = 12345;
    public static final int i = 100;
    public static final int j = 12;
    public byte A = (byte) 1;
    public int B;
    public long C;
    public double D;
    public double E;
    public float F;
    public int G;
    public float H;
    public float I;
    public float J;
    public int K;
    public long L;
    public double[] M = new double[4];
    public double[] N = new double[4];
    public long O;
    public String P = "";
    public int Q;
    public String R = "";
    public long S;
    public String T = "";
    public String U = "";
    public String V = "";
    public int W = 2;
    public String X = "";
    public float Y = -1.0f;
    public int Z = -1;
    public int aa = -1;
    public int ab;
    public int ac = 1;
    public int ad;
    protected boolean ae = false;
    public boolean af = false;
    public byte[] ag;
    public byte[] ah;
    private int ao = h;
    private long ap;
    private int[] aq = new int[4];
    private int[] ar = new int[4];
    private String as;
    private String at;
    private ArrayList<Bitmap> au = new ArrayList(4);
    private ArrayList<Bitmap> av = new ArrayList(4);
    private byte[] aw = new byte[10];
    private boolean ax = false;
    private String ay;
    private String az;
    public long k = 0;
    public short l = (short) 400;
    public short m = (short) 9;
    public int n = 0;
    public long o = 0;
    public byte[] p = new byte[80];
    public String q = null;
    public String r = null;
    public String s = null;
    public String t = "Map Loading";
    public String u = "Map Loading";
    public String v = "Map Loading";
    public String w = "Map Loading";
    public byte x = (byte) 2;
    protected int y = 1;
    protected int z = 0;

    public enum a {
        isFavourite,
        isNew,
        updatetime,
        totalDistance,
        totalTime,
        maxHeight,
        photoNum,
        videoTime
    }

    public synchronized void a(Bitmap bitmap) {
        Object obj;
        if (this.au.size() == 4) {
            this.au.remove(0);
            this.av.remove(0);
        }
        this.au.add(bitmap);
        if (bitmap == null) {
            obj = null;
        } else {
            obj = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 5, bitmap.getHeight() / 5, false);
        }
        this.av.add(obj);
    }

    public void a(int i) {
        this.y = (byte) i;
        if (this.y == 0) {
            this.af = false;
        }
    }

    public int a() {
        return this.y;
    }

    public void a(boolean z) {
        this.z = z ? 1 : 0;
    }

    public boolean b() {
        return this.z == 1;
    }

    protected int c() {
        int i = 0;
        int i2 = 0;
        while (i < this.aq.length) {
            i2 += this.aq[i];
            i++;
        }
        return i2;
    }

    protected int d() {
        int i = 0;
        int i2 = 0;
        while (i < this.ar.length) {
            i2 += this.ar[i];
            i++;
        }
        return i2;
    }

    protected void a(byte[] bArr) {
        int i = 0;
        this.av.clear();
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        options.inPurgeable = true;
        int i2 = 0;
        while (i < this.ar.length) {
            if (i > 0) {
                i2 += this.ar[i - 1];
            }
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, i2, this.ar[i], options);
            if (decodeByteArray != null) {
                this.av.add(decodeByteArray);
            }
            i++;
        }
    }

    protected void b(byte[] bArr) {
        int i = 0;
        this.au.clear();
        int i2 = 0;
        while (i < this.aq.length) {
            if (i > 0) {
                i2 += this.aq[i - 1];
            }
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, i2, this.aq[i]);
            if (decodeByteArray != null) {
                this.au.add(decodeByteArray);
            }
            i++;
        }
    }

    private byte[] b(Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 30, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public List<Bitmap> e() {
        return this.av;
    }

    public List<Bitmap> a(Context context) {
        if (this.au.size() <= 0) {
            i.d(context, this);
        }
        return this.au;
    }

    public void f() {
        Iterator it = this.au.iterator();
        while (it.hasNext()) {
            Bitmap bitmap = (Bitmap) it.next();
            if (!(bitmap == null || bitmap.isRecycled())) {
                bitmap.recycle();
            }
        }
        this.au.clear();
    }

    protected byte[] g() {
        int i;
        int size = this.av.size();
        byte[][] bArr = new byte[size][];
        for (i = 0; i < size; i++) {
            bArr[i] = b((Bitmap) this.av.get(i));
            this.ar[i] = bArr[i].length;
        }
        Object obj = new byte[d()];
        int i2 = 0;
        for (i = 0; i < size; i++) {
            if (i > 0) {
                i2 += this.ar[i - 1];
            }
            System.arraycopy(bArr[i], 0, obj, i2, this.ar[i]);
        }
        return obj;
    }

    protected byte[] h() {
        int i;
        int size = this.au.size();
        byte[][] bArr = new byte[size][];
        for (i = 0; i < size; i++) {
            bArr[i] = b((Bitmap) this.au.get(i));
            this.aq[i] = bArr[i].length;
        }
        Object obj = new byte[c()];
        int i2 = 0;
        for (i = 0; i < size; i++) {
            if (i > 0) {
                i2 += this.aq[i - 1];
            }
            System.arraycopy(bArr[i], 0, obj, i2, this.aq[i]);
        }
        return obj;
    }

    private static String a(byte[] bArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            if (bArr[i3] == (byte) 0) {
                i2 = i3 - i;
                break;
            }
        }
        return new String(bArr, i, i2, ai);
    }

    protected boolean a(byte[] bArr, boolean z) {
        boolean z2 = false;
        int length = this.l > bArr.length ? bArr.length : this.l;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        this.s = e.a(bArr2, "");
        this.ac = bArr[this.l - 1];
        if (this.m <= (short) 3) {
            this.t = a(bArr, 0, 20).trim();
            this.u = a(bArr, 20, 20).trim();
            this.v = a(bArr, 40, 20).trim();
            this.w = a(bArr, 60, 20).trim();
            length = 80;
        } else {
            this.t = "";
            this.u = "";
            this.v = a(bArr, 0, 40).trim();
            this.w = a(bArr, 40, 40).trim();
            length = 80;
        }
        this.x = bArr[length];
        this.A = bArr[81];
        DJILogHelper.getInstance().LOGD("", "buffer[index]=" + bArr[82], false, true);
        this.y = bArr[82] & 1;
        this.z = (bArr[82] >> 2) & 1;
        DJILogHelper.getInstance().LOGD("", "needUpload:" + this.y, false, true);
        DJILogHelper.getInstance().LOGD("", "infoExist:" + this.z, false, true);
        if (this.af) {
            this.y = this.y == 0 ? 2 : this.y;
        }
        this.B = c.b(bArr, 83);
        this.ao = c.b(bArr, 87);
        if (h != this.ao) {
            return false;
        }
        this.C = c.c(bArr, 91);
        this.ap = this.C;
        this.D = c.e(bArr, 99);
        this.E = c.e(bArr, 107);
        this.F = c.d(bArr, 115);
        this.G = c.b(bArr, 119);
        this.H = c.d(bArr, 123);
        this.I = c.d(bArr, TransportMediator.KEYCODE_MEDIA_PAUSE);
        this.J = c.d(bArr, TrackerEvent.PositioningOfflineOutdoor);
        this.K = c.b(bArr, 135);
        this.ab = 139;
        this.O = c.c(bArr, 243);
        if (this.C >= 1390000000000L || this.O == 0) {
            this.ae = false;
        } else {
            this.ae = true;
        }
        if (this.ac == 0 && this.m <= (short) 2 && this.O == 0) {
            this.L = (long) c.b(bArr, 139);
            length = 143;
        } else {
            this.L = c.c(bArr, 139);
            length = 147;
        }
        int i = length;
        for (length = 0; length < this.aq.length; length++) {
            this.aq[length] = c.b(bArr, i);
            i += 4;
        }
        for (length = 0; length < this.ar.length; length++) {
            this.ar[length] = c.b(bArr, i);
            i += 4;
        }
        for (length = 0; length < this.M.length; length++) {
            this.M[length] = c.e(bArr, i);
            i += 8;
        }
        for (length = 0; length < this.N.length; length++) {
            this.N[length] = (double) c.b(bArr, i);
            i += 8;
        }
        this.O = c.c(bArr, i);
        length = i + 8;
        if (this.m <= (short) 5) {
            this.ag = new byte[16];
            System.arraycopy(bArr, length, this.ag, 0, 16);
            length += 16;
            if (this.m > (short) 1) {
                System.arraycopy(bArr, length, this.aw, 0, 10);
                if (!d(this.aw)) {
                    this.ad = length;
                }
                this.P = new String(bArr, length, 10, ai).trim();
                length += 10;
                this.Q = bArr[length];
                length++;
                this.R = new String(bArr, length, 32, ai).trim();
                length += 32;
                this.S = c.c(bArr, length);
                length += 8;
            }
            if (this.m > (short) 2) {
                this.T = new String(bArr, length, 10, ai).trim();
                length += 10;
                this.U = new String(bArr, length, 10, ai).trim();
                length += 10;
                this.V = new String(bArr, length, 10, ai).trim();
                length += 10;
                this.W = bArr[length];
                length++;
                this.X = bArr[length] + ".";
                length++;
                this.X += bArr[length] + ".";
                length++;
                this.X += bArr[length] + "";
                length++;
            }
            if (this.m > (short) 3) {
                this.Y = c.d(bArr, length);
                length += 4;
                this.Z = bArr[length] & 1;
                this.aa = (bArr[length] >> 1) & 3;
                DJILogHelper.getInstance().LOGD("", "takeOffAltitude: " + this.Y + " isShared:" + this.Z + " displayMoment:" + this.aa, false, true);
            }
            if (this.ae) {
                this.C *= 1000;
                this.ap = this.C;
                this.G *= 100;
                this.S *= 1000;
                this.L = (long) (((double) this.L) * 0.1d);
                this.H = ((float) c.b(c.a(this.H))) * 0.1f;
            }
            if (this.ax && dji.pilot.usercenter.b.f.getInstance().l().equals("12345678@dji.com")) {
                return true;
            }
            if (dji.pilot.usercenter.b.f.getInstance().c()) {
                this.at = dji.pilot.usercenter.b.f.getInstance().l();
                byte[] b = c.b(com.dji.frame.c.a.b(this.at + ak));
                bArr2 = c.b(com.dji.frame.c.a.b(this.at.toLowerCase(Locale.US) + ak));
                this.at = dji.pilot.usercenter.b.f.getInstance().l().trim();
                byte[] b2 = c.b(com.dji.frame.c.a.b(this.at + ak));
                if (dji.pilot.c.a.w || z) {
                    return true;
                }
                if (Arrays.equals(bArr2, this.ag) || Arrays.equals(b, this.ag) || Arrays.equals(b2, this.ag) || e(this.ag)) {
                    z2 = true;
                }
                return z2;
            }
        }
        this.ah = new byte[16];
        System.arraycopy(bArr, length, this.ah, 0, 16);
        length += 16;
        this.Y = c.d(bArr, length);
        length += 4;
        this.Q = bArr[length];
        length++;
        this.S = c.c(bArr, length);
        length += 8;
        this.R = new String(bArr, length, 32, ai).trim();
        length += 32;
        this.P = new String(bArr, length, 16, ai).trim();
        length += 16;
        this.T = new String(bArr, length, 16, ai).trim();
        length += 16;
        this.U = new String(bArr, length, 16, ai).trim();
        length += 16;
        this.V = new String(bArr, length, 16, ai).trim();
        length += 16;
        this.W = bArr[length];
        length++;
        this.X = bArr[length] + ".";
        length++;
        this.X += bArr[length] + ".";
        length++;
        this.X += bArr[length] + "";
        length++;
        this.Z = bArr[length] & 1;
        this.aa = (bArr[length] >> 1) & 3;
        length++;
        if (dji.pilot.usercenter.b.f.getInstance().c()) {
            this.ay = dji.pilot.usercenter.b.f.getInstance().o();
            this.az = com.dji.frame.c.a.b(this.ay + ak);
            b = c.b(this.az);
            if (dji.pilot.c.a.w || z) {
                return true;
            }
            if (e(this.ah) || Arrays.equals(b, this.ah)) {
                z2 = true;
            }
            return z2;
        }
        return false;
    }

    private boolean e(byte[] bArr) {
        for (byte b : bArr) {
            if (b != (byte) 0) {
                return false;
            }
        }
        return true;
    }

    public byte[] i() {
        Object bytes;
        Object bytes2;
        int i;
        Object obj = new byte[this.l];
        if (this.m <= (short) 3) {
            bytes = this.t.getBytes(ai);
            System.arraycopy(bytes, 0, obj, 0, bytes.length);
            bytes2 = this.u.getBytes(ai);
            System.arraycopy(bytes2, 0, obj, 20, bytes2.length);
            bytes2 = this.v.getBytes(ai);
            System.arraycopy(bytes2, 0, obj, 40, bytes2.length);
            bytes2 = this.w.getBytes(ai);
            System.arraycopy(bytes2, 0, obj, 60, bytes2.length);
            i = 80;
        } else {
            bytes = this.v.getBytes(ai);
            System.arraycopy(bytes, 0, obj, 0, bytes.length);
            bytes2 = this.w.getBytes(ai);
            System.arraycopy(bytes2, 0, obj, 40, bytes2.length);
            i = 80;
        }
        obj[i] = this.x;
        obj[81] = this.A;
        obj[82] = c.c(this.y + (this.z << 2));
        System.arraycopy(c.a(this.B), 0, obj, 83, 4);
        System.arraycopy(c.a((int) h), 0, obj, 87, 4);
        if (this.ae) {
            System.arraycopy(c.a((this.ap == 0 ? this.C : this.ap) / 1000), 0, obj, 91, 8);
            System.arraycopy(c.a(this.D), 0, obj, 99, 8);
            System.arraycopy(c.a(this.E), 0, obj, 107, 8);
            System.arraycopy(c.a(this.F), 0, obj, 115, 4);
            System.arraycopy(c.a(this.G / 100), 0, obj, 119, 4);
            System.arraycopy(c.a((int) (this.H * 10.0f)), 0, obj, 123, 4);
            System.arraycopy(c.a(this.I), 0, obj, TransportMediator.KEYCODE_MEDIA_PAUSE, 4);
            System.arraycopy(c.a(this.J), 0, obj, TrackerEvent.PositioningOfflineOutdoor, 4);
            System.arraycopy(c.a(this.K), 0, obj, 135, 4);
            System.arraycopy(c.a(this.L * 10), 0, obj, 139, 8);
            i = 147;
        } else {
            System.arraycopy(c.a(this.ap == 0 ? this.C : this.ap), 0, obj, 91, 8);
            System.arraycopy(c.a(this.D), 0, obj, 99, 8);
            System.arraycopy(c.a(this.E), 0, obj, 107, 8);
            System.arraycopy(c.a(this.F), 0, obj, 115, 4);
            System.arraycopy(c.a(this.G), 0, obj, 119, 4);
            System.arraycopy(c.a(this.H), 0, obj, 123, 4);
            System.arraycopy(c.a(this.I), 0, obj, TransportMediator.KEYCODE_MEDIA_PAUSE, 4);
            System.arraycopy(c.a(this.J), 0, obj, TrackerEvent.PositioningOfflineOutdoor, 4);
            System.arraycopy(c.a(this.K), 0, obj, 135, 4);
            System.arraycopy(c.a(this.L), 0, obj, 139, 8);
            i = 147;
        }
        int i2 = i;
        for (int a : this.aq) {
            System.arraycopy(c.a(a), 0, obj, i2, 4);
            i2 += 4;
        }
        for (int a2 : this.ar) {
            System.arraycopy(c.a(a2), 0, obj, i2, 4);
            i2 += 4;
        }
        for (double a3 : this.M) {
            System.arraycopy(c.a(a3), 0, obj, i2, 8);
            i2 += 8;
        }
        for (double a32 : this.N) {
            System.arraycopy(c.a(a32), 0, obj, i2, 8);
            i2 += 8;
        }
        System.arraycopy(c.a(this.O), 0, obj, i2, 8);
        i2 += 8;
        String[] split;
        if (this.m <= (short) 5) {
            if (dji.pilot.usercenter.b.f.getInstance().c()) {
                this.at = dji.pilot.usercenter.b.f.getInstance().l().trim();
                this.as = com.dji.frame.c.a.b(this.at.toLowerCase(Locale.US) + ak);
                bytes = c.b(this.as);
            } else {
                bytes = new byte[16];
            }
            System.arraycopy(bytes, 0, obj, i2, 16);
            i = i2 + 16;
            if (this.m > (short) 1) {
                bytes2 = f(c.a(this.P));
                System.arraycopy(bytes2, 0, obj, i, bytes2.length);
                i += 10;
                obj[i] = (byte) this.Q;
                i++;
                bytes2 = this.R.trim().getBytes(ai);
                System.arraycopy(bytes2, 0, obj, i, bytes2.length);
                i += 32;
                if (this.ae) {
                    System.arraycopy(c.a(this.S / 1000), 0, obj, i, 8);
                    i += 8;
                } else {
                    System.arraycopy(c.a(this.S), 0, obj, i, 8);
                    i += 8;
                }
            }
            if (this.m > (short) 2) {
                bytes2 = f(c.a(this.T));
                System.arraycopy(bytes2, 0, obj, i, bytes2.length);
                i += 10;
                bytes2 = f(c.a(this.U));
                System.arraycopy(bytes2, 0, obj, i, bytes2.length);
                i += 10;
                bytes2 = f(c.a(this.V));
                System.arraycopy(bytes2, 0, obj, i, bytes2.length);
                i += 10;
                obj[i] = (byte) this.W;
                i2 = i + 1;
                if (this.X.equals("")) {
                    split = DJIApplication.e.split("\\.");
                } else {
                    split = this.X.split("\\.");
                }
                obj[i2] = (byte) Integer.parseInt(split[0]);
                i2++;
                obj[i2] = (byte) Integer.parseInt(split[1]);
                i2++;
                obj[i2] = (byte) Integer.parseInt(split[2]);
                i = i2 + 1;
            }
            if (this.m > (short) 3) {
                bytes2 = c.a(this.Y);
                System.arraycopy(bytes2, 0, obj, i, bytes2.length);
                i += 4;
                obj[i] = (byte) ((this.aa << 1) + this.Z);
                i++;
            }
            obj[this.l - 1] = (byte) this.ac;
        } else {
            if (dji.pilot.usercenter.b.f.getInstance().c()) {
                this.ay = dji.pilot.usercenter.b.f.getInstance().o();
                this.az = com.dji.frame.c.a.b(this.ay + ak);
                bytes = c.b(this.az);
            } else {
                bytes = new byte[16];
            }
            System.arraycopy(bytes, 0, obj, i2, 16);
            i = i2 + 16;
            bytes2 = c.a(this.Y);
            System.arraycopy(bytes2, 0, obj, i, bytes2.length);
            i += 4;
            obj[i] = (byte) this.Q;
            i++;
            System.arraycopy(c.a(this.S), 0, obj, i, 8);
            i += 8;
            bytes2 = this.R.trim().getBytes(ai);
            System.arraycopy(bytes2, 0, obj, i, bytes2.length);
            i += 32;
            bytes2 = f(c.a(this.P));
            System.arraycopy(bytes2, 0, obj, i, bytes2.length);
            i += 16;
            bytes2 = f(c.a(this.T));
            System.arraycopy(bytes2, 0, obj, i, bytes2.length);
            i += 16;
            bytes2 = f(c.a(this.U));
            System.arraycopy(bytes2, 0, obj, i, bytes2.length);
            i += 16;
            bytes2 = f(c.a(this.V));
            System.arraycopy(bytes2, 0, obj, i, bytes2.length);
            i += 16;
            obj[i] = (byte) this.W;
            i2 = i + 1;
            split = this.X.equals("") ? DJIApplication.e.split("\\.") : this.X.split("\\.");
            obj[i2] = (byte) Integer.parseInt(split[0]);
            i2++;
            obj[i2] = (byte) Integer.parseInt(split[1]);
            i2++;
            obj[i2] = (byte) Integer.parseInt(split[2]);
            i = i2 + 1;
            obj[i] = (byte) ((this.aa << 1) + this.Z);
            i++;
        }
        return obj;
    }

    public byte[] j() {
        byte[] bArr = new byte[12];
        System.arraycopy(c.a(this.k), 0, bArr, 0, 8);
        System.arraycopy(c.b(this.l), 0, bArr, 8, 2);
        if (this.m < (short) 5) {
            System.arraycopy(c.b(this.m), 0, bArr, 10, 2);
        } else {
            bArr[10] = (byte) this.m;
            bArr[11] = (byte) this.n;
        }
        Log.d("Flightrecord", "读取的版本号：" + this.m);
        if (this.m > (short) 6) {
            bArr = new byte[100];
            System.arraycopy(c.a(this.k), 0, bArr, 0, 8);
            System.arraycopy(c.b(this.l), 0, bArr, 8, 2);
            if (this.m < (short) 5) {
                System.arraycopy(c.b(this.m), 0, bArr, 10, 2);
            } else {
                bArr[10] = (byte) this.m;
                bArr[11] = (byte) this.n;
            }
            System.arraycopy(c.a(this.o), 0, bArr, 12, 8);
            System.arraycopy(this.p, 0, bArr, 20, 80);
        }
        return bArr;
    }

    public void c(byte[] bArr) {
        this.k = c.c(bArr, 0);
        this.l = c.a(bArr, 8);
        int i = 10;
        this.m = c.a(bArr, 10);
        if (this.m < (short) 0 || this.m >= (short) 5) {
            this.m = (short) bArr[10];
            this.n = bArr[11];
            i = 12;
        }
        if (this.m > (short) 6) {
            this.o = c.c(bArr, i);
            i += 8;
            System.arraycopy(bArr, i, this.p, 0, 80);
            i += 80;
        }
    }

    public static boolean a(String str) {
        return str.contains("Loading");
    }

    public byte[] k() {
        return c.a(this.L);
    }

    private byte[] f(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] < TarHeader.LF_NORMAL) {
                bArr[i] = TarHeader.LF_NORMAL;
            }
        }
        return bArr;
    }

    public boolean d(byte[] bArr) {
        for (byte b : bArr) {
            if (b < TarHeader.LF_NORMAL) {
                return false;
            }
        }
        return true;
    }

    public byte[] l() {
        return f(this.aw);
    }

    public static boolean a(float f) {
        return f < a;
    }

    public static boolean b(float f) {
        return f < 60.0f;
    }

    public static boolean c(float f) {
        return d <= f && f <= c;
    }
}
