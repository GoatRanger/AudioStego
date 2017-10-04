package com.google.a.g.a.a;

import android.support.v4.media.TransportMediator;
import com.facebook.internal.l;
import com.google.a.e;
import com.google.a.g.a.a;
import com.google.a.g.a.a.a.j;
import com.google.a.g.a.b;
import com.google.a.g.a.c;
import com.google.a.g.a.f;
import com.google.a.g.q;
import com.google.a.h;
import com.google.a.m;
import com.google.a.r;
import com.google.a.t;
import com.google.api.client.http.HttpStatusCodes;
import com.here.posclient.analytics.TrackerEvent;
import com.loopj.android.http.BuildConfig;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class d extends a {
    private static final int[] a = new int[]{7, 5, 4, 3, 1};
    private static final int[] b = new int[]{4, 20, 52, 104, HttpStatusCodes.STATUS_CODE_NO_CONTENT};
    private static final int[] e = new int[]{0, 348, 1388, 2948, 3988};
    private static final int[][] f = new int[][]{new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    private static final int[][] g = new int[][]{new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, 180, 118, 143, 7, 21, 63}, new int[]{189, 145, 13, 39, 117, 140, 209, 205}, new int[]{193, 157, 49, 147, 19, 57, 171, 91}, new int[]{62, 186, 136, 197, 169, 85, 44, TrackerEvent.PositioningOfflineCommonIndoor}, new int[]{185, TrackerEvent.PositioningOfflinePrivateIndoor, 188, 142, 4, 12, 36, 108}, new int[]{113, 128, 173, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, 123, 158, 52, 156}, new int[]{46, 138, 203, 187, 139, 206, 196, 166}, new int[]{76, 17, 51, 153, 37, TrackerEvent.PositioningOnlineOutdoor, 122, 155}, new int[]{43, 129, 176, 106, 107, FTPCodes.RESTART_MARKER, 119, BuildConfig.VERSION_CODE}, new int[]{16, 48, 144, 10, 30, 90, 59, 177}, new int[]{109, 116, 137, 200, 178, dji.pilot.usercenter.protocol.d.k, FTPCodes.DATA_CONNECTION_ALREADY_OPEN, 164}, new int[]{70, 210, 208, FTPCodes.SUPERFLOUS_COMMAND, 184, TransportMediator.KEYCODE_MEDIA_RECORD, 179, 115}, new int[]{134, 191, 151, 31, 93, 68, HttpStatusCodes.STATUS_CODE_NO_CONTENT, l.f}, new int[]{148, 22, 66, 198, 172, 94, 71, 2}, new int[]{6, 18, 54, 162, 64, 192, 154, 40}, new int[]{120, 149, 25, 75, 14, 42, TransportMediator.KEYCODE_MEDIA_PLAY, 167}, new int[]{79, 26, 78, 23, 69, 207, 199, 175}, new int[]{103, 98, 83, 38, 114, TrackerEvent.PositioningOfflineOutdoor, 182, 124}, new int[]{161, 61, 183, TransportMediator.KEYCODE_MEDIA_PAUSE, 170, 88, 53, 159}, new int[]{55, 165, 73, 8, 24, 72, 5, 15}, new int[]{45, 135, 194, 160, 58, 174, 100, 89}};
    private static final int h = 0;
    private static final int i = 1;
    private static final int j = 2;
    private static final int k = 3;
    private static final int l = 4;
    private static final int m = 5;
    private static final int[][] n = new int[][]{new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};
    private static final int o = 11;
    private final List<b> p = new ArrayList(11);
    private final List<c> q = new ArrayList();
    private final int[] r = new int[2];
    private boolean s = false;

    public r a(int i, com.google.a.c.a aVar, Map<e, ?> map) throws m, h {
        this.p.clear();
        this.s = false;
        try {
            return a(a(i, aVar));
        } catch (m e) {
            this.p.clear();
            this.s = true;
            return a(a(i, aVar));
        }
    }

    public void a() {
        this.p.clear();
        this.q.clear();
    }

    List<b> a(int i, com.google.a.c.a aVar) throws m {
        while (true) {
            try {
                this.p.add(a(aVar, this.p, i));
            } catch (m e) {
                if (this.p.isEmpty()) {
                    throw e;
                } else if (i()) {
                    return this.p;
                } else {
                    boolean z = !this.q.isEmpty();
                    a(i, false);
                    if (z) {
                        List<b> a = a(false);
                        if (a != null) {
                            return a;
                        }
                        a = a(true);
                        if (a != null) {
                            return a;
                        }
                    }
                    throw m.a();
                }
            }
        }
    }

    private List<b> a(boolean z) {
        List<b> list = null;
        if (this.q.size() > 25) {
            this.q.clear();
        } else {
            this.p.clear();
            if (z) {
                Collections.reverse(this.q);
            }
            try {
                list = a(new ArrayList(), 0);
            } catch (m e) {
            }
            if (z) {
                Collections.reverse(this.q);
            }
        }
        return list;
    }

    private List<b> a(List<c> list, int i) throws m {
        while (i < this.q.size()) {
            c cVar = (c) this.q.get(i);
            this.p.clear();
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.p.addAll(((c) list.get(i2)).a());
            }
            this.p.addAll(cVar.a());
            if (b(this.p)) {
                if (i()) {
                    return this.p;
                }
                List arrayList = new ArrayList();
                arrayList.addAll(list);
                arrayList.add(cVar);
                try {
                    return a(arrayList, i + 1);
                } catch (m e) {
                }
            }
            i++;
        }
        throw m.a();
    }

    private static boolean b(List<b> list) {
        for (int[] iArr : n) {
            if (list.size() <= iArr.length) {
                boolean z;
                for (int i = 0; i < list.size(); i++) {
                    if (((b) list.get(i)).d().a() != iArr[i]) {
                        z = false;
                        break;
                    }
                }
                z = true;
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    private void a(int i, boolean z) {
        boolean z2 = false;
        boolean z3 = false;
        int i2 = 0;
        while (i2 < this.q.size()) {
            c cVar = (c) this.q.get(i2);
            if (cVar.b() > i) {
                z2 = cVar.a(this.p);
                break;
            }
            i2++;
            z3 = cVar.a(this.p);
        }
        if (!z2 && !r1 && !a(this.p, this.q)) {
            this.q.add(i2, new c(this.p, i, z));
            a(this.p, this.q);
        }
    }

    private static void a(List<b> list, List<c> list2) {
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            if (cVar.a().size() != list.size()) {
                Object obj;
                for (b bVar : cVar.a()) {
                    for (b equals : list) {
                        if (bVar.equals(equals)) {
                            obj = 1;
                            continue;
                            break;
                        }
                    }
                    obj = null;
                    continue;
                    if (obj == null) {
                        obj = null;
                        break;
                    }
                }
                int i = 1;
                if (obj != null) {
                    it.remove();
                }
            }
        }
    }

    private static boolean a(Iterable<b> iterable, Iterable<c> iterable2) {
        for (c cVar : iterable2) {
            for (b bVar : iterable) {
                Object obj;
                for (b equals : cVar.a()) {
                    if (bVar.equals(equals)) {
                        obj = 1;
                        continue;
                        break;
                    }
                }
                obj = null;
                continue;
                if (obj == null) {
                    Object obj2 = null;
                    continue;
                    break;
                }
            }
            int i = 1;
            continue;
            if (obj2 != null) {
                return true;
            }
        }
        return false;
    }

    List<c> h() {
        return this.q;
    }

    static r a(List<b> list) throws m, h {
        String a = j.a(a.a(list)).a();
        t[] c = ((b) list.get(0)).d().c();
        t[] c2 = ((b) list.get(list.size() - 1)).d().c();
        return new r(a, null, new t[]{c[0], c[1], c2[0], c2[1]}, com.google.a.a.RSS_EXPANDED);
    }

    private boolean i() {
        boolean z = true;
        b bVar = (b) this.p.get(0);
        b b = bVar.b();
        b c = bVar.c();
        if (c == null) {
            return false;
        }
        int i = 2;
        int b2 = c.b();
        for (int i2 = 1; i2 < this.p.size(); i2++) {
            bVar = (b) this.p.get(i2);
            b2 += bVar.b().b();
            i++;
            c = bVar.c();
            if (c != null) {
                b2 += c.b();
                i++;
            }
        }
        if ((b2 % 211) + ((i - 4) * 211) != b.a()) {
            z = false;
        }
        return z;
    }

    private static int a(com.google.a.c.a aVar, int i) {
        if (aVar.a(i)) {
            return aVar.d(aVar.e(i));
        }
        return aVar.e(aVar.d(i));
    }

    b a(com.google.a.c.a aVar, List<b> list, int i) throws m {
        boolean z;
        c a;
        boolean z2 = list.size() % 2 == 0;
        if (this.s) {
            if (z2) {
                z2 = false;
            } else {
                z2 = true;
            }
            z = z2;
        } else {
            z = z2;
        }
        int i2 = -1;
        boolean z3 = true;
        do {
            b(aVar, list, i2);
            a = a(aVar, i, z);
            if (a == null) {
                i2 = a(aVar, this.r[0]);
                continue;
            } else {
                z3 = false;
                continue;
            }
        } while (z3);
        b a2 = a(aVar, a, z, true);
        if (list.isEmpty() || !((b) list.get(list.size() - 1)).e()) {
            b a3;
            try {
                a3 = a(aVar, a, z, false);
            } catch (m e) {
                a3 = null;
            }
            return new b(a2, a3, a, true);
        }
        throw m.a();
    }

    private void b(com.google.a.c.a aVar, List<b> list, int i) throws m {
        int[] b = b();
        b[0] = 0;
        b[1] = 0;
        b[2] = 0;
        b[3] = 0;
        int a = aVar.a();
        if (i < 0) {
            if (list.isEmpty()) {
                i = 0;
            } else {
                i = ((b) list.get(list.size() - 1)).d().b()[1];
            }
        }
        Object obj = list.size() % 2 != 0 ? 1 : null;
        if (this.s) {
            obj = obj == null ? 1 : null;
        }
        int i2 = 0;
        int i3 = i;
        while (i3 < a) {
            i2 = !aVar.a(i3) ? 1 : 0;
            if (i2 == 0) {
                break;
            }
            i3++;
        }
        int i4 = i3;
        i3 = 0;
        int i5 = i2;
        i2 = i4;
        for (int i6 = i3; i6 < a; i6++) {
            if ((aVar.a(i6) ^ i5) != 0) {
                b[i3] = b[i3] + 1;
            } else {
                if (i3 == 3) {
                    if (obj != null) {
                        c(b);
                    }
                    if (a.b(b)) {
                        this.r[0] = i2;
                        this.r[1] = i6;
                        return;
                    }
                    if (obj != null) {
                        c(b);
                    }
                    i2 += b[0] + b[1];
                    b[0] = b[2];
                    b[1] = b[3];
                    b[2] = 0;
                    b[3] = 0;
                    i3--;
                } else {
                    i3++;
                }
                b[i3] = 1;
                i5 = i5 == 0 ? 1 : 0;
            }
        }
        throw m.a();
    }

    private static void c(int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length / 2; i++) {
            int i2 = iArr[i];
            iArr[i] = iArr[(length - i) - 1];
            iArr[(length - i) - 1] = i2;
        }
    }

    private c a(com.google.a.c.a aVar, int i, boolean z) {
        int i2;
        int i3;
        int i4;
        if (z) {
            i2 = this.r[0] - 1;
            while (i2 >= 0 && !aVar.a(i2)) {
                i2--;
            }
            i3 = i2 + 1;
            i2 = this.r[0] - i3;
            i4 = this.r[1];
        } else {
            i3 = this.r[0];
            i4 = aVar.e(this.r[1] + 1);
            i2 = i4 - this.r[1];
        }
        Object b = b();
        System.arraycopy(b, 0, b, 1, b.length - 1);
        b[0] = i2;
        try {
            return new c(a.a((int[]) b, f), new int[]{i3, i4}, i3, i4, i);
        } catch (m e) {
            return null;
        }
    }

    b a(com.google.a.c.a aVar, c cVar, boolean z, boolean z2) throws m {
        int i;
        int length;
        int[] c = c();
        c[0] = 0;
        c[1] = 0;
        c[2] = 0;
        c[3] = 0;
        c[4] = 0;
        c[5] = 0;
        c[6] = 0;
        c[7] = 0;
        if (z2) {
            q.b(aVar, cVar.b()[0], c);
        } else {
            q.a(aVar, cVar.b()[1], c);
            i = 0;
            for (length = c.length - 1; i < length; length--) {
                int i2 = c[i];
                c[i] = c[length];
                c[length] = i2;
                i++;
            }
        }
        float a = ((float) a.a(c)) / ((float) 17);
        float f = ((float) (cVar.b()[1] - cVar.b()[0])) / DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMaxVelocity;
        if (Math.abs(a - f) / f > 0.3f) {
            throw m.a();
        }
        int length2;
        int[] f2 = f();
        int[] g = g();
        float[] d = d();
        float[] e = e();
        for (length = 0; length < c.length; length++) {
            float f3 = (1.0f * ((float) c[length])) / a;
            i = (int) (dji.pilot.visual.a.d.c + f3);
            if (i < 1) {
                if (f3 < 0.3f) {
                    throw m.a();
                }
                i = 1;
            } else if (i > 8) {
                if (f3 > 8.7f) {
                    throw m.a();
                }
                i = 8;
            }
            int i3 = length >> 1;
            if ((length & 1) == 0) {
                f2[i3] = i;
                d[i3] = f3 - ((float) i);
            } else {
                g[i3] = i;
                e[i3] = f3 - ((float) i);
            }
        }
        a(17);
        i = (cVar.a() * 4) + (z ? 0 : 2);
        if (z2) {
            length = 0;
        } else {
            length = 1;
        }
        int i4 = (length + i) - 1;
        i2 = 0;
        i = f2.length - 1;
        length = 0;
        while (i >= 0) {
            if (a(cVar, z, z2)) {
                length += g[i4][i * 2] * f2[i];
            }
            i--;
            i2 = f2[i] + i2;
        }
        i = 0;
        for (length2 = g.length - 1; length2 >= 0; length2--) {
            if (a(cVar, z, z2)) {
                i += g[i4][(length2 * 2) + 1] * g[length2];
            }
        }
        length += i;
        if ((i2 & 1) != 0 || i2 > 13 || i2 < 4) {
            throw m.a();
        }
        i = (13 - i2) / 2;
        length2 = a[i];
        i2 = 9 - length2;
        return new b(e[i] + ((f.a(f2, length2, true) * b[i]) + f.a(g, i2, false)), length);
    }

    private static boolean a(c cVar, boolean z, boolean z2) {
        return (cVar.a() == 0 && z && z2) ? false : true;
    }

    private void a(int i) throws m {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5 = null;
        Object obj6 = 1;
        int a = a.a(f());
        int a2 = a.a(g());
        int i2 = (a + a2) - i;
        Object obj7 = (a & 1) == 1 ? 1 : null;
        if ((a2 & 1) == 0) {
            obj = 1;
        } else {
            obj = null;
        }
        if (a > 13) {
            obj2 = 1;
            obj3 = null;
        } else if (a < 4) {
            obj2 = null;
            int i3 = 1;
        } else {
            obj2 = null;
            obj3 = null;
        }
        if (a2 > 13) {
            obj4 = null;
            obj5 = 1;
        } else if (a2 < 4) {
            int i4 = 1;
        } else {
            obj4 = null;
        }
        int i5;
        if (i2 == 1) {
            if (obj7 != null) {
                if (obj != null) {
                    throw m.a();
                }
                obj2 = obj3;
                obj6 = obj4;
                obj4 = 1;
            } else if (obj == null) {
                throw m.a();
            } else {
                i5 = 1;
                obj6 = obj4;
                obj4 = obj2;
                obj2 = obj3;
            }
        } else if (i2 == -1) {
            if (obj7 != null) {
                if (obj != null) {
                    throw m.a();
                }
                r12 = obj4;
                obj4 = obj2;
                r3 = 1;
                obj6 = r12;
            } else if (obj == null) {
                throw m.a();
            } else {
                obj4 = obj2;
                obj2 = obj3;
            }
        } else if (i2 != 0) {
            throw m.a();
        } else if (obj7 != null) {
            if (obj == null) {
                throw m.a();
            } else if (a < a2) {
                i5 = 1;
                r12 = obj4;
                obj4 = obj2;
                r3 = 1;
                obj6 = r12;
            } else {
                i4 = 1;
                obj2 = obj3;
            }
        } else if (obj != null) {
            throw m.a();
        } else {
            obj6 = obj4;
            obj4 = obj2;
            obj2 = obj3;
        }
        if (obj2 != null) {
            if (obj4 != null) {
                throw m.a();
            }
            a.a(f(), d());
        }
        if (obj4 != null) {
            a.b(f(), d());
        }
        if (obj6 != null) {
            if (obj5 != null) {
                throw m.a();
            }
            a.a(g(), d());
        }
        if (obj5 != null) {
            a.b(g(), e());
        }
    }
}
