package dji.pilot.usercenter.f;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c$i;
import dji.pilot.publics.objects.DJIApplication;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class f {
    private static final int a = 4096;
    private volatile boolean b = false;
    private b c = null;
    private a d = null;
    private c e = null;
    private final HashMap<String, WeakReference<ImageView>> f = new HashMap(20);

    private static final class a extends Handler {
        private final WeakReference<f> a;

        public a(Looper looper, f fVar) {
            super(looper);
            this.a = new WeakReference(fVar);
        }

        public void handleMessage(Message message) {
            f fVar = (f) this.a.get();
            if (fVar != null && fVar.c()) {
                switch (message.what) {
                    case 4096:
                        String str = (String) message.obj;
                        Bitmap bitmap = null;
                        try {
                            bitmap = f.a(str, 1500000);
                        } catch (OutOfMemoryError e) {
                        } catch (Exception e2) {
                        }
                        if (bitmap != null) {
                            dji.pilot2.media.e.getInstance().a(str, bitmap);
                        }
                        fVar.e.obtainMessage(4096, str).sendToTarget();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static final class b extends HandlerThread {
        public b(String str) {
            super(str, 1);
        }
    }

    private static final class c extends Handler {
        private final WeakReference<f> a;

        public c(f fVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(fVar);
        }

        public void handleMessage(Message message) {
            f fVar = (f) this.a.get();
            if (fVar != null && fVar.c()) {
                switch (message.what) {
                    case 4096:
                        String str = (String) message.obj;
                        WeakReference weakReference = (WeakReference) fVar.f.remove(str);
                        if (weakReference != null) {
                            ImageView imageView = (ImageView) weakReference.get();
                            if (imageView != null && str.equals(imageView.getTag())) {
                                Bitmap a = dji.pilot2.media.e.getInstance().a(str);
                                if (a != null) {
                                    imageView.setImageBitmap(a);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static final class d {
        private static final f a = new f();

        private d() {
        }
    }

    public static class e implements FilenameFilter {
        private final String a;

        public e(String str) {
            this.a = str;
        }

        public boolean accept(File file, String str) {
            if (str.startsWith("pov_" + this.a + "_")) {
                return true;
            }
            return false;
        }
    }

    public static f getInstance() {
        return d.a;
    }

    public synchronized void a() {
        if (!this.b) {
            this.c = new b("videothumb_decode");
            this.c.start();
            this.d = new a(this.c.getLooper(), this);
            this.e = new c(this);
            this.b = true;
        }
    }

    public synchronized void b() {
        if (this.b) {
            this.f.clear();
            this.e.removeMessages(4096);
            this.d.removeMessages(4096);
            this.d = null;
            this.c.quit();
            this.c = null;
            this.b = false;
        }
    }

    public void a(ImageView imageView, String str, int i, int i2) {
        if (imageView != null && str != null) {
            if (this.f.containsKey(str)) {
                this.f.put(str, new WeakReference(imageView));
                return;
            }
            Bitmap a = dji.pilot2.media.e.getInstance().a(str);
            if (a != null) {
                imageView.setImageBitmap(a);
                return;
            }
            imageView.setImageResource(R.drawable.v2_photo_defalut);
            imageView.setTag(str);
            this.f.put(str, new WeakReference(imageView));
            this.d.obtainMessage(4096, i, i2, str).sendToTarget();
        }
    }

    private boolean c() {
        return this.b;
    }

    public static Bitmap a(String str, long j) {
        Log.i("zc", "get file path :" + str);
        Bitmap bitmap = null;
        File file = new File(str);
        File file2 = new File(com.dji.frame.c.d.a(DJIApplication.a(), dji.pilot.c.b.e) + "pov_" + com.dji.frame.c.a.a(file.getAbsolutePath()) + "_" + j);
        Boolean valueOf = Boolean.valueOf(false);
        if (file2.exists()) {
            Options options = new Options();
            options.inSampleSize = 2;
            Bitmap decodeFile = BitmapFactory.decodeFile(file2.getAbsolutePath(), options);
            DJILogHelper.getInstance().LOGI("bitmap", new StringBuilder().append("thumb_file exists").append(decodeFile).toString() == null ? "null" : c$i.c);
            if (decodeFile != null) {
                valueOf = Boolean.valueOf(true);
                bitmap = decodeFile;
            } else {
                bitmap = decodeFile;
            }
        }
        if (bitmap == null) {
            Log.i("zc", "bitmap is null!");
            if (!(dji.pilot2.library.b.c(file.getName()) || dji.midware.media.d.c(str))) {
                bitmap = b(str, j);
                DJILogHelper.getInstance().LOGI("bitmap", "createVideoThumbnail");
            }
            if (bitmap == null) {
                bitmap = dji.pilot2.media.f.a(str).a(j);
                DJILogHelper.getInstance().LOGI("bitmap", "createMediaMetadataRetriever");
            }
        }
        if (!(valueOf.booleanValue() || bitmap == null)) {
            Log.i("zc", "bitmap is not null!");
            try {
                OutputStream fileOutputStream = new FileOutputStream(file2.getAbsoluteFile());
                bitmap.compress(CompressFormat.JPEG, 50, fileOutputStream);
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (bitmap == null) {
            DJILogHelper.getInstance().LOGE("bitmap", "can not get bitmap");
        }
        return bitmap;
    }

    public static void a(Context context, String str) {
        File file = new File(str);
        File file2 = new File(com.dji.frame.c.d.a(context, dji.pilot.c.b.e));
        if (file2 != null) {
            for (File delete : file2.listFiles(new e(com.dji.frame.c.a.a(file.getAbsolutePath())))) {
                delete.delete();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap b(java.lang.String r5, long r6) {
        /*
        r1 = 0;
        r2 = new android.media.MediaMetadataRetriever;
        r2.<init>();
        r2.setDataSource(r5);	 Catch:{ IllegalArgumentException -> 0x0019, RuntimeException -> 0x0028 }
        r0 = r2.getFrameAtTime(r6);	 Catch:{ IllegalArgumentException -> 0x0019, RuntimeException -> 0x0028 }
        r2.release();	 Catch:{ RuntimeException -> 0x0014 }
    L_0x0010:
        if (r0 != 0) goto L_0x0041;
    L_0x0012:
        r0 = r1;
    L_0x0013:
        return r0;
    L_0x0014:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0010;
    L_0x0019:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0037 }
        r2.release();	 Catch:{ RuntimeException -> 0x0022 }
        r0 = r1;
        goto L_0x0010;
    L_0x0022:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x0010;
    L_0x0028:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0037 }
        r2.release();	 Catch:{ RuntimeException -> 0x0031 }
        r0 = r1;
        goto L_0x0010;
    L_0x0031:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x0010;
    L_0x0037:
        r0 = move-exception;
        r2.release();	 Catch:{ RuntimeException -> 0x003c }
    L_0x003b:
        throw r0;
    L_0x003c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x003b;
    L_0x0041:
        r1 = r0.getWidth();
        r2 = r0.getHeight();
        r3 = java.lang.Math.max(r1, r2);
        r4 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        if (r3 <= r4) goto L_0x0013;
    L_0x0051:
        r4 = 1140850688; // 0x44000000 float:512.0 double:5.63655132E-315;
        r3 = (float) r3;
        r3 = r4 / r3;
        r1 = (float) r1;
        r1 = r1 * r3;
        r1 = java.lang.Math.round(r1);
        r2 = (float) r2;
        r2 = r2 * r3;
        r2 = java.lang.Math.round(r2);
        r3 = 1;
        r0 = android.graphics.Bitmap.createScaledBitmap(r0, r1, r2, r3);
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.usercenter.f.f.b(java.lang.String, long):android.graphics.Bitmap");
    }
}
