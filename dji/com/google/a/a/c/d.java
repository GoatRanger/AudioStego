package com.google.a.a.c;

import android.support.v4.media.TransportMediator;
import com.google.a.c.a;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public final class d {
    static final String[] a = new String[]{"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};
    static final int b = 0;
    static final int c = 1;
    static final int d = 2;
    static final int e = 3;
    static final int f = 4;
    static final int[][] g = new int[][]{new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};
    static final int[][] h = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{6, 6}));
    private static final int[][] i = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{5, 256}));
    private final byte[] j;

    static {
        int i;
        i[0][32] = 1;
        for (i = 65; i <= 90; i++) {
            i[0][i] = (i - 65) + 2;
        }
        i[1][32] = 1;
        for (i = 97; i <= 122; i++) {
            i[1][i] = (i - 97) + 2;
        }
        i[2][32] = 1;
        for (i = 48; i <= 57; i++) {
            i[2][i] = (i - 48) + 2;
        }
        i[2][44] = 12;
        i[2][46] = 13;
        int[] iArr = new int[]{0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, TransportMediator.KEYCODE_MEDIA_PLAY, TransportMediator.KEYCODE_MEDIA_PAUSE};
        for (i = 0; i < iArr.length; i++) {
            i[3][iArr[i]] = i;
        }
        iArr = new int[]{0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, FTPCodes.DATA_CONNECTION_ALREADY_OPEN};
        for (i = 0; i < iArr.length; i++) {
            if (iArr[i] > 0) {
                i[4][iArr[i]] = i;
            }
        }
        for (int[] fill : h) {
            Arrays.fill(fill, -1);
        }
        h[0][4] = 0;
        h[1][4] = 0;
        h[1][0] = 28;
        h[3][4] = 0;
        h[2][4] = 0;
        h[2][0] = 15;
    }

    public d(byte[] bArr) {
        this.j = bArr;
    }

    public a a() {
        Iterable singletonList = Collections.singletonList(f.a);
        int i = 0;
        while (i < this.j.length) {
            byte b;
            int i2;
            if (i + 1 < this.j.length) {
                b = this.j[i + 1];
            } else {
                b = (byte) 0;
            }
            switch (this.j[i]) {
                case (byte) 13:
                    if (b != (byte) 10) {
                        i2 = 0;
                        break;
                    }
                    i2 = 2;
                    break;
                case (byte) 44:
                    if (b != (byte) 32) {
                        i2 = 0;
                        break;
                    }
                    i2 = 4;
                    break;
                case (byte) 46:
                    if (b != (byte) 32) {
                        i2 = 0;
                        break;
                    }
                    i2 = 3;
                    break;
                case (byte) 58:
                    if (b != (byte) 32) {
                        i2 = 0;
                        break;
                    }
                    i2 = 5;
                    break;
                default:
                    i2 = 0;
                    break;
            }
            if (i2 > 0) {
                singletonList = a(singletonList, i, i2);
                i++;
            } else {
                singletonList = a(singletonList, i);
            }
            i++;
        }
        return ((f) Collections.min(singletonList, new Comparator<f>(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((f) obj, (f) obj2);
            }

            public int a(f fVar, f fVar2) {
                return fVar.d() - fVar2.d();
            }
        })).a(this.j);
    }

    private Collection<f> a(Iterable<f> iterable, int i) {
        Collection linkedList = new LinkedList();
        for (f a : iterable) {
            a(a, i, linkedList);
        }
        return a(linkedList);
    }

    private void a(f fVar, int i, Collection<f> collection) {
        Object obj;
        char c = (char) (this.j[i] & 255);
        if (i[fVar.a()][c] > 0) {
            obj = 1;
        } else {
            obj = null;
        }
        f fVar2 = null;
        int i2 = 0;
        while (i2 <= 4) {
            int i3 = i[i2][c];
            if (i3 > 0) {
                if (fVar2 == null) {
                    fVar2 = fVar.b(i);
                }
                if (obj == null || i2 == fVar.a() || i2 == 2) {
                    collection.add(fVar2.a(i2, i3));
                }
                if (obj == null && h[fVar.a()][i2] >= 0) {
                    collection.add(fVar2.b(i2, i3));
                }
            }
            i2++;
        }
        if (fVar.c() > 0 || i[fVar.a()][c] == 0) {
            collection.add(fVar.a(i));
        }
    }

    private static Collection<f> a(Iterable<f> iterable, int i, int i2) {
        Iterable linkedList = new LinkedList();
        for (f a : iterable) {
            a(a, i, i2, linkedList);
        }
        return a(linkedList);
    }

    private static void a(f fVar, int i, int i2, Collection<f> collection) {
        f b = fVar.b(i);
        collection.add(b.a(4, i2));
        if (fVar.a() != 4) {
            collection.add(b.b(4, i2));
        }
        if (i2 == 3 || i2 == 4) {
            collection.add(b.a(2, 16 - i2).a(2, 1));
        }
        if (fVar.c() > 0) {
            collection.add(fVar.a(i).a(i + 1));
        }
    }

    private static Collection<f> a(Iterable<f> iterable) {
        Collection linkedList = new LinkedList();
        for (f fVar : iterable) {
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                f fVar2 = (f) it.next();
                if (fVar2.a(fVar)) {
                    Object obj = null;
                    break;
                } else if (fVar.a(fVar2)) {
                    it.remove();
                }
            }
            int i = 1;
            if (obj != null) {
                linkedList.add(fVar);
            }
        }
        return linkedList;
    }
}
