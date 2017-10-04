package com.nokia.maps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class bl {
    private static final String[] a = new String[]{"night", "hybrid", "maneuver", "satellite"};

    public static final String a(String str, int i) {
        int i2;
        for (String indexOf : a) {
            if (str.indexOf(indexOf) >= 0) {
                i2 = 1;
                break;
            }
        }
        i2 = 0;
        String str2 = "96px";
        if (i <= 160) {
            str2 = "32px";
        } else if (i <= 240) {
            str2 = "48px";
        } else if (i <= 320) {
            str2 = "64px";
        }
        String str3 = "%s%s%s.png";
        Object[] objArr = new Object[3];
        objArr[0] = "./res/images/logo/";
        objArr[1] = i2 != 0 ? "light/" : "dark/";
        objArr[2] = str2;
        bj.a(bl.class.getSimpleName(), "returns %s", new Object[]{String.format(str3, objArr)});
        return String.format(str3, objArr);
    }

    public static void a(Bitmap bitmap, String str, int i, int i2) {
        byte[] a = ResourceManager.a(MapsEngine.e(), a(str, 320));
        if (a.length > 0) {
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(a, 0, a.length);
            int width = decodeByteArray.getWidth() >> 1;
            new Canvas(bitmap).drawBitmap(decodeByteArray, (float) ((i >> 1) - width), (float) ((i2 - decodeByteArray.getHeight()) - width), new Paint());
        }
    }

    public static void b(Bitmap bitmap, String str, int i, int i2) {
        byte[] a = ResourceManager.a(MapsEngine.e(), a(str, 320));
        if (a.length > 0) {
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(a, 0, a.length);
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setTextSize(20.0f);
            paint.setColor(-1);
            int width = decodeByteArray.getWidth() >> 1;
            float f = ((float) width) / 3.0f;
            float height = (float) ((((i2 - decodeByteArray.getHeight()) - width) - 25) - 10);
            canvas.drawText(" © 2015 HERE ", 0.0f, (float) ((i2 - 25) - 10), paint);
            canvas.drawBitmap(decodeByteArray, f, height, new Paint());
        }
    }
}
