package dji.pilot2.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import android.widget.RelativeLayout;
import dji.log.DJILogHelper;
import dji.pilot2.media.c;
import dji.publics.b.a.b;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class q {
    public static final String a = "VideoEditorThumbnailsUtils";
    public static final int b = 1000;
    public static final int c = 8;
    public static AtomicInteger d = new AtomicInteger(0);
    public static boolean e = false;
    public static int f = 6;
    private static int g = 100;
    private static int h = 80;
    private static ArrayList<ArrayList<Bitmap>> i = new ArrayList();

    public static void a(int i, int i2) {
        g = i;
        h = i2;
    }

    public static int a() {
        return g;
    }

    public static int b() {
        return h;
    }

    public static Bitmap a(String str) {
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            int intValue = Integer.valueOf(mediaMetadataRetriever.extractMetadata(9)).intValue() / 1000;
            Bitmap a = a(d(str), g, h);
            Bitmap createBitmap = Bitmap.createBitmap(g * intValue, h, a.getConfig());
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(a, new Matrix(), null);
            for (int i = 1; i <= intValue; i++) {
                canvas.drawBitmap(a(mediaMetadataRetriever.getFrameAtTime((long) ((i * 1000) * 1000), 2), g, h), (float) (g * i), 0.0f, null);
            }
            canvas.save(31);
            canvas.restore();
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Bitmap> b(String str) {
        int i = 0;
        o.a(b.m + str);
        ArrayList<Bitmap> arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            int i2;
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            String extractMetadata = mediaMetadataRetriever.extractMetadata(9);
            mediaMetadataRetriever.release();
            int intValue = Integer.valueOf(extractMetadata).intValue() / 1000;
            o.a("wbok");
            d.set(0);
            for (i2 = 0; i2 != i.a; i2++) {
                i.add(new ArrayList());
                new Thread(new i(i2, intValue, str, (ArrayList) i.get(i2)), a).start();
            }
            while (d.get() != i.a) {
                Thread.sleep(200);
            }
            for (intValue = 0; intValue != i.a; intValue++) {
                int size = ((ArrayList) i.get(intValue)).size();
                for (i2 = 0; i2 != size; i2++) {
                    arrayList.add(((ArrayList) i.get(intValue)).get(i2));
                }
            }
            i2 = i.size();
            while (i < i2) {
                ArrayList arrayList2 = (ArrayList) i.get(i);
                if (arrayList2 != null) {
                    arrayList2.clear();
                }
                i++;
            }
            i.clear();
            System.gc();
            o.a(b.m + (System.currentTimeMillis() - currentTimeMillis));
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Bitmap> c(String str) {
        ArrayList<Bitmap> arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            int i;
            c cVar = new c();
            cVar.a(str);
            int a = cVar.a() / 1000;
            d.set(0);
            for (i = 0; i != i.a; i++) {
                i.add(new ArrayList());
                new Thread(new j(i, a, str, (ArrayList) i.get(i)), a).start();
            }
            while (d.get() != j.b) {
                Thread.sleep(100);
            }
            for (a = 0; a != j.b; a++) {
                int size = ((ArrayList) i.get(a)).size();
                ArrayList arrayList2 = (ArrayList) i.get(a);
                for (i = 0; i != size; i++) {
                    arrayList.add(arrayList2.get(i));
                }
                arrayList2.clear();
            }
            i.clear();
            System.gc();
            o.a(b.m + (System.currentTimeMillis() - currentTimeMillis));
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Bitmap> a(Context context, String str, ArrayList<ArrayList<Integer>> arrayList, RelativeLayout relativeLayout) {
        ArrayList<Bitmap> arrayList2 = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            d.set(0);
            e = false;
            int size = arrayList.size();
            for (int i = 0; i != size; i++) {
                new Thread(new j(context, i, str, (ArrayList) arrayList.get(i), relativeLayout), a).start();
            }
            int i2 = d.get();
            while (i2 != size) {
                i2 = d.get();
                DJILogHelper.getInstance().LOGD(a, "thread has finished: " + i2 + ", total: " + size);
                Thread.sleep(100);
            }
            o.a(b.m + (System.currentTimeMillis() - currentTimeMillis));
            return arrayList2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap d(java.lang.String r6) {
        /*
        r0 = 0;
        r1 = 1;
        r1 = android.media.ThumbnailUtils.createVideoThumbnail(r6, r1);	 Catch:{ Exception -> 0x0025, OutOfMemoryError -> 0x002a }
        if (r1 != 0) goto L_0x001c;
    L_0x0008:
        r2 = new dji.pilot2.media.c;	 Catch:{ Exception -> 0x0025, OutOfMemoryError -> 0x002c }
        r2.<init>();	 Catch:{ Exception -> 0x0025, OutOfMemoryError -> 0x002c }
        r2.a(r6);	 Catch:{ Exception -> 0x0025, OutOfMemoryError -> 0x002c }
        r3 = r2.a();	 Catch:{ Exception -> 0x0025, OutOfMemoryError -> 0x002c }
        r4 = (long) r3;	 Catch:{ Exception -> 0x0025, OutOfMemoryError -> 0x002c }
        r4 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r3 = 2;
        r1 = r2.a(r4, r3);	 Catch:{ Exception -> 0x0025, OutOfMemoryError -> 0x002c }
    L_0x001c:
        r2 = 160; // 0xa0 float:2.24E-43 double:7.9E-322;
        r3 = 90;
        r0 = dji.pilot2.utils.p.a(r1, r2, r3);	 Catch:{ Exception -> 0x0025, OutOfMemoryError -> 0x002c }
    L_0x0024:
        return r0;
    L_0x0025:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0024;
    L_0x002a:
        r1 = move-exception;
        goto L_0x0024;
    L_0x002c:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot2.utils.q.d(java.lang.String):android.graphics.Bitmap");
    }

    private static Bitmap a(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) width), ((float) i2) / ((float) height));
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap a(String str, int i) {
        Throwable th;
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(str);
                int intValue = Integer.valueOf(mediaMetadataRetriever.extractMetadata(9)).intValue();
                if (i <= 0) {
                    i = 1;
                } else if (i > intValue) {
                    i = intValue;
                }
                Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime((long) (i * 1000), 2);
                if (frameAtTime == null) {
                    frameAtTime = mediaMetadataRetriever.getFrameAtTime();
                }
                bitmap = p.a(frameAtTime, 160, 90);
                if (mediaMetadataRetriever != null) {
                    try {
                        mediaMetadataRetriever.release();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                if (mediaMetadataRetriever != null) {
                    try {
                        mediaMetadataRetriever.release();
                    } catch (Exception e3) {
                    }
                }
                return bitmap;
            } catch (OutOfMemoryError e4) {
                if (mediaMetadataRetriever != null) {
                    try {
                        mediaMetadataRetriever.release();
                    } catch (Exception e5) {
                    }
                }
                return bitmap;
            } catch (Throwable th2) {
                th = th2;
                if (mediaMetadataRetriever != null) {
                    try {
                        mediaMetadataRetriever.release();
                    } catch (Exception e6) {
                    }
                }
                throw th;
            }
        } catch (Exception e7) {
            mediaMetadataRetriever = null;
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
            return bitmap;
        } catch (OutOfMemoryError e8) {
            mediaMetadataRetriever = null;
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
            return bitmap;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            mediaMetadataRetriever = null;
            th = th4;
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
            throw th;
        }
        return bitmap;
    }

    public static Bitmap a(String str, int i, int i2) {
        Exception exception;
        Bitmap bitmap;
        Exception exception2;
        try {
            int i3;
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            int intValue = Integer.valueOf(mediaMetadataRetriever.extractMetadata(9)).intValue() / 1000;
            if (i <= 0) {
                i3 = 1;
            } else {
                i3 = i;
            }
            Bitmap a = a(d(str), g, h);
            Bitmap createBitmap = Bitmap.createBitmap(g * i2, h, a.getConfig());
            try {
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawBitmap(a, new Matrix(), null);
                if (i2 > intValue) {
                    i2 = intValue;
                }
                for (intValue = i3; intValue <= i2; intValue++) {
                    canvas.drawBitmap(a(mediaMetadataRetriever.getFrameAtTime((long) ((intValue * 1000) * 1000), 2), g, h), (float) (g * intValue), 0.0f, null);
                }
                canvas.save(31);
                canvas.restore();
                return createBitmap;
            } catch (Exception e) {
                exception = e;
                bitmap = createBitmap;
                exception2 = exception;
                exception2.printStackTrace();
                return bitmap;
            }
        } catch (Exception e2) {
            exception = e2;
            bitmap = null;
            exception2 = exception;
            exception2.printStackTrace();
            return bitmap;
        }
    }

    public static Bitmap a(Bitmap bitmap, Bitmap bitmap2) {
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        Bitmap createBitmap = Bitmap.createBitmap(bitmap2.getWidth() + width, height + bitmap2.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
        canvas.drawBitmap(bitmap2, (float) width, 0.0f, null);
        canvas.save(31);
        canvas.restore();
        return createBitmap;
    }

    public static Bitmap a(String str, int i, int i2, int i3, int i4) {
        Bitmap createBitmap;
        Exception e;
        try {
            Bitmap a;
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            int intValue = Integer.valueOf(mediaMetadataRetriever.extractMetadata(9)).intValue() / 1000;
            if (i <= 0) {
                a = a(d(str), g, h);
                createBitmap = Bitmap.createBitmap(g * i4, h, a.getConfig());
            } else {
                a = a(d(str), g, h);
                createBitmap = Bitmap.createBitmap(g * i4, h, a.getConfig());
            }
            try {
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawBitmap(a, new Matrix(), null);
                int i5 = i2 > intValue ? 1 : 1;
                for (i5 = 1; i5 < i4; i5++) {
                    canvas.drawBitmap(a(mediaMetadataRetriever.getFrameAtTime((long) (((i5 * i3) * 1000) * 1000), 2), g, h), (float) (g * i5), 0.0f, null);
                }
                canvas.save(31);
                canvas.restore();
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return createBitmap;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            createBitmap = null;
            e = exception;
            e.printStackTrace();
            return createBitmap;
        }
        return createBitmap;
    }
}
