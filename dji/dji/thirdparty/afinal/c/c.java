package dji.thirdparty.afinal.c;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.List;

public class c {
    static final /* synthetic */ boolean a = (!c.class.desiredAssertionStatus());

    private c() {
    }

    public static <T> List<T> a(T... tArr) {
        return new a(tArr);
    }

    public static int a(byte[] bArr, byte b) {
        return a(bArr, 0, bArr.length, b);
    }

    public static int a(byte[] bArr, int i, int i2, byte b) {
        a(i, i2, bArr.length);
        int i3 = i2 - 1;
        int i4 = i;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            byte b2 = bArr[i5];
            if (b2 < b) {
                i4 = i5 + 1;
            } else if (b2 <= b) {
                return i5;
            } else {
                i3 = i5 - 1;
            }
        }
        return i4 ^ -1;
    }

    public static int a(char[] cArr, char c) {
        return a(cArr, 0, cArr.length, c);
    }

    public static int a(char[] cArr, int i, int i2, char c) {
        a(i, i2, cArr.length);
        int i3 = i2 - 1;
        int i4 = i;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            char c2 = cArr[i5];
            if (c2 < c) {
                i4 = i5 + 1;
            } else if (c2 <= c) {
                return i5;
            } else {
                i3 = i5 - 1;
            }
        }
        return i4 ^ -1;
    }

    public static int a(double[] dArr, double d) {
        return a(dArr, 0, dArr.length, d);
    }

    public static int a(double[] dArr, int i, int i2, double d) {
        a(i, i2, dArr.length);
        int i3 = i2 - 1;
        int i4 = i;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            double d2 = dArr[i5];
            if (d2 < d) {
                i4 = i5 + 1;
            } else if (d2 > d) {
                i3 = i5 - 1;
            } else if (d2 != 0.0d && d2 == d) {
                return i5;
            } else {
                long doubleToLongBits = Double.doubleToLongBits(d2);
                long doubleToLongBits2 = Double.doubleToLongBits(d);
                if (doubleToLongBits < doubleToLongBits2) {
                    i4 = i5 + 1;
                } else if (doubleToLongBits <= doubleToLongBits2) {
                    return i5;
                } else {
                    i3 = i5 - 1;
                }
            }
        }
        return i4 ^ -1;
    }

    public static int a(float[] fArr, float f) {
        return a(fArr, 0, fArr.length, f);
    }

    public static int a(float[] fArr, int i, int i2, float f) {
        a(i, i2, fArr.length);
        int i3 = i2 - 1;
        int i4 = i;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            float f2 = fArr[i5];
            if (f2 < f) {
                i4 = i5 + 1;
            } else if (f2 > f) {
                i3 = i5 - 1;
            } else if (f2 != 0.0f && f2 == f) {
                return i5;
            } else {
                int floatToIntBits = Float.floatToIntBits(f2);
                int floatToIntBits2 = Float.floatToIntBits(f);
                if (floatToIntBits < floatToIntBits2) {
                    i4 = i5 + 1;
                } else if (floatToIntBits <= floatToIntBits2) {
                    return i5;
                } else {
                    i3 = i5 - 1;
                }
            }
        }
        return i4 ^ -1;
    }

    public static int a(int[] iArr, int i) {
        return a(iArr, 0, iArr.length, i);
    }

    public static int a(int[] iArr, int i, int i2, int i3) {
        a(i, i2, iArr.length);
        int i4 = i2 - 1;
        int i5 = i;
        while (i5 <= i4) {
            int i6 = (i5 + i4) >>> 1;
            int i7 = iArr[i6];
            if (i7 < i3) {
                i5 = i6 + 1;
            } else if (i7 <= i3) {
                return i6;
            } else {
                i4 = i6 - 1;
            }
        }
        return i5 ^ -1;
    }

    public static int a(long[] jArr, long j) {
        return a(jArr, 0, jArr.length, j);
    }

    public static int a(long[] jArr, int i, int i2, long j) {
        a(i, i2, jArr.length);
        int i3 = i2 - 1;
        int i4 = i;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            long j2 = jArr[i5];
            if (j2 < j) {
                i4 = i5 + 1;
            } else if (j2 <= j) {
                return i5;
            } else {
                i3 = i5 - 1;
            }
        }
        return i4 ^ -1;
    }

    public static int a(Object[] objArr, Object obj) {
        return a(objArr, 0, objArr.length, obj);
    }

    public static int a(Object[] objArr, int i, int i2, Object obj) {
        a(i, i2, objArr.length);
        int i3 = i2 - 1;
        int i4 = i;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            int compareTo = ((Comparable) objArr[i5]).compareTo(obj);
            if (compareTo < 0) {
                int i6 = i3;
                i3 = i5 + 1;
                compareTo = i6;
            } else if (compareTo <= 0) {
                return i5;
            } else {
                compareTo = i5 - 1;
                i3 = i4;
            }
            i4 = i3;
            i3 = compareTo;
        }
        return i4 ^ -1;
    }

    public static <T> int a(T[] tArr, T t, Comparator<? super T> comparator) {
        return a(tArr, 0, tArr.length, t, comparator);
    }

    public static <T> int a(T[] tArr, int i, int i2, T t, Comparator<? super T> comparator) {
        if (comparator == null) {
            return a((Object[]) tArr, i, i2, (Object) t);
        }
        a(i, i2, tArr.length);
        int i3 = i2 - 1;
        int i4 = i;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            int compare = comparator.compare(tArr[i5], t);
            if (compare < 0) {
                i4 = i5 + 1;
            } else if (compare <= 0) {
                return i5;
            } else {
                i3 = i5 - 1;
            }
        }
        return i4 ^ -1;
    }

    public static int a(short[] sArr, short s) {
        return a(sArr, 0, sArr.length, s);
    }

    public static int a(short[] sArr, int i, int i2, short s) {
        a(i, i2, sArr.length);
        int i3 = i2 - 1;
        int i4 = i;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            short s2 = sArr[i5];
            if (s2 < s) {
                i4 = i5 + 1;
            } else if (s2 <= s) {
                return i5;
            } else {
                i3 = i5 - 1;
            }
        }
        return i4 ^ -1;
    }

    private static void a(int i, int i2, int i3) {
        if (i > i2) {
            throw new IllegalArgumentException();
        } else if (i < 0 || i2 > i3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static void b(byte[] bArr, byte b) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = b;
        }
    }

    public static void b(int[] iArr, int i) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = i;
        }
    }

    public static void a(boolean[] zArr, boolean z) {
        for (int i = 0; i < zArr.length; i++) {
            zArr[i] = z;
        }
    }

    public static void b(Object[] objArr, Object obj) {
        for (int i = 0; i < objArr.length; i++) {
            objArr[i] = obj;
        }
    }

    public static int a(boolean[] zArr) {
        int i = 0;
        if (zArr != null) {
            i = 1;
            int i2 = 0;
            while (i2 < zArr.length) {
                i2++;
                i = (i * 31) + (zArr[i2] ? 1231 : 1237);
            }
        }
        return i;
    }

    public static int a(int[] iArr) {
        int i = 0;
        if (iArr != null) {
            i = 1;
            int i2 = 0;
            while (i2 < iArr.length) {
                int i3 = iArr[i2] + (i * 31);
                i2++;
                i = i3;
            }
        }
        return i;
    }

    public static int a(short[] sArr) {
        int i = 0;
        if (sArr != null) {
            i = 1;
            int i2 = 0;
            while (i2 < sArr.length) {
                int i3 = sArr[i2] + (i * 31);
                i2++;
                i = i3;
            }
        }
        return i;
    }

    public static int a(char[] cArr) {
        int i = 0;
        if (cArr != null) {
            i = 1;
            int i2 = 0;
            while (i2 < cArr.length) {
                int i3 = cArr[i2] + (i * 31);
                i2++;
                i = i3;
            }
        }
        return i;
    }

    public static int a(byte[] bArr) {
        int i = 0;
        if (bArr != null) {
            i = 1;
            int i2 = 0;
            while (i2 < bArr.length) {
                int i3 = bArr[i2] + (i * 31);
                i2++;
                i = i3;
            }
        }
        return i;
    }

    public static int a(long[] jArr) {
        int i = 0;
        if (jArr != null) {
            int length = jArr.length;
            i = 1;
            int i2 = 0;
            while (i2 < length) {
                long j = jArr[i2];
                i2++;
                i = ((int) (j ^ (j >>> 32))) + (i * 31);
            }
        }
        return i;
    }

    public static int a(float[] fArr) {
        int i = 0;
        if (fArr != null) {
            i = 1;
            int i2 = 0;
            while (i2 < fArr.length) {
                int floatToIntBits = Float.floatToIntBits(fArr[i2]) + (i * 31);
                i2++;
                i = floatToIntBits;
            }
        }
        return i;
    }

    public static int a(double[] dArr) {
        int i = 0;
        if (dArr != null) {
            int length = dArr.length;
            i = 1;
            int i2 = 0;
            while (i2 < length) {
                long doubleToLongBits = Double.doubleToLongBits(dArr[i2]);
                i2++;
                i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + (i * 31);
            }
        }
        return i;
    }

    public static int b(Object[] objArr) {
        if (objArr == null) {
            return 0;
        }
        int i = 1;
        for (Object obj : objArr) {
            int i2;
            if (obj == null) {
                i2 = 0;
            } else {
                i2 = obj.hashCode();
            }
            i = (i * 31) + i2;
        }
        return i;
    }

    public static int c(Object[] objArr) {
        int i = 0;
        if (objArr != null) {
            i = 1;
            int i2 = 0;
            while (i2 < objArr.length) {
                int a = a(objArr[i2]) + (i * 31);
                i2++;
                i = a;
            }
        }
        return i;
    }

    private static int a(Object obj) {
        if (obj == null) {
            return 0;
        }
        Class componentType = obj.getClass().getComponentType();
        if (componentType == null) {
            return obj.hashCode();
        }
        if (!componentType.isPrimitive()) {
            return c((Object[]) obj);
        }
        if (componentType.equals(Integer.TYPE)) {
            return a((int[]) obj);
        }
        if (componentType.equals(Character.TYPE)) {
            return a((char[]) obj);
        }
        if (componentType.equals(Boolean.TYPE)) {
            return a((boolean[]) obj);
        }
        if (componentType.equals(Byte.TYPE)) {
            return a((byte[]) obj);
        }
        if (componentType.equals(Long.TYPE)) {
            return a((long[]) obj);
        }
        if (componentType.equals(Float.TYPE)) {
            return a((float[]) obj);
        }
        if (componentType.equals(Double.TYPE)) {
            return a((double[]) obj);
        }
        return a((short[]) obj);
    }

    public static boolean a(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(short[] sArr, short[] sArr2) {
        if (sArr == sArr2) {
            return true;
        }
        if (sArr == null || sArr2 == null || sArr.length != sArr2.length) {
            return false;
        }
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] != sArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(char[] cArr, char[] cArr2) {
        if (cArr == cArr2) {
            return true;
        }
        if (cArr == null || cArr2 == null || cArr.length != cArr2.length) {
            return false;
        }
        for (int i = 0; i < cArr.length; i++) {
            if (cArr[i] != cArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(int[] iArr, int[] iArr2) {
        if (iArr == iArr2) {
            return true;
        }
        if (iArr == null || iArr2 == null || iArr.length != iArr2.length) {
            return false;
        }
        for (int i = 0; i < iArr.length; i++) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(long[] jArr, long[] jArr2) {
        if (jArr == jArr2) {
            return true;
        }
        if (jArr == null || jArr2 == null || jArr.length != jArr2.length) {
            return false;
        }
        for (int i = 0; i < jArr.length; i++) {
            if (jArr[i] != jArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(float[] fArr, float[] fArr2) {
        if (fArr == fArr2) {
            return true;
        }
        if (fArr == null || fArr2 == null || fArr.length != fArr2.length) {
            return false;
        }
        for (int i = 0; i < fArr.length; i++) {
            if (Float.floatToIntBits(fArr[i]) != Float.floatToIntBits(fArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(double[] dArr, double[] dArr2) {
        if (dArr == dArr2) {
            return true;
        }
        if (dArr == null || dArr2 == null || dArr.length != dArr2.length) {
            return false;
        }
        for (int i = 0; i < dArr.length; i++) {
            if (Double.doubleToLongBits(dArr[i]) != Double.doubleToLongBits(dArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(boolean[] zArr, boolean[] zArr2) {
        if (zArr == zArr2) {
            return true;
        }
        if (zArr == null || zArr2 == null || zArr.length != zArr2.length) {
            return false;
        }
        for (int i = 0; i < zArr.length; i++) {
            if (zArr[i] != zArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(Object[] objArr, Object[] objArr2) {
        if (objArr == objArr2) {
            return true;
        }
        if (objArr == null || objArr2 == null || objArr.length != objArr2.length) {
            return false;
        }
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            Object obj2 = objArr2[i];
            if (obj == null) {
                if (obj2 != null) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean b(Object[] objArr, Object[] objArr2) {
        if (objArr == objArr2) {
            return true;
        }
        if (objArr == null || objArr2 == null || objArr.length != objArr2.length) {
            return false;
        }
        for (int i = 0; i < objArr.length; i++) {
            if (!a(objArr[i], objArr2[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        Class componentType = obj.getClass().getComponentType();
        if (componentType != obj2.getClass().getComponentType()) {
            return false;
        }
        if (componentType == null) {
            return obj.equals(obj2);
        }
        if (!componentType.isPrimitive()) {
            return b((Object[]) obj, (Object[]) obj2);
        }
        if (componentType.equals(Integer.TYPE)) {
            return a((int[]) obj, (int[]) obj2);
        }
        if (componentType.equals(Character.TYPE)) {
            return a((char[]) obj, (char[]) obj2);
        }
        if (componentType.equals(Boolean.TYPE)) {
            return a((boolean[]) obj, (boolean[]) obj2);
        }
        if (componentType.equals(Byte.TYPE)) {
            return a((byte[]) obj, (byte[]) obj2);
        }
        if (componentType.equals(Long.TYPE)) {
            return a((long[]) obj, (long[]) obj2);
        }
        if (componentType.equals(Float.TYPE)) {
            return a((float[]) obj, (float[]) obj2);
        }
        if (componentType.equals(Double.TYPE)) {
            return a((double[]) obj, (double[]) obj2);
        }
        return a((short[]) obj, (short[]) obj2);
    }

    public static String b(boolean[] zArr) {
        if (zArr == null) {
            return "null";
        }
        if (zArr.length == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder(zArr.length * 7);
        stringBuilder.append('[');
        stringBuilder.append(zArr[0]);
        for (int i = 1; i < zArr.length; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(zArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public static String b(byte[] bArr) {
        if (bArr == null) {
            return "null";
        }
        if (bArr.length == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 6);
        stringBuilder.append('[');
        stringBuilder.append(bArr[0]);
        for (int i = 1; i < bArr.length; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(bArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public static String b(char[] cArr) {
        if (cArr == null) {
            return "null";
        }
        if (cArr.length == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder(cArr.length * 3);
        stringBuilder.append('[');
        stringBuilder.append(cArr[0]);
        for (int i = 1; i < cArr.length; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(cArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public static String b(double[] dArr) {
        if (dArr == null) {
            return "null";
        }
        if (dArr.length == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder(dArr.length * 7);
        stringBuilder.append('[');
        stringBuilder.append(dArr[0]);
        for (int i = 1; i < dArr.length; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(dArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public static String b(float[] fArr) {
        if (fArr == null) {
            return "null";
        }
        if (fArr.length == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder(fArr.length * 7);
        stringBuilder.append('[');
        stringBuilder.append(fArr[0]);
        for (int i = 1; i < fArr.length; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(fArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public static String b(int[] iArr) {
        if (iArr == null) {
            return "null";
        }
        if (iArr.length == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder(iArr.length * 6);
        stringBuilder.append('[');
        stringBuilder.append(iArr[0]);
        for (int i = 1; i < iArr.length; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(iArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public static String b(long[] jArr) {
        if (jArr == null) {
            return "null";
        }
        if (jArr.length == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder(jArr.length * 6);
        stringBuilder.append('[');
        stringBuilder.append(jArr[0]);
        for (int i = 1; i < jArr.length; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(jArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public static String b(short[] sArr) {
        if (sArr == null) {
            return "null";
        }
        if (sArr.length == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder(sArr.length * 6);
        stringBuilder.append('[');
        stringBuilder.append(sArr[0]);
        for (int i = 1; i < sArr.length; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(sArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public static String d(Object[] objArr) {
        if (objArr == null) {
            return "null";
        }
        if (objArr.length == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder(objArr.length * 7);
        stringBuilder.append('[');
        stringBuilder.append(objArr[0]);
        for (int i = 1; i < objArr.length; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(objArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public static String e(Object[] objArr) {
        if (objArr == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder(objArr.length * 9);
        a(objArr, new Object[]{objArr}, stringBuilder);
        return stringBuilder.toString();
    }

    private static void a(Object[] objArr, Object[] objArr2, StringBuilder stringBuilder) {
        if (objArr == null) {
            stringBuilder.append("null");
            return;
        }
        stringBuilder.append('[');
        for (int i = 0; i < objArr.length; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            Object obj = objArr[i];
            if (obj == null) {
                stringBuilder.append("null");
            } else {
                Class cls = obj.getClass();
                if (cls.isArray()) {
                    cls = cls.getComponentType();
                    if (cls.isPrimitive()) {
                        if (Boolean.TYPE.equals(cls)) {
                            stringBuilder.append(b((boolean[]) obj));
                        } else if (Byte.TYPE.equals(cls)) {
                            stringBuilder.append(b((byte[]) obj));
                        } else if (Character.TYPE.equals(cls)) {
                            stringBuilder.append(b((char[]) obj));
                        } else if (Double.TYPE.equals(cls)) {
                            stringBuilder.append(b((double[]) obj));
                        } else if (Float.TYPE.equals(cls)) {
                            stringBuilder.append(b((float[]) obj));
                        } else if (Integer.TYPE.equals(cls)) {
                            stringBuilder.append(b((int[]) obj));
                        } else if (Long.TYPE.equals(cls)) {
                            stringBuilder.append(b((long[]) obj));
                        } else if (Short.TYPE.equals(cls)) {
                            stringBuilder.append(b((short[]) obj));
                        } else {
                            throw new AssertionError();
                        }
                    } else if (!a && !(obj instanceof Object[])) {
                        throw new AssertionError();
                    } else if (c(objArr2, obj)) {
                        stringBuilder.append("[...]");
                    } else {
                        Object[] objArr3 = (Object[]) obj;
                        Object[] objArr4 = new Object[(objArr2.length + 1)];
                        System.arraycopy(objArr2, 0, objArr4, 0, objArr2.length);
                        objArr4[objArr2.length] = objArr3;
                        a(objArr3, objArr4, stringBuilder);
                    }
                } else {
                    stringBuilder.append(objArr[i]);
                }
            }
        }
        stringBuilder.append(']');
    }

    private static boolean c(Object[] objArr, Object obj) {
        if (objArr == null || objArr.length == 0) {
            return false;
        }
        for (Object obj2 : objArr) {
            if (obj2 == obj) {
                return true;
            }
        }
        return false;
    }

    public static boolean[] a(boolean[] zArr, int i) {
        if (i >= 0) {
            return a(zArr, 0, i);
        }
        throw new NegativeArraySizeException();
    }

    public static byte[] a(byte[] bArr, int i) {
        if (i >= 0) {
            return a(bArr, 0, i);
        }
        throw new NegativeArraySizeException();
    }

    public static char[] a(char[] cArr, int i) {
        if (i >= 0) {
            return a(cArr, 0, i);
        }
        throw new NegativeArraySizeException();
    }

    public static double[] a(double[] dArr, int i) {
        if (i >= 0) {
            return a(dArr, 0, i);
        }
        throw new NegativeArraySizeException();
    }

    public static float[] a(float[] fArr, int i) {
        if (i >= 0) {
            return a(fArr, 0, i);
        }
        throw new NegativeArraySizeException();
    }

    public static int[] c(int[] iArr, int i) {
        if (i >= 0) {
            return a(iArr, 0, i);
        }
        throw new NegativeArraySizeException();
    }

    public static long[] a(long[] jArr, int i) {
        if (i >= 0) {
            return a(jArr, 0, i);
        }
        throw new NegativeArraySizeException();
    }

    public static short[] a(short[] sArr, int i) {
        if (i >= 0) {
            return a(sArr, 0, i);
        }
        throw new NegativeArraySizeException();
    }

    public static <T> T[] a(T[] tArr, int i) {
        if (tArr == null) {
            throw new NullPointerException();
        } else if (i >= 0) {
            return a((Object[]) tArr, 0, i);
        } else {
            throw new NegativeArraySizeException();
        }
    }

    public static <T, U> T[] a(U[] uArr, int i, Class<? extends T[]> cls) {
        if (i >= 0) {
            return a((Object[]) uArr, 0, i, (Class) cls);
        }
        throw new NegativeArraySizeException();
    }

    public static boolean[] a(boolean[] zArr, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = zArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        length = Math.min(i3, length - i);
        Object obj = new boolean[i3];
        System.arraycopy(zArr, i, obj, 0, length);
        return obj;
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = bArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        length = Math.min(i3, length - i);
        Object obj = new byte[i3];
        System.arraycopy(bArr, i, obj, 0, length);
        return obj;
    }

    public static char[] a(char[] cArr, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = cArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        length = Math.min(i3, length - i);
        Object obj = new char[i3];
        System.arraycopy(cArr, i, obj, 0, length);
        return obj;
    }

    public static double[] a(double[] dArr, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = dArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        length = Math.min(i3, length - i);
        Object obj = new double[i3];
        System.arraycopy(dArr, i, obj, 0, length);
        return obj;
    }

    public static float[] a(float[] fArr, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        length = Math.min(i3, length - i);
        Object obj = new float[i3];
        System.arraycopy(fArr, i, obj, 0, length);
        return obj;
    }

    public static int[] a(int[] iArr, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = iArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        length = Math.min(i3, length - i);
        Object obj = new int[i3];
        System.arraycopy(iArr, i, obj, 0, length);
        return obj;
    }

    public static long[] a(long[] jArr, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = jArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        length = Math.min(i3, length - i);
        Object obj = new long[i3];
        System.arraycopy(jArr, i, obj, 0, length);
        return obj;
    }

    public static short[] a(short[] sArr, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = sArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        length = Math.min(i3, length - i);
        Object obj = new short[i3];
        System.arraycopy(sArr, i, obj, 0, length);
        return obj;
    }

    public static <T> T[] a(T[] tArr, int i, int i2) {
        int length = tArr.length;
        if (i > i2) {
            throw new IllegalArgumentException();
        } else if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            int i3 = i2 - i;
            int min = Math.min(i3, length - i);
            Object[] objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i3);
            System.arraycopy(tArr, i, objArr, 0, min);
            return objArr;
        }
    }

    public static <T, U> T[] a(U[] uArr, int i, int i2, Class<? extends T[]> cls) {
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        int length = uArr.length;
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        int min = Math.min(i3, length - i);
        Object[] objArr = (Object[]) Array.newInstance(cls.getComponentType(), i3);
        System.arraycopy(uArr, i, objArr, 0, min);
        return objArr;
    }
}
