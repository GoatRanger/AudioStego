package dji.pilot.usercenter.mode;

import android.media.MediaMetadataRetriever;
import com.dji.frame.c.l;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import dji.midware.media.e;
import dji.pilot.usercenter.f.c;
import dji.pilot.usercenter.f.d;
import dji.pilot.usercenter.f.d.a;
import dji.pilot2.media.f;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;

public class g {
    private static final String A = "yyyy-MM-dd HH:mm:ss";
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public boolean d = false;
    public String e;
    public String f;
    public int g;
    public long h;
    public long i;
    public boolean j = false;
    public String k;
    public int l = 0;
    public String m = null;
    public int n = 0;
    public boolean o = false;
    public boolean p = false;
    public int q = 0;
    public String r = null;
    public String s = null;
    public int t = 0;
    public int u = 0;
    public int v = 0;
    public int w;
    public String x;
    public boolean y = false;
    public boolean z = false;

    public static g a(File file, boolean z) {
        if (file == null) {
            return null;
        }
        String absolutePath = file.getAbsolutePath();
        g gVar;
        if (file.isDirectory()) {
            gVar = new g();
            gVar.e = absolutePath;
            gVar.f = file.getName();
            gVar.k = gVar.f;
            gVar.h = file.lastModified();
            gVar.d = true;
            gVar.i = file.length();
            return gVar;
        } else if (!file.isFile()) {
            return null;
        } else {
            a b = d.b(absolutePath);
            if (b == null) {
                return null;
            }
            int i = b.a;
            if (d.c(i)) {
                gVar = new g();
                gVar.e = absolutePath;
                gVar.f = file.getName();
                gVar.g = i;
                gVar.h = file.lastModified();
                gVar.k = d.d(absolutePath);
                gVar.d = false;
                gVar.i = file.length();
                if (!z) {
                    return gVar;
                }
                gVar.m = com.dji.frame.c.a.a(c.g(absolutePath));
                return gVar;
            } else if (!d.b(i)) {
                return null;
            } else {
                gVar = new g();
                gVar.e = absolutePath;
                gVar.f = file.getName();
                gVar.g = i;
                gVar.h = file.lastModified();
                gVar.k = d.d(absolutePath);
                gVar.d = false;
                gVar.i = file.length();
                if (d.a(absolutePath)) {
                    gVar.z = true;
                }
                try {
                    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                    mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
                    gVar.n = new BigDecimal(((double) ((float) Long.parseLong(mediaMetadataRetriever.extractMetadata(9)))) / 1000.0d).setScale(0, 4).intValue();
                    mediaMetadataRetriever.release();
                    return gVar;
                } catch (Exception e) {
                    return gVar;
                }
            }
        }
    }

    public static g b(File file, boolean z) {
        g gVar;
        if (file == null) {
            return null;
        }
        String absolutePath = file.getAbsolutePath();
        if (file.isDirectory()) {
            gVar = new g();
            gVar.e = absolutePath;
            gVar.f = file.getName();
            gVar.k = gVar.f;
            gVar.h = file.lastModified();
            gVar.d = true;
            gVar.i = file.length();
            return gVar;
        } else if (!file.isFile()) {
            return null;
        } else {
            a b = d.b(absolutePath);
            if (b == null) {
                return null;
            }
            int i = b.a;
            if (d.c(i)) {
                gVar = new g();
                gVar.e = absolutePath;
                gVar.f = file.getName();
                gVar.g = i;
                gVar.h = file.lastModified();
                gVar.k = d.d(absolutePath);
                gVar.d = false;
                gVar.i = file.length();
                gVar.o = true;
                if (!z) {
                    return gVar;
                }
                gVar.m = com.dji.frame.c.a.a(c.g(absolutePath));
                return gVar;
            } else if (!d.b(i)) {
                return null;
            } else {
                gVar = new g();
                gVar.e = absolutePath;
                gVar.f = file.getName();
                gVar.g = i;
                gVar.h = file.lastModified();
                gVar.k = d.d(absolutePath);
                gVar.d = false;
                gVar.i = file.length();
                try {
                    dji.pilot2.media.g a = f.a(gVar.e);
                    gVar.v = a.b();
                    gVar.u = a.c();
                    gVar.n = new BigDecimal(((double) ((float) a.a())) / 1000.0d).setScale(0, 4).intValue();
                } catch (Exception e) {
                    gVar.v = 5000;
                    gVar.u = 5000;
                    gVar.n = 0;
                }
                e.b("LocalAlbum", "path=" + gVar.e + "　width=" + gVar.v + " height=" + gVar.u + " duration=" + gVar.n);
                return gVar;
            }
        }
    }

    public PhotoPreviewInfo a() {
        if (!d.c(this.g)) {
            return null;
        }
        PhotoPreviewInfo photoPreviewInfo = new PhotoPreviewInfo();
        photoPreviewInfo.o = Scheme.FILE.wrap(this.e);
        photoPreviewInfo.e = this.k;
        photoPreviewInfo.h = l.a(new Date(this.h), A);
        return photoPreviewInfo;
    }

    public VideoPreviewInfo b() {
        if (!d.b(this.g)) {
            return null;
        }
        VideoPreviewInfo videoPreviewInfo = new VideoPreviewInfo();
        videoPreviewInfo.o = Scheme.FILE.wrap(this.e);
        videoPreviewInfo.e = this.k;
        videoPreviewInfo.h = l.a(new Date(this.h), A);
        return videoPreviewInfo;
    }

    public String c() {
        return Scheme.FILE.wrap(this.e);
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof g)) {
            return equals;
        }
        g gVar = (g) obj;
        if (gVar.e == null || !gVar.e.equals(this.e)) {
            return equals;
        }
        return true;
    }

    public int hashCode() {
        if (this.t == 0) {
            int i = 17;
            if (this.e != null) {
                i = this.e.hashCode() + 527;
            }
            this.t = i;
        }
        return this.t;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(48);
        stringBuilder.append("absPath[").append(this.e).append(dji.pilot.usercenter.protocol.d.H);
        stringBuilder.append("type[").append(String.valueOf(this.g)).append(dji.pilot.usercenter.protocol.d.H);
        return stringBuilder.toString();
    }
}
