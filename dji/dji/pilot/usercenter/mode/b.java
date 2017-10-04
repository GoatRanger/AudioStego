package dji.pilot.usercenter.mode;

import dji.pilot.usercenter.protocol.d;
import org.json.JSONObject;

public class b extends h {
    public static final int a = 0;
    public static final int b = 1;
    public String c;
    public int d;
    public boolean e = true;
    public long f;
    public long g;
    public String h = null;
    public String i = null;
    public String j = null;
    public String k = null;

    public static b a(JSONObject jSONObject, b bVar) {
        if (jSONObject != null) {
            if (bVar == null) {
                bVar = new b();
            }
            try {
                h.a(jSONObject, bVar);
                bVar.c = jSONObject.optString("filename", "");
                bVar.d = jSONObject.optInt(n.at, 1);
                bVar.f = jSONObject.optLong(n.au, 0);
                bVar.e = jSONObject.optBoolean("public", true);
                bVar.g = jSONObject.optLong("duration", 0);
                bVar.h = jSONObject.optString(n.N, "");
                bVar.i = jSONObject.optString(n.P, "");
                bVar.j = jSONObject.optString(n.Q, "");
                bVar.k = jSONObject.optString(n.R, "");
            } catch (Exception e) {
            }
        }
        return bVar;
    }

    public PhotoPreviewInfo a() {
        PhotoPreviewInfo photoPreviewInfo = new PhotoPreviewInfo();
        photoPreviewInfo.e = this.r;
        photoPreviewInfo.a = this.n;
        photoPreviewInfo.c = this.p;
        photoPreviewInfo.b = this.o;
        photoPreviewInfo.d = this.q;
        photoPreviewInfo.e = this.r;
        photoPreviewInfo.f = this.s;
        photoPreviewInfo.g = this.t;
        photoPreviewInfo.h = this.y;
        photoPreviewInfo.i = this.z;
        photoPreviewInfo.o = this.h;
        return photoPreviewInfo;
    }

    public VideoPreviewInfo b() {
        if (this.d != 0) {
            return null;
        }
        VideoPreviewInfo videoPreviewInfo = new VideoPreviewInfo();
        videoPreviewInfo.e = this.r;
        videoPreviewInfo.a = this.n;
        videoPreviewInfo.c = this.p;
        videoPreviewInfo.b = this.o;
        videoPreviewInfo.d = this.q;
        videoPreviewInfo.e = this.r;
        videoPreviewInfo.f = this.s;
        videoPreviewInfo.g = this.t;
        videoPreviewInfo.h = this.y;
        videoPreviewInfo.i = this.z;
        videoPreviewInfo.o = this.h;
        return videoPreviewInfo;
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof b)) {
            return equals;
        }
        b bVar = (b) obj;
        if (bVar.c != null && bVar.c.equals(this.c) && bVar.f == this.f) {
            return true;
        }
        return equals;
    }

    public int hashCode() {
        int i = 17;
        if (this.c != null) {
            i = this.c.hashCode() + 527;
        }
        return (i * 31) + ((int) (this.f ^ (this.f >>> 32)));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(48);
        stringBuilder.append("filename[").append(this.c).append(d.H);
        stringBuilder.append("filesize[").append(String.valueOf(this.f)).append(d.H);
        stringBuilder.append("ourl[").append(this.h).append(d.H);
        return super.toString();
    }
}
