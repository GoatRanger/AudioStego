package dji.thirdparty.afinal.g;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import java.io.File;

public class c {
    private static final String a = "BitmapCommonUtils";
    private static final long b = -7661587058870466123L;
    private static final long c = -1;
    private static long[] d = new long[256];

    static {
        for (int i = 0; i < 256; i++) {
            long j = (long) i;
            for (int i2 = 0; i2 < 8; i2++) {
                j = (j >> 1) ^ ((((int) j) & 1) != 0 ? b : 0);
            }
            d[i] = j;
        }
    }

    public static File a(Context context, String str) {
        return new File(("mounted".equals(Environment.getExternalStorageState()) ? a(context).getPath() : context.getCacheDir().getPath()) + File.separator + str);
    }

    public static int a(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public static File a(Context context) {
        return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/cache/"));
    }

    public static long a(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Exception e) {
            Log.e(a, "获取 sdcard 缓存大小 出错，请查看AndroidManifest.xml 是否添加了sdcard的访问权限");
            e.printStackTrace();
            return -1;
        }
    }

    public static byte[] a(String str) {
        int i = 0;
        byte[] bArr = new byte[(str.length() * 2)];
        char[] toCharArray = str.toCharArray();
        int length = toCharArray.length;
        int i2 = 0;
        while (i < length) {
            char c = toCharArray[i];
            int i3 = i2 + 1;
            bArr[i2] = (byte) (c & 255);
            i2 = i3 + 1;
            bArr[i3] = (byte) (c >> 8);
            i++;
        }
        return bArr;
    }

    public static boolean a(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        if (bArr2.length < length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        int i3 = i2 - i;
        if (i3 < 0) {
            throw new IllegalArgumentException(i + " > " + i2);
        }
        Object obj = new byte[i3];
        System.arraycopy(bArr, i, obj, 0, Math.min(bArr.length - i, i3));
        return obj;
    }

    public static byte[] b(String str) {
        return a(str);
    }

    public static final long c(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return a(a(str));
    }

    public static final long a(byte[] bArr) {
        long j = -1;
        for (byte b : bArr) {
            j = (j >> 8) ^ d[(((int) j) ^ b) & 255];
        }
        return j;
    }
}
