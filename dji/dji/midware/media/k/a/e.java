package dji.midware.media.k.a;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.util.Log;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.media.DJIVideoDataRecver;
import dji.midware.media.b.b;
import dji.midware.media.d;
import dji.midware.media.k.a.c.a;
import dji.midware.natives.FPVController;
import dji.pilot.fpv.control.f;
import java.io.File;

@TargetApi(18)
public class e extends f implements h, Runnable {
    public static String b = "TranscoderAndroid";
    public d a = null;
    private a l = null;
    private c m = null;

    protected void f() {
        try {
            s();
            Log.i(j.a, "stop stage 5: " + System.currentTimeMillis());
            n();
            Log.i(j.a, "stop stage 6: " + System.currentTimeMillis());
            Log.i(b, "Transcoder has been successfully stopped by the user");
        } catch (Exception e) {
            dji.midware.media.e.c(b, e);
        }
    }

    protected void g() {
        new Thread(this).start();
    }

    private void n() {
        File file = new File(this.e + f.b);
        if (!file.exists()) {
            return;
        }
        if (Boolean.valueOf(file.delete()).booleanValue()) {
            Log.i(b, "Temp output File has been deleted");
        } else {
            Log.e(b, "Temp output File not deleted");
        }
    }

    private boolean o() {
        boolean z = false;
        File file = new File(this.e + f.b);
        File file2 = new File(this.e);
        if (file2.exists()) {
            Log.e(b, this.e + " has already existed");
        } else {
            int i = 0;
            while (i < 5 && !r0) {
                boolean renameTo = file.renameTo(file2);
                if (!renameTo) {
                    try {
                        Log.e(b, "waiting 1 more second and try renaming file at the " + i + " time");
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }
                i++;
                z = renameTo;
            }
        }
        if (z) {
            dji.midware.media.e.a("rename file success");
        } else {
            dji.midware.media.e.a("rename file fails");
        }
        return z;
    }

    private boolean p() {
        Exception exception;
        if (new File(this.d).exists()) {
            File file = new File(this.e);
            if (!file.exists() || file.delete()) {
                file = new File(this.e + f.b);
                if (!file.exists() || file.delete()) {
                    return true;
                }
                exception = new Exception("the temp file exists and it can't be deleted. ");
                synchronized (this.g) {
                    if (this.h != null) {
                        this.h.a(exception);
                    }
                }
                dji.midware.media.e.a(exception);
                return false;
            }
            exception = new Exception("the output file exists and it can't be deleted.");
            synchronized (this.g) {
                if (this.h != null) {
                    this.h.a(exception);
                }
            }
            dji.midware.media.e.a(exception);
            return false;
        }
        exception = new Exception("input file " + this.d + "not exist");
        synchronized (this.g) {
            if (this.h != null) {
                this.h.a(exception);
            }
        }
        dji.midware.media.e.a(exception);
        return false;
    }

    public void run() {
        try {
            Log.i(b, "starting");
            r();
            if (p()) {
                synchronized (this.g) {
                    if (this.h != null) {
                        this.h.b();
                    }
                }
                q();
                this.m.a();
                synchronized (this.j) {
                    if (this.i == a.TRANSCODING) {
                        dji.midware.media.e.a(b, "status=" + this.i + " event=COMPLETION");
                        t();
                        Log.i(b, "Offline Transcoder has been successfully ended due to completion");
                        this.i = a.STANDBY;
                    }
                }
            }
        } catch (Exception e) {
            dji.midware.media.e.a(e);
            synchronized (this.g) {
                if (this.h != null) {
                    this.h.a(e);
                }
            }
        }
    }

    private void q() {
        MediaCodec createEncoderByType;
        int a;
        MediaCodec mediaCodec = null;
        try {
            createEncoderByType = MediaCodec.createEncoderByType(d.c[0]);
        } catch (Exception e) {
            e.printStackTrace();
            createEncoderByType = mediaCodec;
        }
        try {
            mediaCodec = MediaCodec.createDecoderByType(d.c[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        int a2 = d.a(createEncoderByType, mediaCodec);
        if (a2 == -1) {
            a = d.a(mediaCodec);
        } else {
            a = a2;
        }
        if (a2 == -1) {
            a2 = d.a(createEncoderByType);
        }
        this.l = new a();
        this.l.a(this.e + f.b);
        this.l.a(120);
        this.l.a(createEncoderByType, a2, this.f.k(), this.f.l());
        if (a2 != a) {
            this.l.a(b.a(a, a2));
        }
        this.a = new d();
        this.a.a(this.l);
        this.a.a(mediaCodec, a, this.f.k(), this.f.l());
        DJIVideoDataRecver.getInstance().setH264FrameListener(true, this.a);
        this.m = new c();
        this.m.a(new a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(int i) {
                this.a.k = i;
                Log.i(e.b, "progress: " + i);
                synchronized (this.a.g) {
                    if (this.a.h != null) {
                        this.a.h.a(i);
                    }
                }
            }
        });
        this.m.a(this.d);
    }

    private void r() {
        ServiceManager.getInstance().pauseRecvThread();
        FPVController.native_clear();
        while (FPVController.native_getQueueSize() != 0) {
            try {
                Log.i(b, "waiting for stopping video stream from the drone");
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void s() {
        Log.i(j.a, "stop stage 1: " + System.currentTimeMillis());
        if (this.m != null) {
            this.m.d();
            this.m = null;
        }
        Log.i(b, "H264 File loader has stopped");
        Log.i(j.a, "stop stage 2: " + System.currentTimeMillis());
        DJIVideoDataRecver.getInstance().setH264FrameListener(true, null);
        Log.i(b, "parser has stopped");
        ServiceManager.getInstance().resumeRecvThread();
        Log.i(b, "has asked ServiceManager to resume recv thread");
        Log.i(j.a, "stop stage 3: " + System.currentTimeMillis());
        if (this.a != null) {
            this.a.a();
            this.a.b();
            this.a = null;
        }
        Log.i(b, "offlineDecoderhas stopped");
        Log.i(j.a, "stop stage 4: " + System.currentTimeMillis());
        if (this.l != null) {
            this.l.a();
            this.l.b();
            this.l = null;
        }
        Log.i(b, "encoderMuxer has stopped");
        k();
        Log.i(b, "Video Info is set to be null");
    }

    private void t() {
        while (FPVController.native_getQueueSize() != 0) {
            Log.i(b, "the queue size of FPVController is " + FPVController.native_getQueueSize());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Log.e(b, "sleeping thread is interrupted when waiting for the FPVController to flush its queue");
            }
        }
        i();
        s();
        synchronized (this.g) {
            if (this.h != null) {
                this.h.a(100);
            }
        }
        if (o()) {
            l();
            m();
            j();
        } else {
            dji.midware.media.e.a("change file name failure");
        }
        synchronized (this.g) {
            if (this.h != null) {
                this.h.a();
                dji.midware.media.e.a("Background transcode", "transcoding completion when UI is active");
            } else {
                dji.midware.media.e.a("Background transcode", "transcoding completion when UI is Inactive");
            }
        }
    }

    String h() {
        return b;
    }
}
