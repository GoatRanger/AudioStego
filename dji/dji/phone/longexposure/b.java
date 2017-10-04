package dji.phone.longexposure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.hardware.Camera.Size;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.here.odnp.config.OdnpConfigStatic;
import dji.midware.usb.P3.DJIUsbAccessoryReceiver;
import dji.phone.controview.DJILPCameraRecordBottomBar;
import dji.phone.d.d;
import dji.phone.e.a.e;
import dji.phone.preview.DJILPPreviewActivity;
import dji.pilot.fpv.R;
import dji.pilot.phonecamera.e.f;
import dji.pilot.phonecamera.e.g;
import dji.pilot.phonecamera.h;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class b {
    public static Bitmap a = null;
    protected static final int g = 1;
    protected static final int h = 2;
    protected static final int i = 3;
    private static final String l = b.class.getSimpleName();
    private static final int v = 4;
    DJILPCameraRecordBottomBar b;
    protected volatile int c;
    protected long d;
    protected int e = 0;
    protected long f;
    protected byte[] j;
    f k = new f(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void a(byte[] bArr, g gVar) {
            if (this.a.x) {
                synchronized (this.a) {
                    if (bArr != null) {
                        this.a.j = bArr;
                    }
                    Log.d(b.l, "onPreviewFrame: data = " + bArr);
                    this.a.notify();
                }
            }
        }
    };
    private Context m;
    private View n;
    private FrameLayout o;
    private ImageView p;
    private TextView q;
    private int r;
    private int s;
    private long t;
    private int u;
    private a w;
    private volatile boolean x = false;
    private String y = "";
    private Handler z = new Handler(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - this.a.t >= OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME) {
                        this.a.y = String.format("  %.2fFPS", new Object[]{Float.valueOf((((float) (this.a.c - this.a.u)) / ((float) (currentTimeMillis - this.a.t))) * 1000.0f)});
                        this.a.t = currentTimeMillis;
                        this.a.u = this.a.c;
                    }
                    synchronized (b.a) {
                        this.a.p.invalidate();
                    }
                    return;
                case 2:
                    this.a.y = "";
                    this.a.p.setVisibility(4);
                    this.a.p.setImageBitmap(null);
                    this.a.b.showTimeForLongEx(false);
                    this.a.j = null;
                    ((DJILPPreviewActivity) this.a.m).mCameraPresenter.d();
                    this.a.i();
                    ((DJILPPreviewActivity) this.a.m).mCameraPresenter.a.switchToTakePhotoUI(false);
                    ((DJILPPreviewActivity) this.a.m).mCameraPresenter.a(false);
                    ((DJILPPreviewActivity) this.a.m).mCameraPresenter.b(false);
                    d.getInstance().a(dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE_PREVIEW, true);
                    return;
                case 3:
                    this.a.e();
                    return;
                case 4:
                    this.a.h();
                    return;
                default:
                    return;
            }
        }
    };

    protected class a implements Runnable {
        final /* synthetic */ b a;

        protected a(b bVar) {
            this.a = bVar;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r14 = this;
            r2 = 0;
            r1 = 1;
            r0 = java.lang.Thread.currentThread();
            r6 = r0.getId();
            r3 = r14.a;
            monitor-enter(r3);
            r0 = r14.a;	 Catch:{ all -> 0x00d8 }
            r4 = r0.e;	 Catch:{ all -> 0x00d8 }
            r4 = r4 + 1;
            r0.e = r4;	 Catch:{ all -> 0x00d8 }
            r0 = dji.phone.longexposure.b.l;	 Catch:{ all -> 0x00d8 }
            r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d8 }
            r4.<init>();	 Catch:{ all -> 0x00d8 }
            r4 = r4.append(r6);	 Catch:{ all -> 0x00d8 }
            r5 = " starts, mThreadCount = ";
            r4 = r4.append(r5);	 Catch:{ all -> 0x00d8 }
            r5 = r14.a;	 Catch:{ all -> 0x00d8 }
            r5 = r5.e;	 Catch:{ all -> 0x00d8 }
            r4 = r4.append(r5);	 Catch:{ all -> 0x00d8 }
            r4 = r4.toString();	 Catch:{ all -> 0x00d8 }
            android.util.Log.i(r0, r4);	 Catch:{ all -> 0x00d8 }
            monitor-exit(r3);	 Catch:{ all -> 0x00d8 }
            r0 = r14.a;
            r0 = r0.r;
            r3 = r14.a;
            r3 = r3.s;
            r0 = r0 * r3;
            r3 = new int[r0];
        L_0x0047:
            r0 = r14.a;
            r0 = r0.x;
            if (r0 == 0) goto L_0x0103;
        L_0x004f:
            r8 = r14.a;
            monitor-enter(r8);
            r0 = dji.phone.longexposure.b.l;	 Catch:{ all -> 0x00e0 }
            r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e0 }
            r4.<init>();	 Catch:{ all -> 0x00e0 }
            r4 = r4.append(r6);	 Catch:{ all -> 0x00e0 }
            r5 = " is getting preview data";
            r4 = r4.append(r5);	 Catch:{ all -> 0x00e0 }
            r4 = r4.toString();	 Catch:{ all -> 0x00e0 }
            android.util.Log.i(r0, r4);	 Catch:{ all -> 0x00e0 }
            r0 = r14.a;	 Catch:{ InterruptedException -> 0x00db }
            r0.wait();	 Catch:{ InterruptedException -> 0x00db }
        L_0x0071:
            r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00e0 }
            r0 = r14.a;	 Catch:{ all -> 0x00e0 }
            r10 = r0.f;	 Catch:{ all -> 0x00e0 }
            r10 = r10 - r4;
            r10 = (double) r10;	 Catch:{ all -> 0x00e0 }
            r12 = 4636737291354636288; // 0x4059000000000000 float:0.0 double:100.0;
            r10 = r10 / r12;
            r10 = java.lang.Math.ceil(r10);	 Catch:{ all -> 0x00e0 }
            r9 = (int) r10;	 Catch:{ all -> 0x00e0 }
            r0 = r14.a;	 Catch:{ all -> 0x00e0 }
            if (r9 >= 0) goto L_0x00e3;
        L_0x0087:
            r10 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
            r4 = r4 + r10;
            r0.f = r4;	 Catch:{ all -> 0x00e0 }
            monitor-exit(r8);	 Catch:{ all -> 0x00e0 }
            r0 = dji.phone.longexposure.b.l;
            r4 = new java.lang.StringBuilder;
            r4.<init>();
            r5 = "run: mNextFrameTime";
            r4 = r4.append(r5);
            r5 = r14.a;
            r10 = r5.f;
            r4 = r4.append(r10);
            r4 = r4.toString();
            android.util.Log.d(r0, r4);
            r0 = dji.phone.longexposure.b.l;
            r4 = new java.lang.StringBuilder;
            r4.<init>();
            r4 = r4.append(r6);
            r5 = " is delaying";
            r4 = r4.append(r5);
            r4 = r4.toString();
            android.util.Log.i(r0, r4);
            r0 = r2;
        L_0x00c6:
            if (r0 >= r9) goto L_0x00ed;
        L_0x00c8:
            r4 = r14.a;
            r4 = r4.x;
            if (r4 == 0) goto L_0x00ed;
        L_0x00d0:
            r4 = 100;
            java.lang.Thread.sleep(r4);	 Catch:{ InterruptedException -> 0x00e8 }
        L_0x00d5:
            r0 = r0 + 1;
            goto L_0x00c6;
        L_0x00d8:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x00d8 }
            throw r0;
        L_0x00db:
            r0 = move-exception;
            r0.printStackTrace();	 Catch:{ all -> 0x00e0 }
            goto L_0x0071;
        L_0x00e0:
            r0 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x00e0 }
            throw r0;
        L_0x00e3:
            r4 = r14.a;	 Catch:{ all -> 0x00e0 }
            r4 = r4.f;	 Catch:{ all -> 0x00e0 }
            goto L_0x0087;
        L_0x00e8:
            r4 = move-exception;
            r4.printStackTrace();
            goto L_0x00d5;
        L_0x00ed:
            r4 = r14.a;
            monitor-enter(r4);
            r0 = r14.a;	 Catch:{ all -> 0x0175 }
            r5 = r0.c;	 Catch:{ all -> 0x0175 }
            r5 = r5 + 1;
            r0.c = r5;	 Catch:{ all -> 0x0175 }
            r0 = r14.a;	 Catch:{ all -> 0x0175 }
            r0 = r0.x;	 Catch:{ all -> 0x0175 }
            if (r0 != 0) goto L_0x0134;
        L_0x0100:
            if (r5 <= r1) goto L_0x0134;
        L_0x0102:
            monitor-exit(r4);	 Catch:{ all -> 0x0175 }
        L_0x0103:
            r1 = r14.a;
            monitor-enter(r1);
            r0 = dji.phone.longexposure.b.l;	 Catch:{ all -> 0x0212 }
            r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0212 }
            r3.<init>();	 Catch:{ all -> 0x0212 }
            r3 = r3.append(r6);	 Catch:{ all -> 0x0212 }
            r4 = " is exiting, mThreadCount = ";
            r3 = r3.append(r4);	 Catch:{ all -> 0x0212 }
            r4 = r14.a;	 Catch:{ all -> 0x0212 }
            r4 = r4.e;	 Catch:{ all -> 0x0212 }
            r3 = r3.append(r4);	 Catch:{ all -> 0x0212 }
            r3 = r3.toString();	 Catch:{ all -> 0x0212 }
            android.util.Log.i(r0, r3);	 Catch:{ all -> 0x0212 }
            r0 = r14.a;	 Catch:{ all -> 0x0212 }
            r3 = r0.e;	 Catch:{ all -> 0x0212 }
            r3 = r3 + -1;
            r0.e = r3;	 Catch:{ all -> 0x0212 }
            if (r3 <= 0) goto L_0x01f3;
        L_0x0132:
            monitor-exit(r1);	 Catch:{ all -> 0x0212 }
        L_0x0133:
            return;
        L_0x0134:
            r0 = dji.pilot.phonecamera.a.c.a();	 Catch:{ all -> 0x0175 }
            r0 = r0.n();	 Catch:{ all -> 0x0175 }
            if (r0 <= 0) goto L_0x0215;
        L_0x013e:
            r8 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0175 }
            r0 = r14.a;	 Catch:{ all -> 0x0175 }
            r10 = r0.d;	 Catch:{ all -> 0x0175 }
            r8 = r8 - r10;
            r10 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
            r8 = r8 / r10;
            r0 = dji.pilot.phonecamera.a.c.a();	 Catch:{ all -> 0x0175 }
            r0 = r0.n();	 Catch:{ all -> 0x0175 }
            r10 = (long) r0;	 Catch:{ all -> 0x0175 }
            r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
            if (r0 < 0) goto L_0x0178;
        L_0x0157:
            r0 = r1;
        L_0x0158:
            if (r0 == 0) goto L_0x017a;
        L_0x015a:
            r0 = r14.a;	 Catch:{ all -> 0x0175 }
            r0 = r0.z;	 Catch:{ all -> 0x0175 }
            r8 = 1;
            r0 = r0.hasMessages(r8);	 Catch:{ all -> 0x0175 }
            if (r0 != 0) goto L_0x0171;
        L_0x0167:
            r0 = r14.a;	 Catch:{ all -> 0x0175 }
            r0 = r0.z;	 Catch:{ all -> 0x0175 }
            r8 = 3;
            r0.sendEmptyMessage(r8);	 Catch:{ all -> 0x0175 }
        L_0x0171:
            if (r5 <= r1) goto L_0x017a;
        L_0x0173:
            monitor-exit(r4);	 Catch:{ all -> 0x0175 }
            goto L_0x0103;
        L_0x0175:
            r0 = move-exception;
            monitor-exit(r4);	 Catch:{ all -> 0x0175 }
            throw r0;
        L_0x0178:
            r0 = r2;
            goto L_0x0158;
        L_0x017a:
            r0 = r14.a;	 Catch:{ all -> 0x0175 }
            r0 = r0.j;	 Catch:{ all -> 0x0175 }
            monitor-exit(r4);	 Catch:{ all -> 0x0175 }
            r4 = dji.phone.longexposure.b.l;
            r8 = new java.lang.StringBuilder;
            r8.<init>();
            r8 = r8.append(r6);
            r9 = " is decoding";
            r8 = r8.append(r9);
            r8 = r8.toString();
            android.util.Log.i(r4, r8);
            r4 = r14.a;
            r4 = r4.w;
            r8 = r14.a;
            r8 = r8.r;
            r9 = r14.a;
            r9 = r9.s;
            r4.decodeYUV420SPtoRGB(r3, r0, r8, r9);
            r4 = dji.phone.longexposure.b.a;
            monitor-enter(r4);
            r0 = dji.phone.longexposure.b.l;	 Catch:{ all -> 0x01f0 }
            r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01f0 }
            r8.<init>();	 Catch:{ all -> 0x01f0 }
            r8 = r8.append(r6);	 Catch:{ all -> 0x01f0 }
            r9 = " is blending";
            r8 = r8.append(r9);	 Catch:{ all -> 0x01f0 }
            r8 = r8.toString();	 Catch:{ all -> 0x01f0 }
            android.util.Log.i(r0, r8);	 Catch:{ all -> 0x01f0 }
            r0 = r14.a;	 Catch:{ all -> 0x01f0 }
            r0 = r0.w;	 Catch:{ all -> 0x01f0 }
            r8 = dji.phone.longexposure.b.a;	 Catch:{ all -> 0x01f0 }
            r0.blendUseAverage(r8, r3, r5);	 Catch:{ all -> 0x01f0 }
            r0 = r14.a;	 Catch:{ all -> 0x01f0 }
            r0 = r0.z;	 Catch:{ all -> 0x01f0 }
            r5 = 1;
            r0 = r0.hasMessages(r5);	 Catch:{ all -> 0x01f0 }
            if (r0 != 0) goto L_0x01ed;
        L_0x01e3:
            r0 = r14.a;	 Catch:{ all -> 0x01f0 }
            r0 = r0.z;	 Catch:{ all -> 0x01f0 }
            r5 = 1;
            r0.sendEmptyMessage(r5);	 Catch:{ all -> 0x01f0 }
        L_0x01ed:
            monitor-exit(r4);	 Catch:{ all -> 0x01f0 }
            goto L_0x0047;
        L_0x01f0:
            r0 = move-exception;
            monitor-exit(r4);	 Catch:{ all -> 0x01f0 }
            throw r0;
        L_0x01f3:
            monitor-exit(r1);	 Catch:{ all -> 0x0212 }
            r0 = r14.a;
            r0 = r0.z;
            r1 = 2;
            r0.sendEmptyMessage(r1);
            r0 = dji.phone.f.a.getInstance();
            r1 = dji.phone.f.d.WORK_FINE_LONG_EXPOSURE;
            r0.a(r1, r2);
            r0 = r14.a;
            r0 = r0.w;
            r0.blenderUninit();
            goto L_0x0133;
        L_0x0212:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0212 }
            throw r0;
        L_0x0215:
            r0 = r2;
            goto L_0x0158;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.phone.longexposure.b.a.run():void");
        }
    }

    private class b extends AsyncTask<Void, Void, String> {
        final /* synthetic */ b a;
        private Bitmap b;
        private String c;
        private final String d = Environment.getExternalStoragePublicDirectory(DJIUsbAccessoryReceiver.c).toString();
        private final String e = (this.d + "/Camera/LongExposure");

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((String) obj);
        }

        public b(b bVar, Bitmap bitmap, String str) {
            this.a = bVar;
            this.b = bitmap;
            this.c = str;
        }

        protected void onPreExecute() {
        }

        protected String a(Void... voidArr) {
            b(this.c, this.b);
            return b(this.c);
        }

        protected void a(String str) {
            this.a.a(str);
        }

        private void b(String str, Bitmap bitmap) {
            String b = b(str);
            Log.d(b.l, "addImage: path = " + b + " title = " + str);
            a(b, bitmap);
            File file = new File(b);
            if (file.exists()) {
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                HashMap hashMap = new HashMap();
                Log.d(b.l, "addImage: add long exposure flag");
                hashMap.put(dji.thirdparty.g.b.b.a.b.aL, "DJI-LongExposure");
                dji.b.a.a.a.a(file, hashMap);
            }
        }

        private String b(String str) {
            File file = new File(this.e);
            if (!file.exists()) {
                file.mkdir();
            }
            return this.e + '/' + str + dji.pilot2.main.a.a.n;
        }

        public void a(String str, Bitmap bitmap) {
            Throwable e;
            OutputStream outputStream;
            FileOutputStream fileOutputStream = null;
            BufferedOutputStream bufferedOutputStream;
            try {
                Log.d(b.l, "writeFile");
                OutputStream fileOutputStream2 = new FileOutputStream(str);
                try {
                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream2);
                } catch (Exception e2) {
                    e = e2;
                    bufferedOutputStream = null;
                    outputStream = fileOutputStream2;
                    try {
                        Log.e(b.l, "Failed to write data", e);
                        try {
                            fileOutputStream.close();
                            bufferedOutputStream.close();
                        } catch (Throwable e3) {
                            Log.e(b.l, "Failed to close file after write", e3);
                            return;
                        }
                    } catch (Throwable th) {
                        e3 = th;
                        try {
                            fileOutputStream.close();
                            bufferedOutputStream.close();
                        } catch (Throwable e4) {
                            Log.e(b.l, "Failed to close file after write", e4);
                        }
                        throw e3;
                    }
                } catch (Throwable th2) {
                    e3 = th2;
                    bufferedOutputStream = null;
                    outputStream = fileOutputStream2;
                    fileOutputStream.close();
                    bufferedOutputStream.close();
                    throw e3;
                }
                try {
                    if (bitmap.compress(CompressFormat.JPEG, 100, bufferedOutputStream)) {
                        bufferedOutputStream.flush();
                    } else {
                        bufferedOutputStream.close();
                        fileOutputStream2.close();
                    }
                    try {
                        fileOutputStream2.close();
                        bufferedOutputStream.close();
                    } catch (Throwable e32) {
                        Log.e(b.l, "Failed to close file after write", e32);
                    }
                } catch (Exception e5) {
                    e32 = e5;
                    fileOutputStream = fileOutputStream2;
                    Log.e(b.l, "Failed to write data", e32);
                    fileOutputStream.close();
                    bufferedOutputStream.close();
                } catch (Throwable th3) {
                    e32 = th3;
                    outputStream = fileOutputStream2;
                    fileOutputStream.close();
                    bufferedOutputStream.close();
                    throw e32;
                }
            } catch (Exception e6) {
                e32 = e6;
                bufferedOutputStream = null;
                Log.e(b.l, "Failed to write data", e32);
                fileOutputStream.close();
                bufferedOutputStream.close();
            } catch (Throwable th4) {
                e32 = th4;
                bufferedOutputStream = null;
                fileOutputStream.close();
                bufferedOutputStream.close();
                throw e32;
            }
        }
    }

    protected class c extends AsyncTask<Void, Void, String> {
        final /* synthetic */ b a;
        private Bitmap b;
        private File c = null;
        private Uri d = null;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((String) obj);
        }

        public c(b bVar, Bitmap bitmap) {
            this.a = bVar;
            this.b = bitmap;
        }

        protected String a(Void... voidArr) {
            Throwable e;
            OutputStream outputStream = null;
            OutputStream fileOutputStream;
            try {
                this.d = dji.pilot.storage.a.a(this.a.m, 1, "jpg", dji.pilot.storage.a.e);
                Log.d(b.l, "saveUri: " + this.d);
                this.c = File.createTempFile("picFile", "jpg", this.a.m.getCacheDir());
                Log.d(b.l, "temp picFile: " + this.c.getAbsolutePath());
                HashMap hashMap = new HashMap();
                Log.d(b.l, "addImage: add long exposure flag");
                hashMap.put(dji.thirdparty.g.b.b.a.b.aL, "DJI-LongExposure");
                dji.b.a.a.a.a(this.c, hashMap);
                fileOutputStream = new FileOutputStream(this.c);
                try {
                    OutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    if (this.b.compress(CompressFormat.JPEG, 100, bufferedOutputStream)) {
                        bufferedOutputStream.flush();
                        a(this.a.m, this.d, this.c);
                    } else {
                        bufferedOutputStream.close();
                        fileOutputStream.close();
                    }
                    try {
                        fileOutputStream.close();
                        fileOutputStream.close();
                    } catch (Throwable e2) {
                        Log.e(b.l, "Failed to close file after write", e2);
                    }
                } catch (Exception e3) {
                    e2 = e3;
                    try {
                        Log.e(b.l, "Failed to write data", e2);
                        try {
                            fileOutputStream.close();
                            fileOutputStream.close();
                        } catch (Throwable e22) {
                            Log.e(b.l, "Failed to close file after write", e22);
                        }
                        return dji.pilot.storage.a.a(this.d).getAbsolutePath();
                    } catch (Throwable th) {
                        e22 = th;
                        outputStream = fileOutputStream;
                        try {
                            outputStream.close();
                            outputStream.close();
                        } catch (Throwable e4) {
                            Log.e(b.l, "Failed to close file after write", e4);
                        }
                        throw e22;
                    }
                }
            } catch (Exception e5) {
                e22 = e5;
                fileOutputStream = null;
                Log.e(b.l, "Failed to write data", e22);
                fileOutputStream.close();
                fileOutputStream.close();
                return dji.pilot.storage.a.a(this.d).getAbsolutePath();
            } catch (Throwable th2) {
                e22 = th2;
                outputStream.close();
                outputStream.close();
                throw e22;
            }
            return dji.pilot.storage.a.a(this.d).getAbsolutePath();
        }

        protected void a(String str) {
            this.a.a(str);
        }

        private void a(Context context, Uri uri, File file) throws FileNotFoundException, IOException {
            Throwable th;
            OutputStream outputStream = null;
            Log.d(b.l, "copyUriToFile");
            Log.d(b.l, "saveUri: " + uri);
            Log.d(b.l, "picFile: " + file);
            InputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    outputStream = context.getContentResolver().openOutputStream(uri);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        outputStream.write(bArr, 0, read);
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                Object obj = outputStream;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                throw th;
            }
        }
    }

    public b(Context context, View view) {
        this.m = context;
        this.n = view;
        g();
    }

    private void g() {
        Size w = dji.phone.c.a.c().w();
        if (w != null) {
            this.s = w.height;
            this.r = w.width;
        } else {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) this.m).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            this.s = displayMetrics.heightPixels;
            this.r = displayMetrics.widthPixels;
        }
        Log.d(l, "init: mPictureHeight = " + this.s + " mPictureWidth = " + this.r);
        this.w = new DJILPCameraLongExposureImpl();
        dji.thirdparty.a.c.a().a(this);
    }

    public void a(int i, int i2, int i3, int i4) {
        LayoutParams layoutParams = (LayoutParams) this.o.getLayoutParams();
        float f = ((float) i3) / ((float) this.r);
        float f2 = ((float) i4) / ((float) this.s);
        if (f >= f2) {
            f = f2;
        }
        layoutParams.width = (int) (((float) this.r) * f);
        layoutParams.height = (int) (f * ((float) this.s));
        layoutParams.leftMargin = i - (layoutParams.width / 2);
        layoutParams.topMargin = i2 - (layoutParams.height / 2);
        Log.d(l, "resize: " + layoutParams.debug(new String()));
        this.o.setLayoutParams(layoutParams);
    }

    public void a() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.m).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        a(displayMetrics.widthPixels / 2, displayMetrics.heightPixels / 2, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public void a(FrameLayout frameLayout) {
        this.o = frameLayout;
    }

    public void b() {
        Log.i(l, "Start exposing");
        dji.phone.c.a.c().a(this.z, this.k);
        a = Bitmap.createBitmap(this.r, this.s, Config.ARGB_8888);
        this.p = (ImageView) this.n.findViewById(R.id.result_preview);
        this.q = (TextView) this.n.findViewById(R.id.output_text);
        this.p.setImageBitmap(a);
        this.b = (DJILPCameraRecordBottomBar) this.n.findViewById(R.id.longan_bottom_bar_layout);
        this.w.blenderInit(a, 0);
        this.c = 0;
        this.t = System.currentTimeMillis();
        this.u = 0;
        this.x = true;
        this.d = System.currentTimeMillis();
        for (int i = 0; i < 4; i++) {
            new Thread(new a(this)).start();
        }
        this.p.setVisibility(0);
        this.b.showTimeForLongEx(true);
        h();
        dji.phone.f.a.getInstance().a(dji.phone.f.d.WORK_FINE_LONG_EXPOSURE, true);
    }

    private void h() {
        Log.d(l, "updateTime: ");
        long currentTimeMillis = (System.currentTimeMillis() - this.d) / 1000;
        long n = (long) dji.pilot.phonecamera.a.c.a().n();
        Log.d(l, "updateTime: remainTime = " + n + " delta = " + currentTimeMillis);
        this.b.updateLongExTimeTv(currentTimeMillis, n, dji.pilot.phonecamera.a.c.a().n() > 0);
        if (n <= 0 && dji.pilot.phonecamera.a.c.a().n() > 0) {
            e();
        } else if (this.x) {
            this.z.sendEmptyMessageDelayed(4, 1000);
        }
    }

    public void c() {
        dji.thirdparty.a.c.a().d(this);
    }

    public boolean d() {
        return this.x;
    }

    public void onEventMainThread(dji.phone.e.b bVar) {
        if (this.x && bVar.a == e.BTN_CAMERA_SHUTTER && bVar.c == dji.phone.e.a.c.e) {
            e();
        }
    }

    private void i() {
        Log.d(l, "savePicture: ");
        if (dji.pilot.storage.a.c(this.m)) {
            new c(this, a).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        new b(this, a, h.a(System.currentTimeMillis())).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void e() {
        if (this.x) {
            Log.i(l, "Stop exposing");
            this.x = false;
            synchronized (this) {
                notifyAll();
            }
            dji.phone.f.a.getInstance().a(dji.phone.f.d.WORK_FINE_LONG_EXPOSURE, false);
        }
    }

    public void a(String str) {
        Log.d(l, "notifyNewMedia: notifyNewMedia");
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(new File(str)));
        this.m.sendBroadcast(intent);
        try {
            Class.forName("dji.pilot.support.longan.DJISupportLongan").getMethod("reflectNotifyNewMedia", new Class[]{String.class}).invoke(null, new Object[]{str});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
