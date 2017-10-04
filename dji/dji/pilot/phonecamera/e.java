package dji.pilot.phonecamera;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Face;
import android.hardware.Camera.OnZoomChangeListener;
import android.hardware.Camera.Parameters;
import android.os.Handler;
import android.view.SurfaceHolder;

public interface e {

    public interface g {
        Camera a();

        void a(int i);

        void a(SurfaceTexture surfaceTexture);

        void a(ErrorCallback errorCallback);

        void a(OnZoomChangeListener onZoomChangeListener);

        void a(Parameters parameters);

        void a(Parameters parameters, h hVar);

        void a(Handler handler, a aVar);

        @TargetApi(16)
        void a(Handler handler, b bVar);

        void a(Handler handler, c cVar);

        void a(Handler handler, f fVar);

        void a(Handler handler, i iVar, e eVar, e eVar2, e eVar3);

        void a(SurfaceHolder surfaceHolder);

        void a(boolean z);

        void a(byte[] bArr);

        boolean a(Handler handler, d dVar);

        void b();

        void b(Handler handler, f fVar);

        void c();

        void d();

        void e();

        void f();

        void g();

        void h();

        void i();

        Parameters j();

        void k();
    }

    public interface d {
        void a(int i);

        void a(e eVar);

        void b(int i);
    }

    public interface h {
        void a(Parameters parameters);

        void b(Parameters parameters);
    }

    public interface a {
        void a(boolean z, g gVar);
    }

    public interface b {
        void a(boolean z, g gVar);
    }

    public interface c {
        void a(Face[] faceArr, g gVar);
    }

    public interface e {
        void a(byte[] bArr, g gVar);
    }

    public interface f {
        void a(byte[] bArr, g gVar);
    }

    public interface i {
        void a(g gVar);
    }

    g a(Handler handler, int i, d dVar);
}
