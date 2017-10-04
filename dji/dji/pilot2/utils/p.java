package dji.pilot2.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import com.dji.frame.c.f;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.fpv.d.e;
import dji.pilot2.b;
import dji.pilot2.publics.b.a$e;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public class p {
    public static int a(int i) {
        return (i % 60000) / 1000;
    }

    public static int b(int i) {
        return (i % 60000) / 60000;
    }

    public static int c(int i) {
        return (i % 60000) / 3600000;
    }

    public static String d(int i) {
        int i2 = i % 60;
        int i3 = i / 60;
        int i4 = i3 % 60;
        if (i3 / 60 > 0) {
            return String.format(Locale.US, "%dh:%02d:%02d", new Object[]{Integer.valueOf(i3 / 60), Integer.valueOf(i4), Integer.valueOf(i2)});
        } else if (i4 > 0) {
            return String.format(Locale.US, "%02d:%02d", new Object[]{Integer.valueOf(i4), Integer.valueOf(i2)});
        } else {
            return String.format(Locale.US, "0:%02d", new Object[]{Integer.valueOf(i2)});
        }
    }

    public static String e(int i) {
        int i2 = i % 60;
        int i3 = i / 60;
        int i4 = i3 % 60;
        if (i3 / 60 > 0) {
            return String.format(Locale.US, "%dh%02d'%02d\"", new Object[]{Integer.valueOf(i3 / 60), Integer.valueOf(i4), Integer.valueOf(i2)});
        } else if (i4 > 0) {
            return String.format(Locale.US, "%d'%02d\"", new Object[]{Integer.valueOf(i4), Integer.valueOf(i2)});
        } else {
            return String.format(Locale.US, "%d\"", new Object[]{Integer.valueOf(i2)});
        }
    }

    public static String f(int i) {
        int i2 = i % 60;
        int i3 = i / 60;
        int i4 = i3 % 60;
        if (i3 / 60 > 0) {
            return String.format(Locale.US, "%d:%02d:%02d", new Object[]{Integer.valueOf(i3 / 60), Integer.valueOf(i4), Integer.valueOf(i2)});
        } else if (i4 > 0) {
            return String.format(Locale.US, "%d:%02d", new Object[]{Integer.valueOf(i4), Integer.valueOf(i2)});
        } else {
            return String.format(Locale.US, "0:%02d", new Object[]{Integer.valueOf(i2)});
        }
    }

    public static String g(int i) {
        int i2 = i % 60;
        int i3 = i / 60;
        int i4 = i3 % 60;
        i3 /= 60;
        return String.format(Locale.US, "%02d:%02d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i2)});
    }

    public static int a(String str) {
        Throwable th;
        int i = 0;
        MediaMetadataRetriever mediaMetadataRetriever;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(str);
                i = Integer.valueOf(mediaMetadataRetriever.extractMetadata(9)).intValue() / 1000;
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
                return i;
            } catch (Throwable th2) {
                th = th2;
                if (mediaMetadataRetriever != null) {
                    try {
                        mediaMetadataRetriever.release();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            mediaMetadataRetriever = null;
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
            return i;
        } catch (Throwable th3) {
            th = th3;
            mediaMetadataRetriever = null;
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
            throw th;
        }
        return i;
    }

    public static void a(String str, String str2) {
        DJILogHelper.getInstance().LOGD("", "==== Share Report ====", false, true);
        if (str != null && str2 != null) {
            String g = g(a(str));
            HashMap hashMap = new HashMap();
            hashMap.put("VideoLength", g);
            e.a(str2, hashMap);
        }
    }

    public static double a(double d, double d2, double d3, double d4) {
        double abs = Math.abs(d - d3);
        double abs2 = Math.abs(d2 - d4);
        return (double) ((float) Math.sqrt((abs * abs) + (abs2 * abs2)));
    }

    public static ArrayList<String> a() {
        ArrayList<String> arrayList = new ArrayList();
        Cursor query = b.a.a().getContentResolver().query(Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "title"}, null, null, null);
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("_data"));
            o.a("filePath=" + string + " fileTitle=" + query.getString(query.getColumnIndex("title")));
            arrayList.add(string);
        }
        o.a("==============imageDatas size:" + arrayList.size());
        return arrayList;
    }

    public static Bitmap b(String str) {
        try {
            return BitmapFactory.decodeStream(new FileInputStream(str));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2) {
        OutOfMemoryError outOfMemoryError;
        Bitmap createBitmap;
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            float f = ((float) i) / ((float) width);
            float f2 = ((float) i2) / ((float) height);
            if (f <= f2) {
                f = f2;
            }
            matrix.postScale(f, f);
            createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            int[] iArr = new int[(i * i2)];
            createBitmap.getPixels(iArr, 0, i, 0, 0, i, i2);
            Bitmap createBitmap2 = Bitmap.createBitmap(iArr, i, i2, Config.RGB_565);
            if (bitmap != null) {
                try {
                    if (!bitmap.isRecycled()) {
                        bitmap.recycle();
                    }
                } catch (OutOfMemoryError e) {
                    OutOfMemoryError outOfMemoryError2 = e;
                    createBitmap = createBitmap2;
                    outOfMemoryError = outOfMemoryError2;
                    outOfMemoryError.printStackTrace();
                    return createBitmap;
                }
            }
            if (!(createBitmap == null || createBitmap.isRecycled())) {
                createBitmap.recycle();
            }
            return createBitmap2;
        } catch (OutOfMemoryError e2) {
            outOfMemoryError = e2;
            createBitmap = null;
            outOfMemoryError.printStackTrace();
            return createBitmap;
        }
    }

    public static Bitmap a(Bitmap[] bitmapArr, int i, int i2, boolean z, int i3) {
        Exception exception;
        OutOfMemoryError outOfMemoryError;
        Bitmap bitmap = null;
        if (bitmapArr == null || bitmapArr.length <= 0) {
            return null;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.RGB_565);
            try {
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawARGB(0, 0, 0, 0);
                int i4 = 0;
                int length = bitmapArr.length;
                int i5 = 0;
                while (i5 < i) {
                    int i6;
                    int i7 = i4 < length ? i4 : i4 % length;
                    if (i - i5 >= i3) {
                        canvas.drawBitmap(bitmapArr[i7], null, new Rect(i5, 0, i5 + i3, i2), paint);
                        i6 = i3;
                    } else {
                        int height;
                        i6 = i - i5;
                        int width = i6 > bitmapArr[i7].getWidth() ? bitmapArr[i7].getWidth() : i6;
                        if (i2 > bitmapArr[i7].getHeight()) {
                            height = bitmapArr[i7].getHeight();
                        } else {
                            height = i2;
                        }
                        Bitmap createBitmap2 = Bitmap.createBitmap(bitmapArr[i7], 0, 0, width, height);
                        canvas.drawBitmap(createBitmap2, null, new Rect(i5, 0, i5 + i6, i2), paint);
                        if (!(createBitmap2 == null || createBitmap2.isRecycled())) {
                            createBitmap2.recycle();
                        }
                    }
                    i4++;
                    i5 = i6 + i5;
                }
                return createBitmap;
            } catch (Exception e) {
                Exception exception2 = e;
                bitmap = createBitmap;
                exception = exception2;
                o.a("VCUtilscreateBmp err");
                exception.printStackTrace();
                return bitmap;
            } catch (OutOfMemoryError e2) {
                OutOfMemoryError outOfMemoryError2 = e2;
                bitmap = createBitmap;
                outOfMemoryError = outOfMemoryError2;
                o.a("VCUtilscreateBmp err");
                outOfMemoryError.printStackTrace();
                return bitmap;
            }
        } catch (Exception e3) {
            exception = e3;
            o.a("VCUtilscreateBmp err");
            exception.printStackTrace();
            return bitmap;
        } catch (OutOfMemoryError e4) {
            outOfMemoryError = e4;
            o.a("VCUtilscreateBmp err");
            outOfMemoryError.printStackTrace();
            return bitmap;
        }
    }

    public static void a(Context context) {
        if (VERSION.SDK_INT >= 19) {
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath())));
            context.sendBroadcast(intent);
            return;
        }
        context.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse(a$e.a + Environment.getExternalStorageDirectory())));
    }

    public static String c(String str) {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str));
    }

    public static void a(Context context, String str) {
        File file = new File(str);
        if (file.isDirectory()) {
            try {
                Iterator it = f.d(file).iterator();
                while (it.hasNext()) {
                    MediaScannerConnection.scanFile(context, new String[]{((File) it.next()).getAbsolutePath()}, null, new OnScanCompletedListener() {
                        public void onScanCompleted(String str, Uri uri) {
                            o.a("file " + str + " was scanned seccessfully: " + uri);
                        }
                    });
                }
                return;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
        MediaScannerConnection.scanFile(context, new String[]{str}, null, new OnScanCompletedListener() {
            public void onScanCompleted(String str, Uri uri) {
                o.a("file " + str + " was scanned seccessfully: " + uri);
            }
        });
    }

    public static int b(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int[] c(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", new Class[]{DisplayMetrics.class}).invoke(defaultDisplay, new Object[]{displayMetrics});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }

    public static int d(Context context) {
        Rect rect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    public static int[] e(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        windowManager.getDefaultDisplay().getMetrics(new DisplayMetrics());
        return new int[]{r1.widthPixels, r1.heightPixels};
    }

    public static boolean d(String str) {
        File file = new File(str);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static String b() {
        String str = "";
        if (dji.pilot.usercenter.b.f.getInstance().c()) {
            return dji.pilot.usercenter.b.f.getInstance().m();
        }
        return str;
    }

    public static ProductType e(String str) {
        ProductType productType = ProductType.OTHER;
        try {
            String name = new File(str).getName();
            int lastIndexOf = name.lastIndexOf(46);
            String str2 = "";
            if (lastIndexOf > 0) {
                name = name.substring(0, lastIndexOf);
            }
            dji.midware.media.e.f f = dji.midware.media.e.e.f(name);
            if (f != null && (f.I().intValue() == 1 || f.I().intValue() == 2)) {
                Log.v("+++++++++", "vri.getFileSourceType() == 1");
                productType = f.a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productType;
    }

    public static String c() {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
    }

    public static boolean b(Context context, String str) {
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(0)) {
            if (packageInfo.packageName.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
