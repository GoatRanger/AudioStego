package dji.pilot2.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import dji.log.DJILogHelper;
import dji.sdksharedlib.b.b;

public class a {
    public static int a(Options options, int i, int i2) {
        int b = b(options, i, i2);
        if (b > 8) {
            return ((b + 7) / 8) * 8;
        }
        int i3 = 1;
        while (i3 < b) {
            i3 <<= 1;
        }
        return i3;
    }

    private static int b(Options options, int i, int i2) {
        double d = (double) options.outWidth;
        double d2 = (double) options.outHeight;
        int ceil = i2 == -1 ? 1 : (int) Math.ceil(Math.sqrt((d * d2) / ((double) i2)));
        int min = i == -1 ? 128 : (int) Math.min(Math.floor(d / ((double) i)), Math.floor(d2 / ((double) i)));
        if (min < ceil) {
            return ceil;
        }
        if (i2 == -1 && i == -1) {
            return 1;
        }
        if (i != -1) {
            return min;
        }
        return ceil;
    }

    public static Bitmap a(String str, int i) {
        Bitmap bitmap = null;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Config.RGB_565;
        BitmapFactory.decodeFile(str, options);
        if (i <= 0) {
            options.inSampleSize = a(options, -1, options.outWidth * options.outHeight);
        } else {
            options.inSampleSize = a(options, -1, i);
        }
        if (options.inSampleSize > 4) {
            options.inSampleSize = 4;
        }
        options.inJustDecodeBounds = false;
        try {
            bitmap = BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError e) {
        }
        return bitmap;
    }

    public static Bitmap a(Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    public static Options a(String str) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return options;
    }

    public static Bitmap a(Bitmap bitmap, ExifInterface exifInterface) {
        int i;
        switch (exifInterface.getAttributeInt(b.bW, 0)) {
            case 0:
                DJILogHelper.getInstance().LOGD("rxq", "ORIENTATION_UNDEFINED");
                i = 0;
                break;
            case 1:
                DJILogHelper.getInstance().LOGD("rxq", "ORIENTATION_NORMAL");
                i = 0;
                break;
            case 3:
                DJILogHelper.getInstance().LOGD("rxq", "ORIENTATION_ROTATE_180");
                i = 180;
                break;
            case 6:
                DJILogHelper.getInstance().LOGD("rxq", "ORIENTATION_ROTATE_90");
                i = 90;
                break;
            case 8:
                DJILogHelper.getInstance().LOGD("rxq", "ORIENTATION_ROTATE_270");
                i = 270;
                break;
            default:
                i = 0;
                break;
        }
        if (i == 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate((float) i);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (createBitmap != null) {
            return createBitmap;
        }
        return bitmap;
    }
}
