package dji.g.a;

import com.dji.frame.c.d;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.media.d.e;
import java.util.Locale;

public class a {

    public enum a {
        USER_NATIVE_MUSIC,
        USER_NATIVE_VIDEO,
        DRONE_PRODUCED,
        APP_RESOURCE,
        FOOTAGE,
        CACHE,
        NONE
    }

    public interface b {
        void a(a aVar);
    }

    public static a a(String str) {
        ServiceManager.getInstance();
        String toLowerCase = d.a(ServiceManager.getContext(), "").toLowerCase(Locale.US);
        String toLowerCase2 = str.toLowerCase(Locale.US);
        if (toLowerCase2.startsWith(toLowerCase + "videoeditor/segmentlibrary_hd") || toLowerCase2.startsWith(toLowerCase + "dji album")) {
            return a.DRONE_PRODUCED;
        }
        if (toLowerCase2.startsWith(toLowerCase + "dji_record")) {
            return a.CACHE;
        }
        if (toLowerCase2.startsWith(toLowerCase + "videoeditor/segmentlibrary")) {
            return a.FOOTAGE;
        }
        if (toLowerCase2.startsWith(toLowerCase)) {
            return a.APP_RESOURCE;
        }
        if (toLowerCase2.endsWith(".mp3") || toLowerCase2.endsWith(".m4a") || toLowerCase2.endsWith(".wav") || toLowerCase2.endsWith(".wma")) {
            return a.USER_NATIVE_MUSIC;
        }
        return a.USER_NATIVE_VIDEO;
    }

    public static dji.midware.media.d.d a(String str, a aVar) {
        switch (aVar) {
            case FOOTAGE:
            case APP_RESOURCE:
            case USER_NATIVE_MUSIC:
            case NONE:
                return new dji.midware.media.d.b();
            default:
                return new e();
        }
    }
}
