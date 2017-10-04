package dji.midware.media.g;

import android.os.Build.VERSION;

public class e {

    public enum a {
        NATIVE,
        FFMPEG
    }

    public static b a() {
        if (VERSION.SDK_INT >= 18) {
            return a(a.NATIVE);
        }
        return a(a.FFMPEG);
    }

    public static b a(a aVar) {
        b aVar2;
        switch (aVar) {
            case NATIVE:
                aVar2 = new a();
                dji.midware.media.e.a("Using Android native Mp4 muxer");
                return aVar2;
            default:
                aVar2 = new d();
                dji.midware.media.e.a("Using FFMpeg-based Mp4 muxer");
                return aVar2;
        }
    }
}
