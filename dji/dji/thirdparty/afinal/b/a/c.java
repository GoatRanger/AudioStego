package dji.thirdparty.afinal.b.a;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import java.io.FileDescriptor;

public class c {
    private c() {
    }

    public static Bitmap a(Resources resources, int i, int i2, int i3) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = a(options, i2, i3);
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeResource(resources, i, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap a(FileDescriptor fileDescriptor, int i, int i2) {
        Bitmap bitmap = null;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, bitmap, options);
        options.inSampleSize = a(options, i, i2);
        options.inJustDecodeBounds = false;
        try {
            bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap a(byte[] bArr, int i, int i2, int i3, int i4) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        BitmapFactory.decodeByteArray(bArr, i, i2, options);
        options.inSampleSize = a(options, i3, i4);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bArr, i, i2, options);
    }

    private static int a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            if (i4 > i3) {
                i5 = Math.round(((float) i3) / ((float) i2));
            } else {
                i5 = Math.round(((float) i4) / ((float) i));
            }
            float f = (float) (i3 * i4);
            while (f / ((float) (i5 * i5)) > ((float) ((i * i2) * 2))) {
                i5++;
            }
        }
        return i5;
    }
}
