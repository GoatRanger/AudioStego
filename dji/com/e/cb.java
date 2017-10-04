package com.e;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import com.facebook.internal.af;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.BitSet;
import java.util.List;

public class cb {
    private static int a(int i, int i2) {
        return i < i2 ? i : i2;
    }

    protected static ci a(Location location, bw bwVar, int i, byte b, long j, boolean z) throws UnsupportedEncodingException {
        ci ciVar = new ci();
        if (i < 1 || i > 3 || bwVar == null) {
            return null;
        }
        int i2;
        int i3;
        Object obj = (i == 1 || i == 3) ? 1 : null;
        Object obj2 = (i == 2 || i == 3) ? 1 : null;
        Object bytes = bwVar.m().getBytes("UTF-8");
        System.arraycopy(bytes, 0, ciVar.d, 0, a(bytes.length, ciVar.d.length));
        bytes = br.f().getBytes("UTF-8");
        System.arraycopy(bytes, 0, ciVar.h, 0, a(bytes.length, ciVar.h.length));
        bytes = bwVar.e().getBytes("UTF-8");
        System.arraycopy(bytes, 0, ciVar.b, 0, a(bytes.length, ciVar.b.length));
        bytes = bwVar.f().getBytes("UTF-8");
        System.arraycopy(bytes, 0, ciVar.c, 0, a(bytes.length, ciVar.c.length));
        ciVar.e = (short) bwVar.n();
        ciVar.f = (short) bwVar.o();
        ciVar.g = (byte) bwVar.p();
        bytes = bwVar.q().getBytes("UTF-8");
        System.arraycopy(bytes, 0, ciVar.i, 0, a(bytes.length, ciVar.i.length));
        long j2 = j / 1000;
        bytes = (location == null || !bwVar.d()) ? null : 1;
        cg cgVar;
        if (bytes != null) {
            cgVar = new cg();
            cgVar.b = (int) j2;
            ch chVar = new ch();
            chVar.a = (int) (location.getLongitude() * 1000000.0d);
            chVar.b = (int) (location.getLatitude() * 1000000.0d);
            chVar.c = (int) location.getAltitude();
            chVar.d = (int) location.getAccuracy();
            chVar.e = (int) location.getSpeed();
            chVar.f = (short) ((int) location.getBearing());
            if (Build.MODEL.equals(af.o) || (bw.a(bwVar.w()) && by.b)) {
                chVar.g = (byte) 1;
            } else {
                chVar.g = (byte) 0;
            }
            chVar.h = b;
            if (chVar.d > 25) {
                chVar.h = (byte) 5;
            }
            chVar.i = System.currentTimeMillis();
            chVar.j = bwVar.l();
            cgVar.c = chVar;
            i2 = 1;
            ciVar.k.add(cgVar);
        } else if (!z) {
            return null;
        } else {
            cgVar = new cg();
            cgVar.b = (int) j2;
            ck ckVar = new ck();
            ckVar.a = bwVar.u();
            for (i2 = 0; i2 < ckVar.a; i2++) {
                cl clVar = new cl();
                clVar.a = (byte) bwVar.b(i2).length();
                System.arraycopy(bwVar.b(i2).getBytes("UTF-8"), 0, clVar.b, 0, clVar.a);
                clVar.c = bwVar.c(i2);
                clVar.d = bwVar.d(i2);
                clVar.e = bwVar.e(i2);
                clVar.f = bwVar.f(i2);
                clVar.g = bwVar.g(i2);
                clVar.h = (byte) bwVar.h(i2).length();
                System.arraycopy(bwVar.h(i2).getBytes("UTF-8"), 0, clVar.i, 0, clVar.h);
                clVar.j = bwVar.i(i2);
                ckVar.b.add(clVar);
            }
            cgVar.g = ckVar;
            i2 = 1;
            ciVar.k.add(cgVar);
        }
        if (!(bwVar.g() || obj == null || z)) {
            cg cgVar2 = new cg();
            cgVar2.b = (int) j2;
            cf cfVar = new cf();
            List a = bwVar.a(location.getSpeed());
            if (a != null && a.size() >= 3) {
                cfVar.a = (short) ((Integer) a.get(0)).intValue();
                cfVar.b = ((Integer) a.get(1)).intValue();
            }
            cfVar.c = bwVar.i();
            List j3 = bwVar.j();
            if (j3 != null) {
                cfVar.d = (byte) j3.size();
                for (i3 = 0; i3 < j3.size(); i3++) {
                    bv bvVar = new bv();
                    bvVar.a = (short) ((ay) j3.get(i3)).c;
                    bvVar.b = ((ay) j3.get(i3)).d;
                    bvVar.c = (byte) ((ay) j3.get(i3)).j;
                    cfVar.e.add(bvVar);
                }
            }
            cgVar2.d = cfVar;
            i2 = 2;
            ciVar.k.add(cgVar2);
        }
        i3 = i2;
        if (!(!bwVar.g() || obj == null || z)) {
            cg cgVar3 = new cg();
            cgVar3.b = (int) j2;
            bu buVar = new bu();
            List b2 = bwVar.b(location.getSpeed());
            if (b2 != null && b2.size() >= 6) {
                buVar.a = ((Integer) b2.get(3)).intValue();
                buVar.b = ((Integer) b2.get(4)).intValue();
                buVar.c = (short) ((Integer) b2.get(0)).intValue();
                buVar.d = (short) ((Integer) b2.get(1)).intValue();
                buVar.e = ((Integer) b2.get(2)).intValue();
                buVar.f = bwVar.i();
            }
            cgVar3.e = buVar;
            i3++;
            ciVar.k.add(cgVar3);
        }
        if (!(!bwVar.c() || obj2 == null || z)) {
            cgVar2 = new cg();
            cm cmVar = new cm();
            List r = bwVar.r();
            cgVar2.b = (int) (((Long) r.get(0)).longValue() / 1000);
            cmVar.a = (byte) (r.size() - 1);
            for (int i4 = 1; i4 < r.size(); i4++) {
                List list = (List) r.get(i4);
                if (list != null && list.size() >= 3) {
                    cn cnVar = new cn();
                    obj2 = ((String) list.get(0)).getBytes("UTF-8");
                    System.arraycopy(obj2, 0, cnVar.a, 0, a(obj2.length, cnVar.a.length));
                    cnVar.b = (short) ((Integer) list.get(1)).intValue();
                    bytes = ((String) list.get(2)).getBytes("UTF-8");
                    System.arraycopy(bytes, 0, cnVar.c, 0, a(bytes.length, cnVar.c.length));
                    cmVar.b.add(cnVar);
                }
            }
            cgVar2.f = cmVar;
            i3++;
            ciVar.k.add(cgVar2);
        }
        ciVar.j = (short) i3;
        return (i3 >= 2 || z) ? ciVar : null;
    }

