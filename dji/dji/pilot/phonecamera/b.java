package dji.pilot.phonecamera;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.AutoFocusMoveCallback;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Face;
import android.hardware.Camera.FaceDetectionListener;
import android.hardware.Camera.OnZoomChangeListener;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import java.io.IOException;

class b implements e {
    private static final int A = 463;
    private static final int B = 464;
    private static final int C = 501;
    private static final int D = 502;
    private static final String a = ("CAM_" + b.class.getSimpleName());
    private static final int e = 1;
    private static final int f = 2;
    private static final int g = 3;
    private static final int h = 4;
    private static final int i = 5;
    private static final int j = 101;
    private static final int k = 102;
    private static final int l = 103;
    private static final int m = 104;
    private static final int n = 105;
    private static final int o = 106;
    private static final int p = 107;
    private static final int q = 201;
    private static final int r = 202;
    private static final int s = 203;
    private static final int t = 204;
    private static final int u = 301;
    private static final int v = 302;
    private static final int w = 303;
    private static final int x = 304;
    private static final int y = 461;
    private static final int z = 462;
    private d E;
    private Camera F;
    private Object G = new Object();
    private Parameters H;
    private Parameters b;
    private boolean c;
    private IOException d;

    private static class a implements AutoFocusCallback {
        private final Handler a;
        private final dji.pilot.phonecamera.e.g b;
        private final dji.pilot.phonecamera.e.a c;

        public static a a(Handler handler, dji.pilot.phonecamera.e.g gVar, dji.pilot.phonecamera.e.a aVar) {
            if (handler == null || gVar == null || aVar == null) {
                return null;
            }
            return new a(handler, gVar, aVar);
        }

        private a(Handler handler, dji.pilot.phonecamera.e.g gVar, dji.pilot.phonecamera.e.a aVar) {
            this.a = handler;
            this.b = gVar;
            this.c = aVar;
        }

