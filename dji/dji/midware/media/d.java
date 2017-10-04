package dji.midware.media;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.util.Log;
import com.dji.frame.c.b;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.media.e.e;
import dji.midware.natives.FPVController;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

public class d {
    private static final long A = 1048575;
    private static final long B = 16777215;
    private static final long C = 60;
    private static final long D = 63;
    private static final int E = 6;
    private static final long F = System.currentTimeMillis();
    private static Context G = null;
    private static final int H = 1048576;
    private static final String I = "crop-left";
    private static final String J = "crop-right";
    private static final String K = "crop-bottom";
    private static final String L = "crop-top";
    public static boolean a = false;
    public static boolean b = false;
    public static final String[] c = new String[]{"video/avc", "video/mp4v-es"};
    public static final String[] d = new String[]{"audio/mp4a-latm", "audio/aac", "audio/mpeg"};
    public static final int e = 18;
    public static final int f = 18;
    public static final int g = 120;
    public static final int h = 150;
    public static final int i = 1;
    public static boolean j = true;
    public static boolean k = false;
    public static boolean l = false;
    public static boolean m = false;
    public static int n = MediaHttpUploader.DEFAULT_CHUNK_SIZE;
    public static int o = 1000;
    public static final int p = 31;
    public static final int q = 8294400;
    public static final int r = 138240;
    public static final int s = 1280;
    public static final int t = 720;
    public static final int u = 1280;
    public static final int v = 720;
    public static final int w = 30;
    public static long x = 0;
    public static long y = 0;
    private static String z = "DJIVideoUtil";

    public static class a {
        public int a;
        public int b;
    }

    public static long a(long j, long j2, long j3) {
        if (j2 > A) {
            j2 = A;
        }
        long j4 = (j - F) & B;
        if (j3 > C) {
            j3 = C;
        }
        return ((j4 + (j2 << 24)) << 6) + j3;
    }

    public static long a(long j) {
        return ((268435440 & j) >>> 6) + F;
    }

    public static int b(long j) {
        return (int) ((281474708275200L & j) >>> 30);
    }

    public static int c(long j) {
        return (int) (D & j);
    }

    public static int a(int i, int i2, int i3) {
        return 1258300;
    }

    public static int a() {
        return VERSION.SDK_INT;
    }

    public static String a(String str) {
        if (f() == null) {
            return null;
        }
        return com.dji.frame.c.d.a(f(), str);
    }

    public static String b() {
        return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
    }

    public static int c() {
        return 30;
    }

    public static double d() {
        return 33.33333d;
    }

    public static long e() {
        return 33333;
    }

    public static long a(int i) {
        return e() * ((long) i);
    }

    public static boolean b(String str) {
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        boolean mkdirs = file.mkdirs();
        if (mkdirs) {
            return mkdirs;
        }
        e.a("can't create the directory " + str);
        return mkdirs;
    }

    public static void a(Context context) {
        if (ServiceManager.getInstance() == null) {
            G = context;
        }
    }

    public static Context f() {
        ServiceManager.getInstance();
        Context context = ServiceManager.getContext();
        return context != null ? context : G;
    }

    public static boolean g() {
        return a(true);
    }

    public static boolean a(boolean z) {
        Context f = f();
        if (f != null && !b.c(f)) {
            return false;
        }
        if (l) {
            return false;
        }
        if (m) {
            return true;
        }
        return z;
    }

    public static boolean c(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (file == null) {
            return false;
        }
        String name = file.getName();
        if (name == null) {
            return false;
        }
        name = name.toLowerCase(Locale.US);
        if (name.startsWith("org_") || name.startsWith("dji_")) {
            return true;
        }
        return false;
    }

    @TargetApi(18)
    public static int a(MediaCodec mediaCodec) {
        int i = 19;
        int[] iArr = mediaCodec.getCodecInfo().getCapabilitiesForType(c[0]).colorFormats;
        Arrays.sort(iArr);
        e.a(mediaCodec.getName() + " supports the colors: " + Arrays.toString(iArr));
        if (Arrays.binarySearch(iArr, 19) < 0) {
            if (Arrays.binarySearch(iArr, 21) >= 0) {
                i = 21;
            } else {
                i = iArr[0];
            }
        }
        Log.i(z, mediaCodec.getName() + "'s best color is: " + i);
        return i;
    }