    protected static File a(Context context) {
        try {
            return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/files/"));
        } catch (Throwable th) {
            bc.a(th, "CollectorTools", "getExternalFilesDir");
            return null;
        }
    }

    protected static BitSet a(byte[] bArr) {
        BitSet bitSet;
        Throwable th;
        try {
            bitSet = new BitSet(bArr.length * 8);
            int i = 0;
            int i2 = 0;
            while (i < bArr.length) {
                try {
                    int i3 = i2;
                    i2 = 7;
                    while (i2 >= 0) {
                        int i4 = i3 + 1;
                        bitSet.set(i3, ((bArr[i] & (1 << i2)) >> i2) == 1);
                        i2--;
                        i3 = i4;
                    }
                    i++;
                    i2 = i3;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            bitSet = null;
            th = th4;
            bc.a(th, "CollectorTools", "byteArray2BitSet");
            return bitSet;
        }
        return bitSet;
    }

    protected static boolean a() {
        if (VERSION.SDK_INT >= 9) {
            try {
                return ((Boolean) Environment.class.getMethod("isExternalStorageRemovable", (Class[]) null).invoke(null, (Object[]) null)).booleanValue();
            } catch (Throwable th) {
                bc.a(th, "CollectorTools", "isExternalStorageRemovable");
            }
        }
        return true;
    }

    protected static byte[] a(BitSet bitSet) {
        byte[] bArr;
        Throwable th;
        try {
            bArr = new byte[(bitSet.size() / 8)];
            int i = 0;
            while (i < bitSet.size()) {
                try {
                    int i2 = i / 8;
                    bArr[i2] = (byte) (((bitSet.get(i) ? 1 : 0) << (7 - (i % 8))) | bArr[i2]);
                    i++;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            bArr = null;
            th = th4;
            bc.a(th, "CollectorTools", "bitSet2ByteArray");
            return bArr;
        }
        return bArr;
    }

    protected static long b() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
        } catch (Throwable th) {
            bc.a(th, "CollectorTools", "getSDCardFreeSpace");
            return 0;
        }
    }
}
