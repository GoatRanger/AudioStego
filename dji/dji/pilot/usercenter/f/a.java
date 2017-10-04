package dji.pilot.usercenter.f;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import dji.log.DJILogHelper;
import dji.pilot.visual.a.d;
import dji.pilot2.explore.fragment.DJISupportShareWebviewFragment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class a {
    public static Bitmap a(String str, int i, int i2, String str2, boolean z) {
        int i3 = 1;
        Bitmap bitmap = null;
        if (c.a(str)) {
            try {
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(str, options);
                int i4 = options.outWidth;
                int i5 = options.outHeight;
                options.inJustDecodeBounds = false;
                if (i4 > i) {
                    i3 = i4 / i;
                }
                options.inSampleSize = i3;
                bitmap = BitmapFactory.decodeFile(str, options);
                File file = new File(str2);
                c.c(file);
                bitmap.compress(CompressFormat.PNG, 80, new FileOutputStream(file));
                if (z && !bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            } catch (Exception e) {
            }
        }
        return bitmap;
    }

    public static void a(Context context, Uri uri, int i, int i2, String str, boolean z) {
        Bitmap decodeStream;
        FileNotFoundException e;
        File file;
        int i3 = 1;
        OutputStream outputStream = null;
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            decodeStream = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
            try {
                int i4 = options.outWidth;
                options.inJustDecodeBounds = false;
                if (i4 > i) {
                    i3 = i4 / i;
                }
                options.inSampleSize = i3;
                decodeStream = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
            } catch (FileNotFoundException e2) {
                e = e2;
                e.printStackTrace();
                if (decodeStream == null) {
                    file = new File(str);
                    c.c(file);
                    try {
                        outputStream = new FileOutputStream(file);
                    } catch (FileNotFoundException e3) {
                        e3.printStackTrace();
                    }
                    if (outputStream == null) {
                        decodeStream.compress(CompressFormat.PNG, 80, outputStream);
                        if (z) {
                        }
                        return;
                    }
                }
                DJILogHelper.getInstance().LOGI(DJISupportShareWebviewFragment.T, "set avatar fail");
                return;
            }
        } catch (FileNotFoundException e4) {
            e3 = e4;
            decodeStream = null;
            e3.printStackTrace();
            if (decodeStream == null) {
                DJILogHelper.getInstance().LOGI(DJISupportShareWebviewFragment.T, "set avatar fail");
                return;
            }
            file = new File(str);
            c.c(file);
            outputStream = new FileOutputStream(file);
            if (outputStream == null) {
                decodeStream.compress(CompressFormat.PNG, 80, outputStream);
                if (z) {
                    return;
                }
            }
        }
        if (decodeStream == null) {
            DJILogHelper.getInstance().LOGI(DJISupportShareWebviewFragment.T, "set avatar fail");
            return;
        }
        file = new File(str);
        c.c(file);
        outputStream = new FileOutputStream(file);
        if (outputStream == null) {
            decodeStream.compress(CompressFormat.PNG, 80, outputStream);
            if (z && !decodeStream.isRecycled()) {
                decodeStream.recycle();
            }
        }
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2, int i3) {
        float f = 0.0f;
        if (bitmap == null || i == 0 || i2 == 0 || i3 == 0) {
            return null;
        }
        try {
            float f2;
            float f3;
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            canvas.drawARGB(0, 0, 0, 0);
            paint.setAntiAlias(true);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width * i2 > i * height) {
                f2 = ((float) i2) / ((float) height);
                f3 = (((float) i) - (((float) width) * f2)) * d.c;
            } else {
                f2 = ((float) i) / ((float) width);
                f3 = 0.0f;
                f = (((float) i2) - (((float) height) * f2)) * d.c;
            }
            Matrix matrix = new Matrix();
            matrix.setScale(f2, f2);
            matrix.postTranslate((float) ((int) (f3 + d.c)), (float) ((int) (f + d.c)));
            canvas.drawRoundRect(new RectF(new Rect(0, 0, i, i2)), (float) i3, (float) i3, paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, matrix, paint);
            return createBitmap;
        } catch (Exception e) {
            return null;
        }
    }

    public static void a(Bitmap bitmap, String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            c.c(file);
            OutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(CompressFormat.PNG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
