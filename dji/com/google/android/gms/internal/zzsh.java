package com.google.android.gms.internal;

import java.io.IOException;

public final class zzsh {
    public static final double[] zzbiA = new double[0];
    public static final boolean[] zzbiB = new boolean[0];
    public static final String[] zzbiC = new String[0];
    public static final byte[][] zzbiD = new byte[0][];
    public static final byte[] zzbiE = new byte[0];
    public static final int[] zzbix = new int[0];
    public static final long[] zzbiy = new long[0];
    public static final float[] zzbiz = new float[0];

    static int zzD(int i, int i2) {
        return (i << 3) | i2;
    }

    public static boolean zzb(zzrw com_google_android_gms_internal_zzrw, int i) throws IOException {
        return com_google_android_gms_internal_zzrw.zzlA(i);
    }

    public static final int zzc(zzrw com_google_android_gms_internal_zzrw, int i) throws IOException {
        int i2 = 1;
        int position = com_google_android_gms_internal_zzrw.getPosition();
        com_google_android_gms_internal_zzrw.zzlA(i);
        while (com_google_android_gms_internal_zzrw.zzFo() == i) {
            com_google_android_gms_internal_zzrw.zzlA(i);
            i2++;
        }
        com_google_android_gms_internal_zzrw.zzlE(position);
        return i2;
    }

    static int zzlU(int i) {
        return i & 7;
    }

    public static int zzlV(int i) {
        return i >>> 3;
    }
}
