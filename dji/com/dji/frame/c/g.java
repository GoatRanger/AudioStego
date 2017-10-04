package com.dji.frame.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import java.io.IOException;
import java.io.InputStream;

public class g {
    public static Bitmap a(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = ((float) i) / ((float) width);
        float f2 = ((float) i2) / ((float) height);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap a(Context context, int i) {
        return BitmapFactory.decodeResource(context.getResources(), i);
    }

    public static Bitmap a(Context context, String str) {
        Bitmap decodeStream;
        IOException e;
        try {
            InputStream open = context.getResources().getAssets().open(str);
            decodeStream = BitmapFactory.decodeStream(open);
            try {
                open.close();
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                return decodeStream;
            }
        } catch (IOException e3) {
            IOException iOException = e3;
            decodeStream = null;
            e = iOException;
            e.printStackTrace();
            return decodeStream;
        }
        return decodeStream;
    }

    public static int a(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}
