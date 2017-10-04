package dji.pilot2.media;

import android.graphics.Bitmap;
import dji.midware.media.e;
import dji.pilot2.videolib.VideoLibWrapper;
import java.util.HashMap;

public class c implements g {
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
        e.e(f, "setDataSource=" + str);
    }

    public void d() {
        if (this.g == null) {
            this.g = VideoLibWrapper.nativeGetMetadata(this.h);
            e.d(f, "metaData=" + this.g);
        }
    }

    public int a() {
        d();
        if (this.g != null) {
            try {
                return Integer.parseInt((String) this.g.get("duration"));
            } catch (Exception e) {
                e.a(f, e);
            }
        }
        return 0;
    }

    public int b() {
        d();
        if (this.g == null) {
            return 0;
        }
        try {
            return Integer.parseInt((String) this.g.get("width"));
        } catch (Exception e) {
            return 0;
        }
    }

    public int c() {
        d();
        if (this.g == null) {
            return 0;
        }
        try {
            return Integer.parseInt((String) this.g.get("height"));
        } catch (Exception e) {
            return 0;
        }
    }

    public float e() {
        float parseFloat;
        d();
        if (this.g != null) {
            try {
                parseFloat = Float.parseFloat((String) this.g.get(e));
            } catch (Exception e) {
                parseFloat = 0.0f;
            }
        } else {
            parseFloat = 0.0f;
        }
        e.d(f, "rotate=" + parseFloat);
        return parseFloat;
    }

    public float f() {
        d();
        if (this.g != null) {
            try {
                return Float.parseFloat((String) this.g.get(b));
            } catch (Exception e) {
                e.a(f, e);
            }
        }
        return 0.0f;
    }

    public Bitmap a(long j, int i) {
        return b(j / 1000, i);
    }

    public Bitmap a(long j) {
        return b(j / 1000, 0);
    }

    public Bitmap[] a(long[] jArr, int i) {
        Bitmap[] bitmapArr = new Bitmap[jArr.length];
        for (int i2 = 0; i2 < bitmapArr.length; i2++) {
            bitmapArr[i2] = b(jArr[i2] / 1000, i);
        }
        return bitmapArr;
    }

    private Bitmap b(long j, int i) {
        return VideoLibWrapper.getFrameAtTime(this.h, j, i);
    }
}
