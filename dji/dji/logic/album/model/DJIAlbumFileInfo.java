package dji.logic.album.model;

import android.text.format.DateFormat;
import com.dji.frame.c.a;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.logic.album.a.b.d;
import dji.logic.album.a.b.f;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DJIAlbumFileInfo implements Serializable {
    public long a;
    public long b;
    public long c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public d i;
    public f j;
    public int k;
    public String l;
    public boolean m;
    public long n;
    public TYPE o;
    public int p;
    public boolean q;

    public enum EXT_TYPE {
        VideoGUID(1),
        PhotoGroupInfo(2),
        FileTag(3),
        OTHER(0);
        
        private int e;

        private EXT_TYPE(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public static EXT_TYPE find(int i) {
            EXT_TYPE ext_type = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return ext_type;
        }
    }

    private String j() {
        return "." + this.j.toString().toLowerCase(Locale.ENGLISH);
    }

    public String a() {
        return a.b(this.d + "_" + this.c + "_" + this.a);
    }

    public String b() {
        if (this.j == f.TIF) {
            return "org_" + a.b(this.d + "_" + this.c + "_" + this.a) + "_" + this.b + dji.pilot2.main.a.a.n;
        }
        return "org_" + a.b(this.d + "_" + this.c + "_" + this.a) + "_" + this.b + j();
    }

    public String c() {
        return "thumb_" + a.b(this.d + "_" + this.c + "_" + this.a) + "_" + this.b + dji.pilot2.main.a.a.n;
    }

    public String d() {
        if ((this.j == f.MP4 || this.j == f.MOV) && DataCameraGetPushStateInfo.getInstance().getCameraType() != CameraType.DJICameraTypeFC6310) {
            return "screen_" + a.b(this.d + "_" + this.c + "_" + this.a) + ".h264";
        }
        return "screen_" + a.b(this.d + "_" + this.c + "_" + this.a) + "_" + this.b + dji.pilot2.main.a.a.n;
    }

    public String e() {
        return "pano_" + a.b(this.e + "_" + this.c + "_" + this.a) + "_" + this.b + dji.pilot2.main.a.a.n;
    }

    public String f() {
        return "pano_" + a.b(this.e + "_" + this.c + "_" + this.a) + "_" + this.b + j();
    }

    public String g() {
        return "panothumb_" + a.b(this.e + "_" + this.c + "_" + this.a) + "_" + this.b + dji.pilot2.main.a.a.n;
    }

    public String h() {
        return "screen_" + a.b(this.d + "_" + this.c + "_" + this.f) + "_" + this.f + ".h264";
    }

    public String i() {
        return "screen_" + a.b(this.d + "_" + this.c + "_" + this.f) + "_" + this.f + ".mp4";
    }

    public String toString() {
        return this.d + j() + "(len=" + this.a + "b " + k() + " pLen=" + this.k + ")";
    }

    private String k() {
        return DateFormat.format("yyyy-MM-dd kk:mm:ss", this.b).toString();
    }

    public void a(long j) {
        this.c = j;
        int i = (int) ((j >> 21) & 15);
        int i2 = (int) ((j >> 16) & 31);
        int i3 = (int) ((j >> 11) & 31);
        int i4 = (int) ((j >> 5) & 63);
        int i5 = (int) (j & 31);
        try {
            this.b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(((int) (1980 + (j >> 25))) + "-" + i + "-" + i2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i3 + ":" + i4 + ":" + i5).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static DJIAlbumFileInfo a(DJIAlbumFileInfo dJIAlbumFileInfo) {
        DJIAlbumFileInfo dJIAlbumFileInfo2 = new DJIAlbumFileInfo();
        dJIAlbumFileInfo2.a = dJIAlbumFileInfo.a;
        dJIAlbumFileInfo2.b = dJIAlbumFileInfo.b;
        dJIAlbumFileInfo2.c = dJIAlbumFileInfo.c;
        dJIAlbumFileInfo2.j = dJIAlbumFileInfo.j;
        dJIAlbumFileInfo2.d = dJIAlbumFileInfo.d;
        dJIAlbumFileInfo2.k = dJIAlbumFileInfo.k;
        dJIAlbumFileInfo2.l = dJIAlbumFileInfo.l;
        return dJIAlbumFileInfo2;
    }
}