        public void onAutoFocus(final boolean z, Camera camera) {
            this.a.post(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    this.b.c.a(z, this.b.b);
                }
            });
        }
    }

    @TargetApi(16)
    private static class b implements AutoFocusMoveCallback {
        private final Handler a;
        private final dji.pilot.phonecamera.e.b b;
        private final dji.pilot.phonecamera.e.g c;

        public static b a(Handler handler, dji.pilot.phonecamera.e.g gVar, dji.pilot.phonecamera.e.b bVar) {
            if (handler == null || gVar == null || bVar == null) {
                return null;
            }
            return new b(handler, gVar, bVar);
        }

        private b(Handler handler, dji.pilot.phonecamera.e.g gVar, dji.pilot.phonecamera.e.b bVar) {
            this.a = handler;
            this.c = gVar;
            this.b = bVar;
        }

        public void onAutoFocusMoving(final boolean z, Camera camera) {
            this.a.post(new Runnable(this) {
                final /* synthetic */ b b;

                public void run() {
                    this.b.b.a(z, this.b.c);
                }
            });
        }
    }

    public class c implements dji.pilot.phonecamera.e.g {
        final /* synthetic */ b a;

        private c(b bVar) {
            this.a = bVar;
            b(bVar.F != null);
        }

        public Camera a() {
            return this.a.F;
        }

        public void b() {
            this.a.E.sendEmptyMessage(2);
            this.a.E.a();
        }

        public boolean a(Handler handler, dji.pilot.phonecamera.e.d dVar) {
            this.a.E.sendEmptyMessage(3);
            this.a.E.a();
            dji.pilot.phonecamera.e.d a = e.a(handler, dVar);
            if (this.a.d == null) {
                return true;
            }
            if (a != null) {
                a.a(this.a);
            }
            return false;
        }

        public void c() {
            this.a.E.sendEmptyMessage(4);
            this.a.E.a();
        }

        public void d() {
            this.a.E.sendEmptyMessage(5);
        }

        public void a(SurfaceTexture surfaceTexture) {
            this.a.E.obtainMessage(101, surfaceTexture).sendToTarget();
        }

        public void a(SurfaceHolder surfaceHolder) {
            this.a.E.obtainMessage(106, surfaceHolder).sendToTarget();
        }

        public void e() {
            this.a.E.sendEmptyMessage(102);
        }

        public void f() {
            this.a.E.sendEmptyMessage(103);
            this.a.E.a();
        }

        public void a(Handler handler, dji.pilot.phonecamera.e.f fVar) {
            this.a.E.obtainMessage(107, i.a(handler, this, fVar)).sendToTarget();
        }

        public void b(Handler handler, dji.pilot.phonecamera.e.f fVar) {
            this.a.E.obtainMessage(104, i.a(handler, this, fVar)).sendToTarget();
        }

        public void a(byte[] bArr) {
            this.a.E.obtainMessage(105, bArr).sendToTarget();
        }

        public void a(Handler handler, dji.pilot.phonecamera.e.a aVar) {
            this.a.E.obtainMessage(301, a.a(handler, this, aVar)).sendToTarget();
        }

        public void g() {
            this.a.E.removeMessages(301);
            this.a.E.sendEmptyMessage(302);
        }

        @TargetApi(16)
        public void a(Handler handler, dji.pilot.phonecamera.e.b bVar) {
            this.a.E.obtainMessage(303, b.a(handler, this, bVar)).sendToTarget();
        }

        public void a(Handler handler, dji.pilot.phonecamera.e.i iVar, dji.pilot.phonecamera.e.e eVar, dji.pilot.phonecamera.e.e eVar2, dji.pilot.phonecamera.e.e eVar3) {
            this.a.E.a(j.a(handler, this, iVar), h.a(handler, this, eVar), h.a(handler, this, eVar2), h.a(handler, this, eVar3));
        }

        public void a(int i) {
            this.a.E.obtainMessage(502, i, 0).sendToTarget();
        }

        public void a(OnZoomChangeListener onZoomChangeListener) {
            this.a.E.obtainMessage(304, onZoomChangeListener).sendToTarget();
        }

        public void a(Handler handler, dji.pilot.phonecamera.e.c cVar) {
            this.a.E.obtainMessage(b.y, g.a(handler, this, cVar)).sendToTarget();
        }

        public void h() {
            this.a.E.sendEmptyMessage(b.z);
        }

        public void i() {
            this.a.E.sendEmptyMessage(b.A);
        }

        public void a(ErrorCallback errorCallback) {
            this.a.E.obtainMessage(b.B, errorCallback).sendToTarget();
        }

        public void a(Parameters parameters, dji.pilot.phonecamera.e.h hVar) {
            if (parameters == null) {
                Log.v(b.a, "null parameters in setParameters()");
                return;
            }
            Log.d(b.a, "setParameters: " + parameters);
            if (hVar == null) {
                this.a.E.obtainMessage(201, parameters).sendToTarget();
            } else {
                this.a.E.obtainMessage(204, f.a(hVar, parameters)).sendToTarget();
            }
        }

        public void a(Parameters parameters) {
            synchronized (this.a.G) {
                try {
                    this.a.F.setParameters(parameters);
                    this.a.c = true;
                } catch (Throwable e) {
                    Log.d(b.a, "setSyncParameters: ", e);
                    this.a.c = true;
                } catch (Throwable th) {
                    this.a.c = true;
                }
            }
        }

        public Parameters j() {
            this.a.E.sendEmptyMessage(202);
            this.a.E.a();
            return this.a.b;
        }

        public void k() {
            this.a.E.sendEmptyMessage(b.s);
        }

        public void a(boolean z) {
            int i;
            d b = this.a.E;
            if (z) {
                i = 1;
            } else {
                i = 0;
            }
            b.obtainMessage(501, i, 0).sendToTarget();
        }

        public void b(boolean z) {
            if (!z) {
                throw new AssertionError();
            }
        }
    }

    private class d extends Handler {
        final /* synthetic */ b a;

        d(b bVar, Looper looper) {
            this.a = bVar;
            super(looper);
        }

        private void b() {
            this.a.F.startFaceDetection();
        }

        private void c() {
            this.a.F.stopFaceDetection();
        }

        private void a(FaceDetectionListener faceDetectionListener) {
            this.a.F.setFaceDetectionListener(faceDetectionListener);
        }

        private void a(Object obj) {
            try {
                this.a.F.setPreviewTexture((SurfaceTexture) obj);
            } catch (Throwable e) {
                Log.e(b.a, "Could not set preview texture", e);
            }
        }

        @TargetApi(17)
        private void a(boolean z) {
            this.a.F.enableShutterSound(z);
        }

        @TargetApi(16)
        private void a(Camera camera, Object obj) {
            camera.setAutoFocusMoveCallback((AutoFocusMoveCallback) obj);
        }

        public void a(ShutterCallback shutterCallback, PictureCallback pictureCallback, PictureCallback pictureCallback2, PictureCallback pictureCallback3) {
            final ShutterCallback shutterCallback2 = shutterCallback;
            final PictureCallback pictureCallback4 = pictureCallback;
            final PictureCallback pictureCallback5 = pictureCallback2;
            final PictureCallback pictureCallback6 = pictureCallback3;
            post(new Runnable(this) {
                final /* synthetic */ d e;

                public void run() {
                    try {
                        this.e.a.F.takePicture(shutterCallback2, pictureCallback4, pictureCallback5, pictureCallback6);
                    } catch (RuntimeException e) {
                        Log.e(b.a, "take picture failed.");
                    }
                }
            });
        }

        public boolean a() {
            final Object obj = new Object();
            Runnable anonymousClass2 = new Runnable(this) {
                final /* synthetic */ d b;

                public void run() {
                    synchronized (obj) {
                        obj.notifyAll();
                    }
                }
            };
            synchronized (obj) {
                this.a.E.post(anonymousClass2);
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    Log.v(b.a, "waitDone interrupted");
                    return false;
                }
            }
            return true;
        }

        public void handleMessage(Message message) {
            boolean z = false;
            try {
                switch (message.what) {
                    case 1:
                        this.a.F = Camera.open(message.arg1);
                        if (this.a.F != null) {
                            this.a.c = true;
                            if (this.a.H == null) {
                                this.a.H = this.a.F.getParameters();
                                return;
                            }
                            return;
                        } else if (message.obj != null) {
                            ((dji.pilot.phonecamera.e.d) message.obj).b(message.arg1);
                            return;
                        } else {
                            return;
                        }
                    case 2:
                        Log.d(b.a, "handleMessage: release");
                        this.a.F.release();
                        this.a.F = null;
                        return;
                    case 3:
                        this.a.d = null;
                        try {
                            this.a.F.reconnect();
                            return;
                        } catch (IOException e) {
                            this.a.d = e;
                            return;
                        }
                    case 4:
                        this.a.F.unlock();
                        return;
                    case 5:
                        this.a.F.lock();
                        return;
                    case 101:
                        a(message.obj);
                        return;
                    case 102:
                        this.a.F.startPreview();
                        return;
                    case 103:
                        this.a.F.stopPreview();
                        return;
                    case 104:
                        this.a.F.setPreviewCallbackWithBuffer((PreviewCallback) message.obj);
                        return;
                    case 105:
                        this.a.F.addCallbackBuffer((byte[]) message.obj);
                        return;
                    case 106:
                        this.a.F.setPreviewDisplay((SurfaceHolder) message.obj);
                        return;
                    case 107:
                        this.a.F.setPreviewCallback((PreviewCallback) message.obj);
                        return;
                    case 201:
                        this.a.H = (Parameters) message.obj;
                        Log.d(b.a, "handleMessage: mParamsToSet");
                        synchronized (this.a.G) {
                            try {
                                this.a.F.setParameters(this.a.H);
                            } catch (RuntimeException e2) {
                                Log.d(b.a, "RuntimeException " + e2);
                            } finally {
                                this.a.c = true;
                            }
                        }
                        return;
                    case 202:
                        if (this.a.c) {
                            try {
                                this.a.b = this.a.F.getParameters();
                                this.a.c = false;
                                return;
                            } catch (RuntimeException e22) {
                                Log.d(b.a, "handleMessage: " + e22);
                                return;
                            }
                        }
                        return;
                    case b.s /*203*/:
                        this.a.c = true;
                        return;
                    case 204:
                        f fVar = (f) message.obj;
                        Log.d(b.a, "handleMessage: SET_PARAMETERS_CALLBACK");
                        this.a.H = fVar.b;
                        synchronized (this.a.G) {
                            try {
                                this.a.F.setParameters(this.a.H);
                            } catch (RuntimeException e3) {
                                Log.d(b.a, "handleMessage: " + e3);
                                this.a.c = true;
                                fVar.a(this.a.F.getParameters());
                                return;
                            }
                        }
                        this.a.c = true;
                        fVar.b(this.a.F.getParameters());
                        return;
                    case 301:
                        Log.d(b.a, "handleMessage: AUTO_FOCUS");
                        try {
                            this.a.F.autoFocus((AutoFocusCallback) message.obj);
                            return;
                        } catch (RuntimeException e222) {
                            Log.d(b.a, "handleMessage: " + e222);
                            ((AutoFocusCallback) message.obj).onAutoFocus(false, this.a.F);
                            return;
                        }
                    case 302:
                        this.a.F.cancelAutoFocus();
                        return;
                    case 303:
                        a(this.a.F, message.obj);
                        return;
                    case 304:
                        this.a.F.setZoomChangeListener((OnZoomChangeListener) message.obj);
                        return;
                    case b.y /*461*/:
                        a((FaceDetectionListener) message.obj);
                        return;
                    case b.z /*462*/:
                        b();
                        return;
                    case b.A /*463*/:
                        c();
                        return;
                    case b.B /*464*/:
                        this.a.F.setErrorCallback((ErrorCallback) message.obj);
                        return;
                    case 501:
                        if (message.arg1 == 1) {
                            z = true;
                        }
                        a(z);
                        return;
                    case 502:
                        Log.e(b.a, "handleMessage: SET_DISPLAY_ORIENTATION");
                        try {
                            this.a.F.setDisplayOrientation(message.arg1);
                            return;
                        } catch (RuntimeException e2222) {
                            e2222.printStackTrace();
                            return;
                        }
                    default:
                        throw new RuntimeException("Invalid CameraProxy message=" + message.what);
                }
            } catch (Throwable e4) {
                throw new RuntimeException(e4);
            } catch (RuntimeException e22222) {
                if (message.what == 2) {
                }
                if (this.a.F == null) {
                    if (message.what == 1) {
                        Log.w(b.a, "Cannot handle message, mCamera is null.");
                        return;
                    } else if (message.obj != null) {
                        ((dji.pilot.phonecamera.e.d) message.obj).b(message.arg1);
                        return;
                    } else {
                        return;
                    }
                }
                throw e22222;
            }
            if (message.what == 2 && this.a.F != null) {
                try {
                    this.a.F.release();
                } catch (Exception e5) {
                    Log.e(b.a, "Fail to release the camera.");
                }
                this.a.F = null;
            } else if (this.a.F == null) {
                if (message.what == 1) {
                    Log.w(b.a, "Cannot handle message, mCamera is null.");
                    return;
                } else if (message.obj != null) {
                    ((dji.pilot.phonecamera.e.d) message.obj).b(message.arg1);
                    return;
                } else {
                    return;
                }
            }
            throw e22222;
        }
    }

    private static class e implements dji.pilot.phonecamera.e.d {
        private final Handler a = new Handler(Looper.getMainLooper());
        private final dji.pilot.phonecamera.e.d b;

        public static e a(Handler handler, dji.pilot.phonecamera.e.d dVar) {
            if (handler == null || dVar == null) {
                return null;
            }
            return new e(handler, dVar);
        }

        private e(Handler handler, dji.pilot.phonecamera.e.d dVar) {
            this.b = dVar;
        }

        public void a(final int i) {
            this.a.post(new Runnable(this) {
                final /* synthetic */ e b;

                public void run() {
                    this.b.b.a(i);
                }
            });
        }

        public void b(final int i) {
            this.a.post(new Runnable(this) {
                final /* synthetic */ e b;

                public void run() {
                    this.b.b.b(i);
                }
            });
        }

        public void a(final e eVar) {
            this.a.post(new Runnable(this) {
                final /* synthetic */ e b;

                public void run() {
                    this.b.b.a(eVar);
                }
            });
        }
    }

    private static class f implements dji.pilot.phonecamera.e.h {
        public String a;
        public Parameters b;
        private final Handler c = new Handler(Looper.getMainLooper());
        private final dji.pilot.phonecamera.e.h d;

        public static f a(dji.pilot.phonecamera.e.h hVar, String str) {
            if (hVar == null || str == null) {
                return null;
            }
            return new f(hVar, str);
        }

        public static f a(dji.pilot.phonecamera.e.h hVar, Parameters parameters) {
            if (hVar == null || parameters == null) {
                return null;
            }
            return new f(hVar, parameters);
        }

        private f(dji.pilot.phonecamera.e.h hVar, String str) {
            this.d = hVar;
            this.a = str;
        }

        private f(dji.pilot.phonecamera.e.h hVar, Parameters parameters) {
            this.d = hVar;
            this.b = parameters;
        }

        public void a(final Parameters parameters) {
            this.c.post(new Runnable(this) {
                final /* synthetic */ f b;

                public void run() {
                    this.b.d.a(parameters);
                }
            });
        }

        public void b(final Parameters parameters) {
            this.c.post(new Runnable(this) {
                final /* synthetic */ f b;

                public void run() {
                    this.b.d.b(parameters);
                }
            });
        }
    }

    private static class g implements FaceDetectionListener {
        private final Handler a;
        private final dji.pilot.phonecamera.e.c b;
        private final dji.pilot.phonecamera.e.g c;

        public static g a(Handler handler, dji.pilot.phonecamera.e.g gVar, dji.pilot.phonecamera.e.c cVar) {
            if (handler == null || gVar == null || cVar == null) {
                return null;
            }
            return new g(handler, gVar, cVar);
        }

        private g(Handler handler, dji.pilot.phonecamera.e.g gVar, dji.pilot.phonecamera.e.c cVar) {
            this.a = handler;
            this.c = gVar;
            this.b = cVar;
        }

        public void onFaceDetection(final Face[] faceArr, Camera camera) {
            this.a.post(new Runnable(this) {
                final /* synthetic */ g b;

                public void run() {
                    this.b.b.a(faceArr, this.b.c);
                }
            });
        }
    }

    private static class h implements PictureCallback {
        private final Handler a;
        private final dji.pilot.phonecamera.e.e b;
        private final dji.pilot.phonecamera.e.g c;

        public static h a(Handler handler, dji.pilot.phonecamera.e.g gVar, dji.pilot.phonecamera.e.e eVar) {
            if (handler == null || gVar == null || eVar == null) {
                return null;
            }
            return new h(handler, gVar, eVar);
        }

        private h(Handler handler, dji.pilot.phonecamera.e.g gVar, dji.pilot.phonecamera.e.e eVar) {
            this.a = handler;
            this.c = gVar;
            this.b = eVar;
        }

        public void onPictureTaken(final byte[] bArr, Camera camera) {
            this.a.post(new Runnable(this) {
                final /* synthetic */ h b;

                public void run() {
                    this.b.b.a(bArr, this.b.c);
                }
            });
        }
    }

    private static class i implements PreviewCallback {
        private final Handler a;
        private final dji.pilot.phonecamera.e.f b;
        private final dji.pilot.phonecamera.e.g c;

        public static i a(Handler handler, dji.pilot.phonecamera.e.g gVar, dji.pilot.phonecamera.e.f fVar) {
            if (handler == null || gVar == null || fVar == null) {
                return null;
            }
            return new i(handler, gVar, fVar);
        }

        private i(Handler handler, dji.pilot.phonecamera.e.g gVar, dji.pilot.phonecamera.e.f fVar) {
            this.a = handler;
            this.c = gVar;
            this.b = fVar;
        }

        public void onPreviewFrame(final byte[] bArr, Camera camera) {
            this.a.post(new Runnable(this) {
                final /* synthetic */ i b;

                public void run() {
                    this.b.b.a(bArr, this.b.c);
                }
            });
        }
    }

    private static class j implements ShutterCallback {
        private final Handler a;
        private final dji.pilot.phonecamera.e.i b;
        private final dji.pilot.phonecamera.e.g c;

        public static j a(Handler handler, dji.pilot.phonecamera.e.g gVar, dji.pilot.phonecamera.e.i iVar) {
            if (handler == null || gVar == null || iVar == null) {
                return null;
            }
            return new j(handler, gVar, iVar);
        }

        private j(Handler handler, dji.pilot.phonecamera.e.g gVar, dji.pilot.phonecamera.e.i iVar) {
            this.a = handler;
            this.c = gVar;
            this.b = iVar;
        }

        public void onShutter() {
            this.a.post(new Runnable(this) {
                final /* synthetic */ j a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.b.a(this.a.c);
                }
            });
        }
    }

    b() {
        HandlerThread handlerThread = new HandlerThread("Camera Handler Thread");
        handlerThread.start();
        this.E = new d(this, handlerThread.getLooper());
    }

    public dji.pilot.phonecamera.e.g a(Handler handler, int i, dji.pilot.phonecamera.e.d dVar) {
        this.E.obtainMessage(1, i, 0, e.a(handler, dVar)).sendToTarget();
        this.E.a();
        if (this.F != null) {
            return new c();
        }
        return null;
    }

    private synchronized String a(Parameters parameters) {
        return parameters.flatten();
    }

    private synchronized Parameters a(Parameters parameters, String str) {
        parameters.unflatten(str);
        return parameters;
    }
}
