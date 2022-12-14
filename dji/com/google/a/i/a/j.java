package com.google.a.i.a;

import android.support.v4.media.TransportMediator;
import com.google.a.h;
import com.here.posclient.analytics.TrackerEvent;
import com.loopj.android.http.BuildConfig;
import dji.pilot.usercenter.protocol.d;
import it.sauronsoftware.ftp4j.FTPCodes;

public final class j {
    private static final int[] a = new int[]{31892, 34236, 39577, 42195, 48118, 51042, 55367, 58893, 63784, 68472, 70749, 76311, 79154, 84390, 87683, 92361, 96236, 102084, 102881, 110507, 110734, 117786, 119615, 126325, 127568, 133589, 136944, 141498, 145311, 150283, 152622, 158308, 161089, 167017};
    private static final j[] b = f();
    private final int c;
    private final int[] d;
    private final b[] e;
    private final int f;

    public static final class a {
        private final int a;
        private final int b;

        a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public int a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }
    }

    public static final class b {
        private final int a;
        private final a[] b;

        b(int i, a... aVarArr) {
            this.a = i;
            this.b = aVarArr;
        }

        public int a() {
            return this.a;
        }

        public int b() {
            int i = 0;
            a[] aVarArr = this.b;
            int i2 = 0;
            while (i < aVarArr.length) {
                i2 += aVarArr[i].a();
                i++;
            }
            return i2;
        }

        public int c() {
            return this.a * b();
        }

        public a[] d() {
            return this.b;
        }
    }

    private j(int i, int[] iArr, b... bVarArr) {
        int i2 = 0;
        this.c = i;
        this.d = iArr;
        this.e = bVarArr;
        int a = bVarArr[0].a();
        a[] d = bVarArr[0].d();
        int length = d.length;
        int i3 = 0;
        while (i2 < length) {
            a aVar = d[i2];
            i3 += (aVar.b() + a) * aVar.a();
            i2++;
        }
        this.f = i3;
    }

    public int a() {
        return this.c;
    }

    public int[] b() {
        return this.d;
    }

    public int c() {
        return this.f;
    }

    public int d() {
        return (this.c * 4) + 17;
    }

    public b a(f fVar) {
        return this.e[fVar.ordinal()];
    }

    public static j a(int i) throws h {
        if (i % 4 != 1) {
            throw h.a();
        }
        try {
            return b((i - 17) >> 2);
        } catch (IllegalArgumentException e) {
            throw h.a();
        }
    }

    public static j b(int i) {
        if (i >= 1 && i <= 40) {
            return b[i - 1];
        }
        throw new IllegalArgumentException();
    }

    static j c(int i) {
        int i2 = 0;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        while (i2 < a.length) {
            int i5 = a[i2];
            if (i5 == i) {
                return b(i2 + 7);
            }
            i5 = g.a(i, i5);
            if (i5 < i3) {
                i4 = i2 + 7;
                i3 = i5;
            }
            i2++;
        }
        if (i3 <= 3) {
            return b(i4);
        }
        return null;
    }

    com.google.a.c.b e() {
        int d = d();
        com.google.a.c.b bVar = new com.google.a.c.b(d);
        bVar.a(0, 0, 9, 9);
        bVar.a(d - 8, 0, 8, 9);
        bVar.a(0, d - 8, 9, 8);
        int length = this.d.length;
        int i = 0;
        while (i < length) {
            int i2 = this.d[i] - 2;
            int i3 = 0;
            while (i3 < length) {
                if (!((i == 0 && (i3 == 0 || i3 == length - 1)) || (i == length - 1 && i3 == 0))) {
                    bVar.a(this.d[i3] - 2, i2, 5, 5);
                }
                i3++;
            }
            i++;
        }
        bVar.a(6, 9, 1, d - 17);
        bVar.a(9, 6, d - 17, 1);
        if (this.c > 6) {
            bVar.a(d - 11, 0, 3, 6);
            bVar.a(0, d - 11, 6, 3);
        }
        return bVar;
    }

    public String toString() {
        return String.valueOf(this.c);
    }

    private static j[] f() {
        r0 = new j[40];
        int[] iArr = new int[0];
        b[] bVarArr = new b[4];
        bVarArr[0] = new b(7, new a(1, 19));
        bVarArr[1] = new b(10, new a(1, 16));
        bVarArr[2] = new b(13, new a(1, 13));
        bVarArr[3] = new b(17, new a(1, 9));
        r0[0] = new j(1, iArr, bVarArr);
        iArr = new int[]{6, 18};
        bVarArr = new b[4];
        bVarArr[0] = new b(10, new a(1, 34));
        bVarArr[1] = new b(16, new a(1, 28));
        bVarArr[2] = new b(22, new a(1, 22));
        bVarArr[3] = new b(28, new a(1, 16));
        r0[1] = new j(2, iArr, bVarArr);
        iArr = new int[]{6, 22};
        bVarArr = new b[4];
        bVarArr[0] = new b(15, new a(1, 55));
        bVarArr[1] = new b(26, new a(1, 44));
        bVarArr[2] = new b(18, new a(2, 17));
        bVarArr[3] = new b(22, new a(2, 13));
        r0[2] = new j(3, iArr, bVarArr);
        iArr = new int[]{6, 26};
        bVarArr = new b[4];
        bVarArr[0] = new b(20, new a(1, 80));
        bVarArr[1] = new b(18, new a(2, 32));
        bVarArr[2] = new b(26, new a(2, 24));
        bVarArr[3] = new b(16, new a(4, 9));
        r0[3] = new j(4, iArr, bVarArr);
        iArr = new int[]{6, 30};
        bVarArr = new b[4];
        bVarArr[0] = new b(26, new a(1, 108));
        bVarArr[1] = new b(24, new a(2, 43));
        bVarArr[2] = new b(18, new a(2, 15), new a(2, 16));
        bVarArr[3] = new b(22, new a(2, 11), new a(2, 12));
        r0[4] = new j(5, iArr, bVarArr);
        iArr = new int[]{6, 34};
        bVarArr = new b[4];
        bVarArr[0] = new b(18, new a(2, 68));
        bVarArr[1] = new b(16, new a(4, 27));
        bVarArr[2] = new b(24, new a(4, 19));
        bVarArr[3] = new b(28, new a(4, 15));
        r0[5] = new j(6, iArr, bVarArr);
        iArr = new int[]{6, 22, 38};
        bVarArr = new b[4];
        bVarArr[0] = new b(20, new a(2, 78));
        bVarArr[1] = new b(18, new a(4, 31));
        bVarArr[2] = new b(18, new a(2, 14), new a(4, 15));
        bVarArr[3] = new b(26, new a(4, 13), new a(1, 14));
        r0[6] = new j(7, iArr, bVarArr);
        iArr = new int[]{6, 24, 42};
        bVarArr = new b[4];
        bVarArr[0] = new b(24, new a(2, 97));
        bVarArr[1] = new b(22, new a(2, 38), new a(2, 39));
        bVarArr[2] = new b(22, new a(4, 18), new a(2, 19));
        bVarArr[3] = new b(26, new a(4, 14), new a(2, 15));
        r0[7] = new j(8, iArr, bVarArr);
        iArr = new int[]{6, 26, 46};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(2, 116));
        bVarArr[1] = new b(22, new a(3, 36), new a(2, 37));
        bVarArr[2] = new b(20, new a(4, 16), new a(4, 17));
        bVarArr[3] = new b(24, new a(4, 12), new a(4, 13));
        r0[8] = new j(9, iArr, bVarArr);
        iArr = new int[]{6, 28, 50};
        bVarArr = new b[4];
        bVarArr[0] = new b(18, new a(2, 68), new a(2, 69));
        bVarArr[1] = new b(26, new a(4, 43), new a(1, 44));
        bVarArr[2] = new b(24, new a(6, 19), new a(2, 20));
        bVarArr[3] = new b(28, new a(6, 15), new a(2, 16));
        r0[9] = new j(10, iArr, bVarArr);
        iArr = new int[]{6, 30, 54};
        bVarArr = new b[4];
        bVarArr[0] = new b(20, new a(4, 81));
        bVarArr[1] = new b(30, new a(1, 50), new a(4, 51));
        bVarArr[2] = new b(28, new a(4, 22), new a(4, 23));
        bVarArr[3] = new b(24, new a(3, 12), new a(8, 13));
        r0[10] = new j(11, iArr, bVarArr);
        iArr = new int[]{6, 32, 58};
        bVarArr = new b[4];
        bVarArr[0] = new b(24, new a(2, 92), new a(2, 93));
        bVarArr[1] = new b(22, new a(6, 36), new a(2, 37));
        bVarArr[2] = new b(26, new a(4, 20), new a(6, 21));
        bVarArr[3] = new b(28, new a(7, 14), new a(4, 15));
        r0[11] = new j(12, iArr, bVarArr);
        iArr = new int[]{6, 34, 62};
        bVarArr = new b[4];
        bVarArr[0] = new b(26, new a(4, 107));
        bVarArr[1] = new b(22, new a(8, 37), new a(1, 38));
        bVarArr[2] = new b(24, new a(8, 20), new a(4, 21));
        bVarArr[3] = new b(22, new a(12, 11), new a(4, 12));
        r0[12] = new j(13, iArr, bVarArr);
        iArr = new int[]{6, 26, 46, 66};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(3, 115), new a(1, 116));
        bVarArr[1] = new b(24, new a(4, 40), new a(5, 41));
        bVarArr[2] = new b(20, new a(11, 16), new a(5, 17));
        bVarArr[3] = new b(24, new a(11, 12), new a(5, 13));
        r0[13] = new j(14, iArr, bVarArr);
        iArr = new int[]{6, 26, 48, 70};
        bVarArr = new b[4];
        bVarArr[0] = new b(22, new a(5, 87), new a(1, 88));
        bVarArr[1] = new b(24, new a(5, 41), new a(5, 42));
        bVarArr[2] = new b(30, new a(5, 24), new a(7, 25));
        bVarArr[3] = new b(24, new a(11, 12), new a(7, 13));
        r0[14] = new j(15, iArr, bVarArr);
        iArr = new int[]{6, 26, 50, 74};
        bVarArr = new b[4];
        bVarArr[0] = new b(24, new a(5, 98), new a(1, 99));
        bVarArr[1] = new b(28, new a(7, 45), new a(3, 46));
        bVarArr[2] = new b(24, new a(15, 19), new a(2, 20));
        bVarArr[3] = new b(30, new a(3, 15), new a(13, 16));
        r0[15] = new j(16, iArr, bVarArr);
        iArr = new int[]{6, 30, 54, 78};
        bVarArr = new b[4];
        bVarArr[0] = new b(28, new a(1, 107), new a(5, 108));
        bVarArr[1] = new b(28, new a(10, 46), new a(1, 47));
        bVarArr[2] = new b(28, new a(1, 22), new a(15, 23));
        bVarArr[3] = new b(28, new a(2, 14), new a(17, 15));
        r0[16] = new j(17, iArr, bVarArr);
        iArr = new int[]{6, 30, 56, 82};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(5, 120), new a(1, TrackerEvent.PositioningHybridOutdoor));
        bVarArr[1] = new b(26, new a(9, 43), new a(4, 44));
        bVarArr[2] = new b(28, new a(17, 22), new a(1, 23));
        bVarArr[3] = new b(28, new a(2, 14), new a(19, 15));
        r0[17] = new j(18, iArr, bVarArr);
        iArr = new int[]{6, 30, 58, 86};
        bVarArr = new b[4];
        bVarArr[0] = new b(28, new a(3, 113), new a(4, 114));
        bVarArr[1] = new b(26, new a(3, 44), new a(11, 45));
        bVarArr[2] = new b(26, new a(17, 21), new a(4, 22));
        bVarArr[3] = new b(26, new a(9, 13), new a(16, 14));
        r0[18] = new j(19, iArr, bVarArr);
        iArr = new int[]{6, 34, 62, 90};
        bVarArr = new b[4];
        bVarArr[0] = new b(28, new a(3, 107), new a(5, 108));
        bVarArr[1] = new b(26, new a(3, 41), new a(13, 42));
        bVarArr[2] = new b(30, new a(15, 24), new a(5, 25));
        bVarArr[3] = new b(28, new a(15, 15), new a(10, 16));
        r0[19] = new j(20, iArr, bVarArr);
        iArr = new int[]{6, 28, 50, 72, 94};
        bVarArr = new b[4];
        bVarArr[0] = new b(28, new a(4, 116), new a(4, 117));
        bVarArr[1] = new b(26, new a(17, 42));
        bVarArr[2] = new b(28, new a(17, 22), new a(6, 23));
        bVarArr[3] = new b(30, new a(19, 16), new a(6, 17));
        r0[20] = new j(21, iArr, bVarArr);
        iArr = new int[]{6, 26, 50, 74, 98};
        bVarArr = new b[4];
        bVarArr[0] = new b(28, new a(2, TrackerEvent.PositioningOnlineOutdoor), new a(7, d.k));
        bVarArr[1] = new b(28, new a(17, 46));
        bVarArr[2] = new b(30, new a(7, 24), new a(16, 25));
        bVarArr[3] = new b(24, new a(34, 13));
        r0[21] = new j(22, iArr, bVarArr);
        iArr = new int[]{6, 30, 54, 78, 102};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(4, TrackerEvent.PositioningHybridOutdoor), new a(5, 122));
        bVarArr[1] = new b(28, new a(4, 47), new a(14, 48));
        bVarArr[2] = new b(30, new a(11, 24), new a(14, 25));
        bVarArr[3] = new b(30, new a(16, 15), new a(14, 16));
        r0[22] = new j(23, iArr, bVarArr);
        iArr = new int[]{6, 28, 54, 80, 106};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(6, 117), new a(4, 118));
        bVarArr[1] = new b(28, new a(6, 45), new a(14, 46));
        bVarArr[2] = new b(30, new a(11, 24), new a(16, 25));
        bVarArr[3] = new b(30, new a(30, 16), new a(2, 17));
        r0[23] = new j(24, iArr, bVarArr);
        iArr = new int[]{6, 32, 58, 84, FTPCodes.RESTART_MARKER};
        bVarArr = new b[4];
        bVarArr[0] = new b(26, new a(8, 106), new a(4, 107));
        bVarArr[1] = new b(28, new a(8, 47), new a(13, 48));
        bVarArr[2] = new b(30, new a(7, 24), new a(22, 25));
        bVarArr[3] = new b(30, new a(22, 15), new a(13, 16));
        r0[24] = new j(25, iArr, bVarArr);
        iArr = new int[]{6, 30, 58, 86, 114};
        bVarArr = new b[4];
        bVarArr[0] = new b(28, new a(10, 114), new a(2, 115));
        bVarArr[1] = new b(28, new a(19, 46), new a(4, 47));
        bVarArr[2] = new b(28, new a(28, 22), new a(6, 23));
        bVarArr[3] = new b(30, new a(33, 16), new a(4, 17));
        r0[25] = new j(26, iArr, bVarArr);
        iArr = new int[]{6, 34, 62, 90, 118};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(8, 122), new a(4, 123));
        bVarArr[1] = new b(28, new a(22, 45), new a(3, 46));
        bVarArr[2] = new b(30, new a(8, 23), new a(26, 24));
        bVarArr[3] = new b(30, new a(12, 15), new a(28, 16));
        r0[26] = new j(27, iArr, bVarArr);
        iArr = new int[]{6, 26, 50, 74, 98, 122};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(3, 117), new a(10, 118));
        bVarArr[1] = new b(28, new a(3, 45), new a(23, 46));
        bVarArr[2] = new b(30, new a(4, 24), new a(31, 25));
        bVarArr[3] = new b(30, new a(11, 15), new a(31, 16));
        r0[27] = new j(28, iArr, bVarArr);
        iArr = new int[]{6, 30, 54, 78, 102, TransportMediator.KEYCODE_MEDIA_PLAY};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(7, 116), new a(7, 117));
        bVarArr[1] = new b(28, new a(21, 45), new a(7, 46));
        bVarArr[2] = new b(30, new a(1, 23), new a(37, 24));
        bVarArr[3] = new b(30, new a(19, 15), new a(26, 16));
        r0[28] = new j(29, iArr, bVarArr);
        iArr = new int[]{6, 26, 52, 78, 104, TransportMediator.KEYCODE_MEDIA_RECORD};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(5, 115), new a(10, 116));
        bVarArr[1] = new b(28, new a(19, 47), new a(10, 48));
        bVarArr[2] = new b(30, new a(15, 24), new a(25, 25));
        bVarArr[3] = new b(30, new a(23, 15), new a(25, 16));
        r0[29] = new j(30, iArr, bVarArr);
        iArr = new int[]{6, 30, 56, 82, 108, 134};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(13, 115), new a(3, 116));
        bVarArr[1] = new b(28, new a(2, 46), new a(29, 47));
        bVarArr[2] = new b(30, new a(42, 24), new a(1, 25));
        bVarArr[3] = new b(30, new a(23, 15), new a(28, 16));
        r0[30] = new j(31, iArr, bVarArr);
        iArr = new int[]{6, 34, 60, 86, d.k, 138};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(17, 115));
        bVarArr[1] = new b(28, new a(10, 46), new a(23, 47));
        bVarArr[2] = new b(30, new a(10, 24), new a(35, 25));
        bVarArr[3] = new b(30, new a(19, 15), new a(35, 16));
        r0[31] = new j(32, iArr, bVarArr);
        iArr = new int[]{6, 30, 58, 86, 114, 142};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(17, 115), new a(1, 116));
        bVarArr[1] = new b(28, new a(14, 46), new a(21, 47));
        bVarArr[2] = new b(30, new a(29, 24), new a(19, 25));
        bVarArr[3] = new b(30, new a(11, 15), new a(46, 16));
        r0[32] = new j(33, iArr, bVarArr);
        iArr = new int[]{6, 34, 62, 90, 118, BuildConfig.VERSION_CODE};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(13, 115), new a(6, 116));
        bVarArr[1] = new b(28, new a(14, 46), new a(23, 47));
        bVarArr[2] = new b(30, new a(44, 24), new a(7, 25));
        bVarArr[3] = new b(30, new a(59, 16), new a(1, 17));
        r0[33] = new j(34, iArr, bVarArr);
        iArr = new int[]{6, 30, 54, 78, 102, TransportMediator.KEYCODE_MEDIA_PLAY, 150};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(12, TrackerEvent.PositioningHybridOutdoor), new a(7, 122));
        bVarArr[1] = new b(28, new a(12, 47), new a(26, 48));
        bVarArr[2] = new b(30, new a(39, 24), new a(14, 25));
        bVarArr[3] = new b(30, new a(22, 15), new a(41, 16));
        r0[34] = new j(35, iArr, bVarArr);
        iArr = new int[]{6, 24, 50, 76, 102, 128, 154};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(6, TrackerEvent.PositioningHybridOutdoor), new a(14, 122));
        bVarArr[1] = new b(28, new a(6, 47), new a(34, 48));
        bVarArr[2] = new b(30, new a(46, 24), new a(10, 25));
        bVarArr[3] = new b(30, new a(2, 15), new a(64, 16));
        r0[35] = new j(36, iArr, bVarArr);
        iArr = new int[]{6, 28, 54, 80, 106, TrackerEvent.PositioningOfflineCommonIndoor, 158};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(17, 122), new a(4, 123));
        bVarArr[1] = new b(28, new a(29, 46), new a(14, 47));
        bVarArr[2] = new b(30, new a(49, 24), new a(10, 25));
        bVarArr[3] = new b(30, new a(24, 15), new a(46, 16));
        r0[36] = new j(37, iArr, bVarArr);
        iArr = new int[]{6, 32, 58, 84, FTPCodes.RESTART_MARKER, 136, 162};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(4, 122), new a(18, 123));
        bVarArr[1] = new b(28, new a(13, 46), new a(32, 47));
        bVarArr[2] = new b(30, new a(48, 24), new a(14, 25));
        bVarArr[3] = new b(30, new a(42, 15), new a(32, 16));
        r0[37] = new j(38, iArr, bVarArr);
        iArr = new int[]{6, 26, 54, 82, FTPCodes.RESTART_MARKER, 138, 166};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(20, 117), new a(4, 118));
        bVarArr[1] = new b(28, new a(40, 47), new a(7, 48));
        bVarArr[2] = new b(30, new a(43, 24), new a(22, 25));
        bVarArr[3] = new b(30, new a(10, 15), new a(67, 16));
        r0[38] = new j(39, iArr, bVarArr);
        iArr = new int[]{6, 30, 58, 86, 114, 142, 170};
        bVarArr = new b[4];
        bVarArr[0] = new b(30, new a(19, 118), new a(6, 119));
        bVarArr[1] = new b(28, new a(18, 47), new a(31, 48));
        bVarArr[2] = new b(30, new a(34, 24), new a(34, 25));
        bVarArr[3] = new b(30, new a(20, 15), new a(61, 16));
        r0[39] = new j(40, iArr, bVarArr);
        return r0;
    }
}
