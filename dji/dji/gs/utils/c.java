package dji.gs.utils;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Region;
import android.location.Location;
import dji.gs.e.b;

public class c {
    private static int a = 1;

    public static float a(b bVar, b bVar2) {
        float[] fArr = new float[1];
        Location.distanceBetween(bVar.b, bVar.c, bVar2.b, bVar2.c, fArr);
        return fArr[0];
    }

    public static float[] b(b bVar, b bVar2) {
        float[] fArr = new float[3];
        Location.distanceBetween(bVar.b, bVar.c, bVar2.b, bVar2.c, fArr);
        return fArr;
    }

    public static String a(Context context, int i) {
        return context.getResources().getString(i);
    }

    public static String a(Context context, int i, Object... objArr) {
        return context.getResources().getString(i, objArr);
    }

    public static boolean a(double d, double d2) {
        if (d <= 21.7569d || d >= 25.942d || d2 <= 119.3d || d2 >= 124.58d) {
            return a((int) d, (int) d2);
        }
        return true;
    }

    private static float a(double d, double d2, double d3) {
        return (float) (((((d3 / 60.0d) + d2) / 60.0d) + d) * ((double) a));
    }

    public static boolean a(int i, int i2) {
        Path path = new Path();
        path.moveTo(a(48.0d, 58.0d, 42.64d), a(87.0d, 5.0d, 59.19d));
        path.lineTo(a(46.0d, 43.0d, 33.27d), a(85.0d, 25.0d, 26.56d));
        path.lineTo(a(47.0d, 0.0d, 18.85d), a(83.0d, 13.0d, 32.25d));
        path.lineTo(a(44.0d, 51.0d, 45.02d), a(79.0d, 52.0d, 21.83d));
        path.lineTo(a(42.0d, 6.0d, 38.75d), a(80.0d, 16.0d, 38.32d));
        path.lineTo(a(40.0d, 26.0d, 33.02d), a(74.0d, 52.0d, 43.66d));
        path.lineTo(a(38.0d, 46.0d, 42.06d), a(73.0d, 45.0d, 47.54d));
        path.lineTo(a(35.0d, 40.0d, 53.53d), a(77.0d, 17.0d, 50.18d));
        path.lineTo(a(35.0d, 18.0d, 19.61d), a(80.0d, 25.0d, 3.57d));
        path.lineTo(a(33.0d, 47.0d, 49.88d), a(79.0d, (double) dji.pilot2.multimoment.videolib.c.c, 29.33d));
        path.lineTo(a(31.0d, 30.0d, 12.49d), a(78.0d, 27.0d, 15.1d));
        path.lineTo(a(29.0d, 56.0d, 32.62d), a(81.0d, 15.0d, 44.46d));
        path.lineTo(a(27.0d, 11.0d, 56.65d), a(89.0d, 1.0d, 43.6d));
        path.lineTo(a(28.0d, 12.0d, 41.71d), a(97.0d, 27.0d, 43.41d));
        path.lineTo(a(25.0d, 53.0d, 39.08d), a(98.0d, 48.0d, 8.7d));
        path.lineTo(a(24.0d, 39.0d, 34.05d), a(97.0d, 28.0d, 21.2d));
        path.lineTo(a(23.0d, 45.0d, 59.97d), a(97.0d, 39.0d, 54.56d));
        path.lineTo(a(21.0d, 6.0d, 32.01d), a(101.0d, 21.0d, 31.23d));
        path.lineTo(a(22.0d, 39.0d, 1.59d), a(103.0d, 26.0d, (double) dji.pilot2.multimoment.videolib.c.c));
        path.lineTo(a(20.0d, 16.0d, 53.73d), a(107.0d, 49.0d, 47.06d));
        path.lineTo(a(15.0d, 47.0d, 6.87d), a(108.0d, 54.0d, 19.46d));
        path.lineTo(a(14.0d, 53.0d, 53.7d), a(114.0d, 38.0d, 35.08d));
        path.lineTo(a(21.0d, (double) dji.pilot2.multimoment.videolib.c.c, 46.9d), a(121.0d, 47.0d, 25.12d));
        path.lineTo(a(30.0d, (double) dji.pilot2.multimoment.videolib.c.c, 39.55d), a(125.0d, 46.0d, 41.78d));
        path.lineTo(a(39.0d, 29.0d, 30.38d), a(123.0d, 11.0d, 52.45d));
        path.lineTo(a(42.0d, 46.0d, 30.75d), a(131.0d, 10.0d, 38.6d));
        path.lineTo(a(48.0d, 15.0d, 27.43d), a(134.0d, 46.0d, 49.54d));
        path.lineTo(a(49.0d, 29.0d, 41.88d), a(127.0d, 50.0d, 21.5d));
        path.lineTo(a(53.0d, 6.0d, 13.32d), a(125.0d, 9.0d, 54.19d));
        path.lineTo(a(52.0d, 52.0d, 56.58d), a(119.0d, 52.0d, 42.41d));
        path.lineTo(a(48.0d, 5.0d, 29.2d), a(115.0d, 17.0d, 35.36d));
        path.lineTo(a(46.0d, 32.0d, 0.41d), a(119.0d, 44.0d, 29.7d));
        path.lineTo(a(44.0d, 36.0d, 38.94d), a(111.0d, 41.0d, 41.13d));
        path.lineTo(a(42.0d, 13.0d, 8.78d), a(107.0d, 12.0d, 24.35d));
        path.lineTo(a(42.0d, 43.0d, 53.7d), a(96.0d, 26.0d, 11.34d));
        path.lineTo(a(44.0d, 44.0d, 42.52d), a(93.0d, 47.0d, 30.02d));
        path.lineTo(a(45.0d, 18.0d, 42.86d), a(90.0d, 47.0d, 27.32d));
        path.lineTo(a(47.0d, 45.0d, 6.43d), a(90.0d, 10.0d, 4.97d));
        path.close();
        Region region = new Region();
        Region region2 = new Region();
        region2.set(a * 1, a * 73, a * 55, a * 138);
        region.setPath(path, region2);
        if (region.contains(i, i2)) {
            return false;
        }
        return true;
    }

    public static float a() {
        return 1.0f;
    }
}
