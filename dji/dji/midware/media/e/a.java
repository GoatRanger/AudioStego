package dji.midware.media.e;

import dji.midware.media.e;
import dji.midware.natives.FPVController;
import java.util.HashMap;

public class a {
    private static final String a = "duration";
    private static final String b = "framerate";
    private static final String c = "width";
    private static final String d = "height";
    private static final String e = "rotate";
    private static final String f = "DJIFFmpegMediaRetriver";
    private HashMap<String, String> g = null;
    private String h;

    public void a(String str) {
        this.h = str;
        e.e(f, "Retrieving metadata: " + str);
    }

    public void a() {
        if (this.g == null) {
            this.g = FPVController.jni_demuxer_getMetadata(this.h);
            e.d(f, "metaData=" + this.g);
        }
    }

    public int b() {
        int parseInt;
        a();
        if (this.g != null) {
            try {
                parseInt = Integer.parseInt((String) this.g.get("duration"));
            } catch (Exception e) {
                parseInt = 0;
            }
        } else {
            parseInt = 0;
        }
        e.d(f, "duration (ms)=" + parseInt);
        return parseInt;
    }

    public int c() {
        int parseInt;
        a();
        if (this.g != null) {
            try {
                parseInt = Integer.parseInt((String) this.g.get("width"));
            } catch (Exception e) {
                parseInt = 0;
            }
        } else {
            parseInt = 0;
        }
        e.d(f, "video width=" + parseInt);
        return parseInt;
    }

    public int d() {
        int parseInt;
        a();
        if (this.g != null) {
            try {
                parseInt = Integer.parseInt((String) this.g.get("height"));
            } catch (Exception e) {
                parseInt = 0;
            }
        } else {
            parseInt = 0;
        }
        e.d(f, "video height=" + parseInt);
        return parseInt;
    }

    public float e() {
        float f = 0.0f;
        a();
        if (this.g != null) {
            if (this.g.containsKey("rotation")) {
                f = Float.parseFloat((String) this.g.get("rotation"));
            } else if (this.g.containsKey(e)) {
                f = Float.parseFloat((String) this.g.get(e));
            }
        }
        e.d(f, "rotate=" + f);
        return f;
    }

    public float f() {
        float parseFloat;
        a();
        if (this.g != null) {
            try {
                parseFloat = Float.parseFloat((String) this.g.get(b));
            } catch (Exception e) {
                e.a(f, e);
            }
            e.d(f, "frame rate=" + parseFloat);
            return parseFloat;
        }
        parseFloat = 0.0f;
        e.d(f, "frame rate=" + parseFloat);
        return parseFloat;
    }
}