    @TargetApi(18)
    public static int a(MediaCodec mediaCodec, MediaCodec mediaCodec2) {
        int i = 21;
        int[] iArr = mediaCodec.getCodecInfo().getCapabilitiesForType(c[0]).colorFormats;
        Log.i(z, mediaCodec.getName() + " supports the colors: " + Arrays.toString(iArr));
        HashSet hashSet = new HashSet();
        for (int valueOf : iArr) {
            hashSet.add(Integer.valueOf(valueOf));
        }
        iArr = mediaCodec2.getCodecInfo().getCapabilitiesForType(c[0]).colorFormats;
        Log.i(z, mediaCodec2.getName() + " supports the colors: " + Arrays.toString(iArr));
        Collection hashSet2 = new HashSet();
        for (int valueOf2 : iArr) {
            hashSet2.add(Integer.valueOf(valueOf2));
        }
        if (!(hashSet.contains(Integer.valueOf(21)) && hashSet2.contains(Integer.valueOf(21)))) {
            if (hashSet.contains(Integer.valueOf(19)) && hashSet2.contains(Integer.valueOf(19))) {
                i = 19;
            } else {
                hashSet.retainAll(hashSet2);
                i = !hashSet.isEmpty() ? ((Integer) hashSet.toArray()[0]).intValue() : -1;
            }
        }
        Log.i(z, "Found common color: " + i);
        return i;
    }

    public static void a(ByteBuffer byteBuffer, int i, int i2, String str) {
        byte[] bArr = new byte[i2];
        try {
            OutputStream fileOutputStream = new FileOutputStream(e.a() + str);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byteBuffer.position(i);
            byteBuffer.limit(i + i2);
            byteBuffer.get(bArr, 0, i2);
            byteBuffer.clear();
            bufferedOutputStream.write(bArr, 0, i2);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(z, str + " saved done");
    }

    public static int[] h() {
        return new int[]{19, 21, 2141391876, 2141391872};
    }

    public static void a(byte[] bArr, byte[] bArr2, int i, int i2) {
        a(bArr, 0, bArr2, 0, i, i2, 19);
    }

    public static a a(MediaFormat mediaFormat) {
        int integer;
        a aVar = new a();
        Object obj = (mediaFormat.containsKey(J) && mediaFormat.containsKey(I) && mediaFormat.containsKey(K) && mediaFormat.containsKey(L)) ? 1 : null;
        if (obj != null) {
            integer = (mediaFormat.getInteger(J) - mediaFormat.getInteger(I)) + 1;
        } else {
            integer = mediaFormat.getInteger("width");
        }
        aVar.a = integer;
        if (obj != null) {
            integer = (mediaFormat.getInteger(K) - mediaFormat.getInteger(L)) + 1;
        } else {
            integer = mediaFormat.getInteger("height");
        }
        aVar.b = integer;
        return aVar;
    }

    public static void a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, int i3) {
        long currentTimeMillis = System.currentTimeMillis();
        FPVController.native_transcodeYUVConvert(byteBuffer, byteBuffer2, i, i2, i3);
        x = (System.currentTimeMillis() - currentTimeMillis) + x;
        y++;
    }

    public static void a(byte[] bArr, int i, byte[] bArr2, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        switch (i5) {
            case 21:
                i6 = i2 + (i3 * i4);
                i7 = i6 + 1;
                break;
            case 2141391872:
            case 2141391876:
                i6 = ((i3 * i4) + i2) + 20480;
                i7 = i6 + 1;
                break;
            default:
                i6 = i2 + (i3 * i4);
                i7 = ((i3 * i4) / 4) + i6;
                break;
        }
        int i8 = 0;
        int i9 = i7;
        int i10 = i2;
        int i11 = i6;
        i6 = i;
        while (i8 < i4) {
            i7 = i6;
            i6 = i11;
            i11 = i10;
            i10 = 0;
            while (i10 < i3) {
                int i12 = i11 + 1;
                int i13 = i7 + 1;
                bArr2[i11] = bArr[i7];
                if (i8 % 2 == 0 && i10 % 2 == 0) {
                    int i14;
                    switch (i5) {
                        case 21:
                        case 2141391872:
                        case 2141391876:
                            i14 = i13 + 1;
                            bArr2[i6] = bArr[i13];
                            i11 = i6 + 2;
                            i7 = i14 + 1;
                            bArr2[i9] = bArr[i14];
                            i6 = i9 + 2;
                            break;
                        default:
                            i11 = i6 + 1;
                            i14 = i13 + 1;
                            bArr2[i6] = bArr[i13];
                            i6 = i9 + 1;
                            i7 = i14 + 1;
                            bArr2[i9] = bArr[i14];
                            break;
                    }
                    i7++;
                } else {
                    i7 = i13 + 3;
                    i11 = i6;
                    i6 = i9;
                }
                i10++;
                i9 = i6;
                i6 = i11;
                i11 = i12;
            }
            i8++;
            i10 = i11;
            i11 = i6;
            i6 = i7;
        }
    }
}
