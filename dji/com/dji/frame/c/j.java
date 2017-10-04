package com.dji.frame.c;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class j {
    private static final String a = "android.intent.action.MEDIA_SCANNER_SCAN_DIR";
    private static MediaScannerConnection b = null;

    public static Bitmap a(String str, int i, int i2) {
        int i3 = 1;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        int i4 = options.outWidth / i;
        int i5 = options.outHeight / i2;
        if (i4 >= i5) {
            i4 = i5;
        }
        if (i4 > 0) {
            i3 = i4;
        }
        options.inSampleSize = i3;
        return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(str, options), i, i2, 2);
    }

    public static Bitmap a(String str, int i, int i2, int i3) {
        return ThumbnailUtils.extractThumbnail(ThumbnailUtils.createVideoThumbnail(str, i3), i, i2, 2);
    }

    public static Bitmap a(String str, String str2) {
        Bitmap extractThumbnail = ThumbnailUtils.extractThumbnail(ThumbnailUtils.createVideoThumbnail(str, 1), 512, 384, 2);
        f.a(extractThumbnail, str2, a.a(str));
        return extractThumbnail;
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent(a);
        intent.setData(Uri.fromFile(new File(str)));
        context.sendBroadcast(intent);
    }

    public static void b(Context context, String str) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(new File(str)));
        context.sendBroadcast(intent);
    }

    public static void a(Context context, final String str, final MediaScannerConnectionClient mediaScannerConnectionClient) {
        b = new MediaScannerConnection(context, new MediaScannerConnectionClient() {
            public void onScanCompleted(String str, Uri uri) {
                mediaScannerConnectionClient.onScanCompleted(str, uri);
                j.b.disconnect();
            }

            public void onMediaScannerConnected() {
                mediaScannerConnectionClient.onMediaScannerConnected();
                j.b(j.b, str);
            }
        });
        b.connect();
    }

    public static String a(String str) {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str));
    }

    private static void b(MediaScannerConnection mediaScannerConnection, String str) {
        File file = new File(str);
        if (file.isDirectory()) {
            try {
                String str2 = "";
                Iterator it = f.d(file).iterator();
                while (it.hasNext()) {
                    file = (File) it.next();
                    mediaScannerConnection.scanFile(file.getAbsolutePath(), a(file.getAbsolutePath()));
                }
                return;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
        mediaScannerConnection.scanFile(str, a(str));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap b(java.lang.String r4, int r5, int r6) {
        /*
        r0 = 0;
        r1 = new android.media.MediaMetadataRetriever;
        r1.<init>();
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ IllegalArgumentException -> 0x0020, Exception -> 0x0027, all -> 0x002e }
        r3 = 14;
        if (r2 < r3) goto L_0x001c;
    L_0x000c:
        r2 = new java.util.HashMap;	 Catch:{ IllegalArgumentException -> 0x0020, Exception -> 0x0027, all -> 0x002e }
        r2.<init>();	 Catch:{ IllegalArgumentException -> 0x0020, Exception -> 0x0027, all -> 0x002e }
        r1.setDataSource(r4, r2);	 Catch:{ IllegalArgumentException -> 0x0020, Exception -> 0x0027, all -> 0x002e }
    L_0x0014:
        r0 = r1.getFrameAtTime();	 Catch:{ IllegalArgumentException -> 0x0020, Exception -> 0x0027, all -> 0x002e }
        r1.release();	 Catch:{ RuntimeException -> 0x0033 }
    L_0x001b:
        return r0;
    L_0x001c:
        r1.setDataSource(r4);	 Catch:{ IllegalArgumentException -> 0x0020, Exception -> 0x0027, all -> 0x002e }
        goto L_0x0014;
    L_0x0020:
        r2 = move-exception;
        r1.release();	 Catch:{ RuntimeException -> 0x0025 }
        goto L_0x001b;
    L_0x0025:
        r1 = move-exception;
        goto L_0x001b;
    L_0x0027:
        r2 = move-exception;
        r1.release();	 Catch:{ RuntimeException -> 0x002c }
        goto L_0x001b;
    L_0x002c:
        r1 = move-exception;
        goto L_0x001b;
    L_0x002e:
        r0 = move-exception;
        r1.release();	 Catch:{ RuntimeException -> 0x0035 }
    L_0x0032:
        throw r0;
    L_0x0033:
        r1 = move-exception;
        goto L_0x001b;
    L_0x0035:
        r1 = move-exception;
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dji.frame.c.j.b(java.lang.String, int, int):android.graphics.Bitmap");
    }
}
